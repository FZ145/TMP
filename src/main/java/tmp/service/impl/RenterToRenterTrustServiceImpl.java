package tmp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tmp.bo.HistoryAndWeight;
import tmp.dao.RenterHistoryMapper;
import tmp.dao.RenterTrustValueMapper;
import tmp.entity.Renter;
import tmp.entity.RenterHistory;
import tmp.entity.RenterTrustValue;
import tmp.service.RenterToRenterTrustService;
import tmp.staticvalue.StaticValue;
import tmp.util.ListUtil;
import tmp.util.Weight;

/**
 * 计算租户与租户的信任，将结果存入rentertrustvalue表中 Created by shining.cui on 2015/11/6.
 */
@Service("renterToRenterTrustService")
public class RenterToRenterTrustServiceImpl implements RenterToRenterTrustService {
    @Resource
    private RenterHistoryMapper renterHistoryMapper;
    @Resource
    private RenterTrustValueMapper renterTrustValueMapper;

    @Override
    public BigDecimal calcRenterToRenterTrust(Renter trustor, Renter trustee) {
        String trustorUid = trustor.getUid();
        String trusteeUid = trustee.getUid();
        BigDecimal overallTrust;
        // 查询租户与租户的交互历史次数
        List<RenterTrustValue> trustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(trustorUid, trusteeUid,
                null);
        int directTimes = trustValues.size();
        // 查询租户与所有租户的交互历史次数
        List<RenterTrustValue> renterTrustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(null, trusteeUid,
                1);
        int totalTimes = renterTrustValues.size();
        // 计算直接信任与间接信任
        BigDecimal directTrust = calcDirectTrust(trustor, trustee);
        BigDecimal indirectTrust = calcIndirectTrust(trustor, trustee);
        // 根据交互次数分配直接信任与间接信任的权重
        if (directTimes >= StaticValue.ACTIVE_TIMES_THRESHOLD) {
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
        renterTrustValue.setTrusteeUid(trusteeUid);
        renterTrustValue.setTrustorUid(trustorUid);
        // uid为唯一键，作为唯一流水号
        renterTrustValue.setUid("" + new Date().getTime() + trustorUid);
        renterTrustValue.setActionType(1);
        renterTrustValueMapper.insert(renterTrustValue);
        return overallTrust;
    }

    private BigDecimal calcDirectTrust(Renter trustor, Renter trustee) {
        String trustorUid = trustor.getUid();
        String trusteeUid = trustee.getUid();
        BigDecimal directTrust;
        // 获取双方实体所有交互历史
        List<RenterHistory> renterHistories = renterHistoryMapper.selectByTrustorAndTrusteeUid(trustorUid, trusteeUid,
                null);
        if (CollectionUtils.isEmpty(renterHistories)) {
            return BigDecimal.ZERO;
        }
        // 获取双方实体可用交互历史
        List<HistoryAndWeight<RenterHistory>> histories = ListUtil.getAvailableRenterHistory(renterHistories,
                StaticValue.DAYS_THRESHOLD);
        if (CollectionUtils.isEmpty(histories)) {
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
        directTrust = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        return directTrust;
    }

    private BigDecimal calcIndirectTrust(Renter trustor, Renter trustee) {
        String trustorUid = trustor.getUid();
        String trusteeUid = trustee.getUid();
        BigDecimal indirectTrust;
        // 查询与该租户有直接信任的租户，作为推荐租户
        List<RenterTrustValue> renterTrustValues = renterTrustValueMapper.selectByTrustorAndTrusteeUid(null, trusteeUid,
                1);
        // 获得推荐人名单，去重，去自身
        List<String> recommenders = new ArrayList<String>();
        for (RenterTrustValue trustValues : renterTrustValues) {
            String uid = trustValues.getTrustorUid();
            if (uid.equals(trustorUid)) {
                continue;
            }
            if (!recommenders.contains(uid)) {
                recommenders.add(uid);
            }
        }
        // 若果没有推荐人，则无推荐信任
        if (CollectionUtils.isEmpty(recommenders)) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        // 有效推荐者次数
        int times = 0;
        // 计算推荐信任
        for (String recommenderUid : recommenders) {
            // 查询推荐者对组件的最近信任值
            RenterTrustValue recommenderToRenterTrust = renterTrustValueMapper.queryLatestTrustValue(recommenderUid,
                    trusteeUid);
            BigDecimal recommenderDirectTrustValue = recommenderToRenterTrust.getTrustValue();
            // 查询请求者对推荐者的最近信任值
            RenterTrustValue renterToRecommenderTrust = renterTrustValueMapper.queryLatestTrustValue(trustorUid,
                    recommenderUid);
            BigDecimal renterToRecommenderTrustValue;
            if (renterToRecommenderTrust == null) {
                renterToRecommenderTrustValue = StaticValue.DEFAULT_TRUST_VALUE;
            } else {
                renterToRecommenderTrustValue = renterToRecommenderTrust.getTrustValue();
            }
            times++;
            sum = sum.add(recommenderDirectTrustValue.multiply(renterToRecommenderTrustValue));
        }
        if (times == 0) {
            return BigDecimal.ZERO;
        }
        indirectTrust = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        return indirectTrust;
    }
}
