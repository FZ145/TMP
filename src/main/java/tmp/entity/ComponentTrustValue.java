package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ComponentTrustValue {
    private Integer id;

    private String uid;

    private String trustorUid;

    private String trusteeUid;

    private BigDecimal trustValue;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComponentTrustValue{");
        sb.append("id=").append(id);
        sb.append(", uid='").append(uid).append('\'');
        sb.append(", trustorUid='").append(trustorUid).append('\'');
        sb.append(", trusteeUid='").append(trusteeUid).append('\'');
        sb.append(", trustValue=").append(trustValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", actionType=").append(actionType);
        sb.append('}');
        return sb.toString();
    }
}