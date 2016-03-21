package tmp.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import tmp.bo.LoginResult;
import tmp.bo.QueryResult;
import tmp.dao.RenterMapper;
import tmp.entity.Renter;
import tmp.entity.RenterHistory;
import tmp.service.RenterHistoryService;


import javax.annotation.Resource;
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
    private RenterHistoryService renterHistoryService;

    QueryResult queryResult= new QueryResult();
    //LoginResult loginResult = new LoginResult();
    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value="/queryHistory.do")
    public ModelAndView queryRenterHistory() throws IOException {
        String uid = "renter1";
        Renter renter = renterMapper.selectByUid(uid);
        List<RenterHistory>  renterHistories = renterHistoryService.selectByTrustorAndTrusteeUid(renter);
        queryResult.setResult(renterHistories);


        ObjectMapper mapper = new ObjectMapper();
        String renterJson = mapper.writeValueAsString(queryResult);
        modelAndView.setViewName("queryHistory");
        modelAndView.addObject("result", renterJson);
        return  modelAndView;
}
}