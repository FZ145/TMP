package tmp.util;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.simDataCreator.SimTrustEvaluateDataCreator;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SimTrustEvaluateDataCreatorTest {
    @Resource
    private SimTrustEvaluateDataCreator simTrustEvaluateDataCreator;

    @Test
    public void testCreateTrustEvaluateDataBetweenRenterAndComponent() throws Exception {
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenRenterAndComponent(100);
    }

    @Test
    public void testCreateTrustEvaluateDataBetweenRenters() throws Exception {
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenRenters(50);
    }

    @Test
    public void testCreateTrustEvaluateDataBetweenComponents() throws Exception {
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenComponents(50);
    }
}