package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerArticleService;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TArticleType;
import pers.jssd.ark.rpc.service.TArticleService;
import pers.jssd.ark.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerArticleServiceImpl implements ManagerArticleService {

    private final TArticleService articleService;

    public ManagerArticleServiceImpl(TArticleService articleService) {this.articleService = articleService;}

    @Override
    public TableResult showArticle(Integer page, Integer limit) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TArticle> tArticlePageInfo = articleService.selectArticleByPageNum(pageNum);

        // 将分页查询结果封装成表格查询结果返回
        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tArticlePageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tArticlePageInfo.getList());

        return tableResult;
    }

    @Override
    public TableResult showUsersBy(Integer page, Integer limit, String key) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TArticle> tArticlePageInfo = articleService.selectArticleByPageNumAndKey(pageNum, key);

        // 将分页查询结果封装成表格查询结果返回
        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tArticlePageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tArticlePageInfo.getList());

        return tableResult;
    }

    @Override
    public ArkResult findTypeByArtId(Integer artId) {
        if (artId == null) {
            return new ArkResult(-1, "查询错误, 文章id为空");
        }
        List<TArticleType> artTypes = articleService.selectArtTypeByArtId(artId);
        ArkResult arkResult = new ArkResult(200, "查询成功");
        arkResult.setData(artTypes);
        return arkResult;
    }

    @Override
    public ArkResult findArticleById(Integer artId) {
        if (artId == null) {
            return new ArkResult(-1, "查询错误, 文章id为空");
        }
        TArticle article = articleService.selectArticleByArtId(artId);
        ArkResult arkResult = new ArkResult(200, "查询成功");
        arkResult.setData(article);

        return arkResult;
    }

    @Override
    public ArkResult addOrEditArticle(TArticle article) {
        Integer artId = article.getArtId();
        if (artId == null) {
            // 添加一个文章
            int i = articleService.addArticle(article);
            if (i == 1) {
                return new ArkResult(200, "文章添加成功");
            } else {
                return new ArkResult(-1, "文章添加失败");
            }
        } else {
            int i = articleService.modifyArticle(article);
            if (i == 1) {
                return new ArkResult(200, "文章修改成功");
            } else {
                return new ArkResult(-1, "文章修改失败");
            }
        }
    }

    @Override
    public ArkResult removeArticle(Integer artId) {

        int i = articleService.deleteArticle(artId);
        ArkResult arkResult = null;
        if (i == 0) {
            arkResult = new ArkResult(-1, "删除用户失败");
        } else {
            arkResult = new ArkResult(200, "删除用户成功");
        }

        return arkResult;
    }

    @Override
    public ArkResult removeArticles(ArrayList<Integer> artIds) {
        if (artIds.size() == 0) {
            return new ArkResult(200, "并没有选中任何用户");
        }
        int i = 0;
        for (Integer artId : artIds) {
            i += articleService.deleteArticle(artId);
        }
        ArkResult arkResult = null;
        if (i == artIds.size()) {
            arkResult = new ArkResult(200, "删除用户成功");
        } else {
            arkResult = new ArkResult(-1, "删除用户失败");
        }
        return arkResult;
    }
}
