### 1. 新建注解

新建一个注解与接口类似，在关键字interface前加个@

```xml
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface BViewBind {
    int value() default -1;
}
```

### 2. 元注解

@Retention 与 @Target 属于Java提供的基础注解，负责注解其他注解。
元注解有：

- @Retention：注解保留的生命周期
- @Target：注解对象的作用范围
- @Inherited：表明所修饰的注解，在所作用的类上，是否可以被继承
- @Documented：如其名，javadoc的工具文档话，一般不关心

#### 2.1. @Retention

Retention表明了注解的生命周期，对应RetentionPolicy的枚举，表示注解在何时生效：

- SOURCE：旨在源码阶段生效，编译时抛弃忽略，如@Override。
- CLASS：编译class文件时生效，不会加载到JVM中。
- RUNTIME：运行时才生效，会被加载到JVM中，程序运行时可获取。

#### 2.2. @Target

Target表明了注解的适用范围，设置注解的类型，对应ElementTyPe枚举，明确了注解的有效范围，不同类型给Java不同的地方进行标记。

- TYPE：类，接口，枚举，注解类型。
- FIELD：类成员/字段。
- METHOD：方法。
- PARAMETER：参数。
- CONSTARUCTOR：构造器。
- LOCAL_VARIABLE：局部变量。
- ANNOTATION_TYPE：注解。
- PACAKAGE：包声明。
- TYPE_PARAMETER：类型参数。
- TYPE_USE：类型使用声明。

#### 2.3. @Inherited

注解所作用的类，在继承时默认是无法继承父类的注解的，除非注解声明了@Inherited。但同时，Inherited声明出来的注解，只对类有效，对方法/属性无效。



### 3. 运行时标签的使用@BViewBind

新建一个标签且标签只有一个属性且为value时，使用时可以不用写名字，用value来保存控件id。

``` xml
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface BViewBind {
    int value() default -1;
}
```

具体实现：找出所有属性，查看是否含有BViewBind标签，包含就对其进行对应操作

```java
@BViewBind(R.id.tvText)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnnotation();
        tvText.setOnClickListener(v -> tvText.setText("测试"));
    }

    private void initAnnotation() {
        //获取成员变量
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field :
                fields) {
            //允许反射修改参数
            field.setAccessible(true);
            BViewBind bViewBind = field.getAnnotation(BViewBind.class);
            if (bViewBind != null) {
                try {      field.set(this,findViewById(bViewBind.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```

这样就简单实现了Butter Knife 的效果，当然这个是在运行时实现的，注解效率比较低，而Butter Knife 是```CLASS```编译时注解，对于编译时的注解则需要进一步的了解。