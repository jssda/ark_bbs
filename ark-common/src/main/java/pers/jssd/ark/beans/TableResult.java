package pers.jssd.ark.beans;

import java.io.Serializable;

/**
 * 数据响应封装
 *
 * @author jssdjing@gmail.com
 */
public class TableResult implements Serializable {
    
    /**
     * 响应状态码
     */
    private Integer code;
    
    
    /**
     * 响应内容
     */
    private Object data;
    
    
    /**
     * 响应信息
     */
    private String msg;
    
    /**
     * 响应的数据数量
     */
    private Integer count;
    
    public TableResult(Integer code, Object data, String msg, Integer count) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
    }
    
    public TableResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public TableResult(Object data) {
        this(200, "ok");
        this.data = data;
    }
    
    public TableResult(Object data, Integer count) {
        this(200, "ok");
        this.data = data;
        this.count = count;
    }
    
    public TableResult() {
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    /**
     * 返回成功的响应信息
     * <pre>
     *     响应状态码为200, 响应消息为ok, 响应内容为null
     * </pre>
     *
     * @return 返回响应封装对象EgoResult
     */
    public static TableResult ok() {
        return new TableResult(null);
    }
    
}
