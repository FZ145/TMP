package tmp.service;

import tmp.entity.Component;
import tmp.entity.Renter;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/6.
 */
public interface RenterToCompTrustService {
    //计算租户对组件的全局信任
    public BigDecimal calcRenterToCompTrust(Renter renter, Component component);

    //计算租户对组件的直接信任
    public BigDecimal calcRenterToCompDirectTrust(Renter renter, Component component);

    //计算租户对组件的间接信任
    public BigDecimal calcRenterToCompIndirectTrust(Renter renter, Component component);
}
