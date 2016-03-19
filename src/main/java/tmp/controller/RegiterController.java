package tmp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tmp.dao.ComponentMapper;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.Provider;
import tmp.entity.Renter;
import tmp.service.RegisterService;
import org.springframework.web.bind.annotation.RequestMethod;
import tmp.staticvalue.StaticValue;

import javax.annotation.Resource;
import javax.resource.spi.Connector;

/**
 * Created by yuanyao on 2016/3/19.
 */
@Controller
@RequestMapping(value = "/user")
public class RegiterController {

    @Resource
    private ProviderMapper providerMapper;

   @Resource
   private  RenterMapper renterMapper;


    @RequestMapping( value = "/RegisterController.do", method = RequestMethod.POST)
    public ModelAndView register(String uid, String password, String indentifyCode) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtils.equals(indentifyCode, StaticValue.RENTER)) {
            Renter renter = new Renter();
            renter.setUid(uid);
            renter.setPassword(password);
            renterMapper.insert(renter);


        }else if(StringUtils.equals(indentifyCode, StaticValue.PROVIDER)){
            Provider provider = new Provider();
            provider.setUid(uid);
            provider.setPassword(password);
            providerMapper.register(provider);

        }
        modelAndView.setViewName("registerSuccess");
        return modelAndView;
    }
}
