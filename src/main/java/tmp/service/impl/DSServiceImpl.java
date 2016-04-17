package tmp.service.impl;

import tmp.service.DSService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yuanyao on 2016/4/17.
 */
public class DSServiceImpl implements DSService {
    /**
     * @param first  第一个分类的可信评估结果，比如[0.5,0.2,0.3]
     * @param second 第二个分类的可信评估结果，比如[0.4,0.2,0.4]
     * @param third  第三个分类的可信评估结果，比如[0.2,0.2,0.6]
     * @return 融合后的可信度，该可信度为基础直接信任度，最为LBCTM的基础数据
     */
    @Override
    public BigDecimal DScalc(List<BigDecimal> first, List<BigDecimal> second, List<BigDecimal> third) {
        // todo 翻译D-S代码
        return BigDecimal.valueOf(0.66);
    }
}
