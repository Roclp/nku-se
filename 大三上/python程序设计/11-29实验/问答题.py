# 5
class Base1:
    def f1(self):
        print("base1.f1")

    def f2(self):
        print('base1.f2')

    def f3(self):
        print('base1.f3')


class Base2:
    def f1(self):
        print("base2.f1")


class Foo(Base1, Base2):
    def f0(self):
        print('foo.f0')
        self.f3()


obj = Foo()
obj.f0()

# 6

class Base:
    def f1(self):
        print('base.f1')

    def f3(self):
        self.f1()
        print('base.f3')


class Foo(Base):
    def f1(self):
        print('foo.f1')

    def f2(self):
        print('foo.f2')
        self.f3()


obj2 = Foo()
obj2.f2()


# 7
class Base:
    def __init__(self):
        print('执行Base.__init__')
        self.func()

    def func(self):
        print('Base.func')


class Foo:
    def __init__(self):
        print('执行Foo.__init__')
        self.func()

        def func(self):
            print('Foo.func')


# f = Foo()


# 8
class A(object):
    @classmethod
    def f1(cls):
        print(cls)

    def f2(self):
        self.f1()
        A.f1()


obj = A()
obj.f2()


# 9
class A(object):
    a = 11

    def __init__(self, num):
        self.b = num


obj = A(123)
print(obj.a)
print(obj.b)
print(A.a)
# print(A.b)

# 10
class A(object):
    a1 = 1

    def __init__(self, num):
        self.b1 = num


obj1 = A(123)
obj2 = A(555)

print(obj1.b1)
print(obj1.a1)

obj1.b1 = 13
obj1.a1 = 22

print(obj1.b1)
print(obj1.a1)

print(obj2.a1)
print(obj2.b1+A.a1)
print(obj2.b1+obj1.a1)

# 11
class A(object):
    @classmethod
    def f(cls):
        print(cls)

A.f()
obj = A()
obj.f()

# 12
class StarkConfig(object):
    def __init__(self, num):
        self.num = num

    def changelist(self, request):
        print(self.num, request)

    def run(self):
        self.changelist(999)


class RoleConfig(StarkConfig):
    def changelist(self, request):
        print(777, self.num)


config_obj_list = [StarkConfig(1), StarkConfig(2), StarkConfig(3)]
config_obj_list[1].run()
config_obj_list[2].run()
