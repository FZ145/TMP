package tmp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.Provider;


import javax.annotation.Resource;


/**
 * Created by yuanyao on 2016/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ProviderMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(RenterMapperTest.class);

    @Resource
    private ProviderMapper providerMapper;
    @Test
    public void testSelectByUid() throws Exception {

        String providerUid = "provider1";
        Provider provider = providerMapper.selectByUid(providerUid);
        String password = provider.getPassword();

        logger.info("=====查到对应的密码，用户名为{}，密码为{}=======",providerUid,password);



    }
}
