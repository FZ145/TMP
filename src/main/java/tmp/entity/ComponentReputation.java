package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ComponentReputation{
    private Integer id;

    private String uid;

    private String componentUid;

    private BigDecimal reputationValue;

    private Date createTime;

    public String getComponentUid() {
        return componentUid;
    }

    public void setComponentUid(String componentUid) {
        this.componentUid = componentUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getReputationValue() {
        return reputationValue;
    }

    public void setReputationValue(BigDecimal reputationValue) {
        this.reputationValue = reputationValue;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ComponentReputation{" + "id=" + id + ", uid='" + uid + '\'' + ", componentUid='" + componentUid + '\''
                + ", reputationValue=" + reputationValue + ", createTime=" + createTime + '}';
    }
}