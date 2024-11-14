# 异常
# 以下代码会出现列表越界的异常，用except语句实现如果抛出异常就输出 index out of bound！
# list_1=[1,2,3,4]
# print(list_1[20])

try :
    list_1=[1,2,3,4]
    print(list_1[20])
except IndexError:
    print("index out of bound！")


# 看代码写出输出结果
def fun():
    try:
        print('try--start')
        a= 1/0
    except ValueError as ret:
        print(ret)
    finally:
        return "finally"
print(fun())



# 看代码写出输出结果
def fun():
    try:
        return 123
    finally:
        return 321
print(fun())

# 看代码写出输出结果
def fun():
    a=1
    try:
        a=a+10
    except:
        a=a+10
    else:
        a=a+10
    finally:
        a=a+10
    return a
print(fun())


# 看代码写出输出结果，并分析其执行逻辑
def fun():
    try:
        return 123
    except:
        return 111
    else:
        print(222)
    finally:
        print(321)
print(fun())

# try中执行return 123 后函数，执行finally中的print，输出321，最后执行输出函数的返回值123
# 由于没有发生异常，所以except不执行
# 由于try中执行了return，所以else不执行