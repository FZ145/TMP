package tmp.bo;

import java.util.List;

import tmp.entity.RenterHistory;
import tmp.entity.RenterTrustValue;


public class CloudRenter {
    private Integer id;

    private String uid;

    private List<RenterHistory> userHistory;

    private List<RenterTrustValue> userTrustValue;

    
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

    
    public List<RenterHistory> getUserHistory() {
    
        return userHistory;
    }

    
    public void setUserHistory(List<RenterHistory> userHistory) {
    
        this.userHistory=userHistory;
    }

    
    public List<RenterTrustValue> getUserTrustValue() {
    
        return userTrustValue;
    }

    
    public void setUserTrustValue(List<RenterTrustValue> userTrustValue) {
    
        this.userTrustValue=userTrustValue;
    }


    @Override
    public String toString() {

        return "CloudRenter [id=" + id + ", uid=" + uid + ", userHistory=" + userHistory
                + ", userTrustValue=" + userTrustValue + "]";
    }
    
}
