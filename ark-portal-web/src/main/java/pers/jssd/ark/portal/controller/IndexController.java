package pers.jssd.ark.portal.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.portal.service.IndexService;

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
     * 添加一个一级评论
     */
    @RequestMapping("addComment")
    public PageResult addComment() {
        return null;
    }

}
