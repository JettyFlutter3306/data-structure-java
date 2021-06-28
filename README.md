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

1.打印二叉树的层次结构

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


