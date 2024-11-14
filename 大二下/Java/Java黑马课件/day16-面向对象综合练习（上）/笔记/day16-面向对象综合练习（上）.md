## 1. 设计游戏的目的

* 锻炼逻辑思维能力
* 利用Java的图形化界面，写一个项目，知道前面学习的知识点在实际开发中的应用场景

## 2. 游戏的最终效果呈现

Hello，各位同学大家好。今天，我们要写一个非常有意思的小游戏 ---《拼图小游戏》
我们先来看一下游戏的最终成品展示，然后再一步一步的从0开始，完成游戏里面每一个细节。
游戏运行之后，就是这样的界面。
                                         ![创建项目1](img\最终效果1.png) 

刚开始打开，是登录页面，因为是第一次运行，需要注册。点击注册就会跳转到注册页面

​                                       ![创建项目1](img\最终效果2.png)

在注册页面我们可以注册账号，用户名如果已存在则会注册失败。

​                                       ![创建项目1](img\最终效果3.png)

在游戏主界面中，我们可以利用上下左右移动小图片去玩游戏，还有快捷键A可以查看最终效果，W一键通关。

## 3. 实现思路

我们在写游戏的时候，也是一部分一部分完成的。

先写游戏主界面，实现步骤如下：

1，完成最外层窗体的搭建。

2，再把菜单添加到窗体当中。

3，把小图片添加到窗体当中。

4，打乱数字图片的顺序。

5，让数字图片可以移动起来。

6，通关之后的胜利判断。

7，添加其他额外的功能。

## 4. 三行代码实现主界面搭建

### 界面效果：

![初始界面](img\初始界面.png)

### 实现步骤：

1. 创建App类, 编写main方法

   作用: 程序的主入口

2. 书写以下代码

```java
//1.召唤主界面
JFrame jFrame = new JFrame();

//2.设置主界面的大小
jFrame.setSize(514,595);

//3.让主界面显示出来
jFrame.setVisible(true);
```

## 5. 主界面的其他设置

   ```java
//1.召唤主界面
JFrame jFrame = new JFrame();

//设置主界面的大小
jFrame.setSize(514,595);

//将主界面设置到屏幕的正中央
jFrame.setLocationRelativeTo(null);

//将主界面置顶
jFrame.setAlwaysOnTop(true);

//关闭主界面的时候让代码一起停止
jFrame.setDefaultCloseOperation(3);

//给主界面设置一个标题
jFrame.setTitle("拼图游戏单机版 v1.0");

//2.让主界面显示出来
jFrame.setVisible(true);
   ```

   注意事项：

​	jFrame.setVisible(true);必须要写在最后一行。

## 6. 利用继承简化代码

需求：

​	如果把所有的代码都写在main方法中，那么main方法里面的代码，就包含游戏主界面的代码，登录界面的代码，注册界面的代码，会变得非常臃肿后期维护也是一件非常难的事情，所以我们需要用继承改进，改进之后，代码就可以分类了。

```java
//登录界面
public class LoginJFrame extends JFrame {
    //LoginJFrame 表示登录界面
    //以后所有跟登录相关的代码，都写在这里


    public LoginJFrame(){
        //在创建登录界面的时候，同时给这个界面去设置一些信息
        //比如，宽高，直接展示出来
        this.setSize(488,430);
        //设置界面的标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //让显示显示出来，建议写在最后
        this.setVisible(true);
    }
}



//注册界面
public class RegisterJFrame extends JFrame {
    //跟注册相关的代码，都写在这个界面中
    public RegisterJFrame(){
        this.setSize(488,500);
        //设置界面的标题
        this.setTitle("拼图 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //让显示显示出来，建议写在最后
        this.setVisible(true);


        getContentPane();
    }
}

//游戏主界面
public class GameJFrame extends JFrame {

    public GameJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }
}
```

注意点：

​	其中this表示当前窗体的意思。

## 7. 菜单制作

菜单就是下图红色边框的内容。

![初始界面](img\菜单.png)

### 7.1菜单的组成

![初始界面](img\菜单2.png)
在菜单中有：JMenuBar、JMenu、JMenuItem三个角色。

JMenuBar：如上图中红色边框

JMenu：如上图蓝色边框

JMenuItem：如上图绿色字体处

其中JMenuBar是整体，一个界面中一般只有一个JMenuBar。

