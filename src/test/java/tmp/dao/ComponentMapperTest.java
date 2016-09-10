package tmp.dao;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.Component;
import tmp.task.RenterReputationTask;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by yuanyao on 2016/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ComponentMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(ComponentMapperTest.class);

    @Resource
    private ComponentMapper componentMapper;
    @Test
    public void testSelectByUid() throws Exception {

        String componentUid = "component1";
        Component component = componentMapper.selectByUid(componentUid);
        String password = component.getPassword();

        logger.info("=====查到对应的密码，用户名为{}，密码为{}=======",componentUid,password);



    }
}