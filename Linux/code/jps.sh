#!/bin/bash

# for循环
for((i=101 ; i<=105; i++)) ; do
    # 更改文本颜色
    tput setaf 2
    # 输出以下文本
    echo ==================== s$i $@ ===================
    # 更改文本颜色
    tput setaf 9
    # ssh 远程登陆主机 s$i ,执行输入的参数的命令
    ssh s$i jps
done
