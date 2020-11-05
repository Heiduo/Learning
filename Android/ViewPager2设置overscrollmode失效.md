把项目中的ViewPager更新为ViewPager2时，发现滑动至两端时阴影效果又重新出现了，无论在代码或者属性中设置```android:overScrollMode="never"```都无效。

需要在代码中设置

```java
View child = viewPager2.getChildAt(0); 
if (child instanceof RecyclerView) {    child.setOverScrollMode(View.OVER_SCROLL_NEVER); 
}
```