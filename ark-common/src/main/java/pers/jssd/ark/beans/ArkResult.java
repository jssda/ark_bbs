package pers.jssd.ark.beans;

/**
 * @author jssdjing@gmail.com
 */
public class ArkResult {
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

    public ArkResult(int code) {
        this.code = code;
    }

    public ArkResult(int code, String msg) {
        this(code);
        this.msg = msg;
    }

    public static ArkResult ok() {
        return new ArkResult(200);
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
}
