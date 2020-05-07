package pers.jssd.ark.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerUserInfoService;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import java.util.ArrayList;

/**
 * 用户信息控制器
 *
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/manager/user")
public class UserInfoController {

    private final ManagerUserInfoService userInfoService;

    public UserInfoController(ManagerUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    /**
     * 分页查询所有用户
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @return 返回分页查询到的数据json格式
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableResult listUsers(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {

        return userInfoService.showUsers(page, limit);
    }

    /**
     * 带条件分页查询用户的信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @param key   查询的key
     * @return 返回分页的json数据
     */
    @RequestMapping(value = "listby", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableResult listUsersBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "") String key) {
        return userInfoService.showUsersBy(page, limit, key);
    }

    /**
     * 带条件不分页查询用户的信息
     *
     * @param key 查询的key
     * @return 返回分页的json数据
     */
    @RequestMapping(value = "queryBy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult listUsersBy(@RequestParam(defaultValue = "") String key) {
        return userInfoService.showUsersBy(key);
    }


    /**
     * 添加或更改用户
     *
     * @param userInfo 用户信息
     */
    @RequestMapping(value = "addOrEditUser", method = RequestMethod.POST)
    @ResponseBody
    public ArkResult addOrEditUser(@RequestBody TUserInfo userInfo) {
        return userInfoService.addOrEditUser(userInfo);
    }

    /**
     * 删除一个用户
     *
     * @param userId 删除的用户id
     * @return 是否删除成功的响应对象
     */
    @RequestMapping(value = "removeUser/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ArkResult removeUser(@PathVariable Integer userId) {
        return userInfoService.removeUser(userId);
    }

    /**
     * 删除多个用户
     *
     * @param usersId 删除的用户id
     * @return 是否删除成功的响应对象
     */
    @RequestMapping(value = "removeUsers", method = RequestMethod.POST)
    @ResponseBody
    public ArkResult removeUser(@RequestParam("usersId") ArrayList<Integer> usersId) {
        return userInfoService.removeUsers(usersId);
    }

}