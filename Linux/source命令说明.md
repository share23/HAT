## source 命令说明

### 说明

&nbsp;&nbsp;&nbsp;&nbsp;`source /etc/profile` 命令我们一般用于生效配置文件

### 定义

&nbsp;&nbsp;&nbsp;&nbsp;`source` 命令也称为 "点命令" ，也就是一个点符号 ( . ) ，是 bash 的内部命令

### 功能

&nbsp;&nbsp;&nbsp;&nbsp;使 Shell 读入指定的 Shell 程序文件并依次执行文件中的所有语句

&nbsp;&nbsp;&nbsp;&nbsp;`source` 命令通常用于重新执行刚修改的初始化文件，使之立即生效，而不必注销并重新登陆。

### 用法

&nbsp;&nbsp;&nbsp;&nbsp;`source filename` 或 `.filename source` 命令(从 C Shell 而来) 是 bash shell 的内置命令

&nbsp;&nbsp;&nbsp;&nbsp;点命令( . ) ，就是点符号(从 Bourne Shell 而来)是 source 的另一名称

`source filename` 或 `sh filename` 及 `./filename` 执行脚本的区别

1. 当 shell脚本 具有可执行权限时，用 `sh filename` 与 `./filename` 执行脚本是没有区别的。 `./filename` 是因为当前目录没有在 PATH 中，所有的 "." 是用来表示当前目录的。

2. `sh filename` 重新建立一个子 shell ，在子 shell 中执行脚本里面的语句，该子 shell 继承父 shell 的环境变量，但是子 shell 新建的、改变的变量不会被带回父 shell ，除非使用 `export`

3. `source filename` 这个命令只是简单地读取脚本里面的语句依次在当前 shell 里面执行，没有建立新的子 shell 。那么脚本里面所有新建、改变变量的语句都会保存在当前 shell 里面。

### example

1. 新建一个 test.sh 脚本,&nbsp;&nbsp;内容为 A=1

2. 赋予该脚本可执行权限&nbsp;&nbsp;chmod +x test.sh

3. 运行 sh test.sh 后， echo $A ,显示为空，因为 A = 1 并未传给当前 shell