package tmp.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class SimHistoryDataCreatorTest {
    @Resource
    private SimHistoryDataCreator simHistoryDataCreator;
    @Test
    public void testCreateHistoryDataBetweenRenterAndComponent() throws Exception {
        simHistoryDataCreator.createHistoryDataBetweenRenterAndComponent();
    }

    @Test
    public void testCreateHistoryDataBetweenComponents() throws Exception {
        simHistoryDataCreator.createHistoryDataBetweenComponents();
    }

    @Test
    public void testCreateHistoryDataBetweenRenters() throws Exception {
        simHistoryDataCreator.createHistoryDataBetweenRenters();
    }
}