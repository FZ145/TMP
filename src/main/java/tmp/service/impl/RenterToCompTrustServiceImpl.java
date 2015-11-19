package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.bo.HistoryAndWeight;
import tmp.dao.ProviderTrustValueMapper;
import tmp.dao.RenterHistoryMapper;
import tmp.dao.RenterTrustValueMapper;
import tmp.entity.Component;
import tmp.entity.ProviderTrustValue;
import tmp.entity.Renter;
import tmp.entity.RenterHistory;
import tmp.entity.RenterTrustValue;
import tmp.service.RenterToCompTrustService;
import tmp.staticValue.staticValue;
import tmp.util.ListUtil;
import tmp.util.Weight;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计算租户与组件的信任，将综合信任评估结果存入数据库rentertrustvalue中
 * Created by shining.cui on 2015/11/6.
 * 测试基本完成，
 */
@Service("renterToCompTrustService")
public class RenterToCompTrustServiceImpl implements RenterToCompTrustService {
    @Resource
    private RenterHistoryMapper renterHistoryMapper;
    @Resource
    private ProviderTrustValueMapper providerTrustValueMapper;
    @Resource
    private RenterTrustValueMapper renterTrustValueMapper;

    @Override
    public BigDecimal calcRenterToCompTrust(Renter renter, Component component) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal overallTrust;
        // 查询租户与组件的评估历史次数
        List<RenterTrustValue> renterTrustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(renterUid,
                componentUid, 2);
        int directTimes = renterTrustValues.size();
        // 查询其他租户与该组件的评估历史次数
        List<RenterTrustValue> otherTrustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(null,
                componentUid, 2);
        int totalTimes = otherTrustValues.size();
        // 计算直接信任与间接信任
        BigDecimal directTrust = calcRenterToCompDirectTrust(renter, component);
        BigDecimal indirectTrust = calcRenterToCompIndirectTrust(renter, component);
        // 根据交互次数分配直接信任与间接信任的权重
        if (directTimes >= staticValue.activeTimesThreshold) {
            overallTrust = directTrust;
        } else if (totalTimes - directTimes == 0) {
            overallTrust = directTrust;
        } else if (directTimes == 0) {
            overallTrust = indirectTrust;
        } else {
            BigDecimal weight = Weight.calcDirectTrustWeight(directTimes, totalTimes);
            overallTrust = weight.multiply(directTrust).add(BigDecimal.ONE.subtract(weight).multiply(indirectTrust));
        }
        // 将全局信任评估结果保存到数据库
        RenterTrustValue renterTrustValue = new RenterTrustValue();
        renterTrustValue.setCreateTime(new Date());
        renterTrustValue.setTrustValue(overallTrust);
        renterTrustValue.setTrusteeUid(componentUid);
        renterTrustValue.setTrustorUid(renterUid);
        // uid为唯一键，作为唯一流水号
        renterTrustValue.setUid("" + new Date().getTime() + renterUid);
        renterTrustValue.setActionType(2);
        renterTrustValueMapper.insert(renterTrustValue);
        return overallTrust;
    }

    private BigDecimal calcRenterToCompDirectTrust(Renter renter, Component component) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal directTrust;
        // 获取双方实体所有交互历史
        List<RenterHistory> renterHistories = renterHistoryMapper.selectByTrustorAndTrusteeUid(renterUid, componentUid,
                null);
        if (renterHistories.size() == 0) {
            return BigDecimal.ZERO;
        }
        // 获取双方实体可用交互历史
        List<HistoryAndWeight<RenterHistory>> histories = ListUtil.getAvailableRenterHistory(renterHistories,
                staticValue.daysThreshold);
        if (histories.size() == 0) {
            return BigDecimal.ZERO;
        }
        int times = histories.size();
        BigDecimal sum = BigDecimal.ZERO;
        // 计算每次交互历史的信任值与权重，求累成和
        for (HistoryAndWeight<RenterHistory> historyAndWeight : histories) {
            // 获得历史行为信任、时间衰减值
            BigDecimal trustValue = historyAndWeight.getHistory().getTrustValue();
            BigDecimal weight = historyAndWeight.getWeight();
            sum = sum.add(trustValue.multiply(weight));
        }
        // 获得组件所属云的最近一次信誉值,如果该云没有信誉值，则默认为0.5
        BigDecimal providerTrust;
        ProviderTrustValue providerTrustValue = providerTrustValueMapper
                .queryLatestByProviderUid(component.getParentUid());
        if (providerTrustValue == null) {
            providerTrust = new BigDecimal(0.5);
        } else {
            providerTrust = providerTrustValue.getTrustValue();
        }
        directTrust = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP).multiply(providerTrust);
        return directTrust;
    }

    private BigDecimal calcRenterToCompIndirectTrust(Renter renter, Component component) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal indirectTrust;
        // 查询与该组件有直接信任的租户，作为推荐租户
        List<RenterTrustValue> renterTrustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(null,
                componentUid, 2);
        // 获得推荐人名单，去重，去自身
        List<String> recommenders = new ArrayList<String>();
        for (RenterTrustValue trustValues : renterTrustValues) {
            String trustorUid = trustValues.getTrustorUid();
            if (trustorUid.equals(renterUid)) {
                continue;
            }
            if (!recommenders.contains(trustorUid)) {
                recommenders.add(trustorUid);
            }
        }
        // 若果没有推荐人，则无推荐信任
        if (recommenders.size() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        // 有效推荐者次数
        int times = 0;
        // 计算推荐信任
        for (String recommenderUid : recommenders) {
            // 查询推荐者对组件的最近信任值
            RenterTrustValue recommenderToRenterTrust = renterTrustValueMapper.queryLatestTrustValue(recommenderUid,
                    componentUid);
            BigDecimal recommenderDirectTrustValue = recommenderToRenterTrust.getTrustValue();
            // 查询请求者对推荐者的最近信任值
            RenterTrustValue renterToRecommenderTrust = renterTrustValueMapper.queryLatestTrustValue(renterUid,
                    recommenderUid);
            BigDecimal renterToRecommenderTrustValue;
            if (renterToRecommenderTrust == null) {
                renterToRecommenderTrustValue = new BigDecimal(0.5);
            } else {
                renterToRecommenderTrustValue = renterToRecommenderTrust.getTrustValue();
                times++;
            }
            sum = sum.add(recommenderDirectTrustValue.multiply(renterToRecommenderTrustValue));
        }
        if (times == 0) {
            return BigDecimal.ZERO;
        }
        indirectTrust = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        return indirectTrust;
    }
}
