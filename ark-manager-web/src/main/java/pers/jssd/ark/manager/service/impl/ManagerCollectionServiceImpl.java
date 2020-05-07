package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerCollectionService;
import pers.jssd.ark.rpc.pojo.TCollection;
import pers.jssd.ark.rpc.service.TCollectionService;
import pers.jssd.ark.util.PageUtil;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerCollectionServiceImpl implements ManagerCollectionService {

    private final TCollectionService collectionService;

    public ManagerCollectionServiceImpl(TCollectionService collectionService) {this.collectionService = collectionService;}

    @Override
    public TableResult listCollection(Integer limit, Integer page) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TCollection> tCollectionPageInfo = collectionService.selectCollectionByPageNum(pageNum);

        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tCollectionPageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tCollectionPageInfo.getList());

        return tableResult;
    }

    @Override
    public TableResult listCollectionBy(Integer limit, Integer page, Integer colUserId) {
        if (colUserId == null) {
            return new TableResult(-1, "查询失败");
        }
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TCollection> tCollectionPageInfo = collectionService.selectCollectionByPageNumAndKey(pageNum, colUserId);

        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setCount((int) tCollectionPageInfo.getTotal());
        tableResult.setMsg("查询成功");
        tableResult.setData(tCollectionPageInfo.getList());
        return tableResult;
    }

    @Override
    public ArkResult removeCollection(Integer colId) {
        if (colId == null) {
            return new ArkResult(-1, "删除失败");
        }
        int i = collectionService.deleteCollection(colId);
        if (i == 0) {
            return new ArkResult(-1, "删除失败");
        } else {
            return new ArkResult(200, "删除成功");
        }
    }

    @Override
    public ArkResult removeCollections(List<Integer> colIds) {
        if (colIds == null) {
            return new ArkResult(-1, "删除失败");
        }
        int i = collectionService.deleteCollections(colIds);
        if (i == colIds.size()) {
            return new ArkResult(200, "删除成功");
        } else {
            return new ArkResult(-1, "删除失败");
        }
    }

    @Override
    public ArkResult addCollection(TCollection tCollection) {
        if (tCollection == null) {
            return new ArkResult(-1, "添加失败");
        }
        int i = collectionService.insertCollection(tCollection);
        if (i == 1) {
            return new ArkResult(200, "添加成功");
        } else {
            return new ArkResult(-1, "添加失败");
        }
    }
}
