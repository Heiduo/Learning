在Android8.0 以前我们可以通过注册静态广播来接收外界发过来的广播信息，但是在Android8.0 以后已经对静态广播做了限制，所以主要是为8.0以下机型或者定制设备做设置。

首先我们需要实现一个广播接受器

```java
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent intentMainActivity = new Intent(context, MainActivity.class);
            intentMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentMainActivity);
        }
    }
}
```

其次我们需要在AndroidManifest.xml中注册静态广播

```xml
<!-- 静态注册广播接收器-->
        <receiver
            android:name=".MyBroadcastReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <category android:name="android.intent.category.HOME" />
            </intent-filter>
        </receiver>
```

通过监听系统boot启动完毕的广播来启动APP，监听这个广播需要添加权限

```xml
<!-- 开机启动权限 -->
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```



这样就能实现设备开机自启的功能，如果是其他应用发送广播，只需要将**Intent.ACTION_BOOT_COMPLETED**替换为对应的消息即可。



这里有几个小点需要注意一下

1. **Intent.FLAG_ACTIVITY_NEW_TASK**是启动Activity时必须要添加的Flags

2. Android在3.1以后会将新安装的应用置为“STOPPED”状态，只有当应用启动过一次之后这个状态才会改变，若应用处于STOPPED状态时，一般的广播是无法启动APP的。这时需要在发送的intent中添加```Intent.FLAG_INCLUDE_STOPPED_PACKAGES```才能正常启动此类应用

   即```intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);```

   若应用安装后，已经启动过一次，应用得到“STOPPED”状态会变更，此时不需要添加Flag也可以正常启动