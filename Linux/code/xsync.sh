#!/bin/bash

# 指出当前用户名
name=`whoami`
# 指定文件所在文件夹名称
dir=`dirname $1`
# 指定文件的文件名
filename=`basename $1`
# 进入到dir中
cd $dir
# 得到当前目录的绝对路径
fullpath=`pwd`

for((i=102 ; i<=105; i++)) ; do
    tput setaf 2
    echo ==================== s$i $@ ===================
    tput setaf 9
    # 远程同步命令  l 保留软连接 r 递归文件夹
    rsync -lr $filename "$name"@s"$i":$fullpath
done
