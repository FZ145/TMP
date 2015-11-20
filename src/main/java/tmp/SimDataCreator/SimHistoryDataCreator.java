package tmp.simDataCreator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tmp.dao.ComponentHistoryMapper;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterHistoryMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.entity.Renter;
import tmp.entity.RenterHistory;

/**
 * Created by shining.cui on 2015/11/19.
 */
@Service("simHistoryDataCreator")
public class SimHistoryDataCreator {
    @Resource
    private ComponentHistoryMapper componentHistoryMapper;
    @Resource
    private RenterHistoryMapper renterHistoryMapper;
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private ComponentMapper componentMapper;

    /**
     * 每次运行随机生成以上随机组件与随机租户的的交互历史数据100条，并插入数据库。
     */
    public void createHistoryDataBetweenRenterAndComponent(int times) {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            ComponentHistory componentHistory = new ComponentHistory();
            componentHistory.setActionTime(new Date());
            // 跨层交互，类型为2
            componentHistory.setActionType(2);
            int randomComponentIndex = random.nextInt(componentList.size());
            int randomRenterIndex = random.nextInt(renterList.size());
            String randomComponentUid = componentList.get(randomComponentIndex).getUid();
            String randomRenterUid = renterList.get(randomRenterIndex).getUid();
            componentHistory.setTrustorUid(randomComponentUid);
            componentHistory.setTrusteeUid(randomRenterUid);
            componentHistory.setUid("" + System.currentTimeMillis() + randomComponentUid);
            double randomDouble = random.nextDouble();
            BigDecimal randomTrustValue = new BigDecimal(randomDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
            componentHistory.setTrustValue(randomTrustValue);
            componentHistoryMapper.insertSelective(componentHistory);

            RenterHistory renterHistory = new RenterHistory();
            renterHistory.setUid("" + System.currentTimeMillis() + randomRenterUid);
            renterHistory.setTrustorUid(randomRenterUid);
            renterHistory.setTrusteeUid(randomComponentUid);
            renterHistory.setActionType(2);
            renterHistory.setActionTime(new Date());
            double randomRenterDouble = random.nextDouble();
            BigDecimal randomRenterTrustValue = new BigDecimal(randomRenterDouble).setScale(3,
                    BigDecimal.ROUND_HALF_UP);
            renterHistory.setTrustValue(randomRenterTrustValue);
            renterHistoryMapper.insertSelective(renterHistory);
        }
    }

    /**
     * 每次运行随机生成以上随机组件与随机其他组件的的交互历史50次，数据100条，并插入数据库。
     */

    public void createHistoryDataBetweenComponents(int times) {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            int trustorRandomIndex = random.nextInt(componentList.size());
            int trusteeRandomIndex = random.nextInt(componentList.size());
            while (trusteeRandomIndex == trustorRandomIndex) {
                trusteeRandomIndex = random.nextInt(componentList.size());
            }
            String trustorUid = componentList.get(trustorRandomIndex).getUid();
            String trusteeUid = componentList.get(trusteeRandomIndex).getUid();

            ComponentHistory trustorCompoentHistory = new ComponentHistory();
            trustorCompoentHistory.setActionTime(new Date());
            // 层内交互，类型为1
            trustorCompoentHistory.setActionType(1);
            trustorCompoentHistory.setTrustorUid(trustorUid);
            trustorCompoentHistory.setTrusteeUid(trusteeUid);
            trustorCompoentHistory.setUid("" + System.currentTimeMillis() + trustorUid);
            double firstTrustvalueRandom = random.nextDouble();
            BigDecimal trustorRandomTrustValue = new BigDecimal(firstTrustvalueRandom).setScale(3,
                    BigDecimal.ROUND_HALF_UP);
            trustorCompoentHistory.setTrustValue(trustorRandomTrustValue);
            componentHistoryMapper.insertSelective(trustorCompoentHistory);

            // <由于是组内交互，所以双方都要入库一次交互记录
            ComponentHistory trusteeComponentHistory = new ComponentHistory();
            trusteeComponentHistory.setActionTime(new Date());
            trusteeComponentHistory.setActionType(1);
            trusteeComponentHistory.setTrustorUid(trusteeUid);
            trusteeComponentHistory.setTrusteeUid(trustorUid);
            trusteeComponentHistory.setUid("" + System.currentTimeMillis() + trusteeUid);
            double secondTrustvalueRandom = random.nextDouble();
            BigDecimal trusteeRandomTrustValue = new BigDecimal(secondTrustvalueRandom).setScale(3,
                    BigDecimal.ROUND_HALF_UP);
            trusteeComponentHistory.setTrustValue(trusteeRandomTrustValue);
            componentHistoryMapper.insertSelective(trusteeComponentHistory);
        }
    }

    /**
     * 每次运行随机生成以上随机租户与随机其他租户的的交互历史50次，数据100条，并插入数据库。
     */

    public void createHistoryDataBetweenRenters(int times) {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            int trustorRandomIndex = random.nextInt(renterList.size());
            int trusteeRandomIndex = random.nextInt(renterList.size());
            while (trusteeRandomIndex == trustorRandomIndex) {
                trusteeRandomIndex = random.nextInt(renterList.size());
            }
            String trustorUid = renterList.get(trustorRandomIndex).getUid();
            String trusteeUid = renterList.get(trusteeRandomIndex).getUid();
            RenterHistory trustorRenterHistory = new RenterHistory();
            trustorRenterHistory.setActionTime(new Date());
            // 层内交互，类型为1
            trustorRenterHistory.setActionType(1);
            trustorRenterHistory.setTrustorUid(trustorUid);
            trustorRenterHistory.setTrusteeUid(trusteeUid);
            trustorRenterHistory.setUid("" + System.currentTimeMillis() + trustorUid);
            double firstTrustvalueRandom = random.nextDouble();
            BigDecimal trustorRandomTrustValue = new BigDecimal(firstTrustvalueRandom).setScale(3,
                    BigDecimal.ROUND_HALF_UP);
            trustorRenterHistory.setTrustValue(trustorRandomTrustValue);
            renterHistoryMapper.insertSelective(trustorRenterHistory);

            // <由于是组内交互，所以双方都要入库一次交互记录
            RenterHistory trusteeRenterHistory = new RenterHistory();
            trusteeRenterHistory.setActionTime(new Date());
            trusteeRenterHistory.setActionType(1);
            trusteeRenterHistory.setTrustorUid(trusteeUid);
            trusteeRenterHistory.setTrusteeUid(trustorUid);
            trusteeRenterHistory.setUid("" + System.currentTimeMillis() + trusteeUid);
            double secondTrustvalueRandom = random.nextDouble();
            BigDecimal trusteeRandomTrustValue = new BigDecimal(secondTrustvalueRandom).setScale(3,
                    BigDecimal.ROUND_HALF_UP);
            trusteeRenterHistory.setTrustValue(trusteeRandomTrustValue);
            renterHistoryMapper.insertSelective(trusteeRenterHistory);
        }
    }

}
