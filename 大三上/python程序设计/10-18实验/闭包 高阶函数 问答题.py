# 飞书文档 https://nankai.feishu.cn/docx/Kq9PdyX7RoaSazxSyhHcMeJOnBds

def f(x):
    return x * x

if __name__ == "__main__":
    '''
    <class 'map'>  map函数返回值是map类型 
    <class 'map'>  map函数返回值是map类型
    [1, 4, 9, 16, 25, 36, 49, 64, 81]  map转为list  每个列表元素平方
    ['1', '2', '3', '4', '5', '6', '7', '8', '9']  map转为list 每个列表元素转为字符串
    '''
    r = map(f, [1, 2, 3, 4, 5, 6, 7, 8, 9])
    s = map(str, [1, 2, 3, 4, 5, 6, 7, 8, 9])
    print(type(r))
    print(type(s))
    print(list(r))
    print(list(s))

from functools import reduce

def add(x, y):
    return x + y
if __name__ == "__main__":
    ''''
    class 'int'> reduce函数返回值和累积操作的结果类型相同
    25
    '''
    result = reduce(add, [1, 3, 5, 7, 9])
    print(type(result))
    print(result)


from functools import reduce

def fn(x, y):
    return x * 10 + y

def charToInt(s):
    return {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}[s]

if __name__ == "__main__":
    '''
    13579 字符转为数字，1*10+3*10+5*10+7*10+9=13579
    '''
    result = reduce(fn, map(charToInt, '13579'))
    print(result)



def is_odd(n):
    return n % 2 == 1

def not_empty(s):
    return s and s.strip()

if __name__ == "__main__":
    '''
    <class 'filter'> filter函数返回值类型为filter
    [1, 5, 9, 15] filter转为list 奇数1 5 9 7
    ['A', 'B', 'c']  非空字符串 
    '''
    result = filter(is_odd, [1, 2, 4, 5, 6, 9, 10, 15])
    print(type(result))
    print(list(result))
    print(list(filter(not_empty, ['A', 'B', None, 'c'])))


def lazy_sum(*args):
    def sum():
        ax = 0
        for n in args:
            ax = ax + n
        return ax
    return sum

if __name__ == "__main__":
    '''
    <class 'function'> 返回sum函数
    25 求和
    '''
    f = lazy_sum(1, 3, 5, 7, 9)
    print(type(f))
    print(f())

def square(num):
    return num ** 2

items = [1, 2, 3, 4]
squared = list(map(square, items))
print(squared)

# lambda 匿名函数表达式改写：
items = [1, 2, 3, 4]
squared = list(map(lambda num: num ** 2, items))
print(squared)

# <class 'filter'> filter函数返回值类型为filter
m = [1, 2, 3, 4]
result = filter(lambda x: x % 2 == 1, m)
print(type(result))
