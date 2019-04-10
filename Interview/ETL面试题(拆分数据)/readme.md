## 思路如下

1. 通过 CleanData.scala 将数据清洗成需求字段并添加标记位
2. 通过 ParseData.scala 读取清洗过的数据进行拼接操作

数据说明

``` bash
    原始数据        raw_data.txt
    清洗过的数据    clean_data.txt
    结果集          result_data.txt
```