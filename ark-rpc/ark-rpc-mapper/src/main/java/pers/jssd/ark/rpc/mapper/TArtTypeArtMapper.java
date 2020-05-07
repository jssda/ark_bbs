package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TArtTypeArt;
import pers.jssd.ark.rpc.pojo.TArtTypeArtExample;

import java.util.List;

public interface TArtTypeArtMapper {
    long countByExample(TArtTypeArtExample example);

    int deleteByExample(TArtTypeArtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TArtTypeArt record);

    int insertSelective(TArtTypeArt record);

    List<TArtTypeArt> selectByExample(TArtTypeArtExample example);

    TArtTypeArt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TArtTypeArt record, @Param("example") TArtTypeArtExample example);

    int updateByExample(@Param("record") TArtTypeArt record, @Param("example") TArtTypeArtExample example);

    int updateByPrimaryKeySelective(TArtTypeArt record);

    int updateByPrimaryKey(TArtTypeArt record);
}