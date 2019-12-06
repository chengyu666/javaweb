package chengyu.printer.controller;

import chengyu.printer.model.Code;
import chengyu.printer.model.DBConnector;
import chengyu.printer.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserViewController {
    Logger logger = Logger.getLogger("logger");

    @Value("${config.version}")
    String version;

    @RequestMapping("/searchCode")
    public String doSearch(HttpServletRequest request, Model model){
        //TODO
        String code=request.getParameter("code");
        logger.info("error code:"+code);
        return "../../include/search";
    }
    @RequestMapping("/allUsers")
    public String showAllUsers(Model model){
        DBConnector connector=new DBConnector();
        try{
            List<User> list=connector.getAllUsers();
            model.addAttribute("userList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/allUsers";
    }
    @RequestMapping("/allCodes")
    public String showAllCodes(Model model){
        DBConnector connector=new DBConnector();
        try{
            List<Code> list=connector.getAllCodes();
            model.addAttribute("codeList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/allCodes";
    }

}
