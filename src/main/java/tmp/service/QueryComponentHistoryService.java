package tmp.service;

import tmp.entity.Component;
import tmp.entity.ComponentHistory;

import java.util.List;

/**
 * Created by yuanyao on 2016/3/22.
 */
public interface QueryComponentHistoryService {
    //查询组件的交互历史
    List<ComponentHistory>   selectByTrustorAndTrusteeUid(Component component);
}
