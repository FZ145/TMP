package tmp.dao;

import org.springframework.stereotype.Repository;
import tmp.entity.Renter;

import java.util.List;

@Repository
public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);

    List<Renter> selectAll();
}