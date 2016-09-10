package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.service.RegisterService;

import javax.annotation.Resource;

/**
 * Created by yuanyao on 2016/3/19.
 */
@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService{
    @Resource
    private RenterMapper renterMapper;
    private Renter renter;
    @Override
    public void register() throws Exception {
        renterMapper.register();

    }
}
