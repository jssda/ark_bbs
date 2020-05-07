package pers.jssd.ark.portal.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.portal.service.IndexService;
import pers.jssd.ark.rpc.pojo.*;
import pers.jssd.ark.rpc.service.TArticleService;
import pers.jssd.ark.rpc.service.TCommentService;
import pers.jssd.ark.rpc.service.TSectionService;
import pers.jssd.ark.rpc.service.TUserInfoService;
import pers.jssd.ark.util.PageUtil;

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

    public IndexServiceImpl(TSectionService sectionService, TArticleService articleService, TUserInfoService userInfoService, TCommentService commentService) {
        this.sectionService = sectionService;
        this.articleService = articleService;
        this.userInfoService = userInfoService;
        this.commentService = commentService;
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
}
