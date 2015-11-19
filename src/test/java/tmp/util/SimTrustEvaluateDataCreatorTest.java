package tmp.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class SimTrustEvaluateDataCreatorTest {
    @Resource
    private SimTrustEvaluateDataCreator simTrustEvaluateDataCreator;

    @Test
    public void testCreateTrustEvaluateDataBetweenRenterAndComponent() throws Exception {
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenRenterAndComponent();
    }

    @Test
    public void testCreateTrustEvaluateDataBetweenRenters() throws Exception {

    }

    @Test
    public void testCreateTrustEvaluateDataBetweenComponents() throws Exception {

    }
}