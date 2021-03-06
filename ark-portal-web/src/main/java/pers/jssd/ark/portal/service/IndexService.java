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

    /**
     * 查询板块信息
     */
    ArkResult querySectionBy(String key);

    /**
     * 分页展示板块信息
     */
    TableResult listSection(Integer page, Integer limit);

    /**
     * 添加一篇文章
     */
    ArkResult addArticle(TArticle article, TUserInfo loginUser);

    /**
     * 分页查询置顶文章
     */
    PageResult listArticleByTopAndPage(Integer page, Integer limit);

    /**
     * 查询评论最多的10篇文章
     *
     * @param secId 板块id
     */
    PageResult mostCommentBySecId(Integer secId);

    /**
     * 查询热度最多的10篇文章
     *
     * @param secId 板块id
     */
    PageResult mostHotBySecId(Integer secId);

    /**
     * 查询登录用户收藏的文章
     */
    PageResult listCollectionByUserIdAndPageNum(TUserInfo loginUser, Integer page, Integer limit);

    /**
     * 修改用户信息
     *
     * @param userInfo 需要修改的用户信息
     * @return 返回是否修改成功的响应信息
     */
    ArkResult modifyUserInfo(TUserInfo userInfo);

    /**
     * 修改密码
     *
     * @param loginUser 当前登录用户
     * @param nowPass   当前密码
     * @param pass      修改密码
     */
    ArkResult modifyPass(TUserInfo loginUser, String nowPass, String pass);

    /**
     * 取消收藏这个文章
     */
    ArkResult unCollection(TUserInfo loginUser, Integer colId);

    /**
     * 置顶一篇文章
     */
    ArkResult topThis(Integer artId);

    /**
     * 取消置顶
     */
    ArkResult unTopThis(Integer artId);

    /**
     * 删除指定的文章
     */
    ArkResult delThis(Integer artId);

    /**
     * 分页列出最近的文章信息
     */
    PageResult listArticleByCreate(Integer page, Integer limit);

    /**
     * 查询;用户未读消息
     */
    ArkResult listMessageByUserId(TUserInfo loginUser);

    /**
     * 删除一个消息
     */
    ArkResult removeMes(Integer mesId);

    /**
     * 添加一个关注用户
     */
    ArkResult followThis(TUserInfo loginUser, Integer userId);

    /**
     * 查看是否被关注
     */
    ArkResult isFollow(TUserInfo loginUser, Integer userId);
}
