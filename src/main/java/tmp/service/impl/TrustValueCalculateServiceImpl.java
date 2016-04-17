package tmp.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import tmp.entity.EvidenceWeight;
import tmp.entity.TrustEvidence;
import tmp.service.TrustValueCalculateService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuanyao on 2016/4/17.
 */
@Service("trustValueCalculator")
public class TrustValueCalculateServiceImpl implements TrustValueCalculateService {
    @Override
    public List<BigDecimal> trustValueCalculate(TrustEvidence trustEvidence, EvidenceWeight evidenceWeight) {
        //获取某一类的行为证据
        String[] trustEvidences = trustEvidence.getTrustValue().split(",");
        List<String> evidencesList = Arrays.asList(trustEvidences);

        //获取某一类行为证据对应的证据权重
        String evidenceWeightStr = evidenceWeight.getEvidenceWeight();
        String[] evidenceWeights = evidenceWeightStr.split(",");
        List<String> weightsList = Arrays.asList(evidenceWeights);

        List<BigDecimal> trustValue = calculateTrustValue(evidencesList, weightsList);
        return trustValue;
    }

    private List<BigDecimal> calculateTrustValue(List<String> evidencesList, List<String> weightsList) {
        BigDecimal positive = BigDecimal.ZERO;
        BigDecimal negtive = BigDecimal.ZERO;
        for (int i = 0; i < 3; i++) {
            BigDecimal evidenceValue = new BigDecimal(evidencesList.get(i));
            BigDecimal weightValue = new BigDecimal(weightsList.get(i));
            BigDecimal multiply = evidenceValue.multiply(weightValue);
            positive = positive.add(multiply);
        }
        for (int i = 3; i < 6; i++) {
            BigDecimal evidenceValue = new BigDecimal(evidencesList.get(i));
            BigDecimal weightValue = new BigDecimal(weightsList.get(i));
            BigDecimal multiply = evidenceValue.multiply(weightValue);
            negtive = negtive.add(multiply);
        }
        List<BigDecimal> trustResult = calcTrustResult(positive,negtive);
        return trustResult;
    }

    private List<BigDecimal> calcTrustResult(BigDecimal positive, BigDecimal negtive) {
        int compareResult = positive.add(negtive).compareTo(BigDecimal.ONE);
        List<BigDecimal> trustResult = Lists.newArrayList();
        if (compareResult <= 0) {
            trustResult.add(positive);
            trustResult.add(BigDecimal.ONE.subtract(positive.add(negtive)));
            trustResult.add(negtive);
        } else {
            BigDecimal addResult = positive.add(negtive);
            trustResult.add(positive.divide(addResult));
            trustResult.add(BigDecimal.ZERO);
            trustResult.add(negtive.divide(addResult));

        }
        return trustResult;
    }
}
