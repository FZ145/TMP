package tmp.service;

import tmp.entity.Component;
import tmp.entity.ComponentHistory;

import java.util.List;

/**
 * Created by yuanyao on 2016/3/4.
 */
public interface ComponentHistoryService {
    List<ComponentHistory> selectByTrustorAndTrusteeUid(Component component);
}
