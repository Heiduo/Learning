### 解决方案

在Android Studio中双击Shift全局搜索"Edit Custom VM Options"

（不存在选择创建文件）

然后在打开的文件中添加

```
-Dfile.encoding=UTF-8
```

然后重启Android Studio即可解决