package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.ArkResult;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.rpc.pojo.TSection;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
public interface ManagerSectionService {


    /**
     * 查询所有的板块信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @return 返回查询到的表格数据
     */
    TableResult listSection(Integer page, Integer limit);

    /**
     * 带条件的分页查询板块信息
     *
     * @param page  第几页
     * @param limit 每页多少条数据
     * @param key   查询关键字
     * @return 返回查询到的表格数据
     */
    TableResult listSectionBy(Integer page, Integer limit, String key);

    /**
     * 删除一个板块信息
     *
     * @param secId 需要删除的板块id
     * @return 返回删除的板块数量
     */
    ArkResult removeSection(Integer secId);

    /**
     * 不分页的查询所有板块信息
     *
     * @param key 查询关键字
     * @return 返回查询到的分页数据
     */
    ArkResult querySectionBy(String key);

    /**
     * 修改板块内软三
     *
     * @param secId    板块的id
     * @param secTitle 板块的内容
     * @return 返回修改是否成功的信息
     */
    ArkResult modifySection(Integer secId, String secTitle);

    /**
     * 批量删除板块内容
     *
     * @param secIds 删除的板块信息容器
     * @return 返回是否删除成功
     */
    ArkResult removeSections(List<Integer> secIds);

    /**
     * 添加或编辑一个板块信息
     *
     * @param tSection 添加的板块信息
     * @return 返回是否添加成功的响应信息
     */
    ArkResult addOrEditSection(TSection tSection);
}
