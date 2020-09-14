# MySQL导入备份数据时出错 ERROR 2005(HY000) unkonw MySQL server host

问题描述：
用mysqldump命令将数据库备份到外部文件，但是用mysql命令行导入备份数据时，出现ERROR 2005(HY000) unkonw MySQL server host…错误

解决方法：
区别是字符集不同，要导入的数据库的字符集是utf8，用如下命令打开mysql客户端工具

```
mysql -uroot -p --default-character-set=utf8
```

然后再导入备份数据，一切ok。





allowPublicKeyRetrieval=true