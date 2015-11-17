package tmp.service;

import tmp.entity.Component;
import tmp.entity.Renter;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/6.
 */
public interface CompToRenterTrustService {
    //计算组件对租户的全局信任
    public BigDecimal calcCompToRenterTrust(Component component,Renter renter);

    //计算组件对租户的直接信任
    public BigDecimal calcCompToRenterDirectTrust(Component component,Renter renter);

    //计算组件对租户的间接信任
    public BigDecimal calcCompToRenterIndirectTrust(Component component,Renter renter);
}
