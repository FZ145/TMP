package tmp.service;

import java.math.BigDecimal;

import tmp.entity.Component;
import tmp.entity.Renter;

/**
 * Created by shining.cui on 2015/11/6.
 */
public interface CompToRenterTrustService {
    // 计算组件对租户的全局信任
    public BigDecimal calcCompToRenterTrust(Component component, Renter renter);
}
