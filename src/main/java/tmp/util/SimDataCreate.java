package tmp.util;

import org.springframework.stereotype.Service;
import tmp.dao.ComponentHistoryMapper;
import tmp.dao.RenterHistoryMapper;
import tmp.entity.ComponentHistory;
import tmp.entity.RenterHistory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * Created by shining.cui on 2015/11/19.
 */
@Service("simDataCreate")
public class SimDataCreate {
    @Resource
    private ComponentHistoryMapper componentHistoryMapper;
    @Resource
    private RenterHistoryMapper renterHistoryMapper;



    private final String[] components = {"component1", "component2", "component3", "component4", "component5", "component6",};
    private final String[] renters = {"renter1", "renter2", "renter3", "renter4"};

    /**
     * 每次运行随机生成以上随机组件与随机租户的的交互历史数据10条，并插入数据库。
     */
    public  void createDataBetweenRenterAndComponent() {
        for (int i = 0; i < 10; i++) {
            ComponentHistory componentHistory = new ComponentHistory();
            Date nowDate = new Date();
            componentHistory.setActionTime(nowDate);
            //跨层交互，类型为2
            componentHistory.setActionType(2);
            Random random = new Random();
            int randomComponentIndex = random.nextInt(6);
            int randomRenterIndex = random.nextInt(4);
            String randomComponentUid = components[randomComponentIndex];
            String randomRenterUid = renters[randomRenterIndex];
            componentHistory.setTrustorUid(randomComponentUid);
            componentHistory.setTrusteeUid(randomRenterUid);
            componentHistory.setUid("" + nowDate.getTime() + randomComponentUid);
            double randomDouble = random.nextDouble();
            BigDecimal randomTrustValue = new BigDecimal(randomDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
            componentHistory.setTrustValue(randomTrustValue);
            componentHistoryMapper.insertSelective(componentHistory);

            RenterHistory renterHistory = new RenterHistory();
            renterHistory.setUid("" + nowDate.getTime() + randomRenterUid);
            renterHistory.setTrustorUid(randomRenterUid);
            renterHistory.setTrusteeUid(randomComponentUid);
            renterHistory.setActionType(2);
            renterHistory.setActionTime(nowDate);
            double randomRenterDouble = random.nextDouble();
            BigDecimal randomRenterTrustValue = new BigDecimal(randomRenterDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
            renterHistory.setTrustValue(randomRenterTrustValue);
            renterHistoryMapper.insertSelective(renterHistory);
        }
    }
}
