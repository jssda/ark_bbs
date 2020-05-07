package pers.jssd.ark.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础页面转发器
 *
 * @author jssdjing@gmail.com
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

   /* @RequestMapping("/{path}")
    public String showPage(@PathVariable String path) {
        return path;
    }*/
    
}
