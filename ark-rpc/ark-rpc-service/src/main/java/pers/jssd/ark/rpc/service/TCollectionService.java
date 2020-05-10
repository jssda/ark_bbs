package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TCollection;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
public interface TCollectionService {

    /**
     * 分页查询收藏
     *
     * @param pageNum 分页信息
     * @return 查询到的收藏列表
     */
    PageInfo<TCollection> selectCollectionByPageNum(PageNum pageNum);

    /**
     * 分页查询指定的用户的收藏信息
     *
     * @param pageNum   分页信息
     * @param colUserId 用户id
     * @return 返回查询到的收藏信息
     */
    PageInfo<TCollection> selectCollectionByPageNumAndKey(PageNum pageNum, Integer colUserId);

    /**
     * 删除一个收藏信息
     *
     * @param colId 收藏信息的id
     * @return 返回删除了几条数据
     */
    int deleteCollection(Integer colId);

    /**
     * 批量删除收藏信息
     */
    int deleteCollections(List<Integer> colIds);

    /**
     * 添加收藏
     */
    int insertCollection(TCollection tCollection);

    /**
     * 查询指定人物是否收藏了指定文章
     */
    List<TCollection> selectCollectionByArtIdAndUserId(TCollection collection);

    /**
     * 查询指定用户的收藏文章信息
     *
     * @return 返回查询到的收藏信息
     */
    PageInfo<TCollection> selectCollectionByPageNumAndUserId(PageNum pageNum, Integer userId);


}
