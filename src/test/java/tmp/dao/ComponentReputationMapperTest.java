package tmp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tmp.entity.ComponentReputation;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class ComponentReputationMapperTest {
@Resource
private ComponentReputationMapper componentReputationMapper;
    @Test
    public void testQueryReputationListByComponentUid() throws Exception {
        List<ComponentReputation> componentReputations = componentReputationMapper.queryReputationListByComponentUid("component1");
        System.out.println(componentReputations);
    }
    @Test
    public void testSelectByPrimaryKey(){
        ComponentReputation reputation = componentReputationMapper.selectByPrimaryKey(50);
        System.out.println(reputation);
    }
}