package pers.jssd.ark.manager.controller;

import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerCommentService;
import pers.jssd.ark.rpc.pojo.TComment;
import pers.jssd.ark.rpc.pojo.TCommentMulti;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/manager/comment")
public class CommentController {

    private final ManagerCommentService commentService;

    public CommentController(ManagerCommentService commentService) {this.commentService = commentService;}

    /**
     * 通过文章id分页查询一级评论
     *
     * @param artId 查询的文章id
     * @return 返回查询到的分页数据
     */
    @RequestMapping("listCommentByArtId")
    public PageResult listCommentByArtId(Integer artId, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {

        return commentService.listCommentByArtId(artId, limit, page);
    }

    /**
     * 分页查询所有一级评论
     *
     * @param limit 每页多少条数据
     * @param page  当前页
     * @return 返回查询到的分页表格数据
     */
    @RequestMapping("listComment")
    public TableResult listComment(@RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return commentService.listComment(limit, page);
    }

    /**
     * 分页查询所有多级评论
     *
     * @param limit 每页多少条数据
     * @param page  当前页
     * @return 返回查询到的分页表格数据
     */
    @RequestMapping("listCommentMulti")
    public TableResult listCommentMulti(@RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return commentService.listCommentMulti(limit, page);
    }

    /**
     * 通过文章id分页查询一级评论, 获得表格数据
     *
     * @param artId 查询的文章id
     * @return 返回查询到的分页数据
     */
    @RequestMapping("listCommentBy")
    public TableResult listCommentBy(Integer artId, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return commentService.listCommentBy(artId, limit, page);
    }

    /**
     * 通过发布者id搜索多级评论
     *
     * @param comUserId 发布者id
     * @param limit 每页多少条数据
     * @param page 当前页
     * @return 返回搜索到的表格数据
     */
    @RequestMapping("listCommentMultiByComUserId")
    public TableResult listCommentMultiByComUserId(Integer comUserId, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return commentService.listCommentMultiByComUserId(comUserId, limit, page);
    }

    /**
     * 通过一级评论的id查询多级评论信息
     *
     * @param commentId 此评论上一级评论id
     * @param limit 每页多少条数据
     * @param page 当前页
     * @return 返回搜索到的表格数据
     */
    @RequestMapping("listCommentMultiByCommentId")
    public TableResult listCommentMultiByCommentId(Integer commentId, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return commentService.listCommentMultiByCommentId(commentId, limit, page);
    }

    /**
     * 更改文章数据
     *
     * @return 返回是否更改成功的响应数据
     */
    @RequestMapping("editComment")
    public ArkResult editComment(Integer comId, String comContent) {
        return commentService.editComment(comId, comContent);
    }

    /**
     * 添加一个评论信息
     *
     * @param comment 评论信息
     * @return 返回是否添加成功的响应信息
     */
    @RequestMapping("addComment")
    public ArkResult addComment(@RequestBody TComment comment) {
        return commentService.addComment(comment);
    }

    /**
     * 添加或者更改一个多级评论信息
     *
     * @return 返回是否添加成功
     */
    @RequestMapping("addAndEditCommentMulti")
    public ArkResult addAndEditCommentMulti(@RequestBody TCommentMulti commentMulti) {

        return commentService.addAndEditCommentMulti(commentMulti);
    }

    /**
     * 删除一个一级评论, 连带删除二级评论
     *
     * @param comId 要删除的一级评论id
     * @return 返回是否删除成功的信息
     */
    @RequestMapping("removeComment/{comId}")
    public ArkResult removeComment(@PathVariable Integer comId) {
        return commentService.removeComment(comId);
    }

    /**
     * 删除一个多级评论
     * @param comMulId 多级评论的id
     * @return 返回是否删除成功
     */
    @RequestMapping("removeCommentMulti/{comMulId}")
    public ArkResult removeCommentMulti(@PathVariable Integer comMulId) {
        return commentService.removeCommentMulti(comMulId);
    }

    /**
     * 批量删除多级评论
     * @param comMulIds 多级评论的id
     * @return 返回是否删除成功
     */
    @RequestMapping("removeCommentMultis")
    public ArkResult removeCommentMultis(@RequestParam("comMulIds") List<Integer> comMulIds) {
        return commentService.removeCommentMultis(comMulIds);
    }

    /**
     * 批量删除评论
     *
     * @param comIds 需要删除的评论id容器
     * @return 返回是否删除成功的信息
     */
    @RequestMapping("removeComments")
    public ArkResult removeComments(@RequestParam("comIds") List<Integer> comIds) {
        return commentService.removeComments(comIds);
    }
}