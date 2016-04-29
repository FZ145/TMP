package tmp.controller;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tmp.bo.LoginResult;
import tmp.bo.QueryResult;
import tmp.dao.ComponentHistoryMapper;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.entity.Renter;
import tmp.entity.RenterHistory;
import tmp.service.ComponentHistoryService;
import tmp.service.QueryComponentHistoryService;
import tmp.service.QueryRenterHistoryService;
import tmp.staticvalue.StaticValue;


import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by yuanyao on 2016/3/20.
 */
@Controller
@RequestMapping(value = "/user")


public class QueryHistoryController {
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private QueryRenterHistoryService queryRenterHistoryService;
    @Resource
    private ComponentHistoryService componentHistoryService;
    @Resource
    private ComponentMapper componentMapper;


    QueryResult queryResult= new QueryResult();

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value="/queryHistory.do")
    public ModelAndView queryRenterHistory(HttpSession httpSession) throws IOException {
        LoginResult result = (LoginResult) httpSession.getAttribute("result");


        String indentifyCode = result.getIndentifyCode();

        if (StringUtils.equals(indentifyCode, StaticValue.RENTER)) {
            //如果为租户，则调用此方法
            String uid = "renter1";
            Renter renter1 = renterMapper.selectByUid(uid);
            List<RenterHistory> renterHistories = queryRenterHistoryService.selectByTrustorAndTrusteeUid(renter1);
            queryResult.setResult(renterHistories);
            show();
        } else if (StringUtils.equals(indentifyCode, StaticValue.COMPONENT)) {
            String uid = "component1";
            Component component = componentMapper.selectByUid(uid);
            List<ComponentHistory> componentHistories = componentHistoryService.selectByTrustorAndTrusteeUid(component);
            queryResult.setResult(componentHistories);
            show();
        }
        return modelAndView;

    }
        //把数据库查询得到的数据转换为字符串，保存到modelAndView中
       private void show() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String renterJson = mapper.writeValueAsString(queryResult);
        modelAndView.setViewName("queryHistory");
        modelAndView.addObject("result", renterJson);
          // return renterJson;

    }

}