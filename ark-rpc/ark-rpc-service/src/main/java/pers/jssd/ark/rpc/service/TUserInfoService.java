package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface TUserInfoService {

    /**
     * 通过分页数据， 分页查询数据库中的数据
     *
     * @param pageNum 分页对象
     * @return 返回分页数据
     */
    PageInfo<TUserInfo> selectUserInfoByPageNum(PageNum pageNum);

    /**
     * 有条件的分页查询信息
     *
     * @param pageNum 分页信息
     * @param key     查询条件
     * @return 返回分页数据
     */
    PageInfo<TUserInfo> selectUserInfoByPageNumAndKey(PageNum pageNum, String key);

    /**
     * 更新用户
     *
     * @param userInfo 更新的用户信息, 如果字段为null, 那么这个字段不会更新
     * @return 返回更新了几个用户
     */
    int updateUserInfo(TUserInfo userInfo);

    /**
     * 添加一个用户
     *
     * @param userInfo 添加的用户信息
     * @return 返回添加的用户id, 如果是null, 那么添加失败
     */
    Integer addUserInfo(TUserInfo userInfo);

    /**
     * 删除一个用户
     *
     * @param userId 用户id
     * @return 返回删除了几条用户
     */
    int deleteUserInfo(Integer userId);

    /**
     * 删除多个用户
     *
     * @param usersId 用户的id容器
     * @return 返回删除的用户个数
     */
    int deleteUserInfos(ArrayList<Integer> usersId);

    /**
     * 带条件的查询用户(姓名模糊查询)
     *
     * @param key 查询用户的姓名
     * @return 返回查询到的用户容器
     */
    List<TUserInfo> selectUserInfoByKey(String key);

    /**
     * 通过用户id 查询用户
     */
    TUserInfo selectUserInfoByUserId(Integer userId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 返回查询到的用户
     */
    TUserInfo selectUserInfoByUserName(String userName);
}
