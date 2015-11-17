package tmp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProviderTrustValue {
    private Integer id;

    private String uid;

    private String providerUid;

    private BigDecimal trustValue;

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

    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid == null ? null : providerUid.trim();
    }


    public BigDecimal getTrustValue() {
        return trustValue;
    }

    public void setTrustValue(BigDecimal trustValue) {
        this.trustValue = trustValue;
    }

    public Date getCreatetime() {
        return createTime;
    }

    public void setCreatetime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProviderTrustValue{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", providerUid='" + providerUid + '\'' +
                ", trustValue=" + trustValue +
                ", createTime=" + createTime +
                '}';
    }
}