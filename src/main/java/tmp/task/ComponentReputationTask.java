package tmp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tmp.dao.ComponentMapper;
import tmp.service.ComponentReputationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("componentReputationTask")
public class ComponentReputationTask {
    @Resource
    private ComponentMapper componentMapper;
    @Resourced
    private ComponentReputationService componentReputationService;
    //每小时运行一次，计算所有组件的声誉，存入数据库
    @Scheduled(cron = "0 0 0/1 * * *")
    public void calcReputation() {
        List<tmp.entity.Component> components = componentMapper.selectAll();
        for (tmp.entity.Component component : components) {
            componentReputationService.calcComponentReputation(component);
        }
        System.out.println("=======组件声誉计算结束===========");
    }
}
