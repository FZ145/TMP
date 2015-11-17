package tmp.bo;

import java.util.List;

import tmp.entity.Component;
import tmp.entity.ProviderTrustValue;


public class CloudProvider {
    private Integer id;

    private String uid;

    private List<Component> componentList;
    
    private List<ProviderTrustValue> providerTrustValue;

    
    public Integer getId() {
    
        return id;
    }

    
    public void setId(Integer id) {
    
        this.id=id;
    }

    
    public String getUid() {
    
        return uid;
    }

    
    public void setUid(String uid) {
    
        this.uid=uid;
    }

    
    public List<Component> getComponentList() {
    
        return componentList;
    }

    
    public void setComponentList(List<Component> componentList) {
    
        this.componentList=componentList;
    }

    
    public List<ProviderTrustValue> getProviderTrustValue() {
    
        return providerTrustValue;
    }

    
    public void setProviderTrustValue(List<ProviderTrustValue> providerTrustValue) {
    
        this.providerTrustValue=providerTrustValue;
    }


    @Override
    public String toString() {

        return "CloudProvider [componentList=" + componentList + ", id=" + id
                + ", providerTrustValue=" + providerTrustValue + ", uid=" + uid + "]";
    }
    
}
