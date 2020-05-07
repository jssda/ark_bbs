package pers.jssd.ark.beans;

import java.io.Serializable;

/**
 * 分页请求携带的数据 第几页？ 每页多少行数据
 *
 * @author jssdjing@gmail.com
 */
public class PageNum implements Serializable {
    
    /**
     * 第几页
     */
    private Integer page;
    /**
     * 每页多少条数据
     */
    private Integer limit;
    
    public PageNum() {
    }
    
    public PageNum(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
