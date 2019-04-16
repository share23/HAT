#!/bin/bash
# 进入到web根目录
cd /soft/azkaban/azkaban-web-server-3.46.0
# 执行
case $1 in
    start)  bin/start-web.sh ;;
    stop) bin/shutdown-web.sh ;;
    *) echo "参数不正确，需要[start | stop]" ; exit ;;
esac
