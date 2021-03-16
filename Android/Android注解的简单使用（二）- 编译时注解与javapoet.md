	运行时通过反射来找到注解会存在一定的性能问题，且大多数注解框架都是使用编译时注解

## 实现原理

1. 注解处理器（Annotation Processor）：编译时扫描与注解处理，我们需要实现注解处理器，来处理我们的注解，一般用于生成我们需要的代码文件。
2. 注解处理器会被打包在编译过程中调用，我们需要主动注册使java编译器能够识别自定义的注解处理器。
3. Android Studio本身不支持注解处理器，需要用到额外的插件。



## 实现（以ViewById为例）

### 1. 创建注解

​	首先，新建一个Java Library，module名为```annotation```用于保存所有我们自定义的注解，然后创建```ViewById```注解

```java
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface ViewById {
    int value() default -1;
}
```

### 2. 注解处理器的创建与注册

​	注解处理器必须放在 Java Library中，新建一个``annotator``模块，用于实现与注册注解处理器，新建好模块后我们添加类```IocProcessor```(继承于```AbstarctProcessor```)用于处理我们自定义的注解。

```java
public class IocProcess extends AbstractProcessor {
···
```

注解处理器的注册需要在resources目录下新建文件夹与文档进行注册，我们这里可以引入谷歌的插件进行简单的注册。在```annotator``模块下依赖库

```
implementation project(':annotation')
implementation 'com.google.auto.service:auto-service:1.0-rc6'
annotationProcessor 'com.google.auto.service:auto-service:1.0-rc6'
implementation 'com.squareup:javapoet:1.13.0'
```

这里有几个需要注意的点：

* gradle版本在5.2.0以上的Android Studio，处理导入auto-service包外，还需要主动添加annotationProcessor，因为其为了更快的构建项目，取消了对自定义注解的注册，所以5.2.0以上版本需要我们添加annotationProcessor
* javapoet可以方便我们对代码的生成，可以对我们要生成的代码有一个更好的把控

添加好插件后在```IocProcessor```上添加注解```AutoService```

```java
@AutoService(Processor.class)
public class IocProcess extends AbstractProcessor {
···
```

对于我们自定义的注解处理器，需要重写四个方法``` init```,``` getSupportedAnnotationTypes```,``` getSupportedSourceVersion```,``` process```。

1. ```init```方法用于获取辅助类

```java
	/**
     * 生成代码使用
     */
    private Filer mFilerUtils;

    /**
     * 根元素相关的辅助类，获取元素相关信息
     * - VariableElement 一般成员变量
     * - ExecutableElement 一般代表类中的方法
     * - TypeElement 一般代表类
     * - PackageElement 一般代表package
     */
    private Elements mElementUtils;

    /**
     * 跟日志相关的辅助类
     */
    private Messager mMessager;

    private Map<String, ProxyInfo> mProxyMap = new HashMap<String, ProxyInfo>();

