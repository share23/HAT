# Shell 脚本中的 export 和 declare 解析

## export

`export` 一般用于设置或显示环境变量

比如我们要用一个命令，但这个命令的执行文件不在当前目录里，我们每次想用这个执行文件的时候必须指定执行文件的目录，现在可以通过export命令指定执行文件的目录，常用于环境变量。

``` bash
# 语法
export [-fnp][变量名称]=[变量设置值]

# 参数说明
-f 　代表[变量名称]中为函数名称。
-n 　删除指定的变量。变量实际上并未删除，只是不会输出到后续指令的执行环境中。
-p 　列出所有的shell赋予程序的环境变量。
```

## declare

`declare` 命令用于声明 shell 变量

declare 为 shell指令，在第一种语法中可用来声明变量并设置变量的属性([rix]即为变量的属性），在第二种语法中可用来显示shell函数。若不加上任何参数，则会显示全部的shell变量与函数(与执行set指令的效果相同)。

``` bash
# 语法
declare [+/-][rxi][变量名称＝设置值] 或 declare -f

# 参数说明
+/- 　"-"可用来指定变量的属性，"+"则是取消变量所设的属性。
-f 　仅显示函数。
r 　将变量设置为只读。
x 　指定的变量会成为环境变量，可供shell以外的程序来使用。
i 　[设置值]可以是数值，字符串或运算式。
```

改变变量属性

``` bash
# declare -i ef //声明整数型变量
# ef=1  //变量赋值（整数值）
# echo $ef //显示变量内容
1
# ef="wer" //变量赋值（文本值）
# echo $ef 
0
# declare +i ef //取消变量属性
# ef="wer"
# echo $ef
wer
```

设置变量只读

``` bash
# declare -r ab //设置变量为只读
# ab=88 //改变变量内容
-bash: ab: 只读变量
# echo $ab //显示变量内容
56
```

声明数组变量

``` bash
# declare -a cd='([0]="a" [1]="b" [2]="c")' //声明数组变量
# echo ${cd[1]}
b //显示变量内容

# echo ${cd[@]} //显示整个数组变量内容
a b c
```

仅显示函数

``` bash
# declare -f
command_not_found_handle () 
{ 
  if [ -x /usr/lib/command-not-found ]; then
    /usr/bin/python /usr/lib/command-not-found -- $1;
    return $?;
  else
    if [ -x /usr/share/command-not-found ]; then
      /usr/bin/python /usr/share/command-not-found -- $1;
      return $?;
    else
      return 127;
    fi;
  fi
}
```