package tmp.service;

import tmp.entity.Component;
import tmp.entity.Renter;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/6.
 */
public interface RenterToRenterTrustService {
    //计算租户对租户的全局信任
    public BigDecimal calcRenterToRenterTrust(Renter trustor, Renter trustee);

    //计算租户对租户的直接信任
    public BigDecimal calcRenterToRenterDirectTrust(Renter trustor, Renter trustee);

    //计算租户对租户的间接信任
    public BigDecimal calcRenterToRenterIndirectTrust(Renter trustor, Renter trustee);
}
