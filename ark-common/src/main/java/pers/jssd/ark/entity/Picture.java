package pers.jssd.ark.entity;

/**
 * 图片文件实体, 图片名字和图片路径
 *
 * @author jssdjing@gmail.com
 */
public class Picture {

    private String src;

    /**
     * 图片名称
     */
    private String title;

    public Picture() {
    }

    public Picture(String src) {
        this.src = src;
    }

    public Picture(String src, String title) {
        this.src = src;
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
