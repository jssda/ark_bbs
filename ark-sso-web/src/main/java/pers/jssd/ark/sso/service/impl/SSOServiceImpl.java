package pers.jssd.ark.sso.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TUserInfoService;
import pers.jssd.ark.sso.service.SSOService;
import pers.jssd.ark.util.CookieUtils;
import pers.jssd.ark.util.JsonUtil;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 单点登录服务类
 *
 * @author jssdjing@gmail.com
 */
@Service
public class SSOServiceImpl implements SSOService {

    private final TUserInfoService userInfoService;
    private final JedisCluster jedisCluster;

    public SSOServiceImpl(TUserInfoService userInfoService, JedisCluster jedisCluster) {
        this.userInfoService = userInfoService;
        this.jedisCluster = jedisCluster;
    }

    @Override
    public ArkResult login(TUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        String userName = userInfo.getUserName();
        TUserInfo tUserInfo = userInfoService.selectUserInfoByUserName(userName);
        if (tUserInfo != null) {
            String password = userInfo.getPassword();
            // 对前端密码加密
            //            if (password != null) {
            //                password = DigestUtils.md5DigestAsHex(password.getBytes());
            //            }

            if (tUserInfo.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                String userStr = JsonUtil.objectToJson(tUserInfo);
                // 将用户信息存储得redis
                jedisCluster.set(token, userStr);
                jedisCluster.expire(token, 1800);

                // 将token信息响应到前端
                // 设置cookie
                CookieUtils.setCookie(request, response, "sso_token", token);
                ArkResult arkResult = new ArkResult(200, "用户登录成功");
                arkResult.setData(token);
                return arkResult;
            }
        }

        return new ArkResult(200, "用户登录失败");
    }


    @Override
    public ArkResult checkUserByToken(String token) {
        if (token == null || "".equals(token.trim())) {
            return new ArkResult(-1, "没用用户登录");
        }
        String s = jedisCluster.get(token);
        ArkResult arkResult = null;
        if (!StringUtils.isEmpty(s)) {
            TUserInfo user = JsonUtil.jsonToPojo(s, TUserInfo.class);
            if (user != null) {
                user.setPassword(null);
                arkResult = new ArkResult(200, "用户查询成功");
                arkResult.setData(user);
            }
        }
        arkResult = new ArkResult(-1, "error");

        return arkResult;
    }
}
