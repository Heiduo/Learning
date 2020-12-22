再写一个根据广播启动APP的功能，根据Intent的传参展示不同的界面。在测试的过程中发现，无论后续的intent内容的修改，getIntent()获取的总是第一次启动时得到的值。



**解决方法**

1. 因为Activity并未onDestory()，所以界面的更新需要在onResume()中进行设置。

   ```java
   	@Override  
       protected void onResume() {  
          Log.i("onResume", "onResume");  
          Bundle b = getIntent().getExtras();  
          updateView(b); 
          super.onResume();  
       }; 
   ```

2. 然后重写onNewIntent()方法，这个方法就是在不onDestroy() activity的同时，能够传值

   ```java
   	@Override  
    	protected void onNewIntent(Intent intent) {  
         	super.onNewIntent(intent);  
         	setIntent(intent);   
    	}  
   ```

   