package tmp.util;

import org.springframework.stereotype.Service;
import tmp.dao.ComponentMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.Renter;
import tmp.service.CompToRenterTrustService;
import tmp.service.RenterToCompTrustService;

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
    private RenterToCompTrustService renterToCompTrustService;

    /**
     * 随即评估组件与租户的信任，生成评估数据插入数据库
     */

    public void createTrustEvaluateDataBetweenRenterAndComponent() {
        List<Component> componentList = componentMapper.selectAll();
        List<Renter> renterList = renterMapper.selectAll();
        Random random = new Random();
        int componentRandomIndex = random.nextInt(componentList.size());
        int renterRandomIndex = random.nextInt(renterList.size());
        Component randomComponent = componentList.get(componentRandomIndex);
        Renter randomRenter = renterList.get(renterRandomIndex);
        compToRenterTrustService.calcCompToRenterTrust(randomComponent, randomRenter);
        renterToCompTrustService.calcRenterToCompTrust(randomRenter,randomComponent);
    }

    public void createTrustEvaluateDataBetweenRenters() {

    }

    public void createTrustEvaluateDataBetweenComponents() {

    }
}
