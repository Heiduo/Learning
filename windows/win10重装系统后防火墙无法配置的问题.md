重装系统后，在配置网络防火墙时发现，防火墙与网络配置底下的几个配置变为灰色无法设置。



> 解决方法

手动配置gpedit.msc文件

新建文本，填入内容

```
@echo off

pushd "%~dp0"

dir /b C:\Windows\servicing\Packages\Microsoft-Windows-GroupPolicy-ClientExtensions-Package~3*.mum >List.txt

dir /b C:\Windows\servicing\Packages\Microsoft-Windows-GroupPolicy-ClientTools-Package~3*.mum >>List.txt

for /f %%i in ('findstr /i . List.txt 2^>nul') do dism /online /norestart /add-package:"C:\Windows\servicing\Packages\%%i"

pause
```

文本后缀改为.bat

双击运行完成后，解决