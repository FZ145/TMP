package tmp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tmp.entity.RenterReputation;

@Repository
public interface RenterReputationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RenterReputation record);

    int insertSelective(RenterReputation record);

    RenterReputation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RenterReputation record);

    int updateByPrimaryKey(RenterReputation record);

    // 查询对应租户的最近20条声誉记录
    List<RenterReputation> queryReputationListByRenterUid(String renterUid);
}