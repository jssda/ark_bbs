package pers.jssd.ark.rpc.pojo;

import java.io.Serializable;
import java.util.Date;

public class TFollow implements Serializable {
    private Integer followId;

    private Integer followUserId;

    private Integer followedNum;

    private Integer followrNum;

    private Date create;

    private Date update;

    private String followUser;

    private String followr;

    private static final long serialVersionUID = 1L;

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }

    public Integer getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(Integer followedNum) {
        this.followedNum = followedNum;
    }

    public Integer getFollowrNum() {
        return followrNum;
    }

    public void setFollowrNum(Integer followrNum) {
        this.followrNum = followrNum;
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

    public String getFollowUser() {
        return followUser;
    }

    public void setFollowUser(String followUser) {
        this.followUser = followUser == null ? null : followUser.trim();
    }

    public String getFollowr() {
        return followr;
    }

    public void setFollowr(String followr) {
        this.followr = followr == null ? null : followr.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", followId=").append(followId);
        sb.append(", followUserId=").append(followUserId);
        sb.append(", followedNum=").append(followedNum);
        sb.append(", followrNum=").append(followrNum);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", followUser=").append(followUser);
        sb.append(", followr=").append(followr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}