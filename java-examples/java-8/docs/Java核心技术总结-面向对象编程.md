# Java 核心技术总结-面向对象编程

## 面向对象与面向过程

### 面向对象

**面向对象** 是一种以对象为基础，以事件或消息来驱动对象执行处理的程序设计技术。它具有抽象性、封装性、继承性及多态性。

### 面向过程

**面向过程(Procedure Oriented)** 是一种以过程/函数为中心的编程思想。

### 面向过程与面向对象的区别

* 从设计思路来看。
    * 面向过程：程序设计的重点是分析解决问题的步骤，以及完成步骤的流程，是一种结构化自上而下的程序设计方法。
    * 面向对象：程序设计的重点是把构成问题的事物分解成对象，从局部着手，通过迭代的方式逐步构建出整个程序，是一种以数据为核心，以类设计为主的自下而上的程序设计方法。
* 从适用范围来看。
    * 面向过程：由于不涉及实例化对象等操作，程序性能更高，开销更少，因此像切入式等对资源要求较高的系统大多都采用面向过程开发。
    * 面向对象：由于其抽象、封装、继承、多态的特性，使得系统具有更好的扩展性、维护性、复用性，对于功能复杂且维护成本较高的系统采用面向对象的开发。
* 从代码复用来看。
    * 二者都可以实现代码复用，但是面向过程主要是函数，而面向对象主要是类。

## 包

为了更好的组织类，Java提供了包机制，用于区分类名的命名空间。

* 定义类的所属包名
  规则：在Java文件最上方加入package 语句

```java
package com.wusuiwei.java;//定义类的所属包名

public class Demo1 {
    int value = 0;
}
```

* 类中导入所需类

```java
package com.wusuiwei.java;

import java.util.Date;//导入所需类

public class TestDemo {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getTime());
    }
}
```

* 类中静态导入所需类

```java
package com.wusuiwei.java;

import static java.lang.Math;//静态导入所需类

public class TestDemo {
    public static void main(String[] args) {
        double x = 30;
        double y = 40;
        // 静态导入的方式写起来更方便一些. 
        // double result = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double result = sqrt(pow(x, 2) + pow(y, 2));
        System.out.println(result);
    }
}

```

## 类

### 类的组成
#### 属性

#### 方法

#### 构造器

#### 代码块
Java中，用`{}`括起来的代码段被成为代码块（Code Block）。
* 静态代码块
必须放在类下，用static修饰。

  ```java
  public class Test2 {
    public static String name;

    // 静态代码块
    static {
        // 初始化静态资源
        name = "张三";
        System.out.println("静态代码块执行...");
    }

    public static void main(String[] args) {
        System.out.println("main方法执行...");
        System.out.println(name);
    }
}
  ```

* 非静态代码块

    * 同步代码块

  ```java

public class Test4 implements Runnable {
    @Override
    public void run() {
        synchronized (CodeBlock.class) {
            System.out.print("同步代码块...");
        }
    }

    public static void main(String[] args) {
        CodeBlock a = new CodeBlock();
        CodeBlock b = new CodeBlock();
        new Thread(a).start();
        new Thread(b).start();
    }
}
  ```

* 局部代码块

#### 内部类

分类：成员内部类、局部内部类（含匿名内部类）

* 成员内部类
    * 访问权限可以设置为public、protected、(缺省）、private
    * 可以访问外部类的结构（包括private的成员)
    * static 修饰，内部类中只能使用外部类static成员变量及方法
    * abstract 修饰，可以被其他内部类继承
    * final 修饰，不能被继承
    * 成员内部类中可以定义属性、方法、构造器等结构，非static的成员内部类的成员不能声明为static
    * 编译后生成OuterClass$InnerClass.class字节码文件

  ```java
  class Oubter {
    private int i;

    public class Inner {
        public void mb() {
            s = 100;
            System.out.println("在内部类Inner中的s=" + s);
        }
    }

    public void ma() {
        Inner inner = new Inner();
        inner.mb();
    }
}

public class InnerTest {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.ma();
    }
}
  ```

输出结果：

  ```
  在内部类Inner中的s=100
  ```

  ```java
  public class Outer {
    private int s = 11;

    public class Inner {
        public void mb(int s) {
            System.out.println(s);
            System.out.println(this.s);
            System.out.println(Outer.this.s);
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Inner inner = outer.new Inner();
        inner.mb(333);
    }
}
  ```

输出结果：

  ```
  333
  222
  111
  ```

* 局部内部类：在代码块或方法中声明
    * 访问权限与局部变量类似，不可使用public、protected、缺省、private
    * 可以访问外部类的结构（包括private的成员)
    * 可以使用外部方法的局部变量，但必须是final的
    * 只能在声明局部内部类的方法或代码块中使用，先声明后使用
    * abstract 修饰，可以被其他内部类继承
    * final 修饰，不能被继承
    * 不能使用static修饰，也不能包含静态变量
    * 编译后生成OuterClass+$+数字编号的字节码文件
      声明方式：

