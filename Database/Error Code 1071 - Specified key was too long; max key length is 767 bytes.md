# Mysql数据库导入时遇到Error Code 1071 
在将sql文件导入数据库时，遇到了如下的问题
> Error Code 1071 - Specified key was too long; max key length is 767 bytes

先检查一下数据库是否被限制了的索引大小
```
SHOW variables like 'innodb_large_prefix';
```
如果查询值为OFF的话，执行下述命令
```
SET GLOBAL INNODB_LARGE_PREFIX = ON;
```
执行完毕后查看innodb_file_format引擎格式类型是否为BARRACUDA
```
SHOW variables like 'innodb_file_format';
```
不是则需要进行修改
```
SET GLOBAL innodb_file_format = BARRACUDA;
```
修改完毕后再次执行导入，则可以成功倒入数据