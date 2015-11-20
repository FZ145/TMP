package tmp.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tmp.dao.ProviderMapper;
import tmp.entity.Provider;
import tmp.service.ProviderReputationService;

/**
 * Created by shining.cui on 2015/11/15.
 */
@Component("providerReputationTask")
public class ProviderReputationTask {
    private static final Logger logger = LoggerFactory.getLogger(ProviderReputationTask.class);
    @Resource
    private ProviderReputationService providerReputationService;
    @Resource
    private ProviderMapper providerMapper;

    // 每小时运行一次，计算所有云的声誉，存入数据库
    @Scheduled(cron = "0 0 0/1 * * *")
    public void calcReputation() {
        List<Provider> providers = providerMapper.selectAll();
        for (Provider provider : providers) {
            providerReputationService.calcProviderReputation(provider);
        }
        logger.info("=======云声誉计算结束===========,当前时间:{}", new Date());

    }
}
