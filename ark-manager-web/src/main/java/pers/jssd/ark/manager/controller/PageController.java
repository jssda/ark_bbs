package pers.jssd.ark.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础页面转发器
 *
 * @author jssdjing@gmail.com
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(@RequestAttribute TUserInfo loginUser, HttpServletRequest request) {
        request.getSession().setAttribute("loginUser", loginUser);
        return "index";
    }
}
