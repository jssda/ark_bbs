package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TArticleType;

import java.util.List;

/**
 * 文章服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface TArticleService {

    /**
     * 通过分页数据， 分页查询数据库中的数据
     *
     * @param pageNum 分页对象
     * @return 返回分页数据
     */
    PageInfo<TArticle> selectArticleByPageNum(PageNum pageNum);

    /**
     * 有条件的分页查询信息
     *
     * @param pageNum 分页信息
     * @param key     查询条件
     * @return 返回分页数据
     */
    PageInfo<TArticle> selectArticleByPageNumAndKey(PageNum pageNum, String key);

    /**
     * 通过文章id查询文章的类别
     *
     * @param artId 文章id
     * @return 返回查询出来的文章类别数组
     */
    List<TArticleType> selectArtTypeByArtId(Integer artId);

    /**
     * 通过文章id 查询出一篇文章
     *
     * @param artId 文章的id
     * @return 返回查询出的文章数据
     */
    TArticle selectArticleByArtId(Integer artId);

    /**
     * 分页查询指定板块的文章
     *
     * @param secId 板块id
     * @return 返回查询到的文章信息
     */
    PageInfo<TArticle> selectArticleByPageNumAndSecId(PageNum pageNum, Integer secId);

    /**
     * 添加一篇文章
     *
     * @param article 需要添加的文章
     * @return 添加的文章数量
     */
    int addArticle(TArticle article);

    /**
     * 有选择的修改文章
     *
     * @param article 文章实体类
     * @return 返回修改的文章数量
     */
    int modifyArticle(TArticle article);

    /**
     * 删除一篇文章
     *
     * @param artId 需要删除的文章id
     * @return 返回删除了几篇文章, 0就是删除失败
     */
    int deleteArticle(Integer artId);

    /**
     * 通过用户id, 分页查询文章信息, 时间倒叙排序
     */
    PageInfo<TArticle> selectArticleByPageNumAndUserId(PageNum pageNum, Integer userId);
}
