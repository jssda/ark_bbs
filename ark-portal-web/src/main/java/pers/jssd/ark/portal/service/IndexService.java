package pers.jssd.ark.portal.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端板块服务类
 *
 * @author jssdjing@gmail.com
 */
public interface IndexService {

    /**
     * 分页查询板块信息
     */
    PageResult listSectionByPage(Integer page, Integer limit);

    /**
     * 分页查询指定板块的文章信息
     *
     * @param secId 板块id
     * @return 返回查询到的文章信息分页数据
     */
    PageResult listArticleBySecId(Integer secId, Integer page, Integer limit);

    /**
     * 通过secId获取板块信息
     *
     * @param secId 板块id
     * @return 返回查询到的板块信息
     */
    ArkResult getSectionBySecId(Integer secId);

    /**
     * 通过userId查询用户信息
     */
    ArkResult getUserInfoByUserId(Integer userId);

    /**
     * 通过文章id查询文章
     */
    ArkResult getArticleByArtId(Integer artId);

    /**
     * 通过文章id查询一级评论信息
     */
    PageResult listCommentByArtId(Integer artId, Integer page, Integer limit);

    /**
     * 通过文章id, 查询此文章具有的类型
     */
    ArkResult listTypeByArtId(Integer artId);

    /**
     * 通过一级评论id, 分页查询多级评论信息
     */
    PageResult listCommentMultiByPageNumAndComId(Integer comId, Integer page, Integer limit);

    /**
     * 添加一个评论信息
     *
     * @param request 请求对象
     */
    ArkResult addComment(HttpServletRequest request);

    /**
     * 查询用户近期发布的帖子
     */
    PageResult listArticleByUserIdAndPageNum(Integer userId, Integer page, Integer limit);

    /**
     * 收藏一篇文章
     */
    ArkResult collectionThis(Integer artId, TUserInfo loginUser);

    /**
     * 查看文章是否被收藏
     */
    ArkResult isCollection(Integer artId, Integer userId);

    ArkResult querySectionBy(String key);

    TableResult listSection(Integer page, Integer limit);

    ArkResult addArticle(TArticle article, TUserInfo loginUser);
}
