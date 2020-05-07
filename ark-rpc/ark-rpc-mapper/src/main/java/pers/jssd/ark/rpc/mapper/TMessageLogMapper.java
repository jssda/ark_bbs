package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TMessageLog;
import pers.jssd.ark.rpc.pojo.TMessageLogExample;

import java.util.List;

public interface TMessageLogMapper {
    long countByExample(TMessageLogExample example);

    int deleteByExample(TMessageLogExample example);

    int deleteByPrimaryKey(Integer mesLogId);

    int insert(TMessageLog record);

    int insertSelective(TMessageLog record);

    List<TMessageLog> selectByExample(TMessageLogExample example);

    TMessageLog selectByPrimaryKey(Integer mesLogId);

    int updateByExampleSelective(@Param("record") TMessageLog record, @Param("example") TMessageLogExample example);

    int updateByExample(@Param("record") TMessageLog record, @Param("example") TMessageLogExample example);

    int updateByPrimaryKeySelective(TMessageLog record);

    int updateByPrimaryKey(TMessageLog record);
}