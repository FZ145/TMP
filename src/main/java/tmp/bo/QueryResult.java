package tmp.bo;

import java.util.List;

/**
 * Created by yuanyao on 2016/3/21.
 */
public class QueryResult<T> {
    private List<T>  result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryResult{");
        sb.append("result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
