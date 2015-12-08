package tmp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.ProviderTrustValue;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ProviderTrustValueMapperTest {
    @Resource
    private ProviderTrustValueMapper providerTrustValueMapper;
    @Test
    public void testQueryReputationListByProviderUid() throws Exception {
        List<ProviderTrustValue> trustValues = providerTrustValueMapper.queryReputationListByProviderUid("provider1");
        System.out.println(trustValues);
    }
    @Test
    public void test1() throws Exception {
        ProviderTrustValue provider1 = providerTrustValueMapper.queryLatestByProviderUid("provider1");
        System.out.println(provider1);
    }
}