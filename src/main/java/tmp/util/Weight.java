package tmp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by shining.cui on 2015/11/6.
 */
public class Weight {
    private Weight() {
    }

    public static BigDecimal calcDirectTrustWeight(int directTimes, int totalTimes) {

        BigDecimal divisor2 = new BigDecimal(totalTimes + directTimes * 4 + 1);
        BigDecimal divisor1 = new BigDecimal(directTimes * 5 + 1);
        BigDecimal weight = divisor1.divide(divisor2, 4, RoundingMode.HALF_UP);
        return weight;
    }
}
