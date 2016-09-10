package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuanyao on 2016/4/17.
 */
public class TrustEvidence {
    private Integer id;

    private String uid;

    private String trustorUid;

    private String trusteeUid;

    private String trustValue;

    private Date actionTime;

    private Integer evidenceType;

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Integer getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(Integer evidenceType) {
        this.evidenceType = evidenceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrusteeUid() {
        return trusteeUid;
    }

    public void setTrusteeUid(String trusteeUid) {
        this.trusteeUid = trusteeUid;
    }

    public String getTrustorUid() {
        return trustorUid;
    }

    public void setTrustorUid(String trustorUid) {
        this.trustorUid = trustorUid;
    }

    public String getTrustValue() {
        return trustValue;
    }

    public void setTrustValue(String trustValue) {
        this.trustValue = trustValue;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrustEvidence{");
        sb.append("actionTime=").append(actionTime);
        sb.append(", id=").append(id);
        sb.append(", uid='").append(uid).append('\'');
        sb.append(", trustorUid='").append(trustorUid).append('\'');
        sb.append(", trusteeUid='").append(trusteeUid).append('\'');
        sb.append(", trustValue='").append(trustValue).append('\'');
        sb.append(", evidenceType=").append(evidenceType);
        sb.append('}');
        return sb.toString();
    }
}
