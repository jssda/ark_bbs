package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerSectionService;
import pers.jssd.ark.rpc.pojo.TSection;
import pers.jssd.ark.rpc.service.TSectionService;
import pers.jssd.ark.util.PageUtil;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerSectionServiceImpl implements ManagerSectionService {

    private final TSectionService sectionService;

    public ManagerSectionServiceImpl(TSectionService sectionService) {this.sectionService = sectionService;}

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
    public TableResult listSectionBy(Integer page, Integer limit, String key) {
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TSection> tSectionPageInfo = sectionService.selectSectionByPageNumAndKey(pageNum, key);

        TableResult tableResult = new TableResult();
        tableResult.setCode(0);
        tableResult.setMsg("查询成功");
        tableResult.setData(tSectionPageInfo.getList());
        tableResult.setCount((int) tSectionPageInfo.getTotal());

        return tableResult;
    }

    @Override
    public ArkResult removeSection(Integer secId) {
        if (secId == null) {
            return new ArkResult(-1, "删除失败, 板块id不可为空");
        }
        int i = sectionService.deleteSection(secId);
        if (i == 0) {
            return new ArkResult(-1, "删除失败");
        } else {
            return new ArkResult(200, "删除成功");
        }
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
    public ArkResult modifySection(Integer secId, String secTitle) {
        ArkResult arkResult = null;
        if (secId == null) {
            arkResult = new ArkResult(-1, "修改失败, 请检查板块id");
        }

        TSection tSection = new TSection();
        tSection.setSecId(secId);
        tSection.setSecTitle(secTitle);

        int i = sectionService.updateSection(tSection);
        if (i == 0) {
            arkResult = new ArkResult(-1, "修改失败");
        } else {
            arkResult = new ArkResult(20, "修改成功");
        }

        return arkResult;
    }

    @Override
    public ArkResult removeSections(List<Integer> secIds) {
        ArkResult arkResult = null;
        if (secIds == null) {
            arkResult = new ArkResult(-1, "删除错误, 请检查是否选中");
        }
        int i = sectionService.deleteSections(secIds);
        if (i == secIds.size()) {
            arkResult = new ArkResult(200, "删除成功");
        } else {
            arkResult = new ArkResult(-1, "删除错误");
        }
        return arkResult;
    }

    @Override
    public ArkResult addOrEditSection(TSection tSection) {
        ArkResult arkResult = null;
        if (tSection == null) {
            return new ArkResult(-1, "操作失败");
        }

        Integer secId = tSection.getSecId();
        if (secId != null) {
            int i = sectionService.updateSection(tSection);
            if (i == 0) {
                arkResult = new ArkResult(-1, "修改失败");
            } else {
                arkResult = new ArkResult(200, "修改成功");
            }
        } else {
            int i = sectionService.addSection(tSection);
            if (i == 0) {
                arkResult = new ArkResult(-1, "添加失败");
            } else {
                arkResult = new ArkResult(200, "添加成功");
            }
        }

        return arkResult;
    }
}
