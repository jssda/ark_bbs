package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TArticle;

import java.util.ArrayList;

/**
 * 文章的后台服务类
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerArticleService {

    /**
     * 展示所有文章
     *
     * @param page  第几页的分页信息
     * @param limit 每页多少条记录
     * @return 返回查询到的分页用户信息
     */
    TableResult showArticle(Integer page, Integer limit);

    /**
     * 有条件的分页查询文章
     *
     * @param page  第几页数据
     * @param limit 每页显示多少条数据
     * @param key   查询条件
     * @return 返回查询到的分页用户数据
     */
    TableResult showUsersBy(Integer page, Integer limit, String key);

    /**
     * 查询文章的类别信息
     *
     * @param artId 文章id
     * @return 返回结果集
     */
    ArkResult findTypeByArtId(Integer artId);

    /**
     * 添加或者修改文章
     *
     * @param article 文章实体类
     * @return 返回是否添加或者修改成功
     */
    ArkResult addOrEditArticle(TArticle article);

    /**
     * 删除一篇文章
     *
     * @param artId 删除的文章id
     * @return 返回前端响应信息
     */
    ArkResult removeArticle(Integer artId);

    /**
     * 删除一批文章
     *
     * @param artIds 文章id
     * @return 返回前端响应信息
     */
    ArkResult removeArticles(ArrayList<Integer> artIds);

    /**
     * 通过文章类型查询一篇文章
     *
     * @param artId 文章的id
     * @return 返回查询到的文章
     */
    ArkResult findArticleById(Integer artId);
}
