package chengyu.printer.controller;

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

    @RequestMapping("/editUserExpire")
    public String editUserExpire(HttpServletRequest request, Model model){
        String idStr=request.getParameter("id");
        String newExpire=request.getParameter("newExpire");
        Integer id= Integer.valueOf(idStr);
        try{
            boolean isSuccess=connector.updateUserExpire(id,newExpire);
            if(isSuccess){
                model.addAttribute("expireResultOk", "使用期限修改成功!");
            }else {
                model.addAttribute("expireResultFail","使用期限修改失败，请重试");
            }
            User user = connector.getUserById(idStr);
            model.addAttribute("userInfo",user);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editUser";
    }

    @RequestMapping("/editUserName")
    public String editUserName(HttpServletRequest request, Model model){
        String idStr=request.getParameter("id");
        String newName=request.getParameter("newName");
        Integer id= Integer.valueOf(idStr);
        try{
            boolean isSuccess=connector.updateUserName(id,newName);
            if(isSuccess){
                model.addAttribute("nameResultOk", "用户名修改成功!");
            }else {
                model.addAttribute("nameResultFail","用户名修改失败，请重试");
            }
            User user = connector.getUserById(idStr);
            model.addAttribute("userInfo",user);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editUser";
    }

    @RequestMapping("/gotoEditUser")
    public String gotoEditUser(HttpServletRequest request, Model model){
        String userId=request.getParameter("id");
        try{
            User user = connector.getUserById(userId);
            model.addAttribute("userInfo",user);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editUser";
    }

    @RequestMapping("/updatePassword")
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
        return "../../include/updatePassword";
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
