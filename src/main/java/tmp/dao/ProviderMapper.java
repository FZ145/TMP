package tmp.dao;

import org.springframework.stereotype.Repository;
import tmp.entity.Provider;

import java.util.List;

@Repository
public interface ProviderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> selectAll();
}