package pers.jssd.ark.portal.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.portal.service.IndexService;
import pers.jssd.ark.rpc.pojo.*;
import pers.jssd.ark.rpc.service.*;
import pers.jssd.ark.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class IndexServiceImpl implements IndexService {

    private final TSectionService sectionService;
    private final TArticleService articleService;
    private final TUserInfoService userInfoService;
    private final TCommentService commentService;
    private final TMessageService messageService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public IndexServiceImpl(TSectionService sectionService, TArticleService articleService, TUserInfoService userInfoService, TCommentService commentService, TMessageService messageService) {
        this.sectionService = sectionService;
        this.articleService = articleService;
        this.userInfoService = userInfoService;
        this.commentService = commentService;
        this.messageService = messageService;
    }

    @Override
    public PageResult listSectionByPage(Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TSection> tSectionPageInfo = sectionService.selectSectionByPageNum(pageNum);

        PageResult pageResult = new PageResult();
        pageResult.setMsg("板块查询成功");
        pageResult.setCount((int) tSectionPageInfo.getTotal());
        pageResult.setCode(200);
        pageResult.setSize(tSectionPageInfo.getSize());
        pageResult.setData(tSectionPageInfo.getList());

        return pageResult;
    }

    @Override
    public PageResult listArticleBySecId(Integer secId, Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);

        PageInfo<TArticle> articlePageInfo = articleService.selectArticleByPageNumAndSecId(pageNum, secId);

        PageResult pageResult = new PageResult();
        pageResult.setCode(200);
        pageResult.setCount((int) articlePageInfo.getTotal());
        pageResult.setSize(articlePageInfo.getSize());
        pageResult.setData(articlePageInfo.getList());
        pageResult.setMsg("文章查询成功");

        return pageResult;
    }

    @Override
    public ArkResult getSectionBySecId(Integer secId) {
        if (secId == null) {
            return new ArkResult(-1, "获取板块信息失败");
        }
        TSection section = sectionService.selectSectionBySecId(secId);
        if (section == null) {
            return new ArkResult(-1, "获取板块信息失败");
        } else {
            ArkResult arkResult = new ArkResult(200, "查询成功");
            arkResult.setData(section);

            return arkResult;
        }
    }

    @Override
    public ArkResult getUserInfoByUserId(Integer userId) {
        if (userId == null) {
            return new ArkResult(-1, "查询用户失败");
        }
        TUserInfo userInfo = userInfoService.selectUserInfoByUserId(userId);
        if (userInfo == null) {
            return new ArkResult(-1, "查询用户失败");
        } else {
            ArkResult arkResult = new ArkResult(200, "查询成功");
            arkResult.setData(userInfo);
            return arkResult;
        }
    }

    @Override
    public ArkResult getArticleByArtId(Integer artId) {
        if (artId == null) {
            return new ArkResult(-1, "查询文章失败");
        }
        TArticle article = articleService.selectArticleByArtId(artId);
        if (article == null) {
            return new ArkResult(-1, "查询文章失败");
        } else {
            ArkResult arkResult = new ArkResult(200, "查询成功");
            arkResult.setData(article);
            return arkResult;
        }
    }

    @Override
    public PageResult listCommentByArtId(Integer artId, Integer page, Integer limit) {
        if (artId == null) {
            PageResult pageResult = new PageResult();
            pageResult.setCode(-1);
            pageResult.setMsg("无文章id, 查询失败");
            pageResult.setCount(0);
            pageResult.setSize(0);
            return pageResult;
        }

        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TComment> tCommentPageInfo = commentService.selectCommentByPageNumAndArtId(pageNum, artId);

        PageResult pageResult = new PageResult();
        pageResult.setCode(200);
        pageResult.setMsg("查询成功");
        pageResult.setCount((int) tCommentPageInfo.getTotal());
        pageResult.setSize(tCommentPageInfo.getSize());
        pageResult.setData(tCommentPageInfo.getList());

        return pageResult;
    }

    @Override
    public ArkResult listTypeByArtId(Integer artId) {
        if (artId == null) {
            return new ArkResult(-1, "查询失败, 文章id为空");
        }

        List<TArticleType> tArticleTypes = articleService.selectArtTypeByArtId(artId);
        ArkResult arkResult = new ArkResult(200, "查询成功");
        arkResult.setData(tArticleTypes);
        return arkResult;
    }

    @Override
    public PageResult listCommentMultiByPageNumAndComId(Integer comId, Integer page, Integer limit) {
        if (comId == null) {
            PageResult pageResult = new PageResult();
            pageResult.setCount(-1);
            pageResult.setMsg("查询失败, 一级评论id为空");
            return pageResult;
        }

        PageNum pageNum = PageUtil.getPageNum(page, limit);

        PageInfo<TCommentMulti> tCommentMultiPageInfo = commentService.selectCommentMultiByPageNumAndCommentId(pageNum, comId);
        if (tCommentMultiPageInfo != null) {
            PageResult pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setMsg("查询成功");
            pageResult.setCount((int) tCommentMultiPageInfo.getTotal());
            pageResult.setSize(tCommentMultiPageInfo.getSize());
            pageResult.setData(tCommentMultiPageInfo.getList());

            return pageResult;
        } else {
            PageResult pageResult = new PageResult();
            pageResult.setCount(-1);
            pageResult.setMsg("查询失败");
            return pageResult;
        }
    }

    @Override
    public ArkResult addComment(HttpServletRequest request) {
        TUserInfo loginUser = (TUserInfo) request.getAttribute("loginUser");
        String content = request.getParameter("content");
        String artId = request.getParameter("artId");

        String replyType = request.getParameter("replyType");

        int i = 0;
        try {
            Integer toUserId = null;
            // 查看是不是对文章的评论信息
            if (StringUtils.isEmpty(replyType)) {
                TComment comment = new TComment();
                comment.setComContent(content);
                comment.setComArtId(Integer.valueOf(artId));
                comment.setComUserId(loginUser.getUserId());
                comment.setCreate(new Date());

                TArticle article = articleService.selectArticleByArtId(Integer.valueOf(artId));
                toUserId = article.getArtUserId();

                i = commentService.addComment(comment);
            } else { // 多级评论信息
                TCommentMulti commentMulti = new TCommentMulti();

                Integer comId = Integer.valueOf(request.getParameter("comId"));
                Integer replayId = Integer.valueOf(request.getParameter("replayId"));
                Integer targetId = Integer.valueOf(request.getParameter("targetId"));
                toUserId = targetId;

                commentMulti.setComMulContent(content);
                commentMulti.setComMulCommentId(comId);
                commentMulti.setReplayId(replayId);
                commentMulti.setReplyType(replyType);
                commentMulti.setCommentMulUserId(loginUser.getUserId());
                commentMulti.setTargetId(targetId);
                commentMulti.setCreate(new Date());

                i = commentService.addCommentMulti(commentMulti);
            }
            if (i > 0) {
                // 添加一条消息
                TMessage message = new TMessage();
                message.setMesTitle("评论信息");
                message.setMesContent("有人对你回复了");
                message.setFromUserId(loginUser.getUserId());
                message.setToUserId(toUserId);
                message.setMesType("0");
                message.setMesState("0");
                message.setCreate(new Date());

                int messageInsertCount = messageService.insertMessage(message);
                if (messageInsertCount < 1) {
                    return new ArkResult(-1, "评论失败");
                }
            } else {
                return new ArkResult(-1, "评论失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArkResult(-1, "评论失败");
        }

        return new ArkResult(200, "评论成功");
    }

    @Override
    public PageResult listArticleByUserIdAndPageNum(Integer userId, Integer page, Integer limit) {
        PageResult pageResult = null;
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TArticle> articlePageInfo = articleService.selectArticleByPageNumAndUserId(pageNum, userId);
        if (articlePageInfo == null || articlePageInfo.getSize() == 0) {
            pageResult = new PageResult();
            pageResult.setCode(-1);
            pageResult.setMsg("没有发布的贴子");
        } else {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setData(articlePageInfo.getList());
            pageResult.setCount((int) articlePageInfo.getTotal());
            pageResult.setSize(articlePageInfo.getSize());
            pageResult.setMsg("查询成功");
        }
        return pageResult;
    }
}
