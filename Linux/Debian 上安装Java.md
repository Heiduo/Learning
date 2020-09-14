# Debian 上安装Java

### 安装OpenJDK

OpenJDK，Java平台的开源实现是Debian 9中的默认Java开发和运行时。

安装简单明了。

#### 安装OpenJDK 8 JDK



```
sudo apt install default-jdk
```



#### 安装OpenJDK 8 JRE



```
sudo apt install default-jre
```





### 安装Oracle Java

在本节中，我们将向您展示如何从Oracle网站下载和安装最新的Oracle Java版本8。 在下载Java tar.gz文件之前，如果有新版本，您应该检查Oracle的下载页面。

按照以下步骤从Oracle网站安装最新的Oracle Java 8：

01、首先，下载Java存档：

```
curl -L -b "oraclelicense=a" -O http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz
```

上面的命令将自动接受Oracle许可证并下载Java tarball。

02、接下来，为Java安装创建一个目录

```
sudo mkdir /usr/local/oracle-java-8
```

03、将Java .tar.gz文件解压缩到先前创建的目录：

```
sudo tar -zxf jdk-8u181-linux-x64.tar.gz -C /usr/local/oracle-java-8
```

04、解压缩文件后，运行以下命令以创建新的替代方案：

```
sudo update-alternatives --install "/usr/bin/java" "java" "/usr/local/oracle-java-8/jdk1.8.0_181/bin/java" 1500
sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/local/oracle-java-8/jdk1.8.0_181/bin/javac" 1500
sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/local/oracle-java-8/jdk1.8.0_181/bin/javaws" 1500
```



### 设置默认版本

我们可以检查我们的默认Java版本，包括：

```
java -version
```

输入内容:

```
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

如果我们在服务器上安装了多个Java版本，我们可以使用update-alternatives系统更改默认版本：

```
sudo update-alternatives --config java
```

输出内容:

```
There are 2 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                            Priority   Status
------------------------------------------------------------
* 0            /usr/local/oracle-java-8/jdk1.8.0_181/bin/java   1500      auto mode
  1            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      manual mode
  2            /usr/local/oracle-java-8/jdk1.8.0_181/bin/java   1500      manual mode

Press  to keep the current choice[*], or type selection number:
```

出现提示时输入数字，然后按Enter键。

### 卸载Java

如果出于任何原因要卸载Java软件包，可以将其卸载为与apt一起安装的任何其他软件包。 例如，如果要卸载default-jre包，只需运行：

```
sudo apt remove default-jre
```