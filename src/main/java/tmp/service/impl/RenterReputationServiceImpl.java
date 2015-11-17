package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterReputationMapper;
import tmp.entity.Component;
import tmp.entity.Renter;
import tmp.entity.RenterReputation;
import tmp.service.CompToRenterTrustService;
import tmp.service.RenterReputationService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by shining.cui on 2015/11/15.
 * 查询所有组件对该租户的信任，加权平均获得该组件此时的声誉，该任务定时触发
 */
@Service("renterReputationService")
public class RenterReputationServiceImpl implements RenterReputationService {
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private CompToRenterTrustService compToRenterTrustService;
    @Resource
    private RenterReputationMapper renterReputationMapper;
    @Override
    public void calcRenterReputation(Renter renter) {
        //查询所有组件对该租户的最新的信任值，融合获得组件层对他的信任
        //查找所有的组件
        List<Component> components = componentMapper.selectAll();
        int times = 0;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal reputation;
        for (Component component : components) {
            BigDecimal trust = compToRenterTrustService.calcCompToRenterTrust(component, renter);
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
        RenterReputation renterReputation = new RenterReputation();
        renterReputation.setReputationValue(reputation);
        renterReputation.setCreateTime(new Date());
        renterReputation.setRenterUid(renter.getUid());
        renterReputation.setUid("" + new Date().getTime() + renter.getUid());
        renterReputationMapper.insertSelective(renterReputation);
    }
}
