package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TCommentMulti implements Serializable {
    private Integer comMulId;

    private String comMulContent;

    private Integer comMulCommentId;

    private Integer replayId;

    private String replyType;

    private Integer commentMulUserId;

    private Integer targetId;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    /**
     * 评论发布者
     */
    private TUserInfo commentMulUser;

    /**
     * 评论目标用户信息
     */
    private TUserInfo targetUser;

    public TUserInfo getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(TUserInfo targetUser) {
        this.targetUser = targetUser;
    }

    public TUserInfo getCommentMulUser() {
        return commentMulUser;
    }

    public void setCommentMulUser(TUserInfo commentMulUser) {
        this.commentMulUser = commentMulUser;
    }

    private static final long serialVersionUID = 1L;

    public Integer getComMulId() {
        return comMulId;
    }

    public void setComMulId(Integer comMulId) {
        this.comMulId = comMulId;
    }

    public String getComMulContent() {
        return comMulContent;
    }

    public void setComMulContent(String comMulContent) {
        this.comMulContent = comMulContent == null ? null : comMulContent.trim();
    }

    public Integer getComMulCommentId() {
        return comMulCommentId;
    }

    public void setComMulCommentId(Integer comMulCommentId) {
        this.comMulCommentId = comMulCommentId;
    }

    public Integer getReplayId() {
        return replayId;
    }

    public void setReplayId(Integer replayId) {
        this.replayId = replayId;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType == null ? null : replyType.trim();
    }

    public Integer getCommentMulUserId() {
        return commentMulUserId;
    }

    public void setCommentMulUserId(Integer commentMulUserId) {
        this.commentMulUserId = commentMulUserId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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
        sb.append(", comMulId=").append(comMulId);
        sb.append(", comMulContent=").append(comMulContent);
        sb.append(", comMulCommentId=").append(comMulCommentId);
        sb.append(", replayId=").append(replayId);
        sb.append(", replyType=").append(replyType);
        sb.append(", commentMulUserId=").append(commentMulUserId);
        sb.append(", targetId=").append(targetId);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}