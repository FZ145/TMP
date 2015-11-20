package tmp.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.entity.Provider;
import tmp.service.ProviderReputationService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ProviderReputationServiceImplTest {
    @Resource
    private ProviderReputationService providerReputationService;

    @Test
    public void testCalcProviderReputation() throws Exception {
        Provider provider = new Provider();
        provider.setUid("provider1");
        providerReputationService.calcProviderReputation(provider);
    }
}