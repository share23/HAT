#!/bin/bash
dateStr=`date '+%Y-%m-%d-%H-%M'`
mv /usr/local/openresty/nginx/logs/access.log /usr/local/openresty/nginx/logs/access.log.${dateStr}
touch access.log
openresty -s reload
