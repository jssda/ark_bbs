package pers.jssd.ark.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TArticleExample;

import java.util.List;

public interface TArticleMapper {
    long countByExample(TArticleExample example);

    int deleteByExample(TArticleExample example);

    int deleteByPrimaryKey(Integer artId);

    int insert(TArticle record);

    int insertSelective(TArticle record);

    List<TArticle> selectByExampleWithBLOBs(TArticleExample example);

    List<TArticle> selectByExample(TArticleExample example);

    List<TArticle> selectByExampleAndOrderByCommentCount(TArticleExample example);

    TArticle selectByPrimaryKey(Integer artId);

    int updateByExampleSelective(@Param("record") TArticle record, @Param("example") TArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") TArticle record, @Param("example") TArticleExample example);

    int updateByExample(@Param("record") TArticle record, @Param("example") TArticleExample example);

    int updateByPrimaryKeySelective(TArticle record);

    int updateByPrimaryKeyWithBLOBs(TArticle record);

    int updateByPrimaryKey(TArticle record);
}