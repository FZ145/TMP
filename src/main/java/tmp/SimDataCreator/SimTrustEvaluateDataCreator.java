package tmp.simDataCreator;

import org.springframework.stereotype.Service;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.Renter;
import tmp.service.CompToCompTrustService;
import tmp.service.CompToRenterTrustService;
import tmp.service.RenterToCompTrustService;
import tmp.service.RenterToRenterTrustService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by shining.cui on 2015/11/19.
 */
@Service("simTrustEvaluateDataCreator")
public class SimTrustEvaluateDataCreator {
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private CompToRenterTrustService compToRenterTrustService;
    @Resource
    private CompToCompTrustService compToCompTrustService;
    @Resource
    private RenterToCompTrustService renterToCompTrustService;
    @Resource
    private RenterToRenterTrustService renterToRenterTrustService;


    /**
     * 随即评估组件与租户的信任，生成评估数据插入数据库
     * 随机评估50次
     */

    public void createTrustEvaluateDataBetweenRenterAndComponent(int times) {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            int componentRandomIndex = random.nextInt(componentList.size());
            int renterRandomIndex = random.nextInt(renterList.size());
            Component randomComponent = componentList.get(componentRandomIndex);
            Renter randomRenter = renterList.get(renterRandomIndex);
            compToRenterTrustService.calcCompToRenterTrust(randomComponent, randomRenter);
            renterToCompTrustService.calcRenterToCompTrust(randomRenter, randomComponent);
        }
    }

    public void createTrustEvaluateDataBetweenRenters(int times) {
        List<Renter> renterList = renterMapper.selectAll();
        int size = renterList.size();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            int trustorRandomIndex = random.nextInt(size);
            int trusteeRandomIndex = random.nextInt(size);
            while (trusteeRandomIndex == trustorRandomIndex) {
                trusteeRandomIndex = random.nextInt(size);
            }
            Renter randomTrustorRenter = renterList.get(trustorRandomIndex);
            Renter randomTrusteeRenter = renterList.get(trusteeRandomIndex);
            renterToRenterTrustService.calcRenterToRenterTrust(randomTrustorRenter, randomTrusteeRenter);
            renterToRenterTrustService.calcRenterToRenterTrust(randomTrusteeRenter, randomTrustorRenter);
        }
    }

    public void createTrustEvaluateDataBetweenComponents(int times) {
        List<Component> componentList = componentMapper.selectAll();
        int size = componentList.size();
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            int trustorRandomIndex = random.nextInt(size);
            int trusteeRandomIndex = random.nextInt(size);
            while (trusteeRandomIndex == trustorRandomIndex) {
                trusteeRandomIndex = random.nextInt(size);
            }
            Component randomTrustorComponent = componentList.get(trustorRandomIndex);
            Component randomTrusteeComponent = componentList.get(trusteeRandomIndex);
            compToCompTrustService.calcCompToCompTrust(randomTrustorComponent, randomTrusteeComponent);
            compToCompTrustService.calcCompToCompTrust(randomTrusteeComponent, randomTrustorComponent);
        }
    }
}
