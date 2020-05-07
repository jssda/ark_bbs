package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TArticleType;
import pers.jssd.ark.rpc.pojo.TArticleTypeExample;

import java.util.List;

public interface TArticleTypeMapper {
    long countByExample(TArticleTypeExample example);

    int deleteByExample(TArticleTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(TArticleType record);

    int insertSelective(TArticleType record);

    List<TArticleType> selectByExample(TArticleTypeExample example);

    TArticleType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") TArticleType record, @Param("example") TArticleTypeExample example);

    int updateByExample(@Param("record") TArticleType record, @Param("example") TArticleTypeExample example);

    int updateByPrimaryKeySelective(TArticleType record);

    int updateByPrimaryKey(TArticleType record);
}