    /**
     * 初始化需要使用的工具类
     *
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFilerUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }
```

2. ``` getSupportedSourceVersion```用于支持自己的注解

```java
    /**
     * 设置需要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(ViewById.class.getCanonicalName());
        return annotationTypes;
    }
```

3. ``` getSupportedSourceVersion```设置支持的版本（一般用固定写法）

```java
    /**
     * 设置支持的JAVA版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
```

4. 最重要的```process```方法，这个方法用于处理注解，流程：保存获取的被注解元素，以外部类为单元，用```ProxyInfo```对象保存一个类中所有被注解的元素，并将其存入```Map<String, ProxyInfo> mProxyMap```中。之后分别将```ProxyInfo```取出，并实现代码的生成。

```java
    /**
     * 最关键的代码生成
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mProxyMap.clear();
        
        //添加需要处理的注解
        List<Class> classList = new ArrayList<>();
        classList.add(ViewById.class);

        //保存注解
        if (!saveAnnotation(roundEnv,classList)){
            return false;
        }

        for (String key : mProxyMap.keySet()) {
            ProxyInfo proxyInfo = mProxyMap.get(key);
            //创建一个新的源文件，并写入
            try {
                JavaFileObject jfo = mFilerUtils.createSourceFile(
                        proxyInfo.getProxyClassFullName(),
                        proxyInfo.getTypeElement()
                );
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                error(proxyInfo.getTypeElement(),
                        "Unable to write injector for type %s: %s",
                        proxyInfo.getTypeElement(), e.getMessage());
            }
        }
        return false;
    }
```

其余的代码

```java
    /**
     * 获取并保存需要处理的注解
     * @param roundEnv
     * @param list
     * @return
     */
    private boolean saveAnnotation(RoundEnvironment roundEnv,List<Class> list) {
        for (Class clazz : list) {
            //获取被注解的元素
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(clazz);
            for (Element element : elements) {
                //检查element类型
                if (!checkAnnotationValid(element)) {
                    return false;
                }
                //获取到这个变量的外部类
                TypeElement typeElement = (TypeElement) element.getEnclosingElement();
                //获取外部类的类名
                String qualifiedName = typeElement.getQualifiedName().toString();

                PackageElement packageElement = mElementUtils.getPackageOf(typeElement);
                String packageName = packageElement.getQualifiedName().toString();
                //类的标志ID
                String id=packageName+"."+qualifiedName;

                //以外部类为单位保存
                ProxyInfo proxyInfo = mProxyMap.get(id);
                if (proxyInfo == null) {
                    proxyInfo = new ProxyInfo(mElementUtils, typeElement);
                    mProxyMap.put(id, proxyInfo);
                }
                //把这个注解保存到proxyInfo里面，用于实现功能
                proxyInfo.mElementList.add(element);
            }
        }
        return true;
    }

    /**
     * 检查元素属性是否能获取（非private）
     *
     * @param annotatedElement
     * @return
     */
    private boolean checkAnnotationValid(Element annotatedElement) {
        if (ClassValidator.isPrivate(annotatedElement)){
            error(annotatedElement, "%s() must can not be private.", annotatedElement.getSimpleName());
            return false;
        }
        return true;
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        mMessager.printMessage(Diagnostic.Kind.NOTE, message, element);
    }
```



代码是通过``` proxyInfo.generateJavaCode()```进行生成，这里我用了专门生成代码的库**Javapoet**

```java
    /**
     * 生成代码
     */
    public String generateJavaCode() {
        //参数

        //方法
        //注入activity
        MethodSpec inject = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addAnnotation(Override.class)
                .addParameter(ClassName.get(typeElement), "host")
                .addParameter(Object.class, "source")
                .addStatement("initViewById($L,$L) ", "host", "source")
                .build();

        //生成 initViewById 方法
        MethodSpec.Builder initViewByIdBuild = MethodSpec.methodBuilder("initViewById")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ClassName.get(typeElement), "host")
                .addParameter(Object.class, "source");

        Iterator<Element> iterator = mElementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            ViewById annotation = element.getAnnotation(ViewById.class);
            if (annotation != null) {
                VariableElement variableElement = (VariableElement) element;
//                initViewByIdBuild.addCode(generateViewById(variableElement));
                generateViewById(initViewByIdBuild, variableElement);
                iterator.remove();
            }
        }

        //注意修改包名
        ParameterizedTypeName typeName =ParameterizedTypeName.get(ClassName.get("com.heiduo.annotationlibrary"
                ,ProxyInfo.PROXY)
                ,ClassName.get(typeElement.asType()));
        //类
        TypeSpec activityProxy = TypeSpec.classBuilder(proxyClassName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(typeName)
                .addMethod(inject)
                .addMethod(initViewByIdBuild.build())
                .build();

        //包
        JavaFile javaFile = JavaFile.builder(packageName, activityProxy)
                .build();

        return javaFile.toString();
    }

    private void generateViewById(MethodSpec.Builder builder, VariableElement variableElement) {
        //获取注释值
        int id = variableElement.getAnnotation(ViewById.class).value();
        //获取变量类型
        String type = variableElement.asType().toString();
        //获取变量名字
        String name = variableElement.getSimpleName().toString();

        TypeName clazz = ClassName.get(variableElement.asType());
        if (id == -1) {
            builder.beginControlFlow("if ($L instanceof android.app.Activity)", "source")
                    .addStatement(" $L.$L = ($T)(((android.app.Activity)source).findViewById( " + "R.id.$L))","host", name,clazz,name)
                    .nextControlFlow("else ")
                    .addStatement(" $L.$L = ($T)(((android.view.View)source).findViewById( " + "R.id.$L))","host",name, clazz,name)
                    .endControlFlow();
        } else {
            builder.beginControlFlow("if ($L instanceof android.app.Activity)", "source")
                    .addStatement(" $L.$L = ($T)(((android.app.Activity)source).findViewById( $L))","host",name, clazz,id)
                    .nextControlFlow("else ")
                    .addStatement(" $L.$L = ($T)(((android.view.View)source).findViewById( $L))","host",name, clazz,id)
                    .endControlFlow();
        }
    }
