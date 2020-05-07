package pers.jssd.ark.manager.controller;

import org.springframework.web.bind.annotation.*;
import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerSectionService;
import pers.jssd.ark.rpc.pojo.TSection;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/manager/section")
public class SectionController {

    private final ManagerSectionService sectionService;

    public SectionController(ManagerSectionService sectionService) {this.sectionService = sectionService;}

    /**
     * 查询所有的板块信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @return 返回查询到的表格数据
     */
    @RequestMapping("/list")
    public TableResult listSection(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return sectionService.listSection(page, limit);
    }

    /**
     * 带条件的分页查询板块信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @param key   查询关键字
     * @return 返回查询到的表格数据
     */
    @RequestMapping("listby")
    public TableResult listSectionBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "") String key) {
        return sectionService.listSectionBy(page, limit, key);
    }

    /**
     * 不分页的查询所有板块信息
     *
     * @param key 查询关键字
     * @return 返回查询到的板块信息
     */
    @RequestMapping("queryBy")
    public ArkResult querySectionBy(@RequestParam(defaultValue = "") String key) {
        return sectionService.querySectionBy(key);
    }

    /**
     * 删除一个板块内容
     *
     * @param secId 需要删除的板块id
     * @return 返回删除了几条内容
     */
    @RequestMapping("removeSection/{secId}")
    public ArkResult removeSection(@PathVariable Integer secId) {
        return sectionService.removeSection(secId);
    }

    /**
     * 修改板块内容
     *
     * @param secId    修改的id
     * @param secTitle 修改后的内容
     * @return 返回是否修改成功的响应信息
     */
    @RequestMapping("editSection")
    public ArkResult editSection(Integer secId, String secTitle) {
        return sectionService.modifySection(secId, secTitle);
    }

    /**
     * 批量删除板块信息
     *
     * @param secIds 要删除的板块信息容器
     * @return 返回是否删除成功的信息
     */
    @RequestMapping("removeSections")
    public ArkResult removeSections(@RequestParam("secIds") List<Integer> secIds) {
        return sectionService.removeSections(secIds);
    }

    /**
     * 添加或编辑一个板块信息
     *
     * @param tSection 需要添加的板块信息
     * @return 返回是否添加成功的响应信息
     */
    @RequestMapping("addOrEditSection")
    public ArkResult addOrEditSection(@RequestBody TSection tSection) {
        return sectionService.addOrEditSection(tSection);
    }

}
