package tmp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tmp.dao.ComponentReputationMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.ComponentReputation;
import tmp.entity.Renter;
import tmp.service.ComponentReputationService;
import tmp.service.RenterToCompTrustService;

/**
 * Created by shining.cui on 2015/11/15. 查询所有租户对该组件的信任，加权平均获得该组件此时的声誉，该任务定时触发
 */
@Service("componentReputationService")
public class ComponentReputationServiceImpl implements ComponentReputationService {
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private RenterToCompTrustService renterToCompTrustService;
    @Resource
    private ComponentReputationMapper componentReputationMapper;

    @Override
    public void calcComponentReputation(Component component) {
        // 查询所有用户对该组件的最新的信任值，融合获得组件层对他的信任
        // 查找所有的租户
        List<Renter> renters = renterMapper.selectAll();
        int times = 0;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal reputation;
        for (Renter renter : renters) {
            BigDecimal trust = renterToCompTrustService.calcRenterToCompTrust(renter, component);
            if (trust != BigDecimal.ZERO) {
                times++;
                sum = sum.add(trust);
            }
        }
        if (times == 0) {
            reputation = BigDecimal.ZERO;
        } else {
            reputation = sum.divide(new BigDecimal(times), 4, BigDecimal.ROUND_HALF_UP);
        }
        // 将组件声誉结果存入数据库
        ComponentReputation componentReputation = new ComponentReputation();
        componentReputation.setUid("" + new Date().getTime() + component.getUid());
        componentReputation.setComponentUid(component.getUid());
        componentReputation.setCreateTime(new Date());
        componentReputation.setReputationValue(reputation);
        componentReputationMapper.insertSelective(componentReputation);
    }
}
