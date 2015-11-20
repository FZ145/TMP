package tmp.service;

import java.math.BigDecimal;

import tmp.entity.Component;

/**
 * Created by shining.cui on 2015/11/6.
 */
public interface CompToCompTrustService {
    // 计算组件对组件的全局信任
    public BigDecimal calcCompToCompTrust(Component trustor, Component trustee);

}
