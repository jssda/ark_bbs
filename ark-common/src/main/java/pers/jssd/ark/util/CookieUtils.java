package pers.jssd.ark.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * Cookie 工具类
 */
public final class CookieUtils {

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request    请求对象
     * @param cookieName cookie名字
     * @return 取得cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 到Cookie的值,
     *
     * @param request    请求对象
     * @param cookieName cookie名字
     * @param isDecoder  是否进行utf8编码
     * @return 返回编程之后cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值, 使用指定的编码方式
     *
     * @param request      请求对象
     * @param cookieName   cookie名字
     * @param encodeString 使用什么方式编码
     * @return 返回cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookie.getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     *
     * @param request     请求对象
     * @param response    响应对象
     * @param cookieName  cookie的名字
     * @param cookieValue cookie的值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param cookieName   cookie的名字
     * @param cookieValue  cookie的值
     * @param cookieMaxAge 设置cookie的存活时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxAge) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     *
     * @param request     请求对象
     * @param response    响应对象
     * @param cookieName  cookie的名字
     * @param cookieValue cookie的值
     * @param isEncode    是否进行编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param cookieName   cookie的名字
     * @param cookieValue  cookie的值
     * @param cookieMaxAge cookie的存活时间
     * @param isEncode     是否进行编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxAge, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param cookieName   cookie的名字
     * @param cookieValue  cookie的值
     * @param cookieMaxAge cookie的存活时间
     * @param encodeString 指定cookie使用什么方式进行编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxAge, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeString);
    }

    /**
     * 删除Cookie
     *
     * @param request    请求对象
     * @param response   响应对象
     * @param cookieName cookie的名字
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param cookieName   cookie的名字
     * @param cookieValue  cookie的值
     * @param cookieMaxAge cookie的存活时间
     * @param isEncode     是否进行编码
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode) {
        if (isEncode) {
            doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, "utf-8");
        } else {
            doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, null);
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param cookieName   cookie的名字
     * @param cookieValue  cookie的值
     * @param cookieMaxAge cookie的存活时间
     * @param encodeString 使用什么方式进行编码
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxAge, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                if (encodeString != null) {
                    cookieValue = URLEncoder.encode(cookieValue, encodeString);
                }
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge > 0) {
                cookie.setMaxAge(cookieMaxAge);
            }
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;

        String serverName = request.getRequestURL().toString();
        if (serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");  //http://www.ego.com/
            serverName = serverName.substring(0, end);


            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

}
