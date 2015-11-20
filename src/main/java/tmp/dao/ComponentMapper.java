package tmp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tmp.entity.Component;

@Repository
public interface ComponentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);

    Component selectByUid(String uid);

    List<Component> selectByParentUid(String parentUid);

    List<Component> selectAll();

}