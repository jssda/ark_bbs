package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.mapper.TArticleMapper;
import pers.jssd.ark.rpc.mapper.TCommentMapper;
import pers.jssd.ark.rpc.mapper.TCommentMultiMapper;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.*;
import pers.jssd.ark.rpc.service.TCommentService;

import java.util.Date;
import java.util.List;

/**
 * 一级评论服务类
 *
 * @author jssdjing@gmail.com
 */
@Service
public class TCommentServiceImpl implements TCommentService {

    private final TCommentMapper commentMapper;
    private final TUserInfoMapper userInfoMapper;
    private final TArticleMapper tArticleMapper;
    private final TCommentMultiMapper commentMultiMapper;

    public TCommentServiceImpl(TCommentMapper commentMapper, TUserInfoMapper userInfoMapper, TArticleMapper tArticleMapper, TCommentMultiMapper commentMultiMapper) {
        this.commentMapper = commentMapper;
        this.userInfoMapper = userInfoMapper;
        this.tArticleMapper = tArticleMapper;
        this.commentMultiMapper = commentMultiMapper;
    }

    @Override
    public PageInfo<TComment> selectCommentByPageNumAndArtId(PageNum pageNum, Integer artId) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TCommentExample commentExample = new TCommentExample();
        TCommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andComArtIdEqualTo(artId);
        List<TComment> tComments = commentMapper.selectByExample(commentExample);

