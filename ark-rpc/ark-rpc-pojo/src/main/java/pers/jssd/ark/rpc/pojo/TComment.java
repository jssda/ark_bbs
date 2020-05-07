package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TComment implements Serializable {
    private Integer comId;

    private String comContent;

    private Integer comArtId;

    private Integer comUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    private TUserInfo user;

    /**
     * 评论所在的文章
     */
    private TArticle article;

    public TUserInfo getUser() {
        return user;
    }

    public void setUser(TUserInfo user) {
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }


    public TArticle getArticle() {
        return article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent == null ? null : comContent.trim();
    }

    public Integer getComArtId() {
        return comArtId;
    }

    public void setComArtId(Integer comArtId) {
        this.comArtId = comArtId;
    }

    public Integer getComUserId() {
        return comUserId;
    }

    public void setComUserId(Integer comUserId) {
        this.comUserId = comUserId;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comId=").append(comId);
        sb.append(", comContent=").append(comContent);
        sb.append(", comArtId=").append(comArtId);
        sb.append(", comUserId=").append(comUserId);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}