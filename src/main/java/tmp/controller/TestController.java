package tmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {
    @RequestMapping("test.do")
    @ResponseBody
    public String printWelcome(ModelMap model) {
        return "hello,我是你大爷";
    }
}