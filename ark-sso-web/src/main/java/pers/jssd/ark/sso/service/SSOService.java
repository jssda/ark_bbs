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
}
