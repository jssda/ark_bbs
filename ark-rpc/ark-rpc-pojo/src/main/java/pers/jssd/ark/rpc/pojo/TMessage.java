package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TMessage implements Serializable {
    private Integer mesId;

    private String mesTitle;

    private String mesContent;

    private Integer fromUserId;

    private Integer toUserId;

    private String mesType;

    private String mesState;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    private static final long serialVersionUID = 1L;

    private TUserInfo fromUser;
    private TUserInfo toUser;

    public TUserInfo getFromUser() {
        return fromUser;
    }

    public void setFromUser(TUserInfo fromUser) {
        this.fromUser = fromUser;
    }

    public TUserInfo getToUser() {
        return toUser;
    }

    public void setToUser(TUserInfo toUser) {
        this.toUser = toUser;
    }

    public Integer getMesId() {
        return mesId;
    }

    public void setMesId(Integer mesId) {
        this.mesId = mesId;
    }

    public String getMesTitle() {
        return mesTitle;
    }

    public void setMesTitle(String mesTitle) {
        this.mesTitle = mesTitle == null ? null : mesTitle.trim();
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent == null ? null : mesContent.trim();
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType == null ? null : mesType.trim();
    }

    public String getMesState() {
        return mesState;
    }

    public void setMesState(String mesState) {
        this.mesState = mesState == null ? null : mesState.trim();
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
        sb.append(", mesId=").append(mesId);
        sb.append(", mesTitle=").append(mesTitle);
        sb.append(", mesContent=").append(mesContent);
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", mesType=").append(mesType);
        sb.append(", mesState=").append(mesState);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}