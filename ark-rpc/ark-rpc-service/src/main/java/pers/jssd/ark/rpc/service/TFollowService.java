package pers.jssd.ark.rpc.service;

/**
 * @author jssdjing@gmail.com
 */
public interface TFollowService {

    /**
     * 添加一个关注用户
     */
    int addFollow(Integer loginUserId, Integer userId);

    /**
     * 用户已经被关注, 返回true
     */
    boolean isFollow(Integer loginUserId, Integer userId);
}
