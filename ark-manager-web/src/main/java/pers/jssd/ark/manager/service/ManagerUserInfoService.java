package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import java.util.ArrayList;

/**
 * 用户信息的业务类
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerUserInfoService {

    /**
     * 展示所有用户
     *
     * @param page  第几页的分页信息
     * @param limit 每页多少条记录
     * @return 返回查询到的分页用户信息
     */
    TableResult showUsers(Integer page, Integer limit);

    /**
     * 有条件的分页查询用户
     *
     * @param page  第几页数据
     * @param limit 每页显示多少条数据
     * @param key   查询条件
     * @return 返回查询到的分页用户数据
     */
    TableResult showUsersBy(Integer page, Integer limit, String key);

    /**
     * 添加或者更改一个用户
     *
     * @param userInfo 用户信息
     * @return 返回一个响应信息
     */
    ArkResult addOrEditUser(TUserInfo userInfo);

    /**
     * 删除用户
     *
     * @param userId 需要删除的用户id
     * @return 返回相应对象
     */
    ArkResult removeUser(Integer userId);

    /**
     * 删除多个用户
     *
     * @param usersId 删除的用户容器
     * @return 返回响应信息
     */
    ArkResult removeUsers(ArrayList<Integer> usersId);

    /**
     * 有条件的查询用户, 不分页
     *
     * @param key 查询的用户姓名(模糊查询)
     * @return 返回查询到的用户信息
     */
    ArkResult showUsersBy(String key);
}
