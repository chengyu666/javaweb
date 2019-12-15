package chengyu.javaweb.controller;

import chengyu.javaweb.model.DBConnector;
import chengyu.javaweb.model.Printer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class PrinterViewController {
    Logger logger = Logger.getLogger("logger");
    DBConnector connector=new DBConnector();

    @RequestMapping("/addPrinter")
    public String addPrinter(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        String information=request.getParameter("information");
        try{
            boolean isSuccess=connector.addPrinter(code,information);
            if(isSuccess){
                model.addAttribute("uResultOk", "添加打印机信息成功!");
            }else {
                model.addAttribute("uResultFail","添加打印机信息失败，请重试");
            }
            List<Printer> list=connector.getAllPrinters();
            model.addAttribute("printerList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/managePrinters";
    }

    @RequestMapping("/editPrinterInfo")
    public String editPrinterInfo(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        String newInfo=request.getParameter("newInfo");
        logger.info("Updating Info of "+code+"\ninformation:"+newInfo);
        try{
            boolean isSuccess=connector.updatePrinterInfo(code,newInfo);
            if(isSuccess){
                model.addAttribute("descResultOk", "机器信息修改成功!");
            }else {
                model.addAttribute("descResultFail","机器信息修改失败，请重试");
            }
            Printer printerInfo = connector.getPrinterByCode(code);
            model.addAttribute("codeInfo", printerInfo);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editPrinter";
    }

    @RequestMapping("/searchPrinter")
    public String doSearchPrinter(HttpServletRequest request, Model model){
        //TODO
        String input=request.getParameter("input");
        logger.info("input code:"+input);
        try{
            logger.info("start searching...");
            Printer printer = connector.getPrinterByCode(input);
            model.addAttribute("printer", printer);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/searchPrinter";
    }

    @RequestMapping("/allPrinters")
    public String showAllPrinters(Model model){
        try{
            List<Printer> list=connector.getAllPrinters();
            model.addAttribute("printerList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/allPrinters";
    }

    @RequestMapping("/managePrinters")
    public String showManagePrinters(Model model){
        logger.info("in here");
        try{
            List<Printer> list=connector.getAllPrinters();
            //logger.info("printerlist:"+list);
            model.addAttribute("printerList", list);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/managePrinters";
    }

    @RequestMapping("/gotoEditPrinter")
    public String gotoEditPrinter(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        try{
            Printer printerInfo = connector.getPrinterByCode(code);
            model.addAttribute("printerInfo", printerInfo);
        }catch(SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/editPrinter";
    }

    @RequestMapping("/removePrinter")
    public String doRemovePrinter(HttpServletRequest request, Model model){
        String code=request.getParameter("code");
        try{
            boolean isSuccess=connector.removePrinterByCode(code);
            if(isSuccess){
                model.addAttribute("uResultOk", "删除成功");
            }else {
                model.addAttribute("uResultFail","删除失败");
            }
            List<Printer> list=connector.getAllPrinters();
            model.addAttribute("printerList", list);
        }catch (SQLException e){
            logger.warning(e.toString());
        }
        return "../../include/managePrinters";
    }
}
