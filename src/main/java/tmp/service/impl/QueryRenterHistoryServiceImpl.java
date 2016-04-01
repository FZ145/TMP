package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.dao.RenterHistoryMapper;

import tmp.entity.Renter;
import tmp.entity.RenterHistory;
import tmp.service.QueryRenterHistoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyao on 2016/3/20.
 */
@Service("RenterHistoryService")
public class QueryRenterHistoryServiceImpl implements QueryRenterHistoryService {

    @Resource
    private RenterHistoryMapper renterHistoryMapper;


    @Override
    public List<RenterHistory> selectByTrustorAndTrusteeUid(Renter renter) {
        String renterUid = renter.getUid();

        List<RenterHistory>  renterHistories = renterHistoryMapper.selectByTrustorAndTrusteeUid(renterUid,null,2);

        return renterHistories;
    }
}
