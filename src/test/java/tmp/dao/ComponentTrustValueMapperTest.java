package tmp.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.entity.ComponentTrustValue;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ComponentTrustValueMapperTest {
    @Resource
    private ComponentTrustValueMapper componentTrustValueMapper;

    @Test
    public void testInsert() throws Exception {
        ComponentTrustValue componentTrustValue = new ComponentTrustValue();
        componentTrustValue.setUid("order4");
        componentTrustValue.setTrustorUid("component1");
        componentTrustValue.setTrusteeUid("component3");
        componentTrustValue.setTrustValue(new BigDecimal(0.64));
        componentTrustValue.setCreateTime(new Date());
        componentTrustValueMapper.insert(componentTrustValue);
    }

    @Test
    public void testSelectByTrustorAndTrusteeUid() throws Exception {
        List<ComponentTrustValue> componentTrustValues = componentTrustValueMapper.selectByTrustorAndTrusteeUid(null,
                "component3", null);
        System.out.println(componentTrustValues);
    }

    @Test
    public void testQueryLatestTrustValue() throws Exception {
        ComponentTrustValue componentTrustValue = componentTrustValueMapper.queryLatestTrustValue("component1",
                "component3");
        System.out.println(componentTrustValue);
    }
}