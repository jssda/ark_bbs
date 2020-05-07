package pers.jssd.ark.sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.sso.service.SSOService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录请求处理
 *
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/sso")
public class SSOController {

    private final SSOService ssoService;

    public SSOController(SSOService ssoService) {this.ssoService = ssoService;}


    /**
     * 用户登录请求
     */
    @RequestMapping("login")
    public ArkResult login(TUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {

        return ssoService.login(userInfo, request, response);
    }

    /**
     * 检查用户是否登录, 如果登录, 返回用户数据
     */
    @RequestMapping("checkUserByToken")
    public ArkResult checkUserByToken(String token) {
        return ssoService.checkUserByToken(token);
    }

}
