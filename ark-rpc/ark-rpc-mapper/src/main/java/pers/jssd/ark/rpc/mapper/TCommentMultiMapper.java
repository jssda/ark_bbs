package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TCommentMulti;
import pers.jssd.ark.rpc.pojo.TCommentMultiExample;

import java.util.List;

public interface TCommentMultiMapper {
    long countByExample(TCommentMultiExample example);

    int deleteByExample(TCommentMultiExample example);

    int deleteByPrimaryKey(Integer comMulId);

    int insert(TCommentMulti record);

    int insertSelective(TCommentMulti record);

    List<TCommentMulti> selectByExample(TCommentMultiExample example);

    TCommentMulti selectByPrimaryKey(Integer comMulId);

    int updateByExampleSelective(@Param("record") TCommentMulti record, @Param("example") TCommentMultiExample example);

    int updateByExample(@Param("record") TCommentMulti record, @Param("example") TCommentMultiExample example);

    int updateByPrimaryKeySelective(TCommentMulti record);

    int updateByPrimaryKey(TCommentMulti record);
}