package tmp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tmp.entity.ProviderTrustValue;

@Repository
public interface ProviderTrustValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProviderTrustValue record);

    int insertSelective(ProviderTrustValue record);

    ProviderTrustValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProviderTrustValue record);

    int updateByPrimaryKey(ProviderTrustValue record);

    ProviderTrustValue queryLatestByProviderUid(String providerUid);

    // 查询对应云的最近20条声誉记录
    List<ProviderTrustValue> queryReputationListByProviderUid(String providerUid);

}