package chengyu.javaweb.controller;

import chengyu.javaweb.model.DBConnector;
import chengyu.javaweb.model.User;
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
    DBConnector connector = new DBConnector();

    @Value("${config.version}")
    String version;

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String money = request.getParameter("money");
        try {
            boolean isSuccess = connector.addUser(name, password, money);
            if (isSuccess) {
                model.addAttribute("uResultOk", "添加用户成功!");
            } else {
                model.addAttribute("uResultFail", "添加用户失败，请重试");
            }
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("uResultFail", "添加用户失败，请重试");
        }
        try {
            List<User> list = connector.getAllUsers();
            model.addAttribute("userList", list);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return "../../include/allUsers";
    }

    @RequestMapping("/signup")
    public String signup(HttpServletRequest request, Model model) {
        model.addAttribute("version", version); // 指定Model的值
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            boolean isSuccess = connector.addUser(name, password, "0");
            if (isSuccess) {
                model.addAttribute("loginResult", "添加用户成功!");
            } else {
                model.addAttribute("loginResult", "添加用户失败，请重试");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
            model.addAttribute("loginResult", "添加用户失败，请重试");
        }
        return "signup";
    }

    @RequestMapping("/editUserName")
    public String editUserName(HttpServletRequest request, Model model) {
        String idStr = request.getParameter("id");
        String newName = request.getParameter("newName");
        Integer id = Integer.valueOf(idStr);
        try {
            boolean isSuccess = connector.updateUserName(id, newName);
            if (isSuccess) {
                model.addAttribute("nameResultOk", "用户名修改成功!");
            } else {
                model.addAttribute("nameResultFail", "用户名修改失败，请重试");
            }
            User user = connector.getUserById(idStr);
            model.addAttribute("userInfo", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("nameResultFail", "用户名修改失败，请重试");
        }
        return "../../include/editUser";
    }

    @RequestMapping("/editUserMoney")
    public String editUserMoney(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String newMoney = request.getParameter("newMoney");
        try {
            boolean isSuccess = connector.updateMoney(id, newMoney);
            if (isSuccess) {
                model.addAttribute("moneyResultOk", "余额修改成功!");
            } else {
                model.addAttribute("moneyResultFail", "余额修改失败，请重试");
            }
            User user = connector.getUserById(id);
            model.addAttribute("userInfo", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("moneyResultFail", "余额修改失败，请重试");
        }
        return "../../include/editUser";
    }

    @RequestMapping("/recharge")
    public String rechargeMoney(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String moneyStr = request.getParameter("money");
        Integer money = Integer.valueOf(moneyStr);
        try {
            User user = connector.getUserById(id);
            Integer newMoney = user.getMoney() + money;
            boolean isSuccess = connector.updateMoney(id, newMoney.toString());
            if (isSuccess) {
                model.addAttribute("chargeOk", "余额充值成功!");
            } else {
                model.addAttribute("chargeFail", "余额充值失败，请重试");
            }
            model.addAttribute("userInfo", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("chargeFail", "余额充值失败，请重试");
        }
        return "../../include/recharge";
    }

    @RequestMapping("/gotoEditUser")
    public String gotoEditUser(HttpServletRequest request, Model model) {
        String userId = request.getParameter("id");
        try {
            User user = connector.getUserById(userId);
            logger.info(user.toString());
            model.addAttribute("userInfo", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return "../../include/editUser";
    }

    @RequestMapping("/updatePassword")
    public String doUpdatePassword(HttpServletRequest request, Model model) {
        String oldPassword = request.getParameter("oldp");
        String newPassword = request.getParameter("newp");
        String idStr = request.getParameter("id");
        Integer id = Integer.valueOf(idStr);
        logger.info("id:" + idStr +
                " old pass:" + oldPassword +
                " new pass:" + newPassword);
        try {
            boolean isSuccess = connector.updatePassword(id, oldPassword, newPassword);
            if (isSuccess) {
                model.addAttribute("uResult", "修改成功");
            } else {
                model.addAttribute("uResult", "修改失败，请重试");
            }
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("uResult", "修改失败，请重试");
        }
        return "../../include/updatePassword";
    }

    @RequestMapping("/removeUser")
    public String doRemoveUser(HttpServletRequest request, Model model) {
        String idStr = request.getParameter("id");
        Integer id = Integer.valueOf(idStr);
        try {
            boolean isSuccess = connector.removeUserById(id);
            if (isSuccess) {
                model.addAttribute("uResultOk", "删除成功");
            } else {
                model.addAttribute("uResultFail", "删除失败");
            }
            //TODO isSuccess is not fully used!
            List<User> list = connector.getAllUsers();
            model.addAttribute("userList", list);
        } catch (SQLException e) {
            logger.warning(e.toString());
            model.addAttribute("uResultFail", "删除失败");
        }
        return "../../include/allUsers";
    }

    @RequestMapping("/searchUser")
    public String doSearchUser(HttpServletRequest request, Model model) {
        String inputId = request.getParameter("input");
        logger.info("input id:" + inputId);
        try {
            logger.info("start searching...");
            User user = connector.getUserById(inputId);
            model.addAttribute("userInfo", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return "../../include/searchUser";
    }

    @RequestMapping("/allUsers")
    public String showAllUsers(Model model) {
        try {
            List<User> list = connector.getAllUsers();
            model.addAttribute("userList", list);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return "../../include/allUsers";
    }
}
