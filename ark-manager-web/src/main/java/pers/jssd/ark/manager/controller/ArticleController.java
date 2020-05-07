package pers.jssd.ark.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerArticleService;
import pers.jssd.ark.rpc.pojo.TArticle;

import java.util.ArrayList;

/**
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/manager/article")
public class ArticleController {

    private final ManagerArticleService articleService;

    public ArticleController(ManagerArticleService articleService) {this.articleService = articleService;}

    /**
     * 分页展示所有文章
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @return 返回分页信息
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableResult listArticle(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {

        return articleService.showArticle(page, limit);
    }

    /**
     * 带条件分页查询文章的信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @param key   查询的key
     * @return 返回分页的json数据
     */
    @RequestMapping(value = "listby", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableResult listArticleBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "") String key) {
        return articleService.showUsersBy(page, limit, key);
    }

    /**
     * 根据文章id, 查询文章类型
     *
     * @param artId 文章id
     * @return 返回查询到的文庄类型数数组
     */
    @RequestMapping(value = "findType/{artId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult findType(@PathVariable Integer artId) {
        return articleService.findTypeByArtId(artId);
    }

    /**
     * 根据文章id查询一篇文章
     *
     * @param artId 文章id
     * @return 返回查询到的文章
     */
    @RequestMapping(value = "findArticle/{artId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult findArticleById(@PathVariable Integer artId) {
        return articleService.findArticleById(artId);
    }


    /**
     * 添加文章
     *
     * @return 返回是否添加成功的信息
     */
    @RequestMapping(value = "addOrEditArticle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult addOrEditArticle(@RequestBody TArticle article) {

        return articleService.addOrEditArticle(article);
    }

    /**
     * 删除一篇文章
     *
     * @param artId 要删除的文章id
     * @return 返回响应信息
     */
    @RequestMapping(value = "removeArticle/{artId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult removeArticle(@PathVariable Integer artId) {

        return articleService.removeArticle(artId);
    }

    /**
     * 删除一批文章
     *
     * @param artIds 要删除的文章id
     * @return 返回响应信息
     */
    @RequestMapping(value = "removeArticles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ArkResult removeArticles(@RequestParam("artIds") ArrayList<Integer> artIds) {

        System.out.println("artIds = " + artIds);

        return articleService.removeArticles(artIds);
    }


}
