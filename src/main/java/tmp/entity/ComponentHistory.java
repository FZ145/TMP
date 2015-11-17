package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ComponentHistory {
    private Integer id;

    private String uid;

    private String trustorUid;

    private String trusteeUid;

    private BigDecimal trustValue;

    private Date actionTime;

    private Integer actionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getTrustorUid() {
        return trustorUid;
    }

    public void setTrustorUid(String trustorUid) {
        this.trustorUid = trustorUid == null ? null : trustorUid.trim();
    }

    public String getTrusteeUid() {
        return trusteeUid;
    }

    public void setTrusteeUid(String trusteeUid) {
        this.trusteeUid = trusteeUid == null ? null : trusteeUid.trim();
    }

    public BigDecimal getTrustValue() {
        return trustValue;
    }

    public void setTrustValue(BigDecimal trustValue) {
        this.trustValue = trustValue;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "ComponentHistory{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", trustorUid='" + trustorUid + '\'' +
                ", trusteeUid='" + trusteeUid + '\'' +
                ", trustValue=" + trustValue +
                ", actionTime=" + actionTime +
                ", actionType=" + actionType +
                '}';
    }
}