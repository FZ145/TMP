package tmp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tmp.entity.RenterHistory;

@Repository
public interface RenterHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RenterHistory record);

    int insertSelective(RenterHistory record);

    RenterHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RenterHistory record);

    int updateByPrimaryKey(RenterHistory record);

    List<RenterHistory> selectByTrustorAndTrusteeUid(@Param(value = "trustorUid") String trustorUid,
            @Param(value = "trusteeUid") String trusteeUid, @Param(value = "actionType") Integer actionType);
}