package pers.jssd.ark.rpc.pojo;

import java.io.Serializable;

public class TArtTypeArt implements Serializable {
    private Integer id;

    private Integer artTypeId;

    private Integer artId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtTypeId() {
        return artTypeId;
    }

    public void setArtTypeId(Integer artTypeId) {
        this.artTypeId = artTypeId;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", artTypeId=").append(artTypeId);
        sb.append(", artId=").append(artId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}