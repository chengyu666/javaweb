package chengyu.javaweb.controller;

import chengyu.javaweb.model.Code;
import chengyu.javaweb.model.DBConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CodeViewController {
    Logger logger = Logger.getLogger("logger");
    DBConnector connector=new DBConnector();

    @RequestMapping("/addCode")
    public String addCode(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        String message=request.getParameter("message");
        try{
            boolean isSuccess=connector.addCode(code,message);
            if(isSuccess){
                model.addAttribute("uResultOk", "添加错误代码成功!");
            }else {
                model.addAttribute("uResultFail","添加错误代码失败，请重试");
            }
            List<Code> list=connector.getAllCodes();
            model.addAttribute("codeList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/manageCodes";
    }

    @RequestMapping("/editCodeMessage")
    public String editCodeMessage(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        String newMessage=request.getParameter("newMessage");
        logger.info("in Edit Message of"+code+"\nmessage:"+newMessage);
        try{
            boolean isSuccess=connector.updateCodeMessage(code,newMessage);
            if(isSuccess){
                model.addAttribute("descResultOk", "错误代码修改成功!");
            }else {
                model.addAttribute("descResultFail","错误代码修改失败，请重试");
            }
            Code codeInfo = connector.getInfoByCode(code);
            model.addAttribute("codeInfo",codeInfo);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editCode";
    }

    @RequestMapping("/searchCode")
    public String doSearchCode(HttpServletRequest request, Model model){
        //TODO
        String inputCode=request.getParameter("input");
        logger.info("input code:"+inputCode);
        try{
            logger.info("start searching...");
            Code code = connector.getInfoByCode(inputCode);
            model.addAttribute("code",code);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/searchCode";
    }

    @RequestMapping("/allCodes")
    public String showAllCodes(Model model){
        try{
            List<Code> list=connector.getAllCodes();
            model.addAttribute("codeList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/allCodes";
    }

    @RequestMapping("/manageCodes")
    public String showManageCodes(Model model){
        logger.info("in here");
        try{
            List<Code> list=connector.getAllCodes();
            //logger.info("codelist:"+list);
            model.addAttribute("codeList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/manageCodes";
    }

    @RequestMapping("/gotoEditCode")
    public String gotoEditCode(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        try{
            Code codeInfo = connector.getInfoByCode(code);
            model.addAttribute("codeInfo",codeInfo);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editCode";
    }

    @RequestMapping("/removeCode")
    public String doRemoveCode(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        try{
            boolean isSuccess=connector.removeCodeByCode(code);
            if(isSuccess){
                model.addAttribute("uResultOk", "删除成功");
            }else {
                model.addAttribute("uResultFail","删除失败");
            }
            List<Code> list=connector.getAllCodes();
            model.addAttribute("codeList", list);
        }catch (SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/manageCodes";
    }
}
