package tmp.controller;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tmp.bo.LoginResult;
import tmp.dao.ComponentMapper;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.entity.Provider;
import tmp.entity.Renter;
import tmp.service.ComponentHistoryService;
import tmp.staticvalue.StaticValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by yuanyao on 2016/1/15.
 */

@Controller
//@RequestMapping(value = "/user")
@SessionAttributes("loginResult")
public class LoginController {
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private ProviderMapper providerMapper;
    @Resource
    private RenterMapper renterMapper;

    // 用户登录,分为三种用户，分别为provider（云），renter（租户），component（组件）；
    @RequestMapping(value = "/index.do", method = RequestMethod.POST)
    public String login(String userName, String password, String indentifyCode,HttpSession httpSession) throws IOException {
        //验证参数合法性，是否非空
        Preconditions.checkNotNull(userName);
        Preconditions.checkNotNull(password);
        Preconditions.checkNotNull(indentifyCode);


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
        //如果loginResult中的EntityId不为空，那么将其保存到session中，否则登录失败
        if (StringUtils.isNoneEmpty(loginResult.getEntityId())) {
            httpSession.setAttribute("result",loginResult);
        } else {

            return "loginFail";
        }
        return "loginsuccess";
    }

    @RequestMapping(value = "/register.do")
    public String register() {

        return "register";
    }
}
