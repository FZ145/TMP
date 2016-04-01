package tmp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Provider;
import tmp.entity.Renter;
import org.springframework.web.bind.annotation.RequestMethod;
import tmp.staticvalue.StaticValue;
import javax.annotation.Resource;


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
    public ModelAndView register(String userName, String password, String indentifyCode) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtils.equals(indentifyCode, StaticValue.RENTER)) {
            Renter renter = new Renter();
            renter.setUid(userName);
            renter.setPassword(password);
            renterMapper.insert(renter);


        }else if(StringUtils.equals(indentifyCode, StaticValue.PROVIDER)){
            Provider provider = new Provider();
            provider.setUid(userName);
            provider.setPassword(password);
            providerMapper.register(provider);

        }
        modelAndView.setViewName("registerSuccess");
        return modelAndView;
    }
}
