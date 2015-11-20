package tmp.entity;

public class Component {
    private Integer id;

    private String uid;

    private String parentUid;

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

    public String getParentUid() {
        return parentUid;
    }

    public void setParentUid(String parentUid) {
        this.parentUid = parentUid == null ? null : parentUid.trim();
    }

    @Override
    public String toString() {
        return "Component{" + "id=" + id + ", uid='" + uid + '\'' + ", parentUid='" + parentUid + '\'' + '}';
    }
}