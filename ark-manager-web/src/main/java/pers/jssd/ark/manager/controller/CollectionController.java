package pers.jssd.ark.manager.controller;

import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerCollectionService;
import pers.jssd.ark.rpc.pojo.TCollection;

import java.util.List;

/**
 * 收藏管理
 *
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/manager/collection")
public class CollectionController {

    private final ManagerCollectionService collectionService;

    public CollectionController(ManagerCollectionService collectionService) {this.collectionService = collectionService;}

    /**
     * 分页查询所有收藏信息
     *
     * @param limit 每页多少条数据
     * @param page  当前页
     * @return 返回查询到的表格数据
     */
    @RequestMapping("listCollection")
    public TableResult listCollection(@RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return collectionService.listCollection(limit, page);
    }

    /**
     * 通过用户id分页查询所有的收藏信息
     *
     * @param limit     每页多少条信息
     * @param page      当前页
     * @param colUserId 查询的用户id
     * @return 返回查询到的收藏信息
     */
    @RequestMapping("listCollectionBy")
    public TableResult listCollectionBy(@RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page, Integer colUserId) {
        return collectionService.listCollectionBy(limit, page, colUserId);
    }

    /**
     * 删除一个收藏信息
     *
     * @param colId 要删除的收藏信息
     * @return 返回是否删除成功的响应信息
     */
    @RequestMapping("removeCollection/{colId}")
    public ArkResult removeCollection(@PathVariable Integer colId) {
        return collectionService.removeCollection(colId);
    }

    @RequestMapping("removeCollections")
    public ArkResult removeCollections(@RequestParam("colIds") List<Integer> colIds) {
        return collectionService.removeCollections(colIds);
    }

    /**
     * 添加一个收藏内容
     *
     * @param tCollection 需要添加的收藏内容
     * @return 返回是否添加成功的信息
     */
    @RequestMapping("addCollection")
    public ArkResult addCollection(@RequestBody TCollection tCollection) {
        return collectionService.addCollection(tCollection);
    }

}
