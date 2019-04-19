# Git 使用指南（简易版）

## 说明

参考 [Git使用教程,最详细，最傻瓜，最浅显，真正手把手教](https://zhuanlan.zhihu.com/p/30044692)

## 提交代码

### 初始化

在指定目录通过 `git init`  命令将该目录初始化为本地仓库

``` bash
    git init
```

### 添加工作区文件到暂存区

``` bash
    git add readme.txt
```

### 将暂存区的文件添加到本地仓库

``` bash
    git commit -m "添加 Git 使用指南（简易版）"
```

### 从本地仓库提交到远程

正常推送

``` bash
    git push origin master
```

第一次推送到空的远程仓库

``` bash
    git push -u origin master
```
