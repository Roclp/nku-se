# 飞书文档 https://nankai.feishu.cn/docx/Zo8rdd9cno51mOxzXIXcJbVrnIh

def get_time(func):
    def wrapper():
        func()
        print("used time: xxx")
    return wrapper

@get_time
def foo():
    print("foo")

foo()


def get_time(func):
    def wrapper():
        func()
        print("used time: xxx")
    return wrapper

def foo():
    print("foo")

foo = get_time(foo)
foo()

def f_a(func):
    print("f_a")
    def w_a():
        print(1)
        func()
    return w_a

def f_b(func):
    print("f_b")
    def w_b():
        print(2)
        func()
    return w_b

@f_a
@f_b
def f_c():
    print("f_c")
    print(3)
'''
（装饰器嵌套）
去掉语法糖@后相当于：
f_c=f_a(f_b(f_c))
f_c()
f_c先被f_b装饰，再被f_a装饰
'''
f_c()



def decorator(func):
    def wrapper(*args):
        print("call1 function")
        return func(*args)
    return wrapper

@decorator
def foo(*args):
    print("this is foo")
    print(args)
'''
（被装饰函数接收参数）
'''
foo(1, 2, 3)

def func_out(info):
    def decorator(func):
        def wrapper():
            print(info)
            print("call1 function")
            return func()
        return wrapper
    return decorator

@func_out(info="hello!")
def foo():
    print("this is foo")

foo()
