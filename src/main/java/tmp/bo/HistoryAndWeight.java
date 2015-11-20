package tmp.bo;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/6.
 */
public class HistoryAndWeight<T> {
    private T history;

    private BigDecimal weight;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public T getHistory() {
        return history;
    }

    public void setHistory(T history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "HistoryAndWeight{" + "history=" + history + ", weight=" + weight + '}';
    }
}
