package tmp.service;

import tmp.entity.Component;
import tmp.entity.Renter;

import java.math.BigDecimal;

/**
 * Created by shining.cui on 2015/11/15.
 */
public interface RenterReputationService {
    public void calcRenterReputation(Renter renter);
}
