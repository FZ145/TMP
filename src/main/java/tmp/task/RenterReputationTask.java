package tmp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.service.ComponentReputationService;
import tmp.service.RenterReputationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("renterReputationTask")
public class RenterReputationTask {
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private RenterReputationService renterReputationService;
    //每小时运行一次，计算所有租户的声誉，存入数据库
    @Scheduled(cron = "0 0 0/1 * * *")
    public void calcReputation() {
        List<Renter> renters = renterMapper.selectAll();
        for (Renter renter : renters) {
            renterReputationService.calcRenterReputation(renter);
        }
        System.out.println("=======租户声誉计算结束===========");
    }
}
