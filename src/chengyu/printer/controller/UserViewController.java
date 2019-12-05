package chengyu.printer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class UserViewController {
    @Value("${config.version}")
    String version;


}
