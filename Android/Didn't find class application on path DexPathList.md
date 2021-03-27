应用安装好后，开始运行时报

```
Didn't find class application on path DexPathList
```

的错误



### 解决方法

##### 删除build文件夹

Android的项目目录里是有两个build文件夹的，一个是：项目目录/app/build，另一个是：项目目录/build。

把两个文件夹都删了，重新运行就好了。

若显示已被应用占用，先关闭Android studio再删除。