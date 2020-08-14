###  linux 安装redis

1. **获取redis资源**
```
wget http://download.redis.io/redis-stable.tar.gz 
```
2. **解压**
```
tar -xzvf redis-stable.tar.gz
```
3. **安装**

```
cd redis-stable
make
cd src
make install PREFIX=/usr/local/redis
```
4. **移动配置文件到安装目录下**

```
cd ../
mkdir /usr/local/redis/etc
mv redis.conf /usr/local/redis/etc
```
5. **配置redis为后台启动**

```
vi /usr/local/redis/etc/redis.conf //将daemonize no 改成daemonize yes
```

![](./picture/1597390117(1).png)


6. **将redis加入至开启启动**

```
vi /etc/rc.local
```
在里面添加内容：
```
/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf 
```
(意思就是开机调用这段开启redis的命令)

7. **常用命令**

```
redis-server /usr/local/redis/etc/redis.conf //启动redis
pkill redis  //停止redis
# 卸载redis：
rm -rf /usr/local/redis //删除安装目录
rm -rf /usr/bin/redis-* //删除所有redis相关命令脚本
m -rf /root/download/redis-4.0.4 //删除redis解压文件夹
```
8. **采用 redis desktop manager 远程连接配置**

```
vi /usr/local/redis/etc/redis.conf 
```
同时如果我们需要使用远程图形化工具 RedisDesktopManager 进行连接的话，我们需要继续修改下面的配置信息

把 bind = 127.0.0.1 改成如下的配置，就能够远程连接了，不然只能够在本地使用

修改成 0.0.0.0 表示所有的网卡都提供redis功能
```
# IF YOU ARE SURE YOU WANT YOUR INSTANCE TO LISTEN TO ALL THE INTERFACES
# JUST COMMENT THE FOLLOWING LINE.
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
bind 0.0.0.0
```
如果小伙伴们需要开启redis验证密码的话，那么需要继续 修改下面的这个配置
```
# Warning: since Redis is pretty fast an outside user can try up to
# 150k passwords per second against a good box. This means that you should
# use a very strong password otherwise it will be very easy to break.
#
requirepass myPassword
```

### 配置中的相关问题
1. **无法重启redis**
	在Linux上，如果开了redis的守护进程，kill -9和redis-cli shutdown 命令是无法杀掉 redis 进程的 ，

杀掉就会重新启动一个新的进程

都无语了 杀掉一个又来一个进程，后来发现因为自己加入了服务器开机自动启动redis也打开了守护进程！
**解决方案**
```
sudo /etc/init.d/redis-server stop
```

2. **redis desktop manager远程连接失败**
	使用redis desktop manager 进行远程连接时，发现无法连接上，log中打印信息如下，
```
	DENIED Redis is running in protected mode because protected mode is enabled,
```


![](./picture/1597389139(1).png)


**解决方法**：是说Redis服务处于保护模式，我们需要修改配置文件redis.conf。将NETWORK下的protected-mode yes修改

为protected-mode no，然后重启服务（./bin/redis-server ./redis.conf）
```
vi /usr/local/redis/etc/redis.conf 
```

![](./picture/1597390847(1).jpg)


### 参考链接
1. [linux安装redis 完整步骤](https://www.cnblogs.com/john-xiong/p/12098827.html)
2. [Linux上无法杀掉redis、redis-sentinel进程](https://blog.csdn.net/qq_22583191/article/details/103399643?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v25-3-103399643.nonecase)
3. [SpringBoot连接Redis服务出现DENIED Redis is running in protected mode because protected mode is enabled](https://blog.csdn.net/y_bccl27/article/details/87347716)