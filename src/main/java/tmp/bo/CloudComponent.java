package tmp.bo;

import java.util.List;

import tmp.entity.ComponentHistory;
import tmp.entity.ComponentTrustValue;

public class CloudComponent {
    private Integer id;

    private String parentUid;

    private String uid;

    private List<ComponentHistory> componentHistory;

    private List<ComponentTrustValue> componentTrustValue;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getParentUid() {

        return parentUid;
    }

    public void setParentUid(String parentUid) {

        this.parentUid = parentUid;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public List<ComponentHistory> getComponentHistory() {

        return componentHistory;
    }

    public void setComponentHistory(List<ComponentHistory> componentHistory) {

        this.componentHistory = componentHistory;
    }

    public List<ComponentTrustValue> getComponentTrustValue() {

        return componentTrustValue;
    }

    public void setComponentTrustValue(List<ComponentTrustValue> componentTrustValue) {

        this.componentTrustValue = componentTrustValue;
    }

    @Override
    public String toString() {

        return "CloudComponent [componentHistory=" + componentHistory + ", componentTrustValue=" + componentTrustValue
                + ", id=" + id + ", parentUid=" + parentUid + ", uid=" + uid + "]";
    }

}
