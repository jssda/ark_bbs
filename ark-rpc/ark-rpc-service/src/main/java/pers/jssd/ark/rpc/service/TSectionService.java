package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TSection;

import java.util.List;

/**
 * 板块服务类
 *
 * @author jssdjing@gmail.com
 */
public interface TSectionService {

    /**
     * 分页查询板块信息
     *
     * @param pageNum 分页信息
     * @return 返回查询到的板块信息
     */
    PageInfo<TSection> selectSectionByPageNum(PageNum pageNum);

    /**
     * 带条件的查询板块信息
     *
     * @param pageNum 分页信息
     * @param key     查询条件
     * @return 返回查询到的表格数据
     */
    PageInfo<TSection> selectSectionByPageNumAndKey(PageNum pageNum, String key);

    /**
     * 删除指定的板块信息
     *
     * @param secId 需要删除的板块id
     * @return 返回删除成功的个数
     */
    int deleteSection(Integer secId);

    /**
     * 不分页的查询所有板块信息
     *
     * @param key 查询关键字
     * @return 返回查询到的所有板块信息
     */
    List<TSection> selectSectionByKey(String key);

    /**
     * 修改板块内容
     *
     * @return 返回修改了几条内容
     */
    int updateSection(TSection tSection);

    /**
     * 批量删除板块信息
     *
     * @param secIds 删除的板块id容器
     * @return 返回删除了多少条数据
     */
    int deleteSections(List<Integer> secIds);

    /**
     * 添加一个板块信息
     *
     * @param tSection 添加的板块信息
     * @return 返回添加了几条信息
     */
    int addSection(TSection tSection);

    /**
     * 通过板块id获取板块信息
     *
     * @return 返回获取到的板块信息
     */
    TSection selectSectionBySecId(Integer secId);



}
