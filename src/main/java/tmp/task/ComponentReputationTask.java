package tmp.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tmp.dao.ComponentMapper;
import tmp.service.ComponentReputationService;

/**
 *云层的声誉计算会触发组件层的声誉计算，这个任务不需要了。
 * Created by shining.cui on 2015/11/15.
 */
//@Component("componentReputationTask")
//public class ComponentReputationTask {
//    private static final Logger logger = LoggerFactory.getLogger(ComponentReputationTask.class);
//    @Resource
//    private ComponentMapper componentMapper;
//    @Resource
//    private ComponentReputationService componentReputationService;
//
//    // 每小时运行一次，计算所有组件的声誉，存入数据库
//    @Scheduled(cron = "0 0 0/1 * * *")
//    public void calcReputation() {
//        List<tmp.entity.Component> components = componentMapper.selectAll();
//        for (tmp.entity.Component component : components) {
//            componentReputationService.calcComponentReputation(component);
//        }
//        logger.info("=======组件声誉计算结束===========,当前时间:{}", new Date());
//    }
//}
