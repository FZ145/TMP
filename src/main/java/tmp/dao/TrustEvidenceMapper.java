package tmp.dao;

import org.springframework.stereotype.Repository;
import tmp.entity.TrustEvidence;

/**
 * Created by yuanyao on 2016/4/17.
 */

@Repository
public interface TrustEvidenceMapper {
    TrustEvidence selectByPrimaryKey(Integer id);

    int insertRecord(TrustEvidence trustEvidence);




}
