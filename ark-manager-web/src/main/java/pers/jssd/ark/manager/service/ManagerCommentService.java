package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TComment;
import pers.jssd.ark.rpc.pojo.TCommentMulti;

import java.util.List;

/**
 * 一级评论业务
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerCommentService {

    /**
     * 分页查询指定文章的评论信息, 评论中不包括文章信息
     *
     * @param artId 文章id
     * @param limit 每页显示多少条数据
     * @param page  当前页
     * @return 返回查询到的分页数据
     */
    PageResult listCommentByArtId(Integer artId, Integer limit, Integer page);

    /**
     * 分页查询所有的评论信息
     *
     * @param limit 每页显示多少条数据
     * @param page  当前页
     * @return 返回查找到的表格数据
     */
    TableResult listComment(Integer limit, Integer page);

    /**
     * 分页查找指定文章的评论信息, 评论信息中包含文章信息
     *
     * @param artId 文章id
     * @param limit 每页显示多少条数据
     * @param page  当前页
     * @return 返回找到的表格数据
     */
    TableResult listCommentBy(Integer artId, Integer limit, Integer page);

    /**
     * 更改文章一级评论
     *
     * @param comId      评论id
     * @param comContent 更改的内容
     * @return 返回是否更改成功的响应数据
     */
    ArkResult editComment(Integer comId, String comContent);

    /**
     * 添加一个评论信息
     *
     * @param comment 需要添加的评论信息
     * @return 返回是否添加成功的响应信息
     */
    ArkResult addComment(TComment comment);

    /**
     * 删除一个一级评论信息
     *
     * @param comId 需要删除的一级评论
     * @return 返回是否删除成功的信息
     */
    ArkResult removeComment(Integer comId);

    /**
     * 批量删除评论信息
     *
     * @param comIds 需要删除的一级评论容器
     * @return 返回是否删除成功的信息
     */
    ArkResult removeComments(List<Integer> comIds);

    /**
     * 分页查询所有多级评论
     *
     * @param limit 每页多少条数据
     * @param page  当前页
     * @return 返回查询到的分页表格信息
     */
    TableResult listCommentMulti(Integer limit, Integer page);

    /**
     * 通过发布者id, 搜索多级评论信息
     *
     * @param comUserId 发布者id
     * @param limit     每页多少条数据
     * @param page      当前也
     * @return 返回搜索到的分页数据
     */
    TableResult listCommentMultiByComUserId(Integer comUserId, Integer limit, Integer page);

    /**
     * 通过上一级评论id, 分页查询多级评论信息
     *
     * @param commentId 此评论上一级评论id
     * @param limit     每页多少条数据
     * @param page      当前也
     * @return 返回搜索到的分页数据
     */
    TableResult listCommentMultiByCommentId(Integer commentId, Integer limit, Integer page);

    /**
     * 添加或更改一个多级评论的信息
     *
     * @param commentMulti 多级评论的信息
     * @return 返回是否添加成功
     */
    ArkResult addAndEditCommentMulti(TCommentMulti commentMulti);

    /**
     * 删除一个多级评论
     *
     * @param comMulId 需要删除的多级评论的id
     * @return 返回是否删除成功
     */
    ArkResult removeCommentMulti(Integer comMulId);

    /**
     * 批量删除多级评论
     *
     * @param comMulIds 删除的多级评论id容器
     * @return 返回是否删除成功
     */
    ArkResult removeCommentMultis(List<Integer> comMulIds);
}
