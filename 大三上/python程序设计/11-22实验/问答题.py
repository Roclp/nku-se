# 1. 设计一个类Point表示点的坐标，有x和y两个属性表示横纵坐标。
# 要求通过重载运算符实现两个Point对象可以通过'+'相加
# 要求通过重载运算符实现两个Point对象可以通过'-'相减
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __add__(self,p):
        return Point(self.x+p.x,self.y+p.y)

    def __sub__(self,p):
        return Point(self.x-p.x,self.y-p.y)

    def __str__(self):
        return f"({self.x},{self.y})"
p1=Point(1,2)
p2=Point(3,4)
print(p1+p2)
print(p1-p2)


# 2. 写出以下代码运行结果并说明理由
class Parent:
    x = 1
class Child1(Parent):
    pass

class Child2(Parent):
    pass
print(Parent.x, Child1.x, Child2.x)
Child1.x = 2
print(Parent.x, Child1.x, Child2.x)
Child1.x = 3
print(Parent.x, Child1.x, Child2.x)

'''

1 1 1
1 2 1
1 3 1
初始时，三个类的 x 属性都继承自父类 因此输出为1 1 1
修改Child1类的 x 属性为 2，所以输出为1 1 1
修改Child1类的 x 属性为 3，所以输出为1 1 1
没有修改Child2的 x 属性，所有Child2.x等于Parent.x

3. 简述描述器__get__, __set__, __delete__会在何时调用。
获取、设置、删除
- get(self, instance, owner)：
  获取描述器属性时（例如，instance.attribute），调用描述器的 __get__ 方法。
  参数：
    self：描述器实例本身。
    instance：调用描述器的实例。
    owner：拥有描述器的类。
- set(self, instance, value)：
  设置描述器属性时（例如，instance.attribute = value），调用描述器的 __set__ 方法。
  参数：
    self：描述器实例本身。
    instance：调用描述器的实例。
    value：要设置的新值。
- delete(self, instance)：
  删除描述器属性时（例如，del instance.attribute），调用描述器的 __delete__ 方法。
  参数：
    self：描述器实例本身。
    instance：调用描述器的实例。
    '''


#4. 补全代码
# 先定义一个温度类，然后定义两个描述符类用于描述摄氏度和华氏度两个属性。可以给摄氏度这个属性赋值，然后打印的华氏度属性是自动转换后的结果。
# 摄氏度类
class Celsius:
    def __init__(self, value=26.0):
        self.value = float(value)

    def __get__(self, instance, owner):
        return self.value

    def __set__(self, instance, value):
        self.value = float(value)

# 华氏度类
class Fahrenheit:
    def __get__(self, instance, owner):
        return instance.cel * 9/5 + 32

    def __set__(self, instance, value):
        instance.cel = (float(value) - 32) * 5/9

# 描述符类
class Temperature:
    cel = Celsius()
    f = Fahrenheit()

temp = Temperature()  # temp为描述符类实例
print(temp.cel)  # 可以查看摄氏度的值
print(temp.f)  # 并且可以直接计算出华氏度

temp.cel = 37.8  # 设置摄氏度的值
print(temp.f)  # 依据该值可计算出华氏度


# 5. 看代码写出其执行结果并说明理由
class Des(object):
    def __init__(self, init_value):
        self.value = init_value

    def __get__(self, instance, typ):
        print('call __get__', instance, typ)
        return self.value

    def __set__(self, instance, value):
        print('call __set__', instance, value)
        self.value = value

    def __delete__(self, instance):
        print('call __delete__', instance)


class Widget(object):
    t = Des(1)


def main():
    w = Widget()
    print(type(w.t))
    w.t = 2
    print(w.t, Widget.t)
    del w.t


if __name__ == '__main__':
    main()
