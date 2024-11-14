# 1. 请实现一个装饰器，通过一次调用使函数重复执行5次。
def decorator1(fn):
    def wrapper():
        for i in range(5):
            fn()
    return wrapper

@ decorator1
def foo():
    print("this is foo")
foo()

print('——'*20)
# 在第一题的基础上，改写为给装饰器传递参数的形式来指定重复次数以及输出信息
def decorator2(num):
    def wrapper(fn):
        def inner(info):
            for i in range(num):
                fn(info)
        return inner
    return wrapper
@ decorator2(3)
def foo(info):
    print(f"this is {info}")
foo("2113850")

'''
3. 在通讯录管理系统代码的基础上，进行如下扩展：
如果你丢了这次实验的代码可以来找助教要之前的提交作业
  1. 通过编写装饰器，为每个功能模块函数的每次调用设置认证。要求：
    - 每次调用函数的时候需要验证用户是否登录。如果未登录要求用户进行登录，并提示用户输入用户名和密码（与文件中的信息对比），成功后调用函数；如果已经登录则直接调用函数。
    - 当用户名密码信息输入错误时需要提供反馈并要求再次输入。
    - 初始用户信息如下，需要读入：
暂时无法在南开飞书文档外展示此内容

  2. 在上述的基础上，编写装饰器，为每个函数添加记录调用的功能
    - 每次调用函数的时候，都将调用的函数名和调用时间记录在文本文件当中。
'''
print('——'*20)

import re
# 读取user_info.txt
def read_user_info(file_path):
    user_info = {}
    with open(file_path, 'r') as f:
        for line in f.readlines():
            line = line.strip()
            if line:
                name, password = line.split()
                user_info[name] = password
    return user_info

# 认证装饰器
def authentication(func):
    def wrapper():
        # 检查用户是否已登录
        if not wrapper.logged_in:
            print("请登录")
            while True:
                username = input("用户名: ")
                password = input("密码: ")
                if username in users and users[username] == password:
                    wrapper.logged_in = True
                    break
                else:
                    print("用户名或密码错误，请重新输入")
        return func()
    wrapper.logged_in = False
    return wrapper

# 记录调用装饰器
def log_calls(func):
    def wrapper():
        with open(log_file, "a") as f:
            f.write(f"{datetime.datetime.now()} 调用了 {func.__name__} \n")
        return func()
    return wrapper
def insert(address,name,qq,tel,email):
    pattern = r'^1[3-9]\d{9}$'
    if re.match(pattern, tel)==None:
        print("手机号异常")
        return
    pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
    if re.match(pattern, email)==None:
        print("邮箱异常")
        return
    list=[]
    list.extend([name,qq,tel,email])
    address.append(list)
    show(address)
def delete(address,no):
    del address[int(no)]
    show(address)
def modify(address,no):
    length=len(address)
    index=int(no)
    if 0<=index and index<length:
        print("请输入要修改的子项：\n"
              "      n：修改姓名\n"
              "      q：修改QQ\n"
              "      p：修改电话\n"
              "      m：修改邮箱")
        item=input()
        if item=='n':
            name=input("请输入新的姓名，若不修改输入空格：")
            if name==' ':
                print("不修改")
                return
            else:
                address[index][0]=name
        elif item=='q':
            qq=input("请输入新的QQ，若不修改请输入空格：")
            if qq == ' ':
                print("不修改")
                return
            else:
                address[index][1] = qq
        elif item=='p':
            tel=input("请输入新的电话，若不修改请输入空格：")
            if tel == ' ':
                print("不修改")
                return
            else:
                address[index][2] = tel
        elif item=='m':
            email=input("请输入新的邮箱，若不修改请输入空格：")
            if email == ' ':
                print("不修改")
                return
            else:
                address[index][3] = email
        elif item==' ':
            print("不修改")
            return
        print("已修改，最新的列表为：")
        show(address)
    else:
        print("您想要修改的序号不存在")
def find(address,no):
    length = len(address)
    index = int(no)
    if 0 <= index and index < length:
        i=address[index]
        info = ['No.', 'Name', 'QQ', 'Phone', 'E-mail']
        print("=" * 64)
        print("{:<5}{:<15}{:<15}{:<15}{:<15}".format(*info))
        print("{:<5}{:<15}{:<15}{:<15}{:<15}".format(index, i[0], i[1], i[2], i[3]))
        print("=" * 64)
    else:
        print("您想要查找的序号不存在")

def show(address):
    print("=" * 64)
    info = ['No.', 'Name', 'QQ', 'Phone', 'E-mail']
    print("{:<5}{:<15}{:<15}{:<15}{:<15}".format(*info))
    for index, i in enumerate(address, start=0):
        print("{:<5}{:<15}{:<15}{:<15}{:<15}".format(index, i[0], i[1], i[2], i[3]))
    print("=" * 64)


'''
附加题
编写下载网页内容的函数，要求：
- 用户传入一个url，函数返回下载页面的结果（存储在html格式文件中）
- 编写装饰器，实现缓存网页内容功能
  当传入url时，如果缓存文件内有值（文件大小不为0），就优先从缓存文件中读取网页的内容。否则就去下载页面，之后缓存在文件中。

例如：
用户输入百度网址，程序发现没有下载过，于是将百度网页进行下载并保存为缓存文件“baidu.html”，并打开了这个文件。当用户再次输入百度的网址时，程序发现本地已经有“baidu.html”，则不需要再次从服务器对网页进行下载了，只需要打开这个本地文件即可。
'''
import os
import requests
import subprocess
def cache_decorator(func):
    def wrapper(url):
        filename = url.split('//')[-1].replace('/', '_') + '.html'
        if os.path.exists(filename) and os.path.getsize(filename) > 0:
            with open(filename, 'r', encoding='utf-8') as f:
                content = f.read()
            print(content)
        else:
            func(url)
        return content
    return wrapper


@cache_decorator
def download_page(url):
    response = requests.get(url)
    filename = url.split('//')[-1].replace('/', '_') + '.html'
    with open(filename, 'w', encoding='utf-8') as f:
        f.write(response.text)
    with open(filename, 'r', encoding='utf-8') as f:
        content = f.read()
    print(content)
    return content


while True:
    url = input('请输入url：')
    if url == 'exit':
        break
    download_page(url)

