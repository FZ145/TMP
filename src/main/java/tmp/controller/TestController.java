package tmp.controller;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import tmp.entity.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class TestController {


/*    @RequestMapping(value="/test.do")
    public String printWelcome( HttpSession httpSession ) {
        User user = new User();
        user.setAge(23);
        user.setId(123);
        user.setUserName("Allen");
        httpSession.setAttribute("user",user);
        System.out.println("我已经把session存入了");
        return "session";
    }



    @RequestMapping("show.do")
    public String logout(HttpSession httpSession){
       User u = (User) httpSession.getAttribute("user");
        System.out.println("我已经把session取到了");
        System.out.println(u.getUserName());
        System.out.println(u.getAge());
        return "success";
    }*/

    @RequestMapping("test.do")
    public String testSet(ModelMap model){
        User user = new User();
        user.setAge(23);
        user.setId(123);
        user.setUserName("Allen");
        model.addAttribute("user",user);
        return "session";
    }

    @RequestMapping("show.do")
    public  String testGet(@ModelAttribute("user")User user){
        user.getUserName();
        return  null;
    }
}