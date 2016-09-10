package tmp.service;

import tmp.entity.EvidenceWeight;
import tmp.entity.TrustEvidence;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yuanyao on 2016/4/17.
 * 根据某类行为证据与行为证据权重计算可信度
 */
public interface TrustValueCalculateService {
    /**
     *
     * @param trustEvidence 行为证据数据对象，证据值比如“1,2,3,4,5,6”
     * @param evidenceWeight 行为证据权重对象，权重值比如““1,2,3,4,5,6””
     * @return 计算得到的可信度结果，比如[0.5,0.2,0.3]
     */
    List<BigDecimal> trustValueCalculate(TrustEvidence trustEvidence, EvidenceWeight evidenceWeight);
}
