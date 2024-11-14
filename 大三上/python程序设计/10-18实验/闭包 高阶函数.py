'''理解python文件(globals().py  closure_t_c.py  )中的代码与原理：
- 理解闭包的原理和概念；
- 理解闭包的案例；
- 明白闭包中调用变量值发生变化的原因；
- 理解global，nonlocal的含义和用法'''


from functools import reduce

def is_prime(x):
    for i in range(2, x):
        if x % i == 0:
            return False
    return True
def add(x, y):
    return x + y



# 使用filter方法求出100以内的素数
f = filter(is_prime, range(2, 100))
f_list=list(f)
print(f_list)

# 使用reduce方法求出1-100的和
r = reduce(add,f_list)
print(r)


# map   规范姓名
lst=['lisa','JACK','Adam']
# capitalize()函数 首字母大写
m=map(lambda x:x.capitalize(),lst)
m_list=list(m)
print(m_list)

# sorted  按照排名对list进行排序
lst=[(1,'byd'),(3,'xiaopeng'),(2,'tesla'),(4,'weilai')]
s=sorted(lst,key=lambda x:x[0])
print(s)

# 使用闭包实现步数记录
# 闭包函数定义
def count_steps(original_step=0):
    totol_steps=original_step
    def wrapper(new_steps):
        '''
        print(new_steps)
        print(original_step)
        print(original_step+new_steps)
        '''
        nonlocal totol_steps
        totol_steps=new_steps+totol_steps
        print(f"total steps:{totol_steps}")
        return totol_steps

    return wrapper


# 执行语句
count_steps = count_steps(10)
print(count_steps(5))
print(count_steps(5))
print(count_steps(8))


