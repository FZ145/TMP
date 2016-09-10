package tmp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tmp.entity.Provider;


@Repository
public interface ProviderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer id);

    Provider selectByUid(String uid);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> selectAll();

    int register(Provider provider);
}