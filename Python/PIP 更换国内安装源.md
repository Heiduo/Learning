### 修改源方法
#### 临时使用
可以在使用pip的时候在后面加上-i参数，指定pip源
```
pip install scrapy -i https://pypi.tuna.tsinghua.edu.cn/simple
```

#### 永久修改
1. **Linux**
	修改 ~/.pip/pip.conf （不存在文件时，新建文件）
```
[global]
index-url = https://pypi.tuna.tsinghua.edu.cn/simple
```

2. **Windows**
	- win + R ，然后输入%HOMEPATH%打开用户目录
	- 创建pip文件夹，在该文件夹内新建文件pip.ini
	- pip.ini 内容 
```
[global]
timeout = 6000
index-url = https://pypi.tuna.tsinghua.edu.cn/simple
trusted-host = pypi.tuna.tsinghua.edu.cn
```

### pip国内的一些镜像

阿里云 http://mirrors.aliyun.com/pypi/simple/ 

中国科技大学 https://pypi.mirrors.ustc.edu.cn/simple/ 

豆瓣(douban) http://pypi.douban.com/simple/ 

清华大学 https://pypi.tuna.tsinghua.edu.cn/simple/ 

中国科学技术大学 http://pypi.mirrors.ustc.edu.cn/simple/
  
  