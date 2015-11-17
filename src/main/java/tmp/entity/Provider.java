package tmp.entity;

public class Provider {
    private Integer id;

    private String uid;

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

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                '}';
    }
}