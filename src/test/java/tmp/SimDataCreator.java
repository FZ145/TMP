package tmp;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tmp.simDataCreator.SimHistoryDataCreator;
import tmp.simDataCreator.SimReputationDataCreator;
import tmp.simDataCreator.SimTrustEvaluateDataCreator;

/**
 * Created by shining.cui on 2015/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SimDataCreator {
    private static final Logger logger = LoggerFactory.getLogger(SimDataCreator.class);
    @Resource
    private SimHistoryDataCreator simHistoryDataCreator;
    @Resource
    private SimReputationDataCreator simReputationDataCreator;
    @Resource
    private SimTrustEvaluateDataCreator simTrustEvaluateDataCreator;

    /**
     * 此方法为系统初始化或者系统测试时第一个运行的方法，跑这个单元测试就可以生成随机测试数据， 测试数据保证都可以正确关联，可以正常展示所有功能。
     * 测试数据是模拟实体间进行交互的，并且交互后进行行为信任记录，从而获取直接信任与间接信任， 最终形成各个实体的声誉值。
     *
     * 由于数据是采用随机数的原理模拟的，因此直供展示功能用，应用本web程序后数据都是真实获取。 所以tmp.simDataCreator包内的类在上线以后均不再使用。
     */
    @Test
    public void createSimData() {
        long beginTime = System.currentTimeMillis();
        /**
         * 生成交互历史数据部分
         */
        // 传入参数，模拟组件之间进行N次交互，生成交互历史记录，存入数据库表
        simHistoryDataCreator.createHistoryDataBetweenComponents(50);
        // 传入参数，模拟组件与租户之间进行N次交互，生成交互历史记录，存入数据库表
        simHistoryDataCreator.createHistoryDataBetweenRenterAndComponent(100);
        // 传入参数，模拟租户与租户之间进行N次交互，生成交互历史记录，存入数据库表
        simHistoryDataCreator.createHistoryDataBetweenRenters(50);

        /**
         * 根据上一步生成的交互历史数据进行实体间信任评估
         */
        // 传入参数，模拟组件之间进行N次信任评估，生成交互信任评估记录，存入数据库表
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenComponents(50);
        // 传入参数，模拟组件与租户之间进行N次信任评估，生成交互信任评估记录，存入数据库表
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenRenterAndComponent(100);
        // 传入参数，模拟租户之间进行N次信任评估，生成交互信任评估记录，存入数据库表
        simTrustEvaluateDataCreator.createTrustEvaluateDataBetweenRenters(50);

        /**
         * 根据上一部生成的信任评估数据进行声誉计算
         */
        // 计算所有云提供商的声誉，存入数据库
        simReputationDataCreator.createReputationDataOfProviders();
        // 计算所有租户的声誉，存入数据库
        simReputationDataCreator.createReputationDataOfRenters();
        // 计算所有组件的声誉，存入数据库
        simReputationDataCreator.createReputationDataOfComponents();

        long endTime = System.currentTimeMillis();
        logger.info("=======================一共耗时:{}毫秒===========================",endTime - beginTime);

    }

}
