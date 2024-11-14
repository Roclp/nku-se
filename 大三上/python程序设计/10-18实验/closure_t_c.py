# 1 闭包的定义：包含两层含义（嵌套函数， 内部函数引用外部函数的变量），严谨的概念除了前面讲到的内涵外，还包括外部函数返回内部函数名
#   当某个函数被当成对象返回时，夹带了外部变量，就形成了一个闭包
#   在一些语言中，在函数中可以（嵌套）定义另一个函数时，如果内部的函数引用了外部的函数的变量，则可能产生闭包。闭包可以用来在一个函数与一组“私有”变量之间创建关联关系。在给定函数被多次调用的过程中，这些私有变量能够保持其持久性。—— 维基百科
"""
A function along with the environment of its enclosing function, are collectively referred to as a closure.
闭包（英语：Closure），又称词法闭包（Lexical Closure）或函数闭包（function closures），是引用了自由变量的函数。这个被引用的自由变量将和这个函数一同存在，即使已经离开了创造它的环境也不例外。所以，有另一种说法认为闭包是由函数和与其相关的引用环境组合而成的实体。闭包在运行时可以有多个实例，不同的引用环境和相同的函数组合可以产生不同的实例。
在Python中创建一个闭包可以归结为以下三点：
1)闭包函数必须有内嵌函数
2)内嵌函数需要引用该嵌套函数上一级中的变量
3)闭包函数必须返回内嵌函数（非必须）
"""
# 2 闭包形式：


def func():
    i = 'I am a closure function'

    def wrapper():
        print(i)

    return wrapper


f = func()
f()


# 3 闭包的作用： 函数调用完毕，内部的变量等内容都在内存中销毁，当需要保留这些内容时，闭包能实现这一功能
# 4 闭包案例
# 4.1 闭包案例一： 打印分割线案例
#  定义的line函数调用参数太多，不方便在多处调用


def line(symbol, title, numbers):
    half_part_symbols = (numbers // 2) * symbol
    print(half_part_symbols + title + half_part_symbols)


line('-', '华丽丽的分割线', 20)
line('*', '我是星星分割线', 40)


# 利用闭包，方便了同一类型line在多处调用的场景
def line_config(symbol, title, numbers):
    def wrapper():
        half_part_symbols = (numbers // 2) * symbol
        print(half_part_symbols + title + half_part_symbols)

    return wrapper


dashed_line = line_config('-', '华丽丽的分割线', 20)
star_line = line_config('*', '天上的星星不说话', 40)
dashed_line()
dashed_line()
dashed_line()
star_line()
star_line()
star_line()


# 4.2 闭包案例二： 连续走步，统计走完之后总共走了多少步


def count_steps(original_steps=0):
    def wrapper(new_steps):
        nonlocal original_steps  # 添加nonlocal是为了改变变量original_steps的值
        total_steps = original_steps + new_steps
        original_steps = total_steps
        return original_steps

    return wrapper


go = count_steps(8)
print(go(2))
print(go(3))
print(go(5))
print(go(5))


# 5 闭包注意事项：
# 5.1
# 案例代码见4.2的wrapper内部函数，original_steps调用不影响，但是在内部函数中需要修改外部变量，必须加上nonlocal关键字
# 5.2 引用闭包中的变量时，必须时刻注意变量是否已经发生变化


# 5.2.1 简单的i值的变化
def func_2():
    i = 'I am a closure function'

    def wrapper():
        print(i, 'in func_2')

    i = 888
    return wrapper


func_2()()


# fun = func_2()
# fun()

# 5.2.2 闭包引用变量案例二：i值的变化


def func_3():
    list_funcs = []
    for i in range(1, 5):
        def wrapper():
            print(i)

        list_funcs.append(wrapper)
    return list_funcs


func_3()[0]()


# 修改后的情况
def func_4():
    list_funcs = []
    for i in range(1, 5):
        def wrapper(i):
            def inner():
                print(i)

            return inner

        list_funcs.append(wrapper(i))
    return list_funcs


func_4()[0]()
func_4()[1]()
func_4()[2]()
func_4()[3]()

# 6 闭包函数的__closure__里面，定义了一个元组用于存放所有的cell对象，每个cell对象保存了这个闭包中所有的外部变量。
# 6.1 如果嵌套函数wrapper引用的是j，则不会产生闭包
j = 'Hi'


def func_demo():
    i = 'Hello'

    def wrapper():
        print(i)

    return wrapper


func_wrapper = func_demo()
func_wrapper()
print("closure function's variables: ",
      func_wrapper.__closure__[0].cell_contents)
for x in func_wrapper.__closure__:
    print(x.cell_contents)

# 6.2 检测不返回嵌套函数名wrapper，是否构成闭包
star_line()


def func_demo_2():
    i = 'Hello'
    print(func_demo.__code__.co_cellvars)  # 自由变量的名字

    def demo():
        def wrapper():
            print(i)
            # 一个函数和它的环境变量（又叫自由变量）组合在一起，就构成了一个闭包(closure)。环境变量取值被保存在函数对象的__closure__属性中,且cell_contents是cell对象的唯一属性
            print(wrapper.__closure__[0].cell_contents)  # 自由变量的值
            print(wrapper.__code__.co_freevars)  # 自由变量的名字
        wrapper()
    # 不返回函数名，也能构成闭包
    # return wrapper
    demo()


func_demo_2()
print('999', func_demo_2.__closure__)
print(dir(func_demo_2))


def demo():
    pass


star_line()
print(demo.__closure__)  # 普通函数也有__closure__
print(dir(demo))
demo()
print(demo)



def read_user_info(file_path):
    user_info = {}
    with open(file_path, 'r') as f:
        for line in f.readlines():
            name, password = line.strip().split()
            user_info[name] = password
    return user_info
print(read_user_info('user_info.txt'))
