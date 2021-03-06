# 重要说明

请以对待正式项目的标准对待此次程序挑战，要求包括但不限于以下几条：

1. 除构建框架和单元测试框架以外（类似JUnit，Ant，NUnit，Maven等）不允许使用任何第三方类库，只允许使用编程语言的标准类库。
2. 请尽量对程序进行设计，并提供readme文档进行简要说明。
3. **以面对正式项目的态度面对此次小程序挑战**。

代码的项目化和规范化程度是评价的重要标准。

# 场景描述

在现实生活中，火车交通网是非常常见的。例如在中国的主要城市中，武汉可以直达北京、上海、广州，广州可以直达深圳。武汉去深圳必须经过广州，北京去广州必须经过武汉。

现假设存在一个火车交通网，以A、B、C、D等英文字母表示车站名，以数字表示两个车站之间的距离。输入以起点车站+终点车站+距离的方式表示存在的车站间的直达通路。例如AB5表示可以从A车站直达B车站，距离是5个距离单位（为了方便起见，一个距离单位就等于100公里，都取正整数）。火车的速度每小时可以行驶1个距离单位，同时除了起点和终点外，火车每经过一个站点需要停靠2小时进行上下乘客。

允许出现从某个车站出发，经过若干车站再回到出发车站的情况。

允许出现在若干个形成闭环的车站中绕圈的情况。

# 输入数据

```
AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7, CF2, DF6
```

**直达通路都是单向，没有标明的直达通路不存在，例如不存在B车站出发直达A车站的直达通路。**此输入数据需要通过input.txt或命令行参数的方式装载入程序。

说明：

A车站出发可以达到B车站，距离5。

B车站出发可以达到C车站，距离4。

C车站出发可以达到D车站，距离8。

D车站出发可以达到C车站，距离8。

D车站出发可以达到E车站，距离6。

A车站出发可以达到D车站，距离5。

C车站出发可以达到E车站，距离2。

E车站出发可以达到B车站，距离3。

A车站出发可以达到E车站，距离7。

C车站出发可以达到F车站，距离2。

D车站出发可以达到F车站，距离6。

# 问题

## 问题类型1：直达

### 问题描述

请提供方法计算从给定起点车站经过给定路径到达终点车站的路径和距离以及总共消耗的时间。如果按照给定路径无法抵达，请输出“NO SUCH ROUTE”。

### 测试案例

1. 测试数据：ABC；期望输出：A->B->C:9:11。测试编号：1。
2. 测试数据：AD；期望输出：A->D:5:5。测试编号：2。
3. 测试数据：ADC；期望输出：A->D->C:13:15。测试编号：3。
4. 测试数据：AEBCD；期望输出：A->E->B->C->D:22:28。测试编号：4。
5. 测试数据：AED；期望输出：NO SUCH ROUTE。测试编号：5。

## 问题类型2：最短距离

### 问题描述

请提供方法计算从给定起点车站到终点车站的最短距离和路径，相同的起点和终点可能有多种方式都能以最短距离到达。

### 测试案例

6. 测试数据：AC；期望输出：[A->B->C:9:11]。测试编号：6。
7. 测试数据：BB；期望输出：[B->C->E->B:9:13]。测试编号：7。
8. 测试数据：AF；期望输出：[A->D->F:11:13, A->B->C->F:11:15]。测试编号：8。

## 问题类型3：最短时间

### 问题描述

请提供方法计算从给定起点车站到终点车站的最短耗时和路径，相同的起点和终点可能有多种方式都能以最短耗时到达。

### 测试案例

9. 测试数据：AC；期望输出：[A->B->C:9:11]。测试编号：9。
10. 测试数据：BB；期望输出： [B->C->E->B:9:13]。测试编号：10。
11. 测试数据：AF；期望输出：[A->D->F:11:13]。测试编号：11。

## 问题类型4：有条件的查询

12. 测试数据：找出所有以C为起点，以C为终点，且停车次数不超过3次的路径；期望输出：[C->D->C:16:18, C->E->B->C:9:13]。测试编号：12。
13. 测试数据：找出所有以C为起点，以C为终点，且距离小于30次的路径；期望输出：[C->D->C->E->B->C:25:33, C->D->C:16:18, C->D->E->B->C:21:27, C->E->B->C->D->C:25:33, C->E->B->C->E->B->C->E->B->C:27:43, C->E->B->C->E->B->C:18:28, C->E->B->C:9:13]。测试编号：13。
14. 测试数据：找出所有以C为起点，以C为终点，且耗时小于30次的路径；期望输出：[C->D->C:16:18, C->D->E->B->C:21:27, C->E->B->C->E->B->C:18:28, C->E->B->C:9:13]。测试编号：14。 
15. 测试数据：找出所有以A为起点，以C为终点，且停车次数不超过4次的路径；期望输出：[A->B->C->D->C:25:31, A->B->C:9:11, A->D->C->D->C:29:35, A->D->C:13:15, A->D->E->B->C:18:24, A->E->B->C:14:18]。测试编号：15。
16. 测试数据：找出所有以A为起点，以C为终点，且距离小于8的路径；期望输出：[]。测试编号：16。
17. 测试数据：找出所有以A为起点，以C为终点，且耗时小于10的路径；期望输出：[]。测试编号：17。

