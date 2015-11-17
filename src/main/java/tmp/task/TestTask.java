package tmp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("testTask")
public class TestTask {
    @Scheduled(cron = "0/5 * *  * * ? ")
    public void doSomething() {
        System.out.println("==============定时任务进行中==========");
    }
}
