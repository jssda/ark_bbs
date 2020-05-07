package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TSection;
import pers.jssd.ark.rpc.pojo.TSectionExample;

import java.util.List;

public interface TSectionMapper {
    long countByExample(TSectionExample example);

    int deleteByExample(TSectionExample example);

    int deleteByPrimaryKey(Integer secId);

    int insert(TSection record);

    int insertSelective(TSection record);

    List<TSection> selectByExample(TSectionExample example);

    TSection selectByPrimaryKey(Integer secId);

    int updateByExampleSelective(@Param("record") TSection record, @Param("example") TSectionExample example);

    int updateByExample(@Param("record") TSection record, @Param("example") TSectionExample example);

    int updateByPrimaryKeySelective(TSection record);

    int updateByPrimaryKey(TSection record);
}