```
class 外部类{
  方法(){
    class 局部内部类{}
  }
  {
  局部内部类{}
  }
}
```

* 匿名内部类
    * 匿名内部类必须继承父类或实现接口；
    * 匿名内部类只能有一个对象
    * 匿名内部类只能采用多态方式引用
    * 格式：
      ```
      new 父类构造器(实参列表)|实现接口(){
        //匿名内部类的类体部分
      }
      ```

```java
interface A {
    void fun();
}

public class Outer {
    public void caller(A a) {
        a.fun();
    }

    public static void main(String[] args) {
        new Outer().caller(new A() {
            @Override
            public void fun() {
                System.out.println("implement for fun");
            }
        });
    }
}
```

### 类的初始化顺序

将一个类加载到JVM需要经历三个阶段：加载->链接(验证、准备、解析)->初始化
加载：这是由类加载器（ClassLoader）执行的。通过一个类的全限定名来获取其定义的二进制字节流（Class字节码），将这个字节流所代表的静态存储结构转化为方法区的运行时数据接口，根据字节码在java堆中生成一个代表这个类的java.lang.Class对象。
链接：
2.1.验证：验证Class文件中的字节流包含的信息是否符合当前虚拟机的要求。
2.2.准备：为静态域分配存储空间并设置类变量的初始值（默认值），
2.3.解析：将常量池中的符号引用转化为直接引用。
初始化：类的初始化顺序 ：父类（静态变量、静态代码块）–>子类（静态变量、静态代码块）–>父类（变量、代码块）–> 父类构造器–>
子类（变量、初始化块）–>子类构造器。注意：静态代码和静态变量同级，变量和代码块同级。谁在前先执行谁。类只会初始化一次。
补充：静态初始化，是在加载类的时候初始化。而非静态初始化，是new类实例对象的时候加载。

```java
public class Test {

    public static void main(String[] args) {
        try {
            System.out.println("-------加载类-----");
            System.out.println(Class.forName("Son"));
            System.out.println("---new类对象实例------");
            new Son();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Parent {
    private static String a = inita();
    private String b = initb();

    static {
        System.out.println("父类静态代码块开始执行");
    }

    {
        System.out.println("父类代码块开始执行");
    }

    public static String inita() {
        System.out.println("父类静态变量开始赋值");
        return "父类静态变量";
    }

    public String initb() {
        System.out.println("父类成员变量开始赋值");
        return "父类成员变量";
    }

    public Parent() {
        System.out.println("父类构造方式开始执行");

    }
}

class Son extends Parent {
    {
        System.out.println("子类代码块开始执行");
    }

    static {
        System.out.println("子类静态代码块开始执行");
    }

    private static String a = initSa();
    private String sb = initSb();

    public static String initSa() {
        System.out.println("子类静态变量开始赋值");
        return "子类静态变量";
    }

    public String initSb() {
        System.out.println("子类成员变量开始赋值");
        return "子类成员变量";
    }

    public Son() {
        System.out.println("子类构造方式开始执行");
    }
}
```

执行结果：

```
-------加载类-----
父类静态变量开始赋值
父类静态代码块开始执行
子类静态代码块开始执行
子类静态变量开始赋值
class Son
---new类对象实例------
父类成员变量开始赋值
父类代码块开始执行
父类构造方式开始执行
子类代码块开始执行
子类成员变量开始赋值
子类构造方式开始执行
```
### 变量作用域

### synchronized 关键字

1. **synchronized 用法**

* **修饰实例方法**：作用于当前对象实例加锁，进入同步代码前要获得**当前对象实例的锁**

  说明：
    * 接口定义方法时不能使用synchronized关键字
    * 子类不能继承synchronized关键字
    * 构造方法不能使用synchronized关键字
  ```java
  class Parent {
    public synchronized void method() {   }
  }
  class Child extends Parent {
    public void method() { super.method();   }
  }
  ```
* **修饰静态方法**：给当前类加锁，会作用于类的所有对象实例，进入同步代码前要获得当前class的锁。

  说明：
    * 类中静态方法和非静态方法都使用synchronized关键字时，不同线程访问静态方法加锁的时当前类，其他线程访问非静态方法加锁的是当前对象，互不影响。

```java

/**
 * 同步线程
 */
class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void run() {
        method();
    }
}

public class Demo00 {

    public static void main(String args[]) {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
```

* 修饰局部代码块：指定加锁对象，对给定对象/类加锁。
  说明：
    * synchronized(this|object) 表示进入同步代码库前要获得给定对象的锁。synchronized(类.class) 表示进入同步代码前要获得
      当前 class 的锁。

```java
 class SynchronizedObjectLock implements Runnable {
    static SynchronizedObjectLock instence = new SynchronizedObjectLock();

    @Override
    public void run() {
        // 同步代码块形式——锁为this,两个线程使用的锁是一样的,线程1必须要等到线程0释放了该锁后，才能执行
        synchronized (this) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        //两个线程传入的都是同一个对象，所以执行是需要等第一个线程执行结束才会执行第二个线程
        Thread t1 = new Thread(instence);
        Thread t2 = new Thread(instence);
        t1.start();
        t2.start();
    }
}
/**
 输出结果：
 　　我是线程Thread-0
 　　Thread-0结束
 　　我是线程Thread-1
 　　Thread-1结束
 */
```

