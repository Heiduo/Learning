# debian下nginx安装

### 预先步骤

```
apt-get install gcc

apt-get install libpcre3 libpcre3-dev openssl libssl-dev libperl-dev

apt-get install make
```

### 下载安装nginx

```
sudo apt-get install nginx

```

### 常用命令

```
sudo service nginx start
sudo service nginx restart
sudo service nginx reload
sudo service nginx stop

启动nginx
./nginx
# 关闭nginx
./nginx -s stop
#退出nginx
./nginx -s quit
# 重启nginx（重启用户基本感觉不到）
./nginx -s reload
```

### 报错
在Debian中运行./configure 时报错，缺少zlib
```
./configure: error: the HTTP gzip module requires the zlib library.
You can either disable the module by using --without-http_gzip_module
option, or install the zlib library into the system, or build the zlib library
statically from the source with nginx by using --with-zlib=<path> option.

```
安装
```
apt-get install zlib1g-dev
```


参考链接：

作者：夜星_3872
链接：https://www.jianshu.com/p/b6d00069d112
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。