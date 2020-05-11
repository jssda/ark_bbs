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
import java.util.Date;
import java.util.List;
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

    public SSOServiceImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") TUserInfoService userInfoService, JedisCluster jedisCluster) {
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
                CookieUtils.setCookie(request, response, "sso_token", token, 1800);
                ArkResult arkResult = new ArkResult(200, "用户登录成功");
                arkResult.setData(token);
                return arkResult;
            }
        }

        return new ArkResult(-1, "用户登录失败");
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
                return arkResult;
            }
        }
        arkResult = new ArkResult(-1, "没有查找到任何用户");

        return arkResult;
    }


    @Override
    public ArkResult logout(String token) {
        ArkResult arkResult = null;

        Long del = jedisCluster.del(token);
        if (!del.equals(0L)) {
            arkResult = new ArkResult(200, "退出成功");
            return arkResult;
        }

        return new ArkResult(-1, "退出失败");
    }

    @Override
    public ArkResult checkUserName(String userName) {
        ArkResult arkResult = null;
        if (userName == null) {
            arkResult = new ArkResult(-1,"用户名为空");
        } else {
            TUserInfo userInfo = userInfoService.selectUserInfoByUserName(userName);
            if (userInfo != null) {
                arkResult = new ArkResult(-1, "用户名重复");
            } else {
                arkResult = new ArkResult(200, "用户名正常");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult checkEmail(String email) {
        ArkResult arkResult = null;
        if (email == null) {
            arkResult = new ArkResult(-1,"邮箱为空");
        } else {
            List<TUserInfo> userInfos = userInfoService.selectUserInfoByEmail(email);
            if (userInfos == null || userInfos.size() > 0) {
                arkResult = new ArkResult(-1, "邮箱已注册");
            } else {
                arkResult = new ArkResult(200, "邮箱可使用");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult checkTelephone(String telephone) {
        ArkResult arkResult = null;
        if (telephone == null) {
            arkResult = new ArkResult(-1,"手机号为空");
        } else {
            List<TUserInfo> userInfos = userInfoService.selectUserInfoByTelephone(telephone);
            if (userInfos == null || userInfos.size() > 0) {
                arkResult = new ArkResult(-1, "手机号已注册");
            } else {
                arkResult = new ArkResult(200, "手机号可使用");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult register(TUserInfo userInfo) {
        ArkResult arkResult = null;
        if (userInfo == null) {
            arkResult = new ArkResult(-1, "用户注册失败");
        } else {
            userInfo.setUserSex("1");
            userInfo.setCredit(1);
            userInfo.setUserLevel("1");
            userInfo.setUsed("0");
            userInfo.setCreate(new Date());
            int i = userInfoService.addUserInfo(userInfo);
            if (i == 0) {
                arkResult = new ArkResult(-1, "用户注册失败");
            } else {
                arkResult = new ArkResult(200, "用户注册成功");
            }
        }

        return arkResult;
    }
}
