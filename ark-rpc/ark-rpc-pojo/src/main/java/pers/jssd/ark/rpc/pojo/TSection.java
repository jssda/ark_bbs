package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TSection implements Serializable {
    private Integer secId;

    private Integer secUserId;

    private String secTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    // 板块用户信息
    private TUserInfo user;

    public TUserInfo getUser() {
        return user;
    }

    public void setUser(TUserInfo user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public Integer getSecUserId() {
        return secUserId;
    }

    public void setSecUserId(Integer secUserId) {
        this.secUserId = secUserId;
    }

    public String getSecTitle() {
        return secTitle;
    }

    public void setSecTitle(String secTitle) {
        this.secTitle = secTitle == null ? null : secTitle.trim();
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
        sb.append(", secId=").append(secId);
        sb.append(", secUserId=").append(secUserId);
        sb.append(", secTitle=").append(secTitle);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}