而JMenu是菜单中的选项，可以有多个。

JMenuItem是选项下面的条目，也可以有多个。

### 7.2代码书写步骤

1，创建JMenuBar对象

2，创建JMenu对象

3，创建JMenuItem对象

4，把JMenuItem添加到JMenu中

5，把JMenu添加到JMenuBar中

6，把整个JMenuBar设置到整个界面中

代码示例：

```java
//创建一个菜单对象
JMenuBar jMenuBar = new JMenuBar();
//设置菜单的宽高
jMenuBar.setSize(514, 20);
//创建一个选项
JMenu jMenu1 = new JMenu("功能");
//创建一个条目
jMenuItem1 = new JMenuItem("重新游戏");

//把条目添加到选项当中
jMenu1.add(jMenuItem1);
//把选项添加到菜单当中
jMenuBar.add(jMenu1);
//把菜单添加到最外层的窗体当中
this.setJMenuBar(jMenuBar);
```

## 8.添加图片

![](img\添加图片.png)

在上图中，其实是15张小图片。我们在添加图片的时候，要把添加图片的操作重复15次，才能把所有图片都添加到界面当中。

### 8.1使用到的Java类

​	ImageIcon：描述图片的类，可以关联计算中任意位置的图片。

​			    但是一般会把图片拷贝到当前项目中。

​	JLabel：用来管理图片，文字的类。

​		      可以用来设置位置，宽高。

### 8.2位置坐标

![](img\坐标.png)

界面左上角的点可以看做是坐标的原点，横向的是X轴，纵向的是Y轴。

图片的位置其实取决于图片左上角的点，在坐标中的位置。

如果是（0,0）那么该图片会显示再屏幕的左上角。

### 8.3添加步骤：

​	1，取消整个界面的默认居中布局

​	2，创建ImageIcon对象，并制定图片位置。

​	3，创建JLabel对象，并把ImageIcon对象放到小括号中。

​	4，利用JLabel对象设置大小，宽高。

​	5，将JLabel对象添加到整个界面当中。

代码示例：

```java
//1，先对整个界面进行设置
	//取消内部居中放置方式
	this.setLayout(null);
//2，创建ImageIcon对象，并制定图片位置。
	ImageIcon imageIcon1 = new ImageIcon("image\\1.png");
//3，创建JLabel对象，并把ImageIcon对象放到小括号中。
	JLabel jLabel1 = new JLabel(imageIcon1);
//4，利用JLabel对象设置大小，宽高。
	jLabel1.setBounds(0, 0, 100, 100);
//5，将JLabel对象添加到整个界面当中。
	this.add(jLabel1);
```

以此类推，只要能确定15张图片的位置，把上面的代码重复写15遍，就可以将所有图片都添加到界面中了。

### 8.4 打乱图片的位置

每一张图片都对应1~15之间的数字，空白处为0，打乱图片实际上就是把数字打乱，添加图片的时候按照打乱的图片添加即可

#### 8.4.1 打乱数组中数据的练习

​	int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

​	要求：打乱一维数组中的数据，并按照4个一组的方式添加到二维数组中。

代码示例：

```java
public class Test1 {
    public static void main(String[] args) {
        //需求：
        //把一个一维数组中的数据：0~15 打乱顺序
        //然后再按照4个一组的方式添加到二维数组当中

        //1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.打乱数组中的数据的顺序
        //遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取到随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //3.遍历数组
        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();

        //4.创建一个二维数组
        int[][] data = new int[4][4];

        //5.给二维数组添加数据
        //解法一：
        //遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }

        //遍历二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}

```

#### 8.4.2 打乱图片

```java
public class GameJFrame extends JFrame {
    //JFrame 界面，窗体
    //子类呢？也表示界面，窗体
    //规定：GameJFrame这个界面表示的就是游戏的主界面
    //以后跟游戏相关的所有逻辑都写在这个类中

    //创建一个二维数组
    //目的：用来管理数据
    //加载图片的时候，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据打乱之后的结果去加载图片）
        initImage();

        //让界面显示出来，建议写在最后
        this.setVisible(true);

    }

    //初始化数据（打乱）
    private void initData() {
        //1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.打乱数组中的数据的顺序
        //遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取到随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //4.给二维数组添加数据
        //遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }


    }

    //初始化图片
    //添加图片的时候，就需要按照二维数组中管理的数据添加图片
    private void initImage() {
        //外循环 --- 把内循环重复执行了4次。
        for (int i = 0; i < 4; i++) {
            //内循环 --- 表示在一行添加4张图片
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int num = data[i][j];
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon("C:\\Users\\moon\\IdeaProjects\\basic-code\\puzzlegame\\image\\animal\\animal3\\" + num + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j, 105 * i, 105, 105);
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }

        }


    }


    private void initJMenuBar() {
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象 （功能  关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        //将每一个选项下面的条目天极爱到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);

    }
}
```

