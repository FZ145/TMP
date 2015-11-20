package tmp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import tmp.bo.HistoryAndWeight;
import tmp.entity.ComponentHistory;
import tmp.entity.RenterHistory;

/**
 * Created by shining.cui on 2015/11/5.
 */
public class ListUtil {
    private ListUtil() {
    }

    public static List<HistoryAndWeight<RenterHistory>> getAvailableRenterHistory(List<RenterHistory> tableList,
            Integer daysThreshold) {
        List<HistoryAndWeight<RenterHistory>> list = new ArrayList<HistoryAndWeight<RenterHistory>>();
        // 获取当前时间
        DateTime nowTime = new DateTime();
        // 遍历数据库中的两个实体的所有交互历史，只取在时间范围内的历史记录
        for (RenterHistory history : tableList) {
            DateTime actionTime = new DateTime(history.getActionTime());
            int days = Days.daysBetween(actionTime, nowTime).getDays();
            if (days <= daysThreshold) {
                HistoryAndWeight<RenterHistory> historyHistoryAndWeight = new HistoryAndWeight<RenterHistory>();
                historyHistoryAndWeight.setHistory(history);
                // 根据记录时间进行权重衰减，包装成一个实体，方便进行信任度计算
                BigDecimal weight = Decay.decayByTime(history.getActionTime());
                historyHistoryAndWeight.setWeight(weight);
                list.add(historyHistoryAndWeight);
            }
        }
        // 返回在时间窗口内的历史记录集合
        return list;
    }

    public static List<HistoryAndWeight<ComponentHistory>> getAvailableComponentHistory(
            List<ComponentHistory> tableList, Integer daysThreshold) {
        List<HistoryAndWeight<ComponentHistory>> list = new ArrayList<HistoryAndWeight<ComponentHistory>>();
        // 获取当前时间
        DateTime nowTime = new DateTime();
        // 遍历数据库中的两个实体的所有交互历史，只取在时间范围内的历史记录
        for (ComponentHistory history : tableList) {
            DateTime actionTime = new DateTime(history.getActionTime());
            int days = Days.daysBetween(actionTime, nowTime).getDays();
            if (days <= daysThreshold) {
                HistoryAndWeight<ComponentHistory> historyHistoryAndWeight = new HistoryAndWeight<ComponentHistory>();
                historyHistoryAndWeight.setHistory(history);
                // 根据记录时间进行权重衰减，包装成一个实体，方便进行信任度计算
                BigDecimal weight = Decay.decayByTime(history.getActionTime());
                historyHistoryAndWeight.setWeight(weight);
                list.add(historyHistoryAndWeight);
            }
        }
        // 返回在时间窗口内的历史记录集合
        return list;
    }
}
