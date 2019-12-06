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
    DBConnector connector=new DBConnector();

    @Value("${config.version}")
    String version;

    @RequestMapping("/updateUser")
    public String doUpdatePassword(HttpServletRequest request, Model model){
        String oldPassword=request.getParameter("oldp");
        String newPassword=request.getParameter("newp");
        String idStr=request.getParameter("id");
        Integer id= Integer.valueOf(idStr);
        logger.info("id:"+idStr+
                " old pass:"+oldPassword+
                " new pass:"+newPassword);
        try{
            boolean isSuccess=connector.updatePassword(id,oldPassword,newPassword);
            if(isSuccess){
                model.addAttribute("uResult", "修改成功");
            }else {
                model.addAttribute("uResult","修改失败，请重试");
            }
        }catch (SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/updateUser";
    }

    @RequestMapping("/editUser")
    public String doEditUser(Model model){
        //TODO
        return "../../include/searchUser";
    }

    @RequestMapping("/removeUser")
    public String doRemoveUser(HttpServletRequest request, Model model){
        String idStr=request.getParameter("id");
        Integer id= Integer.valueOf(idStr);
        try{
            boolean isSuccess=connector.removeUserById(id);
            if(isSuccess){
                model.addAttribute("uResult", "删除成功");
            }else {
                model.addAttribute("uResult","删除失败");
            }
            //TODO isSuccess is not fully used!
            List<User> list=connector.getAllUsers();
            model.addAttribute("userList", list);
        }catch (SQLException e){
            logger.warning(e.toString());
        }

        return "../../include/allUsers";
    }

    @RequestMapping("/searchUser")
    public String doSearchUser(HttpServletRequest request, Model model){
        String inputId=request.getParameter("input");
        logger.info("input id:"+inputId);
        try{
            logger.info("start searching...");
            User user = connector.getUserById(inputId);
            model.addAttribute("userInfo",user);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/searchUser";
    }
    @RequestMapping("/allUsers")
    public String showAllUsers(Model model){
        try{
            List<User> list=connector.getAllUsers();
            model.addAttribute("userList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/allUsers";
    }
}
