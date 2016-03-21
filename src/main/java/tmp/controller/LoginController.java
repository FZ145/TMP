package tmp.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tmp.bo.LoginResult;
import tmp.dao.ComponentMapper;
import tmp.dao.ComponentTrustValueMapper;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;

import tmp.entity.ComponentHistory;
import tmp.entity.Provider;
import tmp.entity.Renter;

import tmp.service.ComponentHistoryService;
import tmp.staticvalue.StaticValue;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuanyao on 2016/1/15.
 */

@Controller
@RequestMapping(value = "/user")

//@SessionAttributes("userName")
public class LoginController {
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private ProviderMapper providerMapper;
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private ComponentHistoryService componentHistoryService;




    // 用户登录,分为三种用户，分别为provider（云），renter（租户），component（组件）；
    @RequestMapping(value = "/index.do", method = RequestMethod.POST)
    public ModelAndView login( String userName, String password, String indentifyCode) throws IOException {
        //验证参数合法性，是否非空
        Preconditions.checkNotNull(userName);
        Preconditions.checkNotNull(password);
        Preconditions.checkNotNull(indentifyCode);

        ModelAndView modelAndView = new ModelAndView();


        LoginResult loginResult = new LoginResult();
        if (StringUtils.equals(indentifyCode, StaticValue.COMPONENT)) {
            //查询组件表对应密码，判断是否匹配，调转到登陆成功页面，给出对应身份标志位给前段判断
            Component component = componentMapper.selectByUid(userName);
            if (component != null) {
                String DBpassword = component.getPassword();
                if (StringUtils.equals(DBpassword, password)) {
                    String componentUid = component.getUid();
                    loginResult.setEntityId(componentUid);
                    loginResult.setIndentifyCode(indentifyCode);
                     List<ComponentHistory> componentHistories = componentHistoryService.selectByTrustorAndTrusteeUid(component);
                     loginResult.setContent(componentHistories);
                }
            }

        } else if (StringUtils.equals(indentifyCode, StaticValue.PROVIDER)) {
            Provider provider = providerMapper.selectByUid(userName);
            if (provider != null) {
                String DBpassword = provider.getPassword();
                if (StringUtils.equals(DBpassword, password)) {
                    String providerUid = provider.getUid();
                    loginResult.setEntityId(providerUid);
                    loginResult.setIndentifyCode(indentifyCode);
                }
            }

        } else if (StringUtils.equals(indentifyCode, StaticValue.RENTER)) {
            Renter renter = renterMapper.selectByUid(userName);
            if (renter != null) {
                String DBpassword = renter.getPassword();
                if (StringUtils.equals(DBpassword, password)) {
                    String renterUid = renter.getUid();
                    loginResult.setEntityId(renterUid);
                    loginResult.setIndentifyCode(indentifyCode);

                }
            }

        }
        if (StringUtils.isNoneEmpty(loginResult.getEntityId())) {
            //把登录的结果（只有身份和实体id）作为字符串返回给modleandview
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(loginResult);
            modelAndView.setViewName("success");
            modelAndView.addObject("result", json);
        } else {
            modelAndView.setViewName("loginFail");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register.do")
    public String register() {
        return "register";
    }
}
