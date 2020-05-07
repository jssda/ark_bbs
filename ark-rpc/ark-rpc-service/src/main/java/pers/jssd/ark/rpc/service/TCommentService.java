package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TComment;
import pers.jssd.ark.rpc.pojo.TCommentMulti;

import java.util.List;

/**
 * 一级评论服务类
 *
 * @author jssdjing@gmail.com
 */
public interface TCommentService {

    /**
     * 分页查询指定文章的一级评论
     *
     * @param pageNum 分页信息
     * @param artId   文章的id
     * @return 返回查询到的分页评论容器
     */
    PageInfo<TComment> selectCommentByPageNumAndArtId(PageNum pageNum, Integer artId);

    /**
     * 分页查询所有的一级评论信息
     *
     * @param pageNum 分页信息
     * @return 返回查询到的分页数据
     */
    PageInfo<TComment> selectCommentByPageNum(PageNum pageNum);

    /**
     * 分页查询所有的二级评论信息
     *
     * @param pageNum 分页信息
     * @return 返回查询到的分页数据
     */
    PageInfo<TCommentMulti> selectCommentMultiByPageNum(PageNum pageNum);

    /**
     * 带条件的查询评论信息, 保留文章信息的查询
     *
     * @param pageNum 分页信息
     * @param artId   文章id
     * @return 返回查询到的评论数据
     */
    PageInfo<TComment> selectCommentByPageNumAndArtIdKeepArtInfo(PageNum pageNum, Integer artId);

    /**
     * 通过评论id查询此评论
     * @param comId 查询的评论id
     * @return 返回查询到的评论信息
     */
    TComment selectCommentByComId(Integer comId);

    /**
     * 更改评论内容
     *
     * @param comId      评论的id
     * @param comContent 更改的评论内容
     * @return 返回是否更改成功, 1成功, 0失败
     */
    int modifyComment(Integer comId, String comContent);

    /**
     * 添加一个评论信息
     *
     * @param comment 评论信息
     * @return 返回添加了多少条数据
     */
    int addComment(TComment comment);

    /**
     * 删除一个一级评论的信息, 连带删除多级评论信息
     *
     * @param comId 需要删除的一级评论id
     * @return 返回是否删除成功
     */
    int deleteComment(Integer comId);

    /**
     * 批量删除评论信息
     *
     * @param comIds 需要删除的评论id容器
     * @return 返回删除了多少条数据
     */
    int deleteComments(List<Integer> comIds);

    /**
     * 通过发布者id, 分页查询多级评论信息
     *
     * @param pageNum   分页信息
     * @param comUserId 发布者id
     * @return 返回查询到的多级评论信息
     */
    PageInfo<TCommentMulti> selectCommentMultiByPageNumAndUserId(PageNum pageNum, Integer comUserId);

    /**
     * 通过此评论上一级评论id, 分页查询多级评论信息
     *
     * @param pageNum 分页信息
     * @param commentId 上一级评论id
     * @return 返回查询到的多级评论信息
     */
    PageInfo<TCommentMulti> selectCommentMultiByPageNumAndCommentId(PageNum pageNum, Integer commentId);

    /**
     * 添加一个多级评论
     *
     * @param commentMulti 多级评论信息
     * @return 返回添加的多级评论数量
     */
    int addCommentMulti(TCommentMulti commentMulti);

    /**
     * 修改一个多级评论内容
     *
     * @param commentMulti 多级评论信息
     * @return 返回修改的多级评论信息
     */
    int editCommentMulti(TCommentMulti commentMulti);

    /**
     * 删除一个多级评论信息
     *
     * @param comMulId 需要删除的多级评论id
     * @return 返回删除成功的个数
     */
    int deleteCommentMulti(Integer comMulId);

    /**
     * 批量删除多级评论
     * @param comMulIds 需要删除的多级评论的id容器
     * @return 返回删除成功的个数
     */
    int deleteCommentMutis(List<Integer> comMulIds);

}
