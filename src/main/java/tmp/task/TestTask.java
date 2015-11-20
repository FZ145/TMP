package tmp.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("testTask")
public class TestTask {
    private static final Logger logger = LoggerFactory.getLogger(RenterReputationTask.class);

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void doSomething() {
        logger.info("=======定时任务进行中===========,当前时间:{}", new Date());

    }

}
