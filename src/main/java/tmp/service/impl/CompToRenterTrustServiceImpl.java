package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.bo.HistoryAndWeight;
import tmp.dao.ComponentHistoryMapper;
import tmp.dao.ComponentMapper;
import tmp.dao.ComponentTrustValueMapper;
import tmp.dao.ProviderTrustValueMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.entity.ComponentTrustValue;
import tmp.entity.ProviderTrustValue;
import tmp.entity.Renter;
import tmp.service.CompToRenterTrustService;
import tmp.staticValue.staticValue;
import tmp.util.ListUtil;
import tmp.util.Weight;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * * 计算组件与租户的信任，将综合信任评估结果存入数据库componentreputation中 Created by shining.cui on 2015/11/6.
 */
@Service("compToRenterTrustService")
public class CompToRenterTrustServiceImpl implements CompToRenterTrustService {
    @Resource
    private ComponentHistoryMapper componentHistoryMapper;
    @Resource
    private ComponentTrustValueMapper componentTrustValueMapper;
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private ProviderTrustValueMapper providerTrustValueMapper;

    @Override
    public BigDecimal calcCompToRenterTrust(Component component, Renter renter) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal overallTrust;
        // 查询组件与租户的交互历史次数
        List<ComponentTrustValue> trustValues = componentTrustValueMapper.selectByTrustorAndTrusteeUid(componentUid,
                renterUid, null);
        int directTimes = trustValues.size();
        // 查询其他组件与该租户的信任评估历史次数
        List<ComponentTrustValue> componentTrustValues = componentTrustValueMapper.selectByTrustorAndTrusteeUid(null, renterUid, 2);
        int totalTimes = componentTrustValues.size();
        // 计算直接信任与间接信任
        BigDecimal directTrust = calcCompToRenterDirectTrust(component, renter);
        BigDecimal indirectTrust = calcCompToRenterIndirectTrust(component, renter);
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
        ComponentTrustValue componentTrustValue = new ComponentTrustValue();
        componentTrustValue.setCreateTime(new Date());
        componentTrustValue.setTrustValue(overallTrust);
        componentTrustValue.setTrusteeUid(renterUid);
        componentTrustValue.setTrustorUid(componentUid);
        // uid为唯一键，作为唯一流水号
        componentTrustValue.setUid("" + new Date().getTime() + componentUid);
        componentTrustValue.setActionType(2);
        componentTrustValueMapper.insert(componentTrustValue);
        return overallTrust;
    }

    private BigDecimal calcCompToRenterDirectTrust(Component component, Renter renter) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal directTrust;
        // 获取双方实体所有交互历史
        List<ComponentHistory> componentHistories = componentHistoryMapper.selectByTrustorAndTrusteeUid(componentUid,
                renterUid, null);
        if (componentHistories.size() == 0) {
            return BigDecimal.ZERO;
        }
        // 获取双方实体可用交互历史
        List<HistoryAndWeight<ComponentHistory>> histories = ListUtil.getAvailableComponentHistory(componentHistories,
                staticValue.daysThreshold);
        if (histories.size() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        // 计算每次交互历史的信任值与权重，求累成和
        for (HistoryAndWeight<ComponentHistory> historyAndWeight : histories) {
            BigDecimal trustValue = historyAndWeight.getHistory().getTrustValue();
            BigDecimal weight = historyAndWeight.getWeight();
            sum = sum.add(trustValue.multiply(weight));
        }
        // 计算有效交互历史的次数，累成和除以次数即为直接信任
        BigDecimal times = new BigDecimal(histories.size());
        directTrust = sum.divide(times, 4, BigDecimal.ROUND_HALF_UP);
        return directTrust;
    }

    private BigDecimal calcCompToRenterIndirectTrust(Component component, Renter renter) {
        String componentUid = component.getUid();
        String renterUid = renter.getUid();
        BigDecimal indirectTrust;
        // 查询与该租户有直接信任的组件，作为推荐组件
        List<ComponentTrustValue> componentTrustValues = componentTrustValueMapper.selectByTrustorAndTrusteeUid(null,
                renterUid,2);
        // 获得推荐人名单，去重，去自身
        List<String> recommenders = new ArrayList<String>();
        for (ComponentTrustValue trustValues : componentTrustValues) {
            String trustorUid = trustValues.getTrustorUid();
            if (trustorUid.equals(componentUid)) {
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
            // 查询推荐者对租户的最近信任值
            ComponentTrustValue recommenderToRenterTrust = componentTrustValueMapper
                    .queryLatestTrustValue(recommenderUid, renterUid);
            BigDecimal recommenderDirectTrustValue = recommenderToRenterTrust.getTrustValue();
            // 查询请求者对推荐者的最近信任值
            ComponentTrustValue componentToRecommenderTrust = componentTrustValueMapper
                    .queryLatestTrustValue(componentUid, recommenderUid);
            BigDecimal componentToRecommenderTrustValue;
            if (componentToRecommenderTrust == null) {
                continue;
            } else {
                componentToRecommenderTrustValue = componentToRecommenderTrust.getTrustValue();
                times++;
            }
            // 查询推荐者所属云的信誉
            BigDecimal recommendersProviderTrust;
            Component recommender = componentMapper.selectByUid(recommenderUid);
            // 如果推荐者与请求者属于同一个云，则默认完全信任
            if (recommender.getParentUid().equals(component.getParentUid())) {
                recommendersProviderTrust = new BigDecimal(0.5);
            } else {
                ProviderTrustValue providerTrustValue = providerTrustValueMapper
                        .queryLatestByProviderUid(recommender.getParentUid());
                // 获得组件所属云的最近一次信誉值,如果该云没有信誉值，则默认为0.5
                if (providerTrustValue == null) {
                    recommendersProviderTrust = new BigDecimal(0.5);
                } else {
                    recommendersProviderTrust = providerTrustValue.getTrustValue();
                }
            }
            sum = sum.add(recommenderDirectTrustValue.multiply(componentToRecommenderTrustValue)
                    .multiply(recommendersProviderTrust));
        }
        if (times == 0) {
            return BigDecimal.ZERO;
        }
        indirectTrust = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        return indirectTrust;
    }
}
