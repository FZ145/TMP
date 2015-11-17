package tmp.util;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shining.cui on 2015/11/6.
 */
public class Decay {
    //根据时间进行衰减，这里先采取简单衰减策略，以后根据实验改进
    public static BigDecimal decayByTime(Date time) {
        DateTime actionTime = new DateTime(time);
        DateTime nowTime = new DateTime();
        int days = Days.daysBetween(nowTime, actionTime).getDays();
        if (days ==0) {
            return BigDecimal.ONE;
        }else if (days == 1) {
            return new BigDecimal(0.96);
        }else if (days == 2) {
            return new BigDecimal(0.92);
        }else if (days == 3) {
            return new BigDecimal(0.88);
        }else if (days == 4) {
            return new BigDecimal(0.84);
        } else {
            return new BigDecimal(0.80);
        }
    }
}
