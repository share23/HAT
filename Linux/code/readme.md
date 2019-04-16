# 脚本说明

## xcall.sh

同时对多个服务器进行操作&nbsp;&nbsp;&nbsp;&nbsp;[xcall.sh](https://github.com/share23/HAT/blob/master/Linux/code/xcall.sh)

## jps.sh

查看集群所有节点进程&nbsp;&nbsp;&nbsp;&nbsp;[jps.sh](https://github.com/share23/HAT/blob/master/Linux/code/jps.sh)

先给每一个节点添加 jps 的符号链接

``` bash
    ln -s /soft/jdk/bin/jps jps
```

## xsync.sh

远程同步脚本&nbsp;&nbsp;&nbsp;&nbsp;[xsync.sh](https://github.com/share23/HAT/blob/master/Linux/code/xsync.sh)

先配&nbsp;&nbsp;[SSH免密登陆](https://github.com/share23/HAT/blob/master/Linux/SSH%E5%85%8D%E5%AF%86%E7%99%BB%E9%99%86.md)

再给每个节点安装远程数据同步工具 `rsync`

``` bash
    # 需要以 root 用户身份执行
    xcall.sh yum install -y rsync
```

## xk.sh && xkill.sh

强制结束进程&nbsp;&nbsp;&nbsp;&nbsp;[xk.sh](https://github.com/share23/HAT/blob/master/Linux/code/xk.sh)

&nbsp;&nbsp;&nbsp;&nbsp;分发 `xk.sh`

``` bash
　　su root
　　xsync.sh /usr/local/bin/xk.sh
　　exit
```

远程执行 xk.sh 脚本&nbsp;&nbsp;&nbsp;&nbsp;[xkill.sh](https://github.com/share23/HAT/blob/master/Linux/code/xkill.sh)

## xzk.sh

ZooKeeper 集群管理脚本&nbsp;&nbsp;&nbsp;&nbsp;[xzk.sh](https://github.com/share23/HAT/blob/master/Linux/code/xzk.sh)

&nbsp;&nbsp;&nbsp;&nbsp;启动 `ZooKeeper`

``` bash
    # 启动
    xzk.sh start

    # 停止
    xzk.sh stop
```

## roll_log.sh

滚动日志文件脚本&nbsp;&nbsp;&nbsp;&nbsp;[roll_log.sh](https://github.com/share23/HAT/blob/master/Linux/code/roll_log.sh)

## xkafka.sh

xkafka 集群管理脚本&nbsp;&nbsp;&nbsp;&nbsp;[xkafka.sh](https://github.com/share23/HAT/blob/master/Linux/code/xkafka.sh)