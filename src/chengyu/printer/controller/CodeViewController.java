package chengyu.printer.controller;

import chengyu.printer.model.Code;
import chengyu.printer.model.DBConnector;
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
}
