package tmp.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yuanyao on 2016/1/15.
 */
public class LoginResult<T> {
    private String EntityId;

    //向前段表明是何种身份
    private String indentifyCode;

  /*  private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
*/
    public String getEntityId() {
        return EntityId;
    }

    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public String getIndentifyCode() {
        return indentifyCode;
    }

    public void setIndentifyCode(String indentifyCode) {
        this.indentifyCode = indentifyCode;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResult{");
        sb.append("EntityId='").append(EntityId).append('\'');
        sb.append(", indentifyCode='").append(indentifyCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
