签名方法：

```java
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore [keystore文件路径] -storepass [keystore文件密码] [待签名apk路径] [keystore文件别名]
```