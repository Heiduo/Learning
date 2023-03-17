<b>jks与keystore</b>
jks是在android studio里面生成的签名证书。
keystore是eclipse里面生成的。
两者在使用方式上没有什么区别,但是在算法上有一点点区别。

<b>jks转keystore</b>
很多第三方市场，我们上传apk的时候，他们只支持keystore，需要我们把jks签名转化为keystore。
直接用命令行，先生成p12文件，用p12生成keystore。

```
keytool -importkeystore -srckeystore D:\test.jks -srcstoretype JKS -deststoretype PKCS12 -destkeystore test.p12

keytool -v -importkeystore -srckeystore D:\test.p12 -srcstoretype PKCS12 -destkeystore D:\test.keystore -deststoretype JKS
```
现在test.keystore的签名应该与test.jks的签名信息是一样的了。
可以通过以下命令来验证

```
keytool -v -list -keystore D:\test.keystore
```

<b>keystore转jks</b>
同理，keystore也可以转jks
```
keytool -importkeystore -srckeystore ***.keystore -srcstoretype JKS -deststoretype PKCS12 -destkeystore ***.p12

keytool -v -importkeystore -srckeystore ***.p12 -srcstoretype PKCS12 -destkeystore ***.jks -deststoretype
```