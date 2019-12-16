package chengyu.javaweb.controller;

import chengyu.javaweb.model.DBConnector;
import chengyu.javaweb.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.logging.Logger;

@Controller
public class LoginController {
    Logger logger = Logger.getLogger("logger");

    @Value("${config.version}")
    String version;

    @RequestMapping("/login")
    public String indexController(Model model) {
        logger.info("\nversion:"+version);
        model.addAttribute("version",version); // 指定Model的值
        return "login";
    }


    @RequestMapping("/gotoSignup")
    public String gotoSignup(Model model){
        model.addAttribute("version",version); // 指定Model的值
        return "signup";
    }



    @RequestMapping("/login/try")
    public String loginController(HttpServletRequest request, Model model) {
        String name = request.getParameter("name"); //得到jsp页面传过来的参数
        String password = request.getParameter("password");
        System.out.println("name:"+name+" password:"+password);
        DBConnector connector=new DBConnector();
        try{
            User user=connector.login(name,password);
            if(user.getId()>0){
                System.out.println("login success!");
                model.addAttribute("version",version);
                model.addAttribute("user",user);
                model.addAttribute("page","introduce");
                return "main";
            }else{
                System.out.println("login failed");
                model.addAttribute("version",version);
                model.addAttribute("loginResult", "登陆失败！请重试...");
                return "login";
            }
            //modelAndView.addObject("userList", list);
        }catch(Exception e){
            System.out.println(e);
        }
        return "login";
    }

    @RequestMapping("/login/testResult")
    public String testController(Model model) {
        model.addAttribute("version",version); // 指定Model的值
        model.addAttribute("loginResult", "登陆失败！");
        return "loginSuccess";
    }
}
