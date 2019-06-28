# Hive 内置函数的应用

## 说明


## 基本操作

查看函数

``` sql
show functions;
```

查看函数的使用方法

``` sql
desc function function_name;
```

查看函数的扩展信息

``` sql
desc function extended format_name;
```

## NVL 函数

`nvl` 函数是用来判断值是否为 null ,如果是 null ，则返回默认值，不是则返回原值

``` sql
nvl(value,default_value)  - Returns default value if value is null else returns value

Example:
>select nvl(null,'aaa');
aaa

>select nvl('aaa','bbb');
aaa

```

## 时间函数

``` sql
-- 显示当前数据库
>select current_database();
default

-- 显示当前日期
>select current_date();
2019-06-27

-- 显示当前时间戳，精确到毫秒
>select current_timestamp();
2019-06-27 11:25:51.656

-- 将时间格式化
>select date_format( current_timestamp(), 'yyyy-MM-dd HH:mm:ss');
2019-06-27 11:25:51

-- 将日期转换成时间戳，精确到秒
>select unix_timestamp(current_timestamp());
1561606200

-- 将时间戳转化成日期
>select from_unixtime(1561606200, 'yyyy-MM-dd HH:mm:ss');
2019-06-27 11:30:00

-- 计算两个指定日期相差多少天
>select datediff('2018-03-01','2018-02-01'); 
28

-- 得到当前日期多少天之前的日期
>select date_sub('2019-06-28',8);
2019-06-20
```
