# Debian下创建新用户useradd

1.使用sudo:

```undefined
sudo useradd -m 用户名 -g sudo -s /bin/bash -d /home/用户名
sudo passwd 用户名
```

2.root用户：

```undefined
groupadd 群组
useradd -m 用户名 -g 群组 -s /bin/bash -d /home/用户名
passwd 用户名
```

关于useradd命令的参数意义如下，供参考：
 -c<备注>：加上备注文字。备注文字会保存在passwd的备注栏位中；
 -d<登入目录>：指定用户登入时的启始目录； -D：变更预设值；
 -e<有效期限>：指定帐号的有效期限；
 -f<缓冲天数>：指定在密码过期后多少天即关闭该帐号；
 -g<群组>：指定用户所属的群组；
 -G<群组>：指定用户所属的附加群组；
 -m：自动建立用户的登入目录；
 -M：不要自动建立用户的登入目录；
 -n：取消建立以用户名称为名的群组；
 -r：建立系统帐号；
 -s：指定用户登入后所使用的shell；
 -u：指定用户id。

到此，用户添加成功。如果需要让此用户有root权限，执行命令：



```undefined
vim /etc/sudoers
```

修改文件如下：



```bash
# User privilege specification
root ALL=(ALL) ALL
abc ALL=(ALL) ALL
```

保存退出，abc用户就拥有了root权限。



参考链接:

作者：幻雪孤蓝
链接：https://www.jianshu.com/p/a60340df9004
来源：简书