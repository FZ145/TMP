package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RenterReputation extends AbstractReputation  {
    private Integer id;

    private String uid;

    private String renterUid;

    private BigDecimal reputationValue;

    private Date createTime;

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

    public String getRenterUid() {
        return renterUid;
    }

    public void setRenterUid(String renterUid) {
        this.renterUid = renterUid == null ? null : renterUid.trim();
    }

    public BigDecimal getReputationValue() {
        return reputationValue;
    }

    public void setReputationValue(BigDecimal reputationValue) {
        this.reputationValue = reputationValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RenterReputation{" + "id=" + id + ", uid='" + uid + '\'' + ", renterUid='" + renterUid + '\''
                + ", reputationValue=" + reputationValue + ", createTime=" + createTime + '}';
    }
}