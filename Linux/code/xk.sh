#!/bin/bash
# 强制结束进程
kill -9 `jps | grep -i $1 | awk '{print $1}'` > /dev/null 2>&1
