package tmp.service.impl;

import tmp.dao.ComponentHistoryMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.service.QueryComponentHistoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyao on 2016/3/22.
 */
public class QueryComponentHistoryServiceImpl implements QueryComponentHistoryService {

    @Resource
    private ComponentHistoryMapper componentHistoryMapper;

    @Override
    public List<ComponentHistory> selectByTrustorAndTrusteeUid(Component component) {

        String componentUid = component.getUid();
        List<ComponentHistory> componentHistories = componentHistoryMapper.selectByTrustorAndTrusteeUid(componentUid,null,1);

        return componentHistories;
    }
}