## 9. 事件

​	事件是可以被组件识别的操作。

### 9.1 常见的三个核心要素

* 事件源： 按钮 图片 窗体... 
* 事件：某些操作
* 绑定监听：当事件源上发生了某个事件，则执行某段代码 

### 9.2 常见的三种事件监听

* 键盘监听 KeyListener
* 鼠标监听 MouseListener
* 动作监听 ActionListener

### 9.3 动作监听

包含：

* 鼠标左键点击
* 空格

#### 9.3.1 事件的三种实现方式

* 定义实现类实现接口
* 匿名内部类
* 本类实现接口

#### 方式一：实现类

​	定义实现类实现ActionListener接口

```java
public class Test3 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        //设置界面的宽高
        jFrame.setSize(603, 680);
        //设置界面的标题
        jFrame.setTitle("事件演示");
        //设置界面置顶
        jFrame.setAlwaysOnTop(true);
        //设置界面居中
        jFrame.setLocationRelativeTo(null);
        //设置关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        jFrame.setLayout(null);


        //创建一个按钮对象
        JButton jtb = new JButton("点我啊");
        //设置位置和宽高
        jtb.setBounds(0,0,100,50);
        //给按钮添加动作监听
        //jtb:组件对象，表示你要给哪个组件添加事件
        //addActionListener：表示我要给组件添加哪个事件监听（动作监听包含鼠标左键点击，空格）
        //参数：表示事件被触发之后要执行的代码
        jtb.addActionListener(new MyActionListener());


        //把按钮添加到界面当中
        jFrame.getContentPane().add(jtb);


        jFrame.setVisible(true);
    }
}


public class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击了");
    }
}
```

#### 方式二：匿名内部类

```java
public class Test3 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        //设置界面的宽高
        jFrame.setSize(603, 680);
        //设置界面的标题
        jFrame.setTitle("事件演示");
        //设置界面置顶
        jFrame.setAlwaysOnTop(true);
        //设置界面居中
        jFrame.setLocationRelativeTo(null);
        //设置关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        jFrame.setLayout(null);


        //创建一个按钮对象
        JButton jtb = new JButton("点我啊");
        //设置位置和宽高
        jtb.setBounds(0,0,100,50);
        //给按钮添加动作监听
        //jtb:组件对象，表示你要给哪个组件添加事件
        //addActionListener：表示我要给组件添加哪个事件监听（动作监听包含鼠标左键点击，空格）
        //参数：表示事件被触发之后要执行的代码

        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("达咩~不要点我哟~");
            }
        });


        //把按钮添加到界面当中
        jFrame.getContentPane().add(jtb);


        jFrame.setVisible(true);
    }
}
```

#### 方式三：本类实现接口

```java
public class MyJFrame extends JFrame implements ActionListener {

    //创建一个按钮对象
    JButton jtb1 = new JButton("点我啊");
    //创建一个按钮对象
    JButton jtb2 = new JButton("再点我啊");

    public MyJFrame(){
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);


        //给按钮设置位置和宽高
        jtb1.setBounds(0,0,100,50);
        //给按钮添加事件
        jtb1.addActionListener(this);


        //给按钮设置位置和宽高
        jtb2.setBounds(100,0,100,50);
        jtb2.addActionListener(this);


        //那按钮添加到整个界面当中
        this.getContentPane().add(jtb1);
        this.getContentPane().add(jtb2);

        //让整个界面显示出来
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //对当前的按钮进行判断

        //获取当前被操作的那个按钮对象
        Object source = e.getSource();

        if(source == jtb1){
            jtb1.setSize(200,200);
        }else if(source == jtb2){
            Random r = new Random();
            jtb2.setLocation(r.nextInt(500),r.nextInt(500));
        }
    }
}
```



​	











