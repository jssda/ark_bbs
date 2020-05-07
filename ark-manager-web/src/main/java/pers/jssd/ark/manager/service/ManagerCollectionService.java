package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TCollection;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
public interface ManagerCollectionService {

    /**
     * 分页查询所有收藏信息
     *
     * @param limit 每页多少条数据
     * @param page  当前页
     * @return 返回查询到的表格信息
     */
    TableResult listCollection(Integer limit, Integer page);

    /**
     * 查询指定用户的收藏信息, 分页查询
     *
     * @param limit     每页多少条数据
     * @param page      当前也
     * @param colUserId 收藏用户id
     * @return 返回查询到首层信息
     */
    TableResult listCollectionBy(Integer limit, Integer page, Integer colUserId);

    /**
     * 删除一个收藏信息
     *
     * @param colId 需要删除的收藏的id
     * @return 返回是否删除成功
     */
    ArkResult removeCollection(Integer colId);

    /**
     * 批量删除信息
     */
    ArkResult removeCollections(List<Integer> colIds);

    /**
     * 添加文章
     */
    ArkResult addCollection(TCollection tCollection);
}
