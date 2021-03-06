package tmp.simdata;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tmp.dao.ComponentMapper;
import tmp.dao.ProviderMapper;
import tmp.dao.RenterMapper;
import tmp.entity.Component;
import tmp.entity.Provider;
import tmp.entity.Renter;
import tmp.service.ComponentReputationService;
import tmp.service.ProviderReputationService;
import tmp.service.RenterReputationService;

/**
 * Created by shining.cui on 2015/11/20.
 */
@Service("simReputationDataCreator")
public class SimReputationDataCreator {
    @Resource
    private RenterMapper renterMapper;
    @Resource
    private RenterReputationService renterReputationService;
    @Resource
    private ComponentMapper componentMapper;
    @Resource
    private ComponentReputationService componentReputationService;
    @Resource
    private ProviderReputationService providerReputationService;
    @Resource
    private ProviderMapper providerMapper;

    public void createReputationDataOfRenters() {
        List<Renter> renters = renterMapper.selectAll();
        for (Renter renter : renters) {
            renterReputationService.calcRenterReputation(renter);
        }
    }

    public void createReputationDataOfComponents() {
        List<Component> components = componentMapper.selectAll();
        for (Component component : components) {
            componentReputationService.calcComponentReputation(component);
        }
    }

    public void createReputationDataOfProviders() {
        List<Provider> providers = providerMapper.selectAll();
        for (Provider provider : providers) {
            providerReputationService.calcProviderReputation(provider);
        }
    }
}
