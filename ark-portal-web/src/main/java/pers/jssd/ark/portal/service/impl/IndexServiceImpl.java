package pers.jssd.ark.portal.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.PageResult;
import pers.jssd.ark.beans.TableResult;
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
    private final TCollectionService collectionService;
    private final TFollowService followService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public IndexServiceImpl(TSectionService sectionService, TArticleService articleService, TUserInfoService userInfoService, TCommentService commentService, TMessageService messageService, TCollectionService collectionService, TFollowService followService) {
        this.sectionService = sectionService;
        this.articleService = articleService;
        this.userInfoService = userInfoService;
        this.commentService = commentService;
        this.messageService = messageService;
        this.collectionService = collectionService;
        this.followService = followService;
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
                message.setMesContent(artId);
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

    @Override
    public ArkResult collectionThis(Integer artId, TUserInfo loginUser) {
        ArkResult arkResult = null;
        TCollection collection = new TCollection();
        collection.setColArtId(artId);
        collection.setColUserId(loginUser.getUserId());
        int i = collectionService.insertCollection(collection);
        if (i == 0) {
            arkResult = new ArkResult(-1, "收藏失败");
        } else {
            arkResult = new ArkResult(200, "收藏成功");
        }
        return arkResult;
    }

    @Override
    public ArkResult isCollection(Integer artId, Integer userId) {
        ArkResult arkResult = null;
        if (userId == null) {
            arkResult = new ArkResult(-1, "用户未登录");
        } else {
            TCollection collection = new TCollection();
            collection.setColUserId(userId);
            collection.setColArtId(artId);
            List<TCollection> collections = collectionService.selectCollectionByArtIdAndUserId(collection);
            if (collections.size() > 0) {
                arkResult = new ArkResult(200, "此文章已被收藏");
            } else {
                arkResult = new ArkResult(-1, "此文章未被收藏");
            }
        }

        return arkResult;
    }


    @Override
    public ArkResult querySectionBy(String key) {
        ArkResult arkResult = null;
        if (key == null) {
            arkResult = new ArkResult(-1, "查询失败");
        }

        List<TSection> tSections = sectionService.selectSectionByKey(key);
        if (tSections != null) {
            arkResult = new ArkResult(200, "查询成功");
            arkResult.setData(tSections);
        }
        return arkResult;
    }

    @Override
    public TableResult listSection(Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);

        PageInfo<TSection> tSectionPageInfo = sectionService.selectSectionByPageNum(pageNum);
        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setMsg("查询成功");
        tableResult.setData(tSectionPageInfo.getList());
        tableResult.setCount((int) tSectionPageInfo.getTotal());

        return tableResult;
    }

    @Override
    public ArkResult addArticle(TArticle article, TUserInfo loginUser) {
        if (loginUser == null) {
            return new ArkResult(-1, "文章添加失败");
        } else {
            article.setArtUserId(loginUser.getUserId());
        }
        // 添加一个文章
        int i = articleService.addArticle(article);
        if (i == 1) {
            return new ArkResult(200, "文章添加成功");
        } else {
            return new ArkResult(-1, "文章添加失败");
        }
    }

    @Override
    public PageResult listArticleByTopAndPage(Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TArticle> articlePageInfo = articleService.selectArticleByPageNumAndTop(pageNum);
        PageResult pageResult = new PageResult();
        pageResult.setCode(200);
        pageResult.setCount((int) articlePageInfo.getTotal());
        pageResult.setData(articlePageInfo.getList());
        pageResult.setMsg("置顶文章查询成功");
        pageResult.setSize(articlePageInfo.getSize());

        return pageResult;
    }

    @Override
    public PageResult mostCommentBySecId(Integer secId) {
        PageResult pageResult = null;
        PageNum pageNum = PageUtil.getPageNum(1, 10);
        PageInfo<TArticle> tArticlePageInfo = articleService.selectArticleByPageNumAndSecIdOrderByComment(pageNum, secId);
        if (tArticlePageInfo.getSize() == 0) {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setMsg("没有数据");
        } else {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setCount((int) tArticlePageInfo.getTotal());
            pageResult.setSize(tArticlePageInfo.getSize());
            pageResult.setData(tArticlePageInfo.getList());
            pageResult.setMsg("查询成功");
        }
        return pageResult;
    }

    @Override
    public PageResult mostHotBySecId(Integer secId) {
        PageResult pageResult = null;
        PageNum pageNum = PageUtil.getPageNum(1, 10);

        PageInfo<TArticle> tArticlePageInfo = articleService.selectArticleByPageNumAndSecIdOrderByHot(pageNum, secId);
        if (tArticlePageInfo.getSize() == 0) {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setMsg("没有数据");
        } else {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setCount((int) tArticlePageInfo.getTotal());
            pageResult.setSize(tArticlePageInfo.getSize());
            pageResult.setData(tArticlePageInfo.getList());
            pageResult.setMsg("查询成功");
        }
        return pageResult;
    }

    @Override
    public PageResult listCollectionByUserIdAndPageNum(TUserInfo loginUser, Integer page, Integer limit) {
        PageResult pageResult = null;
        if (loginUser == null) {
            pageResult = new PageResult();
            pageResult.setCount(-1);
            pageResult.setMsg("用户没有登录");
        } else {
            PageNum pageNum = PageUtil.getPageNum(page, limit);
            PageInfo<TCollection> collectionPageInfo = collectionService.selectCollectionByPageNumAndUserId(pageNum, loginUser.getUserId());
            if (collectionPageInfo.getSize() == 0) {
                pageResult = new PageResult();
                pageResult.setCode(200);
                pageResult.setMsg("没有数据");
            } else {
                pageResult = new PageResult();
                pageResult.setCode(200);
                pageResult.setMsg("查询成功");
                pageResult.setCount((int) collectionPageInfo.getTotal());
                pageResult.setSize(collectionPageInfo.getSize());
                pageResult.setData(collectionPageInfo.getList());
            }
        }
        return pageResult;
    }

    @Override
    public ArkResult modifyUserInfo(TUserInfo userInfo) {
        ArkResult arkResult = null;
        int i = userInfoService.updateUserInfo(userInfo);
        if (i == 0) {
            arkResult = new ArkResult(-1, "用户信息更改失败");
        } else {
            arkResult = new ArkResult(200, "用户信息更新成功");
        }

        return arkResult;
    }

    @Override
    public ArkResult modifyPass(TUserInfo loginUser, String nowPass, String pass) {
        ArkResult arkResult = null;
        if (loginUser == null) {
            arkResult = new ArkResult(-1, "修改失败");
        } else {
            String password = loginUser.getPassword();
            if (password.equals(nowPass)) {
                TUserInfo userInfo = new TUserInfo();
                userInfo.setUserId(loginUser.getUserId());
                userInfo.setPassword(pass);
                int i = userInfoService.updateUserInfo(userInfo);
                if (i == 0) {
                    arkResult = new ArkResult(-1, "修改失败");
                } else {
                    arkResult = new ArkResult(200, "密码修改成功");
                }
            } else {
                arkResult = new ArkResult(-1, "当前密码不正确");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult unCollection(TUserInfo loginUser, Integer colId) {
        ArkResult arkResult = null;
        if (loginUser == null || colId == null) {
            arkResult = new ArkResult(-1, "取消收藏失败");
        } else {
            int i = collectionService.deleteCollection(colId);
            if (i == 0) {
                arkResult = new ArkResult(-1, "取消收藏失败");
            } else {
                arkResult = new ArkResult(200, "取消收藏成功");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult topThis(Integer artId) {
        ArkResult arkResult = null;
        if (artId == null) {
            arkResult = new ArkResult(-1, "置顶失败");
        } else {
            TArticle article = new TArticle();
            article.setArtId(artId);
            article.setIsTop("1");
            int i = articleService.modifyArticle(article);
            if (i == 0) {
                arkResult = new ArkResult(-1, "置顶失败");
            } else {
                arkResult = new ArkResult(200, "置顶成功");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult unTopThis(Integer artId) {
        ArkResult arkResult = null;
        if (artId == null) {
            arkResult = new ArkResult(-1, "取消置顶失败");
        } else {
            TArticle article = new TArticle();
            article.setArtId(artId);
            article.setIsTop("0");
            int i = articleService.modifyArticle(article);
            if (i == 0) {
                arkResult = new ArkResult(-1, "取消置顶失败");
            } else {
                arkResult = new ArkResult(200, "取消置顶成功");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult delThis(Integer artId) {
        ArkResult arkResult = null;
        if (artId == null) {
            arkResult = new ArkResult(-1, "删除失败");
        } else {
            int i = articleService.deleteArticle(artId);
            if (i == 0) {
                arkResult = new ArkResult(-1, "删除失败");
            } else {
                arkResult = new ArkResult(200, "删除成功");
            }
        }

        return arkResult;
    }

    @Override
    public PageResult listArticleByCreate(Integer page, Integer limit) {
        PageResult pageResult = null;
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TArticle> articlePageInfo = articleService.selectArticleByPageNumAndCreate(pageNum);
        if (articlePageInfo != null && articlePageInfo.getSize() != 0) {
            pageResult = new PageResult();
            pageResult.setCode(200);
            pageResult.setCount((int) articlePageInfo.getTotal());
            pageResult.setSize(articlePageInfo.getSize());
            pageResult.setData(articlePageInfo.getList());
            pageResult.setMsg("查询成功");
        } else {
            pageResult = new PageResult();
            pageResult.setMsg("查询失败");
            pageResult.setCount(-1);
        }

        return pageResult;
    }

    @Override
    public ArkResult listMessageByUserId(TUserInfo loginUser) {
        ArkResult arkResult = null;
        if (loginUser == null) {
            arkResult = new ArkResult(-1, "用户未登录");
        } else {
            Integer userId = loginUser.getUserId();
            List<TMessage> messages = messageService.selectMessageByUserId(userId);
            if (messages != null && messages.size() != 0) {
                arkResult = new ArkResult(200, "信息查询成功");
                arkResult.setData(messages);
            } else {
                arkResult = new ArkResult(-1, "信息查询失败");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult removeMes(Integer mesId) {
        ArkResult arkResult = null;
        if (mesId == null) {
            arkResult = new ArkResult(-1, "删除失败");
        } else {
            int i = messageService.deleteMessage(mesId);
            if (i == 0) {
                arkResult = new ArkResult(-1, "删除失败");
            } else {
                arkResult = new ArkResult(200, "删除成功");
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult followThis(TUserInfo loginUser, Integer userId) {
        ArkResult arkResult = null;
        if (loginUser == null || userId == null) {
            arkResult = new ArkResult(-1, "关注失败");
        } else {
            Integer loginUserId = loginUser.getUserId();
            int i = followService.addFollow(loginUserId, userId);
            if (i == 0) {
                arkResult = new ArkResult(-1, "关注失败");
            } else {
                arkResult = new ArkResult(200, "关注成功");
                // 添加关注信息
                TMessage message = new TMessage();
                message.setMesTitle("关注信息");
                message.setFromUserId(loginUser.getUserId());
                message.setToUserId(userId);
                message.setMesType("0");
                message.setMesState("0");
                message.setCreate(new Date());
                messageService.insertMessage(message);
            }
        }

        return arkResult;
    }

    @Override
    public ArkResult isFollow(TUserInfo loginUser, Integer userId) {
        ArkResult arkResult = null;
        if (loginUser == null || userId == null) {
            arkResult = new ArkResult(-1, "用户未登录");
        } else {
            Integer loginUserId = loginUser.getUserId();
            boolean flag = followService.isFollow(loginUserId, userId);
            if (flag) {
                arkResult = new ArkResult(200);
            } else {
                arkResult = new ArkResult(-1, "用户未关注");
            }
        }

        return arkResult;
    }
}
