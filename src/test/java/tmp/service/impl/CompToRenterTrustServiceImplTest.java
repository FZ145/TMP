package tmp.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.Component;
import tmp.entity.Renter;
import tmp.service.CompToRenterTrustService;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CompToRenterTrustServiceImplTest {
    @Resource
    private CompToRenterTrustService compToRenterTrustService;
    @Test
    public void testCalcCompToRenterTrust() throws Exception {
        Component component = new Component();
        component.setUid("component1");
        component.setParentUid("provider1");
        Renter renter = new Renter();
        renter.setUid("renter1");
        BigDecimal trust = compToRenterTrustService.calcCompToRenterTrust(component, renter);
        System.out.println("==========done=========");
        System.out.println(trust);
    }

    @Test
    public void testCalcCompToRenterDirectTrust() throws Exception {
        Component component = new Component();
        component.setUid("component1");
        component.setParentUid("provider1");
        Renter renter = new Renter();
        renter.setUid("renter1");
        BigDecimal directTrust = compToRenterTrustService.calcCompToRenterDirectTrust(component, renter);
        System.out.println("=========done=========");
        System.out.println(directTrust);
    }

    @Test
    public void testCalcCompToRenterIndirectTrust() throws Exception {
        Component component = new Component();
        component.setUid("component1");
        component.setParentUid("provider1");
        Renter renter = new Renter();
        renter.setUid("renter1");
        BigDecimal indirectTrust = compToRenterTrustService.calcCompToRenterIndirectTrust(component, renter);
        System.out.println("=========done=========");
        System.out.println(indirectTrust);
    }
}