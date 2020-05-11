package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import pers.jssd.ark.rpc.mapper.TFollowMapper;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.TFollow;
import pers.jssd.ark.rpc.pojo.TFollowExample;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TFollowService;
import pers.jssd.ark.util.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TFollowServiceImpl implements TFollowService {

    private final TFollowMapper followMapper;
    private final TUserInfoMapper userInfoMapper;

    public TFollowServiceImpl(TFollowMapper followMapper, TUserInfoMapper userInfoMapper) {
        this.followMapper = followMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int addFollow(Integer loginUserId, Integer userId) {
        // 查询是否有关注关系
        TFollowExample followExample = new TFollowExample();
        TFollowExample.Criteria followExampleCriteria = followExample.createCriteria();
        followExampleCriteria.andFollowUserIdEqualTo(loginUserId);
        List<TFollow> tFollows = followMapper.selectByExample(followExample);
        if (tFollows != null && tFollows.size() != 0) { // 有粉丝关系, 改变关系即可
            TFollow follow = tFollows.get(0);
            String followUserJson = follow.getFollowUser();
            List<TUserInfo> tUserInfos = null;
            if (!StringUtil.isEmpty(followUserJson)) {
                tUserInfos = JsonUtil.jsonToList(followUserJson, TUserInfo.class);
            }

            System.out.println("userInfos = " + tUserInfos);

            // 查询要关注的用户信息
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if (tUserInfos != null) {
                tUserInfos.add(userInfo);
            } else {
                tUserInfos = new ArrayList<>();
                tUserInfos.add(userInfo);
            }
            String newJson = JsonUtil.objectToJson(tUserInfos);
            follow.setFollowUser(newJson);

            follow.setFollowedNum(follow.getFollowedNum() + 1);
            // 添加粉丝关系
            addFollowr(userId, loginUserId);

            // 更新数据
            return followMapper.updateByPrimaryKey(follow);
        } else { // 没有粉丝关系, 添加一个粉丝关系
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

            TFollow follow = new TFollow();
            follow.setFollowUserId(loginUserId);
            ArrayList<TUserInfo> followedUser = new ArrayList<>();
            followedUser.add(userInfo);
            String followedJson = JsonUtil.objectToJson(followedUser);
            follow.setFollowr("");
            follow.setFollowedNum(1);
            follow.setFollowrNum(0);
            follow.setFollowUser(followedJson);
            follow.setCreate(new Date());

            addFollowr(userId, loginUserId);
            return followMapper.insertSelective(follow);
        }
    }

    /**
     * 添加一个粉丝关系
     */
    private int addFollowr(Integer loginUserId, Integer userId) {
        // 查询是否有关注关系
        TFollowExample followExample = new TFollowExample();
        TFollowExample.Criteria followExampleCriteria = followExample.createCriteria();
        followExampleCriteria.andFollowUserIdEqualTo(loginUserId);
        List<TFollow> tFollows = followMapper.selectByExample(followExample);
        if (tFollows != null && tFollows.size() != 0) { // 有粉丝关系, 改变关系即可
            TFollow follow = tFollows.get(0);
            String followrJson = follow.getFollowr();
            List<TUserInfo> tUserInfos = null;
            if (!StringUtil.isEmpty(followrJson)) {
                tUserInfos = JsonUtil.jsonToList(followrJson, TUserInfo.class);
            }
            // 查询添加的粉丝
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if (tUserInfos == null) {
                tUserInfos = new ArrayList<>();
                tUserInfos.add(userInfo);
            } else {
                tUserInfos.add(userInfo);
            }
            String newJson = JsonUtil.objectToJson(tUserInfos);
            follow.setFollowr(newJson);
            follow.setFollowrNum(follow.getFollowrNum() + 1);
            // 更新数据
            return followMapper.updateByPrimaryKey(follow);
        } else { // 添加一个粉丝关系
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

            TFollow follow = new TFollow();
            follow.setFollowUserId(loginUserId);
            ArrayList<TUserInfo> followr = new ArrayList<>();
            followr.add(userInfo);
            String followrJson = JsonUtil.objectToJson(followr);
            follow.setFollowr(followrJson);
            follow.setFollowedNum(0);
            follow.setFollowrNum(1);
            follow.setFollowUser("");
            follow.setCreate(new Date());

            return followMapper.insertSelective(follow);
        }
    }

    @Override
    public boolean isFollow(Integer loginUserId, Integer userId) {
        TFollowExample followExample = new TFollowExample();
        TFollowExample.Criteria criteria = followExample.createCriteria();
        criteria.andFollowUserIdEqualTo(loginUserId);
        List<TFollow> tFollows = followMapper.selectByExample(followExample);
        if (tFollows == null || tFollows.size() == 0) {
            return false;
        } else {
            TFollow follow = tFollows.get(0);
            String followUserJson = follow.getFollowUser();
            if (followUserJson == null) {
                return false;
            }
            List<TUserInfo> tUserInfos = JsonUtil.jsonToList(followUserJson, TUserInfo.class);
            if (tUserInfos == null) {
                return false;
            }
            for (TUserInfo tUserInfo : tUserInfos) {
                if (tUserInfo.getUserId().equals(userId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
