# DataStructure

#### 介绍
数据结构Java语言实现教学,教科书般的代码,多变的算法例题解析,丰富的注释......  

本项目参考教材:  <br/>
    <<数据结构析>> 叶核亚 著 <br/>
    <<数据结构与算法分析 Java语言描述>> [美] Mark Allen Weiss 著 <br/>
这主要是作者闲暇时光里写的一个项目,主要以数据结构教程为主,也是首个个人开源项目. <br/>
适合人群: 考研复习者,刚接触数据结构的大学生,想深入学习数据结构的在职程序员......
目前还在不断维护中...


#### 软件架构
软件架构说明
这是一个用Maven构建项目,除了一个JUnit Maven依赖,没有用什么第三方类库,这是基本的代码结构截图

**顺序表类** 
![顺序表类](https://images.gitee.com/uploads/images/2021/0520/122106_03f08a6e_7713888.png "main1.png")

**字符串类**
![字符串类](https://images.gitee.com/uploads/images/2021/0607/102305_4e3d09b3_7713888.png "QQ截图20210607102200.png")

**二叉树类**
![二叉树类](https://images.gitee.com/uploads/images/2021/0607/102314_0cf85e22_7713888.png "QQ截图20210607102217.png")

**散列表类**
![散列表类](https://images.gitee.com/uploads/images/2021/0607/102324_976d4bcb_7713888.png "QQ截图20210607102240.png")

**集合UML类图**
![集合UML类图](https://images.gitee.com/uploads/images/2021/0627/222452_27963dfd_7713888.png "集合UML图.png")

 **Java集合UML类图** 
![JavaCollectionUML](https://images.gitee.com/uploads/images/2021/0628/165418_d5b8bc00_7713888.png "JavaCollection.png")

目录结构
```
├── 
├── 
├── main.java.cn.element       // 源代码
│   ├── algorithm              // 一些经典的算法题
│   ├── app                    // 一些数据结构的应用,比如说计算表达式
│   ├── common                 // 公共类和接口,例如 Addable<T>
│   ├── graph                  // 图
│   ├── list                   // 线性表
│   ├── matrix                 // 矩阵
│   ├── queue                  // 队列
│   ├── search                 // 查找算法
│   ├── sort                   // 排序算法
│   ├── stack                  // 栈
│   ├── string                 // 串
│   ├── tree                   // 树
├── test                       // 存放一些测试类
```



#### 安装教程

1.  直接点击下载源码然后解压到电脑任意位置,再在IDEA或者Eclipse里面打开即可,十分简单

#### 使用说明

1.  项目里面的每一个包都对应着一个数据结构的实现,例如list表示线性表,包内有顺序表,链表的实现...


#### 参与贡献

1.  暴躁程序猿老哥倾力打造
2.  拉取代码 git pull https://gitee.com/Lobidas_Daddy/data-structure
3.  提交代码 git push https://gitee.com/Lobidas_Daddy/data-structure
4.  新建 Pull Request


#### 特技

**1.打印二叉树的层次结构**

```
/**
 * 二叉树结构示例:
 *              1
 *            /   \
 *          2       3
 *        /   \   /   \
 *       4     5  6    7
 */
```


  工具类BinaryTrees的静态show(BinaryTree<T> tree)方法可以直观清晰地打印二叉树的层次结构


```
   /**
    * 测试打印二叉树的层次结构
    */
    @Test
    public void test05() {

        String[] preList = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};  //先根遍历序列

        BinaryTree<String> tree = new BinaryTree<>(preList);

        BinaryTrees.show(tree);
    }
```

**输出结果:** 
```
                        A                        
                    /       \                    
                B               C                
             /               /     \             
          D               E           F          
            \                       /            
              G                   H 
```

**2.论Java如何实现深拷贝?**

​	Java语言实现深拷贝通常有以下几种方法:

​	(1) 直接对一个类使用 new 关键字,然后填充要拷贝的源数据的值,此种方法不推荐,十分繁琐!

​	(2) 实现Cloneable接口,重写Object的clone()方法.注意!Cloneable接口里面什么也没有跟Serializable接口一样,它仅仅是一个标识接口而已.clone()方法,默认是浅拷贝,必须要重写!但是假如一个类中有多种引用数据类型,而且这些引用数据类型内部又嵌套了其他的引用数据类型,就跟俄罗斯套娃一样,层层嵌套,用clone()方法实现深拷贝就显得有些捉襟见肘了!所以也不推荐!

What?  你 TM 逗老子玩呢?  这也不行,那也不行,那到底改怎么办?
别急! 听老油条码农娓娓道来!

最终大招!  序列化和反序列化实现深拷贝!

​	(3)  首先先让要拷贝的类实现Serializable接口,上面说了Serializable接口里面什么也没有,仅仅是一种标识接口而已.这表示这个类可以序列化成数据流.我们常用的JSON组件如GSON,Jackson等组件实现了将Java对象转换为JSON数据流返回给前端,或者将前端传给后端的JSON数据转换为Java对象,这其中就用到了序列化和反序列化的知识,只不过它们要转换为JSON格式的而已!在这里,我们仅仅是想把Java对象转为普通数据流而已,Java有默认的序列化方式,我们不必操心.

​		我们再来说说序列化和反序列化的本质是什么?

​		序列化和反序列化本质是一种I/O操作,I/O操作必然要调用操作系统内核API,因为一切底层操作必然由操作系统来完成.序列化就是把数据从应用程序所在内存(这里是JVM)拷贝到操作系统内核所在内存,相当于操作系统帮我们完成了拷贝!反序列化刚好与之相反,就是把数据流从操作系统内核所在内存拷贝到应用程序所在内存,然后转为Java对象,就这么简单,这一切操作都相当于操作系统在帮我们完成!

​		说了这么多废话!直接上代码!为了代码的复用,我们最好在项目里面创建工具类!名称为SerializeUtil,里面有三个方法.

```java
/**
 * 序列化工具类
 */
public class SerializeUtil {

    private static OutputStream os;

    /**
     * 序列化
     */
    public static <T> void serialize(T obj) throws IOException {

        //将对象写到流里面
        os = new ByteArrayOutputStream();

        ObjectOutputStream oo = new ObjectOutputStream(os);

        oo.writeObject(obj);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(OutputStream os) throws IOException, ClassNotFoundException {

        //从流里面读取出来
        InputStream is = new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());

        ObjectInputStream oi = new ObjectInputStream(is);

        T obj = (T) oi.readObject();

        os.close();
        is.close();
        oi.close();

        return obj;
    }

    /**
     * 使用序列化和反序列化实现深拷贝
     */
    public static <T> T deepClone(T obj) throws IOException, ClassNotFoundException {

        serialize(obj);

        return deserialize(os);
    }


}
```

如何测试是否成功拷贝,很简单,打印两个对象的hashcode比较是否相等,或者直接System.out.println(o1 == o2);比较两个对象是否相等,如果返回false,那么就拷贝成功了!在上面的UML图中可知道所有的集合都继承AbstractCollection类,并实现了Serializable接口!所以可以用此法实现深拷贝!

