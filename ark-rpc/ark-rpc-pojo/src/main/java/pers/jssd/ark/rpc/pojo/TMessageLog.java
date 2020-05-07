package pers.jssd.ark.rpc.pojo;

import java.io.Serializable;
import java.util.Date;

public class TMessageLog implements Serializable {
    private Integer mesLogId;

    private Integer mesId;

    private Date create;

    private static final long serialVersionUID = 1L;

    public Integer getMesLogId() {
        return mesLogId;
    }

    public void setMesLogId(Integer mesLogId) {
        this.mesLogId = mesLogId;
    }

    public Integer getMesId() {
        return mesId;
    }

    public void setMesId(Integer mesId) {
        this.mesId = mesId;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mesLogId=").append(mesLogId);
        sb.append(", mesId=").append(mesId);
        sb.append(", create=").append(create);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}