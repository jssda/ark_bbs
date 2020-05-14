package pers.jssd.ark.manager.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.util.CookieUtils;
import pers.jssd.ark.util.JsonUtil;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器, 如果用户没有登录, 重定向到登录页面
 *
 * @author jssdjing@gmail.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JedisCluster cluster;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sso_token = CookieUtils.getCookieValue(request, "sso_token");
        // 如果取得了token, 证明客户端有cookie存在
        if (!StringUtils.isEmpty(sso_token)) {
            String userJson = cluster.get(sso_token);
            // 如果能够取得用户信息, 那么, 此用户已经登录了
            if (!StringUtils.isEmpty(userJson)) {
                TUserInfo userInfo = JsonUtil.jsonToPojo(userJson, TUserInfo.class);
                if ("2".equals(userInfo.getUserLevel())) {
                    request.setAttribute("loginUser", userInfo);
                    // 登录成功, 应该放行
                    return true;
                }
            }
        }
        // 如果没有登录. 需要访问sso进行登录. 同时, 在sso登录成功之后, 需要重定向到此次想要访问的页面
        //重定向到登录页面. 并且将登录成功之后的跳转页面作为参数传递给登录请求处理器
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            // 告诉ajax我重定向的路径
            response.setHeader("redirectUrl", "http://localhost:8083/html/login/login.html?redirectUrl=http://localhost:8080");
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
            response.sendRedirect("http://localhost:8083/html/login/login.html?redirectUrl=http://localhost:8080");
        }
        return false;
    }
}
