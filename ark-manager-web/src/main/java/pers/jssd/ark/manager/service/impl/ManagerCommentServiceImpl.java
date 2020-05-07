package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerCommentService;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TComment;
import pers.jssd.ark.rpc.pojo.TCommentMulti;
import pers.jssd.ark.rpc.service.TArticleService;
import pers.jssd.ark.rpc.service.TCommentService;
import pers.jssd.ark.util.PageUtil;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerCommentServiceImpl implements ManagerCommentService {

    private final TCommentService commentService;
    private final TArticleService articleService;

    public ManagerCommentServiceImpl(TCommentService commentService, TArticleService articleService) {this.commentService = commentService;
        this.articleService = articleService;
    }

    @Override
    public PageResult listCommentByArtId(Integer artId, Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TComment> tCommentPageInfo = commentService.selectCommentByPageNumAndArtId(pageNum, artId);
        PageResult pageResult = new PageResult();
        pageResult.setCode(0);
        pageResult.setCount((int) tCommentPageInfo.getTotal());
        pageResult.setData(tCommentPageInfo.getList());
        pageResult.setSize(tCommentPageInfo.getSize());
        pageResult.setMsg("查询成功");

        return pageResult;
    }

    @Override
    public TableResult listCommentBy(Integer artId, Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TComment> tCommentPageInfo = commentService.selectCommentByPageNumAndArtIdKeepArtInfo(pageNum, artId);

        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tCommentPageInfo.getTotal());
        tableResult.setData(tCommentPageInfo.getList());
        tableResult.setMsg("查询成功");
        return tableResult;
    }

    @Override
    public TableResult listComment(Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TComment> tCommentPageInfo = commentService.selectCommentByPageNum(pageNum);

        TableResult tableResult = new TableResult(0, "查询成功");
        tableResult.setCount((int) tCommentPageInfo.getTotal());
        tableResult.setData(tCommentPageInfo.getList());

        return tableResult;
    }

    @Override
    public TableResult listCommentMulti(Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TCommentMulti> tCommentMultiPageInfo = commentService.selectCommentMultiByPageNum(pageNum);

        TableResult tableResult = new TableResult(0, "查询成功");
        tableResult.setCount((int) tCommentMultiPageInfo.getTotal());
        tableResult.setData(tCommentMultiPageInfo.getList());

        return tableResult;
    }

    @Override
    public TableResult listCommentMultiByComUserId(Integer comUserId, Integer limit, Integer page) {
        if (comUserId == null) {
            return new TableResult(-1, "查询错误, 用户id为空");
        }

        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TCommentMulti> tCommentMultiPageInfo = commentService.selectCommentMultiByPageNumAndUserId(pageNum, comUserId);

        TableResult tableResult = new TableResult(0, "查询成功");
        tableResult.setCount((int) tCommentMultiPageInfo.getTotal());
        tableResult.setData(tCommentMultiPageInfo.getList());
        return tableResult;
    }

    @Override
    public TableResult listCommentMultiByCommentId(Integer commentId, Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TCommentMulti> tCommentMultiPageInfo = commentService.selectCommentMultiByPageNumAndCommentId(pageNum, commentId);

        TableResult tableResult = new TableResult(0, "查询成功");
        tableResult.setCount((int) tCommentMultiPageInfo.getTotal());
        tableResult.setData(tCommentMultiPageInfo.getList());
        return tableResult;
    }

    @Override
    public ArkResult editComment(Integer comId, String comContent) {
        if (comId == null) {
            return new ArkResult(-1, "更改失败, 评论id不应该为空");
        }
        int i = commentService.modifyComment(comId, comContent);
        if (i == 0) {
            return new ArkResult(-1, "更改失败");
        } else {
            return new ArkResult(200, "更改成功");
        }
    }

    @Override
    public ArkResult addComment(TComment comment) {
        Integer artId = comment.getComArtId();
        TArticle article = articleService.selectArticleByArtId(artId);
        if (article == null) {
            return new ArkResult(-1, "添加失败, 没有此文章");
        }
        int i = commentService.addComment(comment);
        if (i == 0) {
            return new ArkResult(-1, "添加失败");
        } else {
            return new ArkResult(200, "添加成功");
        }
    }

    @Override
    public ArkResult removeCommentMulti(Integer comMulId) {
        if (comMulId == null) {
            return new ArkResult(-1, "删除失败");
        }
        int i = commentService.deleteCommentMulti(comMulId);
        if (i == 0) {
            return new ArkResult(-1, "删除失败");
        } else {
            return new ArkResult(200, "删除成功");
        }
    }

    @Override
    public ArkResult removeCommentMultis(List<Integer> comMulIds) {
        int i = commentService.deleteCommentMutis(comMulIds);
        if (i == comMulIds.size()) {
            return new ArkResult(200, "删除成功");
        } else {
            return new ArkResult(-1, "删除失败");
        }
    }

    @Override
    public ArkResult addAndEditCommentMulti(TCommentMulti commentMulti) {
        Integer comMulId = commentMulti.getComMulId();
        Integer replyId = commentMulti.getReplayId();
        TComment tComment = commentService.selectCommentByComId(replyId);
        if (tComment == null) {
            return new ArkResult(200, "操作失败, 没有对应的上一级评论id");
        } else {
            commentMulti.setTargetId(tComment.getComUserId());
        }

        String replyType = commentMulti.getReplyType();
        if (replyType == null) {
            commentMulti.setReplyType("0");
        }

        if (comMulId == null) { // 添加内容
            int i = commentService.addCommentMulti(commentMulti);
            if (i == 0) {
                return new ArkResult(-1, "添加失败");
            } else {
                return new ArkResult(200, "添加成功");
            }
        } else { // 修改内容
            int i = commentService.editCommentMulti(commentMulti);
            if (i == 0) {
                return new ArkResult(-1, "修改失败");
            } else {
                return new ArkResult(200, "修改成功");
            }
        }
    }

    @Override
    public ArkResult removeComment(Integer comId) {
        if (comId == null) {
            return new ArkResult(-1, "删除失败");
        }
        int i = commentService.deleteComment(comId);
        if (i == 0) {
            return new ArkResult(-1, "删除失败");
        } else {
            return new ArkResult(200, "删除成功");
        }
    }

    @Override
    public ArkResult removeComments(List<Integer> comIds) {
        int i = commentService.deleteComments(comIds);
        if (i == comIds.size()) {
            return new ArkResult(200, "删除成功");
        } else {
            return new ArkResult(-1, "删除失败");
        }
    }
}
