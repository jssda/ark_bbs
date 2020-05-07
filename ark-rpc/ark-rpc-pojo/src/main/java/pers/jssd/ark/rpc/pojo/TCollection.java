package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TCollection implements Serializable {
    private Integer colId;

    private Integer colUserId;

    private Integer colArtId;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    private TUserInfo user;

    private TArticle article;

    public TUserInfo getUser() {
        return user;
    }

    public void setUser(TUserInfo user) {
        this.user = user;
    }

    public TArticle getArticle() {
        return article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    private static final long serialVersionUID = 1L;

    public Integer getColId() {
        return colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public Integer getColUserId() {
        return colUserId;
    }

    public void setColUserId(Integer colUserId) {
        this.colUserId = colUserId;
    }

    public Integer getColArtId() {
        return colArtId;
    }

    public void setColArtId(Integer colArtId) {
        this.colArtId = colArtId;
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
        sb.append(", colId=").append(colId);
        sb.append(", colUserId=").append(colUserId);
        sb.append(", colArtId=").append(colArtId);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}