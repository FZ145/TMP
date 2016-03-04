package tmp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tmp.entity.Renter;

@Repository
public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    Renter selectByUid(String uid);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);

    List<Renter> selectAll();
}