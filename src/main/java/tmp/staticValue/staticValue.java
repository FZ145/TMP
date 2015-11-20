package tmp.staticvalue;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/6.
 */
public class StaticValue {
    // 历史记录时间窗口大小
    public static final int DAYS_THRESHOLD = 5;
    // 交互历史次数阈值，决定直接信任与间接信任的动态分配比例
    public static final int ACTIVE_TIMES_THRESHOLD = 100;
    // 推荐实体与请求实体没有信任的时候，默认为0.25
    public static final BigDecimal DEFAULT_TRUST_VALUE = new BigDecimal(0.25);
    private StaticValue() {
    }
}