```

当然也可以直接拼接字符串，但拼接字符串会麻烦些。

还有部分代码并没展示出来，有需要可以在GitHub源码中查看

### 3. 调用代码

​	对于生成的注解我们需要在项目中使用，我们还需要新建一个Android Library, ```annotationlibrary```用于专门提供API，可以实现注解与项目的完全分离。

​	新建的Android library需要导入注解：

​	``` api project(':annotation') ```

这里的功能是比较简单的，我们生成的代码是只有编译时才能生成，所以只能在代码中反射获取。

```java
    private static final String SUFFIX = "$$ViewInject";
	/**
     * 根据使用注解的类和约定的命名规则，反射获取注解生成的类
     *
     * @param object
     * @return
     */
    private static ViewInject findProxyActivity(Object object) {
        try {
            Class clazz = object.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (ViewInject) injectorClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("can not find %s , something when compiler.", object.getClass().getSimpleName() + SUFFIX));
    }
```

绑定控件,提供给生成的代码绑定id用。

```java
public interface ViewInject<T> {
    void inject(T t,Object source);
}
```

调用函数：

```java
    public static void injectView(Activity activity){
        ViewInject viewInject = findProxyActivity(activity);
        if(viewInject == null){
            return;
        }
        viewInject.inject(activity,activity);
    }

    public static void injectView(Object object, View view){
        ViewInject viewInject = findProxyActivity(object);
        if(viewInject == null){
            return;
        }
        viewInject.inject(object,view);
    }
```

然后在需要调用的module（:app 或其他）中添加依赖，然后使用注解

```
    implementation project(':annotationlibrary')
    annotationProcessor project(':annotator')
```

项目中添加注解

```java
    @ViewById(R.id.tvText)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);
        tvText.setOnClickListener(v -> tvText.setText("测试"));
    }
```

添加注解后Rebuild项目一下，可以在

![http://image.heiduo.top/129af895fdb945e995de6fd6ff1d7400](http://image.heiduo.top/129af895fdb945e995de6fd6ff1d7400)

中看到自动生成的代码。

最终调用注解处理器生成的代码：

``` java
package com.heiduo.testannotion;

import android.widget.TextView;
import com.heiduo.annotationlibrary.ViewInject;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewInject implements ViewInject<MainActivity> {
  @Override
  public void inject(MainActivity host, Object source) {
    initViewById(host,source) ;
  }

  public void initViewById(MainActivity host, Object source) {
    if (source instanceof android.app.Activity) {
       host.tvText = (TextView)(((android.app.Activity)source).findViewById( 2131231079));
    } else  {
       host.tvText = (TextView)(((android.view.View)source).findViewById( 2131231079));
    }
  }
}
```



> 项目地址：[https://github.com/Heiduo/TestAnnotion](https://github.com/Heiduo/TestAnnotion)
>
> 参考文章：[Android编译时注解](https://www.jianshu.com/p/3052fa51ee95)