        // 取得用户信息
        getCommentUserInfo(tComments.toArray(new TComment[0]));
        return new PageInfo<>(tComments);
    }

    @Override
    public PageInfo<TComment> selectCommentByPageNumAndArtIdKeepArtInfo(PageNum pageNum, Integer artId) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TCommentExample commentExample = new TCommentExample();
        TCommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andComArtIdEqualTo(artId);
        List<TComment> tComments = commentMapper.selectByExample(commentExample);

        // 取得用户信息
        getCommentUserInfo(tComments.toArray(new TComment[0]));
        // 获取文章信息
        getCommentArtInfo(tComments.toArray(new TComment[0]));
        return new PageInfo<>(tComments);
    }

    @Override
    public TComment selectCommentByComId(Integer comId) {
        TComment tComment = commentMapper.selectByPrimaryKey(comId);
        // 取得用户信息
        getCommentUserInfo(tComment);
        // 获取文章信息
        getCommentArtInfo(tComment);
        return tComment;
    }

    @Override
    public PageInfo<TComment> selectCommentByPageNum(PageNum pageNum) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TCommentExample commentExample = new TCommentExample();
        List<TComment> tComments = commentMapper.selectByExample(commentExample);
        // 取得用户信息
        getCommentUserInfo(tComments.toArray(new TComment[0]));
        // 取得评论所在的文章信息
        getCommentArtInfo(tComments.toArray(new TComment[0]));

        return new PageInfo<>(tComments);
    }

    @Override
    public PageInfo<TCommentMulti> selectCommentMultiByPageNum(PageNum pageNum) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());

        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        List<TCommentMulti> commentMultis = commentMultiMapper.selectByExample(commentMultiExample);

        // 取得发布者信息
        getCommentMultiUserInfo(commentMultis.toArray(new TCommentMulti[0]));
        // 取得评论目标用户的信息
        getCommentMultiTargetUserInfo(commentMultis.toArray(new TCommentMulti[0]));

        return new PageInfo<>(commentMultis);
    }

    @Override
    public PageInfo<TCommentMulti> selectCommentMultiByPageNumAndUserId(PageNum pageNum, Integer comUserId) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());

        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        TCommentMultiExample.Criteria commentMultiExampleCriteria = commentMultiExample.createCriteria();
        commentMultiExampleCriteria.andCommentMulUserIdEqualTo(comUserId);
        List<TCommentMulti> commentMultis = commentMultiMapper.selectByExample(commentMultiExample);

        // 取得发布者信息
        getCommentMultiUserInfo(commentMultis.toArray(new TCommentMulti[0]));
        // 取得评论目标用户的信息
        getCommentMultiTargetUserInfo(commentMultis.toArray(new TCommentMulti[0]));

        return new PageInfo<>(commentMultis);
    }

    @Override
    public PageInfo<TCommentMulti> selectCommentMultiByPageNumAndCommentId(PageNum pageNum, Integer commentId) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());

        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        TCommentMultiExample.Criteria commentMultiExampleCriteria = commentMultiExample.createCriteria();
        commentMultiExampleCriteria.andReplayIdEqualTo(commentId);
        // 按照时间排序
        commentMultiExample.setOrderByClause("`create`  asc");

        List<TCommentMulti> commentMultis = commentMultiMapper.selectByExample(commentMultiExample);

        // 取得发布者信息
        getCommentMultiUserInfo(commentMultis.toArray(new TCommentMulti[0]));
        // 取得评论目标用户的信息
        getCommentMultiTargetUserInfo(commentMultis.toArray(new TCommentMulti[0]));

        return new PageInfo<>(commentMultis);
    }

    @Override
    public int addCommentMulti(TCommentMulti commentMulti) {
        commentMulti.setCreate(new Date());
        return commentMultiMapper.insertSelective(commentMulti);
    }

    @Override
    public int editCommentMulti(TCommentMulti commentMulti) {
        return commentMultiMapper.updateByPrimaryKeySelective(commentMulti);
    }

    @Override
    public int deleteCommentMulti(Integer comMulId) {
        return commentMultiMapper.deleteByPrimaryKey(comMulId);
    }

    @Override
    public int deleteCommentMutis(List<Integer> comMulIds) {
        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        TCommentMultiExample.Criteria commentMultiExampleCriteria = commentMultiExample.createCriteria();
        commentMultiExampleCriteria.andComMulIdIn(comMulIds);
        return commentMultiMapper.deleteByExample(commentMultiExample);
    }

    /**
     * 查询多级回复信息的发布用户信息
     *
     * @param commentMultis 多级评论信息的容器
     */
    private void getCommentMultiUserInfo(TCommentMulti... commentMultis) {
        for (TCommentMulti commentMulti : commentMultis) {
            if (commentMulti != null) {
                Integer commentMulUserId = commentMulti.getCommentMulUserId();
                TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(commentMulUserId);
                commentMulti.setCommentMulUser(tUserInfo);
            }
        }
    }

    /**
     * 取得多级评论的评论目标用户的信息
     *
     * @param commentMultis 多级评论数组
     */
    private void getCommentMultiTargetUserInfo(TCommentMulti... commentMultis) {
        for (TCommentMulti commentMulti : commentMultis) {
            if (commentMulti != null) {
                Integer targetId = commentMulti.getTargetId();
                TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(targetId);
                commentMulti.setTargetUser(tUserInfo);
            }
        }
    }

    @Override
    public int modifyComment(Integer comId, String comContent) {
        TComment tComment = new TComment();
        tComment.setComId(comId);
        tComment.setComContent(comContent);
        return commentMapper.updateByPrimaryKeySelective(tComment);
    }

    @Override
    public int addComment(TComment comment) {
        comment.setCreate(new Date());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public int deleteComment(Integer comId) {
        int i = commentMapper.deleteByPrimaryKey(comId);
        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        TCommentMultiExample.Criteria criteria = commentMultiExample.createCriteria();
        criteria.andComMulCommentIdEqualTo(comId);
        commentMultiMapper.deleteByExample(commentMultiExample);
        return i;
    }

    @Override
    public int deleteComments(List<Integer> comIds) {
        TCommentExample commentExample = new TCommentExample();
        TCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andComIdIn(comIds);
        int i = commentMapper.deleteByExample(commentExample);

        // 删除与之关联的二级评论信息
        TCommentMultiExample commentMultiExample = new TCommentMultiExample();
        TCommentMultiExample.Criteria commentMultiExampleCriteria = commentMultiExample.createCriteria();
        commentMultiExampleCriteria.andComMulCommentIdIn(comIds);
        commentMultiMapper.deleteByExample(commentMultiExample);

        return i;
    }

    /**
     * 获取评论得用户信息
     *
     * @param comments 需要获取用户信息得评论
     */
    private void getCommentUserInfo(TComment... comments) {
        for (TComment comment : comments) {
            if (comment != null) {
                Integer comUserId = comment.getComUserId();
                TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(comUserId);
                comment.setUser(tUserInfo);
            }
        }
    }

    /**
     * 获取评论所在的文章信息
     *
     * @param comments 评论列表
     */
    private void getCommentArtInfo(TComment... comments) {
        for (TComment comment : comments) {
            if (comment != null) {
                Integer artId = comment.getComArtId();
                TArticle article = tArticleMapper.selectByPrimaryKey(artId);
                comment.setArticle(article);
            }
        }
    }

}
