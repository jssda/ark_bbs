package pers.jssd.ark.sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.sso.service.SSOService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Area;

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

    /**
     * 用户退出
     */
    @RequestMapping("logout")
    public ArkResult logout(String token) {
        return ssoService.logout(token);
    }

    /**
     * 检查用户名是否重复
     *
     * @param userName 用户名
     * @return 返回响应结果
     */
    @RequestMapping("checkUserName")
    public ArkResult checkUserName(String userName) {
        return ssoService.checkUserName(userName);
    }

    /**
     * 检测邮箱是否注册
     *
     * @param email 邮箱
     * @return 返回响应结果
     */
    @RequestMapping("checkEmail")
    public ArkResult checkEmail(String email) {
        return ssoService.checkEmail(email);
    }

    /**
     * 检查手机号是否注册
     *
     * @param telephone 手机号
     * @return 返回响应信息
     */
    @RequestMapping("checkTelephone")
    public ArkResult checkTelephone(String telephone) {
        return ssoService.checkTelephone(telephone);
    }

    /**
     * 用户注册
     */
    @RequestMapping("register")
    public ArkResult register(TUserInfo userInfo) {
        return ssoService.register(userInfo);
    }

}
