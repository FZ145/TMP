package tmp.service.impl;

import org.springframework.stereotype.Service;
import tmp.dao.ComponentHistoryMapper;
import tmp.entity.Component;
import tmp.entity.ComponentHistory;
import tmp.service.ComponentHistoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyao on 2016/3/4.
 */
@Service("componentHistoryService")
public class ComponentHistoryServiceImpl implements ComponentHistoryService {
    @Resource
    private ComponentHistoryMapper componentHistoryMapper;

    @Override
    public List<ComponentHistory> selectByTrustorAndTrusteeUid(Component component) {
        String componentUid = component.getUid();
        List<ComponentHistory> componentHistories = componentHistoryMapper.selectByTrustorAndTrusteeUid(componentUid, null,0);
        return componentHistories;
    }
}
