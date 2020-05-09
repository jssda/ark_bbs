package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerUserInfoService;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TUserInfoService;
import pers.jssd.ark.util.PageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerUserInfoServiceImpl implements ManagerUserInfoService {

    private final TUserInfoService tUserInfoService;

    public ManagerUserInfoServiceImpl(TUserInfoService tUserInfoService) {
        this.tUserInfoService = tUserInfoService;
    }

    @Override
    public TableResult showUsers(Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        // 从远程服务类中查询分类信息
        PageInfo<TUserInfo> tUserInfoPageInfo = tUserInfoService.selectUserInfoByPageNum(pageNum);

        // 将分页查询结果封装成表格查询结果返回
        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tUserInfoPageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tUserInfoPageInfo.getList());

        return tableResult;
    }

    @Override
    public TableResult showUsersBy(Integer page, Integer limit, String key) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TUserInfo> tUserInfoPageInfo = tUserInfoService.selectUserInfoByPageNumAndKey(pageNum, key);

        // 将分页查询结果封装成表格查询结果返回
        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tUserInfoPageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tUserInfoPageInfo.getList());

        return tableResult;
    }

    @Override
    public ArkResult showUsersBy(String key) {
        List<TUserInfo> tUserInfos = tUserInfoService.selectUserInfoByKey(key);
        ArkResult arkResult = new ArkResult(200, "用户查询成功");
        arkResult.setData(tUserInfos);

        return arkResult;
    }

    @Override
    public ArkResult addOrEditUser(TUserInfo userInfo) {
        Integer userId = userInfo.getUserId();
        if (userId != null) {
            int i = tUserInfoService.updateUserInfo(userInfo);
            if (i != 0) {
                return new ArkResult(200, "更新用户成功");
            } else {
                return new ArkResult(-1, "更新用户失败");
            }
        } else {
            userInfo.setUsed("0");
            userInfo.setCreate(new Date());
            Integer addUserId = tUserInfoService.addUserInfo(userInfo);
            if (addUserId != 0) {
                return new ArkResult(200, "添加用户成功");
            } else {
                return new ArkResult(-1, "添加用户失败");
            }
        }
    }

    @Override
    public ArkResult removeUser(Integer userId) {
        if (userId == null) {
            return new ArkResult(-1, "用户id为空");
        }

        int i = tUserInfoService.deleteUserInfo(userId);
        if (i == 0) {
            return new ArkResult(-1, "删除用户失败");
        } else {
            return new ArkResult(200, "删除用户成功");
        }
    }

    @Override
    public ArkResult removeUsers(ArrayList<Integer> usersId) {
        int i = tUserInfoService.deleteUserInfos(usersId);
        if (i == 0) {
            return new ArkResult(-1, "没有删除用户");
        } else {
            return new ArkResult(200, "删除用户成功");
        }
    }

}
