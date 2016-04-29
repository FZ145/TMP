package tmp.entity;


import java.util.Date;

/**
 * Created by yuanyao on 2016/4/17.
 */
public class EvidenceWeight {
    private Integer id;

    private String uid;

    private String evidenceWeight;

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

    public String getEvidenceWeight() {
        return evidenceWeight;
    }

    public void setEvidenceWeight(String evidenceWeight) {
        this.evidenceWeight = evidenceWeight;
    }

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
        this.uid = uid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EvidenceWeight{");
        sb.append("actionTime=").append(actionTime);
        sb.append(", id=").append(id);
        sb.append(", uid='").append(uid).append('\'');
        sb.append(", evidenceWeight='").append(evidenceWeight).append('\'');
        sb.append(", evidenceType=").append(evidenceType);
        sb.append('}');
        return sb.toString();
    }
}
