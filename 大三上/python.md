# python

数字 字符串 元组 None



set 无索引，不可变



可迭代对象 _____iter_()或 ____getitem__()

迭代器 ____iter____() 和____next____()

迭代器一定是可迭代对象

生成器是迭代器



循环完了执行else，如果循环中break、return，则不执行else



没有异常才会执行else，try中异常或try中return则跳过else，直接执行finally



实例可以访问类的属性▪ 类无法访问实例方法▪ 类无法访问实例属性

实例方法：没有任何装饰器，有默认self的普通函数• 类调用实例方法(详见方法绑定内容)，需要先传入实例作为参数

类方法：有 classmethod装饰的函数• 类方法：既可以被类调用，也可以被实例调用

静态方法：有 staticmethod装饰的函数• 静态方法无法通过self或f或者cls访问到实例或类中的属性• 静态方法可以被实例或类调用

**实例方法、类方法、静态方法均可被类或实例调用**



赋值语句左边的.调用不触发getattribute

类调用不触发getattribute

赋值左边的.调用出发set

![image-20231227201410473](https://s2.loli.net/2023/12/27/yXhVNmLHGb2akZ9.png)



通过实例调用属性：其优先级分别是：数据描述器、实例变量、非数据描述器、类变量，最后是 __getattr___() （如果存在的话）

• 数据描述器始终会覆盖实例字典• 非数据描述器会被实例字典覆盖



![1280X1280](https://s2.loli.net/2024/01/02/C4YAGQtDqNZFs6I.png)

f_b

f_a

1

2

f_c

3



<img src="https://s2.loli.net/2024/01/02/iXEbHJZLUdCok3e.png" alt="e851715e-fe2c-4991-a04f-5f8b8ad09f6d" style="zoom:50%;" />

call __get__ <__main__.Widget object at 0x000001F5D3B47940> <class '__main__.Widget'>

<class 'int'>

call __set__ <__main__.Widget object at 0x000001F5D3B47940> 2

call __get__ <__main__.Widget object at 0x000001F5D3B47940> <class '__main__.Widget'>

call __get__ None <class '__main__.Widget'>

2 2

call __delete__<__main__.Widget object at 0x000001F5D3B47940>



![3af227ea-7d73-4300-a21f-65d513d8d9a6](https://s2.loli.net/2024/01/02/KvCe9TUZNoMm8BD.png)

foo.f2

foo.f1

base.f3



```Python
class Singleton(object):
    _instance = None
    def __new__(cls, *args, **kw):
        if not cls._instance:
            cls._instance = super.__new__(cls, *args, **kw)
        return cls._instance
    

single1 = Single()
single2 = Single()
print(id(single1) == id(single2))
```



![image-20240103110017342](https://s2.loli.net/2024/01/03/8aWo2tGSjfwVEDs.png)

面试题1

def demo():
    for i in range(4):
        yield i

g=demo()
g1=(i for i in g)#此时并不执行for循环，只是生成一个生成器
'''
上句代码相当于：
def func():
    for i in g:
        yield i
g1 = func()
'''
g2=(i for i in g1) #生成器表达式  返回的是一个生成器，表达式里的代码不执行
'''
执行过程就是，list找g1要全部的值，g1通过for循环找g一个一个的要值
'''
print(list(g1))#执行这行代码的时候，list把g1中所有的值都要走了
print(list(g2))#list找g2要值，g2找g1要值，但是g1把所以的值给了前一个list了，所以就没值可给了

运行结果：

[0, 1, 2, 3]
[]



面试题2

def add(n,i):
    return n+i

def test():
    for i in range(4):
        yield i

g = test()

for n in [1,10]:#所有for循环里套生成器表达式的，要把for循环拆开来算，不容易晕
    g=(add(n,i) for i in g)
'''
上两句代码相当于：
n = 1
g = (add(n,i) for i in g) #生成器表达式  返回的是一个生成器，表达式里的代码不执行
n = 10          #此后代码的n值都为10
g = (add(n,i) for i in g)  
———> g = (add(10,i) for i in (add(10,i) for i in g)) 
———> g = (add(10,i) for i in (add(10,i) for i in test())) 
———> g = (add(10,i) for i in (add(10,i)) for i in (0,1,2,3))
———> g = (add(10,i) for i in (add(10,i)) for i in (0,1,2,3))
———> g = (add(10,i) for i in (10,11,12,13))
———> g = (20,21,22,23)
'''
print(list(g))#list 找 g要值，然后g找上一个g要值,上一个g找test要值
'''
执行list(g)的时候，前面的生成器表达式才工作，此时n已经为10了，所以，所有的生成器表达式里的值都要用n=10来计算
'''

运行结果：

[20, 21, 22, 23]

![image-20231226225030511](https://s2.loli.net/2023/12/26/kDwUc9NsRFGIKtA.png)

## python语法基础



python解释器：广泛使用CPython IPython



标识符：用于变量、函数、类、模块等的名称

- 区分大小写
- 字母下划线开头，+字母数字下划线
- 不能使用关键字 True
- 双下划线开头结尾的标识符通常有特殊含义（____init____）类的初始函数



### python中一切皆对象



每个对象由**标识（identity）、类型（type）、值（value）**组成

- 标识：对象内存地址。内置函数id(obj)查看对象obj的标识

- 类型：对象的数据类型。内置函数type（obj）查看对象所属类型

- 值：对象的数据信息。print（obj）查看对象的值

python对象的本质：**一个内存块，拥有特定的值，只支持特定类型的相关操作**



![image-20231202123351593](https://s2.loli.net/2023/12/02/o9KANInyF2irvYp.png)



### python变量赋值

![image-20231202122930909](https://s2.loli.net/2023/12/02/52nA8pFtPgvrNQX.png)

![image-20231202122955698](https://s2.loli.net/2023/12/02/Lgcb9TpJkSARN2u.png)



![image-20231202134529073](https://s2.loli.net/2023/12/02/CAaHzNPlEFiGd7s.png)
![image-20231202134639593](https://s2.loli.net/2023/12/02/N9yVlYBz1Cc72on.png)
![image-20231202134811139](https://s2.loli.net/2023/12/02/SNK8BIZJizeywQ9.png)
字符串驻留机制：
![image-20231202134930866](https://s2.loli.net/2023/12/02/7JHLQqlx3NeU61A.png)



### python数据类型

![image-20231202123115369](https://s2.loli.net/2023/12/02/G35vZEqQkT84Nob.png)

![image-20231202123209017](https://s2.loli.net/2023/12/02/Rd4Jpn1U5hxQOZW.png)

可以使用type()函数查看变量类型



隐式类型转换（向上转换)（低精度——高精度）（int——float）

显式类型转换（强制转换）类型+（）int（5.5）



python中的数字

- 整数 Integer（int）
- 浮点数Float（float）
- 复数Complex（complex）



python中的二八十六进制

- 进制前缀：二八十六  0+box
- bin(100)
- oct(100)
- hex(100)



python中的字符串

- 字符串是字符的集合，可以使用索引访问

- 转义字符：

\n	\\\\	\\'	\\"	\\t：Tab	\\b：Backspace	\\r：Carriage Return回车

- 多行字符串可以使用三引号，同时保留制表符和空格

![image-20231202124510502](https://s2.loli.net/2023/12/02/EUFGRAvm58TutSi.png)

字符串运算符：

![image-20231029160116929](https://s2.loli.net/2023/10/29/wgGEhNq1risBb6Q.png)

字符串方法：

![image-20231029160226756](https://s2.loli.net/2023/10/29/HgL6GRKXfDaItm3.png)

![image-20231202124609174](https://s2.loli.net/2023/12/02/mhix1GMLw9BFYkD.png)

![image-20231202124747020](https://s2.loli.net/2023/12/02/5LZP9ja7wuDTHVI.png)



列表list

[] [:] \+ *

![image-20231202124831071](https://s2.loli.net/2023/12/02/6gVzabfNR3rnL7E.png)

![image-20231202125045130](https://s2.loli.net/2023/12/02/MXIyBavdzQJtqRm.png)

![image-20231202125144787](https://s2.loli.net/2023/12/02/j7owH63OTSvFfdr.png)

**remove方法根据元素值进行删除 只会删除第一个和指定值相同的元素**

![image-20231202133411759](https://s2.loli.net/2023/12/02/T8AMRKe6XoZH175.png)

元组tuple

tup = () 创建一个空元组

单元素元组的表示  (1, )

[] [:] 

\+ *

![image-20231202133658299](https://s2.loli.net/2023/12/02/O9Qvl6LIM1UJczw.png)

![image-20231202133742150](https://s2.loli.net/2023/12/02/U5BHyOhSmdYFwXo.png)

注意，使用 < 或 > 比较不同类型的对象是合法的，前提是这些对象具有适当的比较方法。例如混合数值类型根据其数值进行比较，因此等于 0 == 0.0 ， 结果是True。



字典dictionary

dic={}创建一个空字典

![image-20231202133829723](https://s2.loli.net/2023/12/02/WxCdqm5nB1G7tX6.png)

!![image-20231202133939557](https://s2.loli.net/2023/12/02/YIJK8CieOb39ZAl.png)



![image-20231202134009473](https://s2.loli.net/2023/12/02/UeLmFIJGkBnvcR6.png)



集合set

元素无序、确定、唯一

{}使用逗号分隔，也可使用set()函数创建集合

集合元素没有索引

集合是不可变的

![image-20231202134034343](https://s2.loli.net/2023/12/02/7rD1Y85ObjIBoq6.png)



### 格式化输出

- %
- format()
- f-strings() 推荐
- string template

![image-20231202135239519](https://s2.loli.net/2023/12/02/kK4rjz6TvyXDdW7.png)

![image-20231202135314276](https://s2.loli.net/2023/12/02/oMEQJ1SDO6UiHcG.png)

![image-20231202135348065](https://s2.loli.net/2023/12/02/PLMgpdthQuisjXC.png)

![image-20231202135418463](https://s2.loli.net/2023/12/02/wARyLNri8fFDuMt.png)

![image-20231202135532867](https://s2.loli.net/2023/12/02/V6ZWonP5e8uSNjM.png)



2<a<7 是可以的



in not in（成员）

is is not（identity 对象内存地址）



while——else

for——else

break

continue

pass

![image-20231202135951026](https://s2.loli.net/2023/12/02/t8BiYQvyfSeoc3b.png)

![image-20231202140319217](https://s2.loli.net/2023/12/02/4uWaFIfw8zbkJGA.png)

![image-20231202140715482](https://s2.loli.net/2023/12/02/mZOnMxiSyBYUeTK.png)

![image-20231202140738912](https://s2.loli.net/2023/12/02/X2WaxbeAKzDLl9Z.png)



### python中的函数

![image-20231202140900331](https://s2.loli.net/2023/12/02/f1ukB7AD6HeQsCi.png)

### python中的参数

- #### 位置参数

- #### 默认参数 arg 简单变量

- #### 可变参数 *args 元组tuple

- #### 关键字参数 **kw 字典dic


![image-20231202140933340](https://s2.loli.net/2023/12/02/k6z2giJIRN8vWrA.png)

![image-20231202141018257](https://s2.loli.net/2023/12/02/4aHUs6N9oSBkZE1.png)

**默认参数值在函数被定义时已创建，而非在程序运行时**



![image-20231202141250926](https://s2.loli.net/2023/12/02/91dwW7x48E3KaZ6.png)

![image-20231202141116232](https://s2.loli.net/2023/12/02/1ODCKtjYFZRbmXg.png)

![image-20231202141321275](https://s2.loli.net/2023/12/02/sTC1qLl3X6KYVEb.png)

只要函数调用时没有传递新的列表来覆盖默认参数列表，函数会使用定义时的列表，并且操作依次叠加。（**列表是可变数据类型**）

![image-20231202141455882](https://s2.loli.net/2023/12/02/fNHEb6K37zQGDPB.png)

![image-20231202141635063](https://s2.loli.net/2023/12/02/lzkQ68RfWGJAqtK.png)

![image-20231202141707155](https://s2.loli.net/2023/12/02/FyHMRowABtsOpID.png)

**有部分元素被跳过访问（输出后索引前移）**

**实际只访问了1，2，第四个2，4，2**



#### 函数参数传递的本质

![image-20231202141733686](https://s2.loli.net/2023/12/02/ArTkplnIjQSzUR2.png)



![image-20231202141908598](https://s2.loli.net/2023/12/02/NsBpwnP3tgJuUzb.png)

![image-20231202143001860](https://s2.loli.net/2023/12/02/GdN9UP4MpAZbC1B.png)

![image-20231202143039733](https://s2.loli.net/2023/12/02/o1j5lIAZ7fXFbhG.png)

![image-20231202143941450](https://s2.loli.net/2023/12/02/veC3Dayz6IEM4xt.png)



### python中的作用域

![image-20231202144115241](https://s2.loli.net/2023/12/02/KFRHnJvqAMOtTml.png)

![image-20231202145137510](https://s2.loli.net/2023/12/02/yJrGVC9NMvQ8B5S.png)



闭包 必考

嵌套函数

内部函数引用外部函数的变量

外部函数返回内部函数名

![image-20231202145721721](https://s2.loli.net/2023/12/02/6Ryv8M91ICtows5.png)

![image-20231202145832344](https://s2.loli.net/2023/12/02/rAxs3yISHQv1gGl.png)

![image-20231202150109388](https://s2.loli.net/2023/12/02/CRtTaIJ4x8cyZpW.png)

a.reverse()原列表

[-1] 生成新列表



### python中的推导式

![image-20231202150155051](https://s2.loli.net/2023/12/02/f2KrIhvyEGMT5jF.png)



### 匿名函数（lambda表达式）

![image-20231202150237149](https://s2.loli.net/2023/12/02/KMN27IuXrgkB3ch.png)



### 高阶函数

- sorted(list, key=)   把列表按 key 排序（升序）
- filter(is_odd, list)  筛选 is_odd 为true的list
- map(f, llist)      list(map(f,list))：对list中的每个元素执行f
- reduce(f, llist)  求阶乘

![image-20231202150431968](https://s2.loli.net/2023/12/02/3PAtETz8Qsp17XV.png)

![image-20231202150626343](https://s2.loli.net/2023/12/02/S5VtxNYdnWMIHyA.png)

![image-20231202150703871](https://s2.loli.net/2023/12/02/SOYBfrnRdmZh3LQ.png)

![image-20231202150822764](https://s2.loli.net/2023/12/02/mQqWdEJVyrUxf3M.png)

![image-20231202150901232](https://s2.loli.net/2023/12/02/w8QzV2FCbUiJZH7.png)



## 三器语法

### 装饰器Decorator

![image-20231202151530549](https://s2.loli.net/2023/12/02/6mSyXJ5jFfHOIgl.png)

![image-20231202151822210](https://s2.loli.net/2023/12/02/K8hXf3GFiwCtymz.png)

![image-20231202152106647](https://s2.loli.net/2023/12/02/QCfhKu5Yi9gW1Ej.png)

![image-20231202163424569](https://s2.loli.net/2023/12/02/PDdZxpuX4JGng1t.png)





### 迭代器Iterator

#### 可迭代对象iterable

![image-20231206185233682](https://s2.loli.net/2023/12/06/n6cr49Fzd5MZRGj.png)

#### 迭代器Iteror

![image-20231206185329543](https://s2.loli.net/2023/12/06/HsKOzAYFZQw4cbN.png)



### 生成器

#### 生成器表达式

![image-20231206185528946](https://s2.loli.net/2023/12/06/5GIsFPkhmcoJOvl.png)

#### 生成器函数





常见的三种设计模式

装饰器

类的Mixin

单例模式







继承type就是元类





单例模式的实现代码（一种即可）



vars是dict的对应内置函数

实例.__dict__可通过字典的形式修改属性值：实例.__dict__[属性名]="新属性值"› 类.__dict__只读，不可修改属性值





构造方法init

析构方法del



delete 描述器



托管属性 自动命名通告 定制名称 自己看



讲数据描述器和非数据描述器

![image-20231206172929093](https://s2.loli.net/2023/12/06/knRmJTgLrIZoAMp.png)











## 面向对象编程



### 定义类

- 实例可以访问类的属性和方法
- 类无法访问实例的属性和方法



属性的查找顺序

- 类名调用：类的属性
- 实例调用：先看实例的属性，再看类的属性



- 实例方法
- 类方法
- 静态方法 （函数）



![image-20231209102038424](https://s2.loli.net/2023/12/09/SiwBO2UrZNIGFvf.png)

![image-20231209102144342](https://s2.loli.net/2023/12/09/nVymJFN4x9K5lsS.png)

![image-20231209102713551](https://s2.loli.net/2023/12/09/UfxLckuvB3PgGn6.png)

![image-20231209102805886](https://s2.loli.net/2023/12/09/ADyaf9wSgtoYCvd.png)

![image-20231209102935483](https://s2.loli.net/2023/12/09/Yvz8dADIPrGVXZe.png)

![image-20231209103011630](https://s2.loli.net/2023/12/09/cjRYo6SaFQrZzT9.png)

![image-20231209103059836](https://s2.loli.net/2023/12/09/djfabBc9H6PkQMW.png)

![image-20231209103252855](https://s2.loli.net/2023/12/09/eiaxv3BOsSUdzhH.png)

![image-20231209103741357](https://s2.loli.net/2023/12/09/yUiNrP4bdJY8xHQ.png)

![image-20231209103850999](https://s2.loli.net/2023/12/09/Qoy4Z5eGiaOtJRM.png)

![image-20231209104021464](https://s2.loli.net/2023/12/09/oguAXtriY251SDB.png)

![image-20231209104131148](https://s2.loli.net/2023/12/09/VQ4Xpf8xhwCPyTn.png)



实例的私有属性不能在类外被直接访问 x.__var=1

私有方法不能在类外直接调用



类外访问私有属性和方法：

- _类名__属性名/方法名\_\_

![image-20231209104942832](https://s2.loli.net/2023/12/09/XnDkJbTQLsBPyou.png)

![image-20231209105104237](https://s2.loli.net/2023/12/09/i6XD9zrumKfCJlo.png)







### 类之间的关系

**多用组合 少用继承**

#### 继承 is-a

#### 组合（关联） has-a

#### 依赖 use-a

![image-20231209111726597](https://s2.loli.net/2023/12/09/8IBfzH2xQitjUy4.png)

![image-20231209111938864](https://s2.loli.net/2023/12/09/7ciQJWPCx6k8pvB.png)

![image-20231209111903334](https://s2.loli.net/2023/12/09/7vTIo8MSwXfgldj.png)

![image-20231209112006778](https://s2.loli.net/2023/12/09/hjWK7VTFmyrZnJi.png)

![image-20231209113254718](https://s2.loli.net/2023/12/09/BJSeGsufbYtCOrR.png)

![image-20231209115224895](https://s2.loli.net/2023/12/09/eOBnaLmuNtH5CQ8.png)

![image-20231209133753721](https://s2.loli.net/2023/12/09/sT4eBxlSinKjotJ.png)

![image-20231209133828562](https://s2.loli.net/2023/12/09/ojyerq73dzpEHbx.png)









### Mixin 类的设计模式：”接口"





















### 类的多态

#### 含义

在python中，不同的对象调用同一个接口（同名的方法），从而表现出不同状态。

python是动态类型语言，使用变量无需指定具体类型，故多态是其原始特性。

#### 多态发生的条件

- 继承：发生在父类和子类之间
- 重写：子类重写父类的方法

#### 多态的作用

- 增加程序灵活性
- 增加程序可扩展性

#### 多态的约束方法

- 抛异常

- 抽象基类和抽象方法

  ![image-20231209135804993](https://s2.loli.net/2023/12/09/TBIZkxbq21LAmfu.png)

  ![image-20231209140001246](https://s2.loli.net/2023/12/09/DsLkq6Zf7Huh14R.png)

抽象基类ABC（Abstract Base Class)

- 包含抽象方法的类，是特殊类，只能被继承，不能被实例化，且子类必须实现父类的抽象方法。





### 判断类实例的函数

![image-20231209140717546](https://s2.loli.net/2023/12/09/Kp9FoOtSTDwynkQ.png)



### 元类

![image-20231209140759378](https://s2.loli.net/2023/12/09/ZegHSKiu798RY6p.png)

![image-20231209140958560](https://s2.loli.net/2023/12/09/eAOotfIiB57wcPx.png)

![image-20231209141021912](https://s2.loli.net/2023/12/09/B2aNyZVtqSG8WH5.png)







### 协议编程

![image-20231209141145137](https://s2.loli.net/2023/12/09/xtiuTOqBdIJRH6r.png)

![image-20231209141205490](https://s2.loli.net/2023/12/09/UAzrJkEYmiHwoWP.png)

![image-20231209141240203](https://s2.loli.net/2023/12/09/juK9JvPGBsZb4TI.png)

![image-20231209141945790](https://s2.loli.net/2023/12/09/lmpHLbafwEFxV3g.png)

![image-20231209142014770](https://s2.loli.net/2023/12/09/9eugjJXBWSw5RQU.png)

![image-20231209142047721](https://s2.loli.net/2023/12/09/HzXnA8Umreu6Kk2.png)

![image-20231209142202971](https://s2.loli.net/2023/12/09/LSlEb7zCys2jJ58.png)

![image-20231209142222318](https://s2.loli.net/2023/12/09/Qae43hFjbgSw2fN.png)

![image-20231209142259610](https://s2.loli.net/2023/12/09/3KoW2jqwURHckdl.png)















#### 单例模式（singleton）

含义：一个类创建的所有对象都是同一个。

实现：判断是否已有该对象的实例化对象，有则返回，无则创建。

三种实现方式：new super 元类 装饰器

![image-20231209141654639](https://s2.loli.net/2023/12/09/cqUTirsXbjP9Kg4.png)

![image-20231209141843879](https://s2.loli.net/2023/12/09/YHxgJw9X573yTVG.png)

![image-20231209141908297](https://s2.loli.net/2023/12/09/UB3PWDh5nf2OLuS.png)





### 描述器协议

![image-20231209142359118](https://s2.loli.net/2023/12/09/onFzBE8XSehHM1R.png)

![image-20231209142433781](https://s2.loli.net/2023/12/09/LrQMwHWizZogVOj.png)

![image-20231209142459645](https://s2.loli.net/2023/12/09/bc7lhxmXaR1dWHe.png)

![image-20231209142607239](https://s2.loli.net/2023/12/09/46j3Sq79iBFXdZD.png)

![image-20231209142625958](https://s2.loli.net/2023/12/09/N834wD9JM1GKbZh.png)

![image-20231209142645964](https://s2.loli.net/2023/12/09/lsR6o3ESTnDCFIV.png)



#### 数据描述器和非数据描述器

![image-20231209142715839](https://s2.loli.net/2023/12/09/Bu1pcN67jwH4gVF.png)

![image-20231209142734857](https://s2.loli.net/2023/12/09/5xMo2hjCpFLvd3Z.png)

![image-20231209142752371](https://s2.loli.net/2023/12/09/dsHj7ZMAkbIqaVY.png)

![image-20231209142813826](https://s2.loli.net/2023/12/09/fwgPReJE2kj3onM.png)

![image-20231209142843838](https://s2.loli.net/2023/12/09/iB8ZWhXnbNdTkqr.png)

![image-20231209142916631](https://s2.loli.net/2023/12/09/Pjcwl8xD5aBquHS.png)

![image-20231209142935715](https://s2.loli.net/2023/12/09/xFiGNgdeZIrYCAn.png)

![image-20231209143004362](https://s2.loli.net/2023/12/09/DeV8lfNdtoYmB5u.png)

![image-20231209143024671](https://s2.loli.net/2023/12/09/vf2Ps8wYcitVh7r.png)

![image-20231209143054729](https://s2.loli.net/2023/12/09/6SaozryKP1m2YBp.png)

![image-20231209143113992](https://s2.loli.net/2023/12/09/r4FSVNopqHbhMOj.png)

![image-20231209143132362](https://s2.loli.net/2023/12/09/f5SOBTsCq6EuFwJ.png)



了解即可

![image-20231209143149422](https://s2.loli.net/2023/12/09/XaS2D8qIAZitN9C.png)



#### property属性描述器

![image-20231209143209058](https://s2.loli.net/2023/12/09/YgTWwZJovixL25V.png)

![image-20231209143223434](https://s2.loli.net/2023/12/09/r2Xz1MDVLwEvxSa.png)

![image-20231209143239048](https://s2.loli.net/2023/12/09/5hABYHWV9S7ylZU.png)

![image-20231209143253013](https://s2.loli.net/2023/12/09/BHMkbLGUzZ3mJOa.png)

![image-20231209143312159](https://s2.loli.net/2023/12/09/aTjwOu3yBZhs2PX.png)

![image-20231209143326887](https://s2.loli.net/2023/12/09/LvBIwauoRdG4lKA.png)

![image-20231209143344422](https://s2.loli.net/2023/12/09/JUFsImxEZPfaYOt.png)

![image-20231209143402397](https://s2.loli.net/2023/12/09/hvFHTOzWcBKAuMN.png)







## 异常



没有异常才会执行else

如果有异常，但未能捕获，也不会执行else

raise 抛异常



try和finally都有return，看finally的return

BaseException是所有内置异常类的基类（Exception继承BaseException）

用户自定义的异常不应直接继承BaseException，而应继承Exception类或其子类

![image-20231220184907920](https://s2.loli.net/2023/12/20/zXRqrbZQxO5Ymdu.png)

![image-20231220185050758](https://s2.loli.net/2023/12/20/RPcSJfLyGF8nCAw.png)

![image-20231220184620147](https://s2.loli.net/2023/12/20/MbqwO5p9UE3HPuv.png)

![image-20231220185216916](https://s2.loli.net/2023/12/20/FSuUrLVcoWYCIln.png)

![image-20231220185138371](https://s2.loli.net/2023/12/20/StkFJUYyDAqfgr1.png)

![image-20231220185401171](https://s2.loli.net/2023/12/20/BTqh6fkmvg9AFpw.png)

![image-20231220185425832](https://s2.loli.net/2023/12/20/FhBb6YnoAqES8xG.png)

![image-20231220185524261](https://s2.loli.net/2023/12/20/6O4IGVxDSApyWPd.png)











**重点**



![image-20231220190107753](https://s2.loli.net/2023/12/20/PbUHOWDEJ1Zxae5.png)

![image-20231220190221819](https://s2.loli.net/2023/12/20/Wmtc5fReJNiu9bo.png)

![image-20231220190248354](https://s2.loli.net/2023/12/20/sZLzH4UCXFNxgBn.png)

![image-20231220190339983](https://s2.loli.net/2023/12/20/oQZB1MFECaAuPdm.png)

5个内置异常直接继承BaseException（仅5个）

其余异常和自定义异常继承Exception或其子类



with open（“a.txt") as f



with对应的两个协议： \__enter__ 和 \__exit__





## 文件 I/O

文件流

open（）

read（）

write（）

close（）



默认 rt（read text）

![image-20231220192214189](https://s2.loli.net/2023/12/20/SOVKvm2s6D1wJQB.png)

![image-20231220192456379](https://s2.loli.net/2023/12/20/lWj7kOMDIHXanZu.png)

![image-20231220192555475](https://s2.loli.net/2023/12/20/gbrtdHFpuSnNOXi.png)

readline()

![image-20231220192630975](https://s2.loli.net/2023/12/20/q7eFrCDPTbvBJUM.png)



## 模块module、包

python中模块和包的概念，为什么引入这样的概念？

方便维护，代码重用，将一组相关功能的代码（多个类或函数）写成单独的py文件



搜索路径：就近原则

当前目录

默认路径

环境变量



if \_name_=="\_main__":

方便测试，其他调不执行，自己掉才执行



包：有层次的目录结构（多个模块和多个子包）

必须有\_init_.py文件



库、框架 不是python中的概念

flask django            
