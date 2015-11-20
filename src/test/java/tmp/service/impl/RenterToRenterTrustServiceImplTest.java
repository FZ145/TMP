package tmp.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.service.RenterToRenterTrustService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class RenterToRenterTrustServiceImplTest {
    @Resource
    private RenterToRenterTrustService renterToRenterTrustService;
    @Resource
    private RenterMapper renterMapper;

    @Test
    public void testCalcRenterToRenterTrust() throws Exception {
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        int trustorRandomIndex = random.nextInt(4);
        int trusteeRandomIndex = random.nextInt(4);
        while (trusteeRandomIndex == trustorRandomIndex) {
            trusteeRandomIndex = random.nextInt(4);
        }
        Renter trustor = renterList.get(trustorRandomIndex);
        Renter trustee = renterList.get(trusteeRandomIndex);
        renterToRenterTrustService.calcRenterToRenterTrust(trustor, trustee);
        renterToRenterTrustService.calcRenterToRenterTrust(trustee, trustor);
    }
}