```java
 class SynchronizedObjectLock implements Runnable {
    @Override
    public void run() {
        // 同步代码块形式——SynchronizedObjectLock.class,因此多个线程的对象公用的同一把锁
        synchronized (SynchronizedObjectLock.class) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        SynchronizedObjectLock instence1 = new SynchronizedObjectLock();
        SynchronizedObjectLock instence2 = new SynchronizedObjectLock();
        Thread t1 = new Thread(instence1);
        Thread t2 = new Thread(instence2);
        t1.start();
        t2.start();
    }
}
/**

 */
```

[//]: # (//todo synchronized 同步原理)

### final 关键字

### static 关键字

### this 关键字

### super 关键字


## 对象

## 抽象类

## 接口


## 面向对象的三大特征

### 封装

**封装**
是指利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体，数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，只保留一些对外接口使之与外部发生联系。系统的其他对象只能通过包裹在数据外面的已经授权的操作来与这个封装的对象进行交流和交互。
#### this 关键字
#### 类的访问控制

对于Class的权限修饰符只可以使用public和default(缺省)。

- public 修饰的类可以在任意地方被访问。
- default修饰的类只可以被同一个包内部的类访问。

#### 类的属性、方法、内部类的访问控制

Java提供了权限修饰符public、protected、（缺省）、private用于类的属性、方法、内部类的访问控制。
![权限修饰符](images/权限修饰符.png)

### 继承

继承就是子类继承父类的特征和行为，使得子类对象（实例）具有父类的实例域和方法，或子类从父类继承方法，使得子类具有父类相同的行为。Java继承是
**单继承**和**多层继承**。
语法规则：class SubClass **extends** SuperClass{}

#### 方法的重写（override/overwrite)

* 要求
  * 子类重写的方法必须和父类被重写的方法具有相同的方法名称和参数列表
  * 子类重写的方法返回值不能大于父类被重写的方法返回值类型
  * 子类重写的方法的访问权限不能小于父类被重写的方法访问权限
    * 子类不可重写父类的private权限的方法
    * 子类不可重写父类被static修饰的方法
  * 子类重写的方法抛出的异常不能大于父类被重写的方法抛出的异常

```java
class Fruit {
    public void eat() {
        System.out.printl('eat fruit');
    }
}

class Apple extends Fruit {
    @Override
    public void eat() {
        System.out.printl('eat apple');
    }
}
```

#### super 关键字

* super 用于访问父类中定义的属性

```java
class Animal {
    String color = "白色";
}

class Dog extends Animal {
    String color = "黑色";

    void printColor() {
        System.out.println(color);
        System.out.println(super.color);
    }
}
```

* super 用于访问父类中定义的方法

```java
class Animal {
    void eat() {
        System.out.println("吃...");
    }
}

class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("吃...");
    }

    void bark() {
        System.out.println("汪汪汪...");
    }

    void work() {
        super.eat();
        bark();
    }
}  
```

* super 用于子类构造器中调用父类的构造器

```java
public class ReferParentConstructor {
    public static void main(String[] args) {
        new Dog();
    }
}

class Animal {
    Animal() {
        System.out.println("动物来了");
    }
}

class Dog extends Animal {
    Dog() {
        super();
        System.out.println("狗狗来了");
    }
}
```

输出结果：

```
动物来了
狗狗来了
```

子类构造器默认都会访问父类的无参构造器

```java
public class ReferParentConstructor {
    public static void main(String[] args) {
        new Dog();
    }
}

class Animal {
    Animal() {
        System.out.println("动物来了");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("狗狗来了");
    }
}
```

输出结果：

```
动物来了
狗狗来了
```

当父类中没有无参构造器时，子类的构造器可以通过this(参数列表)或super(参数列表)
语句指定本类或者父类中的相应构造器，只能“二选一”，且必须放在构造器首行。如果父类没有无参构造器，则不可使用super()。

```java
public class MyTest {

    public static void main(String[] args) {
        new Cat();
    }
}

//父类，Animal类
class Animal {
    //构造函数
    public Animal() {
        System.out.println("1：Animal类的无参数构造函数执行");
    }

    public Animal(int i) {
        System.out.println("2：Animal类的有int参数构造函数执行");
    }
}

//子类，Cat类
class Cat extends Animal {
    //构造函数
    public Cat() {
        this("");
        System.out.println("3：Cat类的无参数构造函数执行");
    }

    public Cat(String str) {
        super(5);
        System.out.println("4：Cat类的有String参数构造函数执行");
    }
}
```

输出结果：

```
2：Animal类的有int参数构造函数执行
4：Cat类的有String参数构造函数执行
3：Cat类的无参数构造函数执行
```

### 多态













