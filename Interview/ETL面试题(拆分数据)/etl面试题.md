# 题目1. 拆分数据

原始数据样例：

``` bash
2019-02-01 00:00:01.752 [http-nio-80-exec-1894] INFO  data_log -ip=115.60.183.78&source=TCL&deviceid=6431f2287dfa2c934e9df05bb0b39c133f3e7f1d&clienttype=TCL-CN-RT95-E5800A-UDM&huanid=546250600&userid=cd57bed7e53c7a2f945ebb6482467f393b0347ad&macline=3C:59:1E:00:76:24&macwifi=0c:91:60:76:bb:38&lcd=&devicesoftwareversion=V8-RT95016-LF1V161&projectid=121&modelname=&flash=717 MB/1.48 GB&netstats=WIFI&dnum=440874744&netspeed=2171.0KB&software=UsageStats&version=2.9.6&data=com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=429000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=436000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=456000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=494000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=771000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=1354000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=1654000}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=1952000}
com.tcl.TVBasicBehavior,{1=2019-01-31 00:05:35,2=2019-01-31 00:38:36}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=2245000}
com.qiyi.video,{1=2019-01-31 00:05:37,2=2019-01-31 00:40:22,3=2}
com.tcl.thirdAppPlayBehavior,{1=qiyi,2=587133,3=武松,4=2620,5=2245000}
...
```

前一部分是公共数据，后一部分是访问包数据

注意：这里多行才是一条完成的行为记录

需要取以下 3 种格式的包数据：

``` bash
1.com.tcl.thirdAppPlayBehavior,{1=qiyi,2=214260401,3=天乩之白蛇传说,4=2739,5=1000}
字段对照
package_name = com.tcl.thirdAppPlayBehavior
origin = qiyi
program_name = 天乩之白蛇传说
total_duration = 2739
duration = 1000
```

``` bash
2.com.tcl.thirdAppPlayBehavior,{1=mgtv,2=a1e678c6da1ccac10315d2303acb1e81,3=托马斯和他的朋友们 第十四季,4=1320,5=0}
同上

```

``` bash
3.com.ktcp.video,{1=2019-01-31 21:45:29,2=2019-01-31 22:18:40,3=2}
package_name = com.ktcp.video
start_time = 2019-01-31 21:45:29
end_time = 2019-01-31 22:18:40
```

拆分成如下结果集：

``` bash
client_type,mac_line,package_name,start_time,end_time,program_name,duration,origin,version,total_duration,dt
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.TVBasicBehavior,2019-01-31 18:04:10,2019-01-31 18:48:15,,,,2.9.7,,2019-02-01
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.tv,2019-01-31 18:04:12,2019-01-31 18:48:15,,,,2.9.7,,2019-02-01
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.TVBasicBehavior,2019-01-31 20:29:39,2019-01-31 23:29:47,,,,2.9.7,,2019-02-01
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.tv,2019-01-31 20:29:41,2019-01-31 23:29:47,,,,2.9.7,,2019-02-01
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.TVBasicBehavior,2019-01-31 23:34:40,2019-01-31 23:58:37,,,,2.9.7,,2019-02-01
TCL-CN-MS801-F3390A-3DG,40:8B:F6:84:49:40,com.tcl.tv,2019-01-31 23:34:42,2019-01-31 23:58:37,,,,2.9.7,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.TVBasicBehavior,2019-01-31 00:09:11,2019-01-31 00:36:11,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.qiyiguo,2019-01-31 00:09:13,2019-01-31 00:38:13,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.cyberui,2019-01-31 20:53:43,2019-01-31 20:54:03,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.tv,2019-01-31 20:54:03,2019-01-31 20:54:05,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.cyberui,2019-01-31 20:54:05,2019-01-31 20:54:21,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.tv,2019-01-31 20:54:21,2019-01-31 20:54:25,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.golive.cinema,2019-01-31 20:54:23,2019-01-31 20:54:55,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.cyberui,2019-01-31 20:54:57,2019-01-31 20:54:59,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.tv,2019-01-31 20:54:59,2019-01-31 23:38:43,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700A-UDMG,5C:36:B8:33:EE:5B,com.tcl.cyberui,2019-01-31 20:55:01,2019-01-31 23:38:55,,,,2.9.6,,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,迷你卡车乐园,-1000,qiyi,2.9.8,219,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,迷你卡车乐园,42000,qiyi,2.9.8,219,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.qiyi.video,2019-01-31 08:47:02,2019-01-31 08:47:54,迷你卡车乐园,42000,qiyi,2.9.8,219,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.cyberui,2019-01-31 08:47:56,2019-01-31 08:48:08,迷你卡车乐园,42000,qiyi,2.9.8,219,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
TCL-CN-RT95-E5700Q-UDMG,5C:36:B8:84:26:7A,com.tcl.thirdAppPlayBehavior,,,汽车城之火车特洛伊,-1000,qiyi,2.9.8,283,2019-02-01
```