package pers.jssd.ark.beans;

/**
 * 分页实体类
 *
 * @author jssdjing@gmail.com
 */
public class PageResult {

    private Integer code;
    private String msg;
    private Object data;
    // 总数据量
    private Integer count;
    // 本页多少条数据
    private Integer size;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
