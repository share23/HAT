# SSH 免密登陆

生成公私密钥对

``` bash
    ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
```

将公钥发送到其他节点

``` bash
    ssh-copy-id centos@s101
    ssh-copy-id centos@s102
    ssh-copy-id centos@s103
    ssh-copy-id centos@s104
    ssh-copy-id centos@s105
```

将本机的 /soft/hadoop/etc 分发到其他节点

``` bash
    scp -r /soft/hadoop/etc centos@s102:/soft/hadoop/
    scp -r /soft/hadoop/etc centos@s103:/soft/hadoop/
    scp -r /soft/hadoop/etc centos@s104:/soft/hadoop/
    scp -r /soft/hadoop/etc centos@s105:/soft/hadoop/
```