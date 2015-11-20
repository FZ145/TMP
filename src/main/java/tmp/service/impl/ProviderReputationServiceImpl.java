package tmp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import tmp.dao.ComponentMapper;
import tmp.dao.ProviderTrustValueMapper;
import tmp.entity.Component;
import tmp.entity.Provider;
import tmp.entity.ProviderTrustValue;
import tmp.service.ComponentReputationService;
import tmp.service.ProviderReputationService;
import tmp.service.RenterToCompTrustService;

/**
 * Created by shining.cui on 2015/11/7.
 * 本模块计算量较大，数据量小的时候可以实时计算，数据量大了应当定时计算 其他模块需要使用云的声誉时，都是采用的查表的方式，即默认本模块是定时计算并存入数据库中的
 * 定时计算可以使用Spring进行配置(已完成)
 */
@Service("providerReputationService")
public class ProviderReputationServiceImpl implements ProviderReputationService {
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private ProviderTrustValueMapper providerTrustValueMapper;
    @Resource
    private RenterToCompTrustService renterToCompTrustService;
    @Resource
    private ComponentReputationService componentReputationService;

    @Override
    public void calcProviderReputation(Provider provider) {
        BigDecimal reputation;
        String providerUid = provider.getUid();
        // 遍历所有组件查找属于该云的组件
        List<Component> components = componentMapper.selectByParentUid(providerUid);
        // 累加计算所有租户对云内所有组件的信任
        int times = components.size();
        BigDecimal sum = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(components)) {
            reputation = new BigDecimal(0);
        } else {
            for (Component component : components) {
                BigDecimal componentReputation = componentReputationService.calcComponentReputation(component);
                sum = sum.add(componentReputation);
            }
            reputation = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        }
        // 将声誉评估结果存入数据库
        ProviderTrustValue providerTrustValue = new ProviderTrustValue();
        providerTrustValue.setUid("" + new Date().getTime() + providerUid);
        providerTrustValue.setCreatetime(new Date());
        providerTrustValue.setProviderUid(providerUid);
        providerTrustValue.setTrustValue(reputation);
        providerTrustValueMapper.insertSelective(providerTrustValue);
    }
}
