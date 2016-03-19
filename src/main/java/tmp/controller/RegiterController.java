package tmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tmp.dao.ComponentMapper;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.service.RegisterService;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.resource.spi.Connector;

/**
 * Created by yuanyao on 2016/3/19.
 */
@Controller
@RequestMapping(value = "/user")
public class RegiterController {
    @Resource
  private RegisterService registerService;
    @Resource
    private RenterMapper renterMapper;
    private  String uid;
    private  String password;

    @RequestMapping( value = "/RegisterController.do", method = RequestMethod.POST)
    public void register(String uid, String password, String indentifyCode) throws Exception {
                Renter renter = new Renter();
                renter.setUid(uid);
                renter.setPassword(password);
                renterMapper.insert(renter);
            //registerService.register();
    }
}
