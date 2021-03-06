# TMP
## 云计算环境中面向多用户的信任管理系统V1.0
    2015年11月20日01:35:03
    联系人:kingszelda@163.com
## 功能：
1. 实现三层体系中的任何维度的信任评估
2. 功能均已做过测试，暂时没有发现问题

## 说明:
1. 需要首先运行tmp.SimDataCreator单元测试生成测试数据才有展示的数据源
2. 等正式运营后，所有tmp.simdata包内的类均不再需要
3. 系统中所有的单元测试都可以运行，可以帮助理解程序
4. 代码中有大量注释，但不排除注释有的地方有错，因为代码改过好几版，注释没跟上
5. 项目中的sql为响应的表结构语句，我在这里初始化了几条简单的数据作为注册数据，省去了初始注册阶段，不是测试冗余数据，不要删除。
6. 数据库中有个user_t表是用来向初学者演示Spring+Spring MVC+Mabatis框架功能的，是冗余表。

## 距离正式发布的距离：
1. 目前系统缺少前端页面，展示页面需要做
2. 与前端联调的接口没有商定，商定后需要写controller

## 可以学术上改善的地方：
1. tmp.util包中的工具类需要改善，这里只是实现了基础功能，但准确率肯定不高
2. 衰减函数，根据时间进行衰减，这里制定了简单的静态指定衰减策略
3. tmp.staticvalue.StaticValue中的几个静态阈值需要从学术上完善
4. 分配直接信任与间接信任时的权重分配策略，需要数学证明，这里制定了简单的静态策略
