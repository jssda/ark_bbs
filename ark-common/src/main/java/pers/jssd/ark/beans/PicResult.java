package pers.jssd.ark.beans;

import pers.jssd.ark.entity.Picture;

/**
 * 图片上传响应结果
 *
 * @author jssdjing@gmail.com
 */
public class PicResult {

    /**
     * 响应代码 0表示成功 -1表示失败
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 存放图片实体信息, 主要是图片的路径
     */
    private Picture data;

    public PicResult() {
    }

    public PicResult(Integer code, String msg, Picture data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Picture getData() {
        return data;
    }

    public void setData(Picture data) {
        this.data = data;
    }
}
