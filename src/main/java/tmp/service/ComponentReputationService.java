package tmp.service;

import tmp.entity.Component;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/15.
 */
public interface ComponentReputationService {
    public BigDecimal calcComponentReputation(Component component);
}
