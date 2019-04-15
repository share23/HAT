# Shell 脚本

## 介绍

Shell 脚本以 `.sh` 结尾，在脚本头部声明解释器，内容如下

``` bash
#!/bin/bash
```

注释以 `#` 开头


``` bash
    # 强制命令解析用 ` `
    `COMMAND`
```

## 变量
``` bash
	$#		//参数个数
	$n		//第n个参数
	$0		//当前脚本(命令)名称
	$@		//取出所有参数
	shift		//参数左移
```

## 条件判断

### if then

``` bash
	if COMMANDS; then COMMANDS; elif COMMANDS; then COMMANDS; else COMMANDS; fi

	i=10
	if [ $i -lt 30 ] ; then echo young ; elif [ $i -lt 50 ] ; then echo middle ; else echo old ; fi

	if [ $i -eq 30 ]		//相等	equal
	if [ $i -ne 30 ]		//不等	not equal
	if [ $i -gt 30 ]		//大于	greater than
	if [ $i -lt 30 ]		//小于	less than
	if [ $i -ge 30 ]		//大于等于	greater equal
	if [ $i -le 30 ]		//小于等于	less equal
```

### case esac

``` bash
	case WORD in [PATTERN [| PATTERN]...) COMMANDS ;;]... esac

	#!/bin/bash
	case $1 in
	    helloworld | tom ) echo 1 ;;
	    hello ) echo 2 ;;
	    * ) echo 3 ;;
	esac
```

## Loop

### while do done
``` bash
	while COMMANDS; do COMMANDS; done

	#!/bin/bash
	i=1
	while (( $i<10 )); do
	    echo $i
	    i=$((i+1))
	done

	while99.sh
============================
	#!/bin/bash
	i=1
	while (( $i<10 )); do
	    j=1
	    while (( $j<=$i )); do
		echo -ne ${j}x${i}=$(( j*i ))'\t'
		j=$((j+1))
	    done
	    i=$((i+1))
	    echo
	done
```

### for ... do ... done

``` bash
	for：
		//for ... in 语句
		for NAME [in WORDS ... ] ; do COMMANDS; done

		eg：
			for x in `cat 1.txt` ; do echo $x ; done    //通过对1.txt中的单词进行分割，取出所有单词并进行打印
		
		//for ((
		for (( exp1; exp2; exp3 )); do COMMANDS; done

		eg：
			for (( i=0; i<10; i++ )); do echo $i ; done	//循环打印1-9


	通过for循环，打印99乘法表
		
		1	1x1=1
		2	1x2=2	2x2=4
		3	1x3=3	...	...


    for99.sh
============================
    #!/bin/bash
    for (( i=1; i<10; i++ )); do
        for (( j=1; j<=i; j++ )); do
            echo -ne "$j"x"$i"=$((j*i))'\t'
        done
        echo
    done
```