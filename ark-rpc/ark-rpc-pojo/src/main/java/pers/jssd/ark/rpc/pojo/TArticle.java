package pers.jssd.ark.rpc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TArticle implements Serializable {
    private Integer artId;

    private Integer artUserId;

    private String artTitle;

    private Integer artHotNum;

    private Integer artLikeNum;

    private String isTop;

    private Integer artSecId;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date create;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date update;

    private String artContent;

    private Integer commentCount;

    public TUserInfo getUser() {
        return user;
    }

    public void setUser(TUserInfo user) {
        this.user = user;
    }

    // 文章的作者
    private TUserInfo user;

    // 文章类型
    private List<TArticleType> articleTypes;

    // 文章的板块信息
    private TSection section;

    // 文章类型id数组
    private List<Integer> typeIds;

    // 文章类型名数组
    private List<String> typeNames;

    public List<String> getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(List<String> typeNames) {
        this.typeNames = typeNames;
    }

    public List<Integer> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Integer> typeIds) {
        this.typeIds = typeIds;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TArticleType> getArticleTypes() {
        return articleTypes;
    }

    public void setArticleTypes(List<TArticleType> articleTypes) {
        this.articleTypes = articleTypes;
    }

    private static final long serialVersionUID = 1L;

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public Integer getArtUserId() {
        return artUserId;
    }

    public void setArtUserId(Integer artUserId) {
        this.artUserId = artUserId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle == null ? null : artTitle.trim();
    }

    public Integer getArtHotNum() {
        return artHotNum;
    }

    public void setArtHotNum(Integer artHotNum) {
        this.artHotNum = artHotNum;
    }

    public Integer getArtLikeNum() {
        return artLikeNum;
    }

    public void setArtLikeNum(Integer artLikeNum) {
        this.artLikeNum = artLikeNum;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop == null ? null : isTop.trim();
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

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent == null ? null : artContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", artId=").append(artId);
        sb.append(", artUserId=").append(artUserId);
        sb.append(", artTitle=").append(artTitle);
        sb.append(", artSecId=").append(artSecId);
        sb.append(", artHotNum=").append(artHotNum);
        sb.append(", artLikeNum=").append(artLikeNum);
        sb.append(", isTop=").append(isTop);
        sb.append(", create=").append(create);
        sb.append(", update=").append(update);
        sb.append(", artContent=").append(artContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getArtSecId() {
        return artSecId;
    }

    public void setArtSecId(Integer artSecId) {
        this.artSecId = artSecId;
    }

    public TSection getSection() {
        return section;
    }

    public void setSection(TSection section) {
        this.section = section;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TArticle article = (TArticle) o;
        return Objects.equals(artId, article.artId) && Objects.equals(artUserId, article.artUserId) && Objects.equals(artTitle, article.artTitle) && Objects.equals(artHotNum, article.artHotNum) && Objects.equals(artLikeNum, article.artLikeNum) && Objects.equals(isTop, article.isTop) && Objects.equals(artSecId, article.artSecId) && Objects.equals(create, article.create) && Objects.equals(update, article.update) && Objects.equals(artContent, article.artContent) && Objects.equals(commentCount, article.commentCount) && Objects.equals(user, article.user) && Objects.equals(articleTypes, article.articleTypes) && Objects.equals(section, article.section) && Objects.equals(typeIds, article.typeIds) && Objects.equals(typeNames, article.typeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artId, artUserId, artTitle, artHotNum, artLikeNum, isTop, artSecId, create, update, artContent, commentCount, user, articleTypes, section, typeIds, typeNames);
    }
}