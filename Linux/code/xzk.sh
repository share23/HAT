#!/bin/bash
for((i=102 ; i<=104; i++)) ; do
    tput setaf 2
    echo ==================== s$i $1 ===================
    tput setaf 9
    ssh s$i "source /etc/profile ; zkServer.sh $1"
done
