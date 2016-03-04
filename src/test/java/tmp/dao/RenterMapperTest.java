package tmp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.Renter;

import javax.annotation.Resource;

/**
 * Created by yuanyao on 2016/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class RenterMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(RenterMapperTest.class);

    @Resource
    private RenterMapper renterMapper;
    @Test
    public void testSelectByUid() throws Exception {

        String renterUid = "renter1";
        Renter renter = renterMapper.selectByUid(renterUid);
        String password = renter.getPassword();

        logger.info("=====查到对应的密码，用户名为{}，密码为{}=======",renterUid,password);



    }
}
