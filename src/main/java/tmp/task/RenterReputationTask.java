package tmp.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.service.RenterReputationService;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("renterReputationTask")
public class RenterReputationTask {
    private static final Logger logger = LoggerFactory.getLogger(RenterReputationTask.class);

    @Resource
    private RenterMapper renterMapper;
    @Resource
    private RenterReputationService renterReputationService;

    // 每小时运行一次，计算所有租户的声誉，存入数据库
    @Scheduled(cron = "0 0 0/1 * * *")
    public void calcReputation() {
        List<Renter> renters = renterMapper.selectAll();
        for (Renter renter : renters) {
            renterReputationService.calcRenterReputation(renter);
        }
        logger.info("=======租户声誉计算结束===========,当前时间:{}", new Date());

    }
}
