#!/bin/bash
for((i=102 ; i<=104; i++)) ; do
    tput setaf 2
    echo ==================== s$i $@ ===================
    tput setaf 9
    case $1 in
        start) ssh s$i "source /etc/profile;kafka-server-start.sh -daemon /soft/kafka/config/server.properties" ;;
        stop) ssh s$i "source /etc/profile;kafka-server-stop.sh" ;;
        *) echo "参数不正确，需要[start | stop]" ; exit ;;
    esac
done
