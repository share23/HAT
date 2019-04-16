# Hive 优化

## 1. 性能工具

1. explain
2. analyze

## 2. 设计优化

1. 分区(避免全表扫描)
2. 分桶(避免分区文件过多)(数据抽样、map join)
3. 索引

## 3. 数据文件

1. 文件格式(textfile sequencefile | rc orc parquet)
2. 压缩
3. 存储

## 4. 作业和查询优化

### 4.1 本地模式 | 集群模式

&nbsp;&nbsp;&nbsp;&nbsp;本地模式用于测试和小表，设置低于 xx 字节/数量开启本地模式

### 4.2 并行计算

&nbsp;&nbsp;&nbsp;&nbsp;Job1 同时依赖于 Job2、Job3 可开启并行计算

&nbsp;&nbsp;&nbsp;&nbsp;并行计算在内存够的情况下能提高性能

### 4.3 严格模式

&nbsp;&nbsp;&nbsp;&nbsp;分区表必须用 `where` 对分区字段进行条件过滤

&nbsp;&nbsp;&nbsp;&nbsp;`order by` 必须包含 `limit` 限制输出

&nbsp;&nbsp;&nbsp;&nbsp;限制执行笛卡儿积的查询

### 4.4 Join

&nbsp;&nbsp;&nbsp;&nbsp; 小表 + 大表 / map 端 join

### 4.5 group by 数据倾斜

&nbsp;&nbsp;&nbsp;&nbsp;随机分区 + 二次作业 避免数据倾斜

### 4.6 JVM 重用

&nbsp;&nbsp;&nbsp;&nbsp;小文件个数过多
&nbsp;&nbsp;&nbsp;&nbsp;task 个数过多

## 5. 排序

&nbsp;&nbsp;&nbsp;&nbsp;order by&nbsp;&nbsp;&nbsp;&nbsp;全排，1个 Reduce

&nbsp;&nbsp;&nbsp;&nbsp;sort by&nbsp;&nbsp;&nbsp;&nbsp;在每个Reduce 中进行排序

&nbsp;&nbsp;&nbsp;&nbsp;distribute by&nbsp;&nbsp;&nbsp;&nbsp;分区排序

&nbsp;&nbsp;&nbsp;&nbsp;cluster by&nbsp;&nbsp;&nbsp;&nbsp;相当于 sort by + distribute by (不支持 asc/desc)

## 6. 数据倾斜产生的原因

&nbsp;&nbsp;&nbsp;&nbsp;在 Shuffle 阶段，如果 partition 规则没定义好，抓取相同的 key 进入同一个 Reduce ，导致 Reduce 数据倾斜
