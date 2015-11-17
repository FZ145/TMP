package tmp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tmp.dao.ProviderMapper;
import tmp.entity.Provider;
import tmp.service.ProviderReputationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("providerReputationTask")
public class ProviderReputationTask {
    @Resource
    private ProviderReputationService providerReputationService;
    @Resource
    private ProviderMapper providerMapper;
    //每小时运行一次，计算所有云的声誉，存入数据库
    @Scheduled(cron = "0 0 0/1 * * *")
    public void calcReputation() {
        List<Provider> providers = providerMapper.selectAll();
        for (Provider provider : providers) {
            providerReputationService.calcProviderReputation(provider);
        }
        System.out.println("=======云声誉计算结束===========");

    }
}
