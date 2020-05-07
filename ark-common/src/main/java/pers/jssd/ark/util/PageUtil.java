package pers.jssd.ark.util;

import pers.jssd.ark.beans.PageNum;

/**
 * @author jssdjing@gmail.com
 */
public class PageUtil {
    //取得分页信息
    public static PageNum getPageNum(Integer page, Integer limit) {
        // 封装分页信息
        PageNum pageNum = new PageNum();
        if (page != null) {
            pageNum.setPage(page);
        } else {
            pageNum.setPage(1);
        }
        if (limit != null) {
            pageNum.setLimit(limit);
        } else {
            pageNum.setLimit(10);
        }
        return pageNum;
    }
}
