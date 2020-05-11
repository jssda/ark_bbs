package pers.jssd.ark.sso.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录服务类
 *
 * @author jssdjing@gmail.com
 */
public interface SSOService {

    /**
     * 用户登录
     */
    ArkResult login(TUserInfo userInfo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过令牌检查用户是否登录
     */
    ArkResult checkUserByToken(String token);

    /**
     * 用户退出
     */
    ArkResult logout(String token);

    /**
     * 检查用户名是否重复
     *
     * @param userName 用户名
     * @return 返回响应结果
     */
    ArkResult checkUserName(String userName);

    /**
     * 检测邮箱是否注册
     */
    ArkResult checkEmail(String email);

    /**
     * 检查手机号是否注册
     */
    ArkResult checkTelephone(String telephone);

    /**
     * 用户注册
     */
    ArkResult register(TUserInfo userInfo);
}
