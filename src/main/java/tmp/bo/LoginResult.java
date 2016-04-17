package tmp.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yuanyao on 2016/1/15.
 */
public class LoginResult<T> {
    //实体id,即用户名
    private String EntityId;
    //身份识别码，（包括三种身份：renter，component，provider）
    private String indentifyCode;

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
