package tmp.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shining.cui on 2015/12/8.
 */
public class ReputationData {

    //1组件 2租户 3云服务提供商
    private Integer reputationType;

    private String entityUid;

    private List<BigDecimal> reputationList;

    public String getEntityUid() {
        return entityUid;
    }

    public void setEntityUid(String entityUid) {
        this.entityUid = entityUid;
    }

    public List<BigDecimal> getReputationList() {
        return reputationList;
    }

    public void setReputationList(List<BigDecimal> reputationList) {
        this.reputationList = reputationList;
    }

    public Integer getReputationType() {
        return reputationType;
    }

    public void setReputationType(Integer reputationType) {
        this.reputationType = reputationType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReputationDate{");
        sb.append("entityUid='").append(entityUid).append('\'');
        sb.append(", reputationType=").append(reputationType);
        sb.append(", reputationList=").append(reputationList);
        sb.append('}');
        return sb.toString();
    }
}
