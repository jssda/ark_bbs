package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TFollow;
import pers.jssd.ark.rpc.pojo.TFollowExample;

import java.util.List;

public interface TFollowMapper {
    long countByExample(TFollowExample example);

    int deleteByExample(TFollowExample example);

    int deleteByPrimaryKey(Integer followId);

    int insert(TFollow record);

    int insertSelective(TFollow record);

    List<TFollow> selectByExampleWithBLOBs(TFollowExample example);

    List<TFollow> selectByExample(TFollowExample example);

    TFollow selectByPrimaryKey(Integer followId);

    int updateByExampleSelective(@Param("record") TFollow record, @Param("example") TFollowExample example);

    int updateByExampleWithBLOBs(@Param("record") TFollow record, @Param("example") TFollowExample example);

    int updateByExample(@Param("record") TFollow record, @Param("example") TFollowExample example);

    int updateByPrimaryKeySelective(TFollow record);

    int updateByPrimaryKeyWithBLOBs(TFollow record);

    int updateByPrimaryKey(TFollow record);
}