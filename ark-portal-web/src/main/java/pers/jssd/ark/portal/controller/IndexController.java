package pers.jssd.ark.portal.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.portal.service.IndexService;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 核心业务请求处理
 *
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/portal/index")
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {this.indexService = indexService;}

    /**
     * 分页查询板块信息
     *
     * @param page  当前页
     * @param limit 每页多少条数据
     * @return 返回查询到的分页数据
     */
    @RequestMapping("listSection")
    public PageResult listSectionByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {

        return indexService.listSectionByPage(page, limit);
    }

    /**
     * 分页查询指定板块中有的5篇文章
     *
     * @param secId 板块id
     * @return 返回查询到的分页数据
     */
    @RequestMapping("listArticleBySecId/{secId}")
    public PageResult listArticleBySecId(@PathVariable Integer secId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listArticleBySecId(secId, page, limit);
    }

    /**
     * 获取板块id, 获取板块信息
     *
     * @param secId 板块信息
     * @return 返回获取到的板块信息
     */
    @RequestMapping("getSectionBySecId/{secId}")
    public ArkResult getSectionBySecId(@PathVariable Integer secId) {
        return indexService.getSectionBySecId(secId);
    }

    /**
     * 通过用户id查询用户
     *
     * @param userId 用户id
     * @return 返回查询到的用户信息
     */
    @RequestMapping("getUserInfoByUserId/{userId}")
    public ArkResult getUserInfoByUserId(@PathVariable Integer userId) {
        return indexService.getUserInfoByUserId(userId);
    }

    /**
     * 通过文章id查询文章
     */
    @RequestMapping("getArticleByArtId/{artId}")
    public ArkResult getArticleByArtId(@PathVariable Integer artId) {
        return indexService.getArticleByArtId(artId);
    }

    /**
     * 通过文章id分页查询一级评论信息
     */
    @RequestMapping("listCommentByPageNumAndArtId")
    public PageResult listCommentByArtId(Integer artId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listCommentByArtId(artId, page, limit);
    }

    /**
     * 通过文章id查询此文章具有的文章类型
     *
     * @param artId 文章id
     */
    @RequestMapping("listTypeByArtId/{artId}")
    public ArkResult listTypeByArtId(@PathVariable Integer artId) {
        return indexService.listTypeByArtId(artId);
    }

    /**
     * 通过一级评论id分页查询二级评论信息, 按照时间排序
     */
    @RequestMapping("listCommentMultiByPageNumAndComId")
    public PageResult listCommentMultiByPageNumAndComId(Integer comId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listCommentMultiByPageNumAndComId(comId, page, limit);
    }

    /**
     * 添加一个评论
     */
    @RequestMapping("addComment")
    public ArkResult addComment(HttpServletRequest request) {
        return indexService.addComment(request);
    }

    /**
     * 查询用户近期发布的帖子
     */
    @RequestMapping("listArticleByUserIdAndPageNum")
    public PageResult listArticleByUserIdAndPageNum(Integer userId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listArticleByUserIdAndPageNum(userId, page, limit);
    }

    /**
     * 查询登录用户近期发布的帖子
     */
    @RequestMapping("listArticleByLoginUserIdAndPageNum")
    public PageResult listArticleByLoginUserIdAndPageNum(@RequestAttribute TUserInfo loginUser, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listArticleByUserIdAndPageNum(loginUser.getUserId(), page, limit);
    }

    /**
     * 收藏一篇文章
     */
    @RequestMapping("collectionThis/{artId}")
    public ArkResult collectionThis(@PathVariable Integer artId, @RequestAttribute TUserInfo loginUser) {
        return indexService.collectionThis(artId, loginUser);
    }

    /**
     * 置顶此文章
     */
    @RequestMapping("topThis/{artId}")
    public ArkResult topThis(@PathVariable Integer artId) {
        return indexService.topThis(artId);
    }

    /**
     * 取消置顶此文章
     */
    @RequestMapping("unTopThis/{artId}")
    public ArkResult unTopThis(@PathVariable Integer artId) {
        return indexService.unTopThis(artId);
    }

    /**
     * 删除此文章
     */
    @RequestMapping("delThis/{artId}")
    public ArkResult delThis(@PathVariable Integer artId) {
        return indexService.delThis(artId);
    }

    /**
     * 查看文章是否被收藏
     */
    @RequestMapping("isCollection")
    public ArkResult isCollection(Integer artId, @RequestParam(required = false) Integer userId) {
        return indexService.isCollection(artId, userId);
    }

    /**
     * 不分页的查询所有板块信息
     *
     * @param key 查询关键字
     * @return 返回查询到的板块信息
     */
    @RequestMapping("queryBy")
    public ArkResult querySectionBy(@RequestParam(defaultValue = "") String key) {
        return indexService.querySectionBy(key);
    }

    /**
     * 查询所有的板块信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @return 返回查询到的表格数据
     */
    @RequestMapping("/list")
    public TableResult listSection(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return indexService.listSection(page, limit);
    }

    /**
     * 添加文章
     *
     * @return 返回是否添加成功的信息
     */
    @RequestMapping(value = "addArticle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult addArticle(@RequestBody TArticle article, @RequestAttribute TUserInfo loginUser) {

        return indexService.addArticle(article, loginUser);
    }

    /**
     * 分页查询置顶的文章
     */
    @RequestMapping("listArticleByTop")
    public PageResult listArticleByTopAndPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listArticleByTopAndPage(page, limit);
    }

    /**
     * 查询评论最多的10篇文章
     *
     * @param secId 板块id, 可为空
     */
    @RequestMapping("mostCommentBySecId")
    public PageResult mostCommentBySecId(@RequestParam(required = false) Integer secId) {
        return indexService.mostCommentBySecId(secId);
    }

    /**
     * 查询热度最高的10篇文章
     *
     * @param secId 板块id, 可为空
     */
    @RequestMapping("mostHotBySecId")
    public PageResult mostHotBySecId(@RequestParam(required = false) Integer secId) {
        return indexService.mostHotBySecId(secId);
    }

    /**
     * 查询登录用户收藏的文章
     */
    @RequestMapping("listCollectionByUserIdAndPageNum")
    public PageResult listCollectionByUserIdAndPageNum(@RequestAttribute TUserInfo loginUser, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        return indexService.listCollectionByUserIdAndPageNum(loginUser, page, limit);
    }

    /**
     * 获取登录的用户信息
     *
     * @param loginUser 登录的用户
     */
    @RequestMapping("getLoginUserInfo")
    public ArkResult getLoginUserInfo(@RequestAttribute TUserInfo loginUser) {
        ArkResult arkResult = new ArkResult(200);
        arkResult.setData(loginUser);
        return arkResult;
    }

    /**
     * 修改用户信息
     *
     * @param userInfo 需要修改的用户信息
     * @return 返回是否修改成功
     */
    @RequestMapping("modifyUserInfo")
    public ArkResult modifyUserInfo(TUserInfo userInfo) {
        return indexService.modifyUserInfo(userInfo);
    }

    /**
     * 修改用户密码
     */
    @RequestMapping("modifyPass")
    public ArkResult modifyPass(@RequestAttribute TUserInfo loginUser, @RequestParam(name = "nowpass") String nowPass, String pass) {
        return indexService.modifyPass(loginUser, nowPass, pass);
    }

    /**
     * 取消收藏这个文章
     */
    @RequestMapping("unCollection")
    public ArkResult unCollection(@RequestAttribute TUserInfo loginUser, Integer colId) {
        return indexService.unCollection(loginUser, colId);
    }

}
