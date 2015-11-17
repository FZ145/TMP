package tmp.dao;

import org.springframework.stereotype.Repository;
import tmp.entity.Component;

import java.util.List;

@Repository
public interface ComponentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);

    Component selectByUid(String Uid);

    List<Component> selectByParentUid(String parentUid);

    List<Component> selectAll();

}