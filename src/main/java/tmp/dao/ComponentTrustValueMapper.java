package tmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tmp.entity.ComponentTrustValue;

import java.util.List;

@Repository
public interface ComponentTrustValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComponentTrustValue record);

    int insertSelective(ComponentTrustValue record);

    ComponentTrustValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComponentTrustValue record);

    int updateByPrimaryKey(ComponentTrustValue record);

    List<ComponentTrustValue> selectByTrustorAndTrusteeUid(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid, @Param(value = "actionType") Integer actionType);

    ComponentTrustValue queryLatestTrustValue(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid);
}