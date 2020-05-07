package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.pojo.TUserInfoExample;

import java.util.List;

public interface TUserInfoMapper {
    long countByExample(TUserInfoExample example);

    int deleteByExample(TUserInfoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TUserInfo record);

    int insertSelective(TUserInfo record);

    List<TUserInfo> selectByExample(TUserInfoExample example);

    TUserInfo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByExample(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByPrimaryKeySelective(TUserInfo record);

    int updateByPrimaryKey(TUserInfo record);
}