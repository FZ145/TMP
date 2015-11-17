package tmp.staticValue;

/**
 * Created by shining.cui on 2015/11/6.
 */
public class staticValue {
    //历史记录时间窗口大小
    public static final int daysThreshold = 5;
    //交互历史次数阈值，决定直接信任与间接信任的动态分配比例
    public static final int activeTimesThreshold = 100;
}
