package tmp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tmp.entity.RenterTrustValue;

import java.util.List;

@Repository
public interface RenterTrustValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RenterTrustValue record);

    int insertSelective(RenterTrustValue record);

    RenterTrustValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RenterTrustValue record);

    int updateByPrimaryKey(RenterTrustValue record);

    RenterTrustValue queryLatestTrustValue(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid);

    List<RenterTrustValue> selectByTrustorAndTrusteeUid(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid, @Param(value = "actionType") Integer actionType);
}