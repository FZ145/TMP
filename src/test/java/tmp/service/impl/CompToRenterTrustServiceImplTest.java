package tmp.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.dao.ComponentMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.Renter;
import tmp.service.CompToRenterTrustService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class CompToRenterTrustServiceImplTest {
    @Resource
    private CompToRenterTrustService compToRenterTrustService;
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private ComponentMapper componentMapper;

    @Test
    public void testCalcCompToRenterTrust() throws Exception {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        int componentRandomIndex = random.nextInt(componentList.size());
        int renterRandomIndex = random.nextInt(renterList.size());
        Component randomComponent = componentList.get(componentRandomIndex);
        Renter randomRenter = renterList.get(renterRandomIndex);
        compToRenterTrustService.calcCompToRenterTrust(randomComponent, randomRenter);
    }

}