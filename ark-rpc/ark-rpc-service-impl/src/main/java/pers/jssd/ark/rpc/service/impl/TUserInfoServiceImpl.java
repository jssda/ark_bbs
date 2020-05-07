package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.pojo.TUserInfoExample;
import pers.jssd.ark.rpc.service.TUserInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TUserInfoServiceImpl implements TUserInfoService {

    private final Logger logger = Logger.getLogger(TUserInfoServiceImpl.class);

    private final TUserInfoMapper userInfoMapper;

    @Autowired
    public TUserInfoServiceImpl(TUserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public PageInfo<TUserInfo> selectUserInfoByPageNum(PageNum pageNum) {

        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());

        TUserInfoExample userInfoExample = new TUserInfoExample();

        List<TUserInfo> tUserInfos = userInfoMapper.selectByExample(userInfoExample);

        return new PageInfo<TUserInfo>(tUserInfos);
    }

    @Override
    public PageInfo<TUserInfo> selectUserInfoByPageNumAndKey(PageNum pageNum, String key) {
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());

        // 模糊查询, 包含此字段
        key = "%" + key + "%";

        TUserInfoExample userInfoExample = new TUserInfoExample();

        TUserInfoExample.Criteria criteria1 = userInfoExample.createCriteria();
        criteria1.andUserNameLike(key);
        userInfoExample.or(criteria1);

        TUserInfoExample.Criteria criteria2 = userInfoExample.createCriteria();
        criteria2.andUserShowLike(key);
        userInfoExample.or(criteria2);

        TUserInfoExample.Criteria criteria3 = userInfoExample.createCriteria();
        criteria3.andTelephoneLike(key);
        userInfoExample.or(criteria3);

        TUserInfoExample.Criteria criteria4 = userInfoExample.createCriteria();
        criteria4.andEmailLike(key);
        userInfoExample.or(criteria4);

        List<TUserInfo> tUserInfos = userInfoMapper.selectByExample(userInfoExample);

        for (TUserInfo tUserInfo : tUserInfos) {
            logger.debug(tUserInfo);
        }

        return new PageInfo<TUserInfo>(tUserInfos);
    }

    @Override
    public List<TUserInfo> selectUserInfoByKey(String key) {
        // 模糊查询, 包含此字段
        key = "%" + key + "%";

        TUserInfoExample userInfoExample = new TUserInfoExample();

        TUserInfoExample.Criteria criteria1 = userInfoExample.createCriteria();
        criteria1.andUserNameLike(key);

        return userInfoMapper.selectByExample(userInfoExample);
    }

    @Override
    public TUserInfo selectUserInfoByUserId(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public TUserInfo selectUserInfoByUserName(String userName) {
        TUserInfoExample userInfoExample = new TUserInfoExample();
        TUserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<TUserInfo> tUserInfos = userInfoMapper.selectByExample(userInfoExample);
        if (tUserInfos != null) {
            return tUserInfos.get(0);
        }
        return null;
    }

    @Override
    public int updateUserInfo(TUserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public Integer addUserInfo(TUserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
        return userInfo.getUserId();
    }

    @Override
    public int deleteUserInfo(Integer userId) {
        return userInfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int deleteUserInfos(ArrayList<Integer> usersId) {
        TUserInfoExample userInfoExample = new TUserInfoExample();
        TUserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUserIdIn(usersId);

        return userInfoMapper.deleteByExample(userInfoExample);
    }
}
