package tmp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tmp.entity.ComponentHistory;

@Repository
public interface ComponentHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComponentHistory record);

    int insertSelective(ComponentHistory record);

    ComponentHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComponentHistory record);

    int updateByPrimaryKey(ComponentHistory record);

    List<ComponentHistory> selectByTrustorAndTrusteeUid(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid, @Param(value = "actionType") Integer actionType);
}