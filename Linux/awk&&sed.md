# awk && sed

## awk

### 说明

把文件逐行的读入，以制表符为默认分隔符将每行切片，切开的部分再进行各种分析处理

### 得到第二块切片的数据

``` bash
    cat pv.txt | awk '{print $2}'
```

### 指定分隔符

``` bash
    # -F 为指定分隔符
    cat pv.txt | awk -F '\t' '{print $1}'
```

### 将串中含有 name 的进程号切片得出

``` bash
    # grep -i 忽略大小写查询
    jps | grep -i name | awk '{print $1}'
```
### 删除名称含name的进程

``` bash
    # 反引号间的内容，会被shell先执行
    kill -9 `jps | grep -i name | awk '{print $1}'`        
```

## sed

### 删除一行数据

``` bash
    # 删除第一行,打印执行结果,真实数据不变
    sed '1d' 2.txt

    # 删除第一行，直接执行，真实数据改变
    sed -i '1d' 2.txt

    # 删除1-3行
    sed '1,3d' 2.txt

    # 删除最后一行
    sed '$d' 2.txt
```
                       
### 追加数据，数据后插入一行
``` bash
    # 第一行后追加数据helloworld
    sed '1ahelloworld' 2.txt

    # 第1-3行后追加how
    sed '1,3ahow' 2.txt
 ```

### insert数据

``` bash
    # 数据前插入一行
    sed '1ihelloworld' 2.txt
 ```

### 替换

``` bash
    # 前三行替换
    sed '1,3chello' 2.txt
```

### 替换指定字符

``` bash
    # hello => how    s: 正则 g: copy
    sed 's/hello/how/g' 2.txt
                        
    # hello => how
    sed 's@hello@how@g' 2.txt
```