package tmp.service;

import tmp.entity.Renter;
import tmp.entity.RenterHistory;

import java.util.List;

/**
 * Created by yuanyao on 2016/3/20.
 */
public interface RenterHistoryService {
    //查询租户的交互历史记录
    List<RenterHistory>   selectByTrustorAndTrusteeUid(Renter renter);

}
