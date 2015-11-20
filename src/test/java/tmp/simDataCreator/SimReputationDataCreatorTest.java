package tmp.simdata;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SimReputationDataCreatorTest {
    @Resource
    private SimReputationDataCreator simReputationDataCreator;

    @Test
    public void testCreateReputationDataOfRenters() throws Exception {
        simReputationDataCreator.createReputationDataOfRenters();
    }

    @Test
    public void testCreateReputationDataOfComponents() throws Exception {
        simReputationDataCreator.createReputationDataOfComponents();
    }

    @Test
    public void testCreateReputationDataOfProviders() throws Exception {
        simReputationDataCreator.createReputationDataOfProviders();
    }
}