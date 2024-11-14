import math
def isPrimes1(n):
    if n <= 1:
        return False
    for i in range(2, int(math.sqrt(n) + 1)):
        if n % i == 0:
            return False
    return True
num=int(input("请输入一个正整数："))
if isPrimes1(num):
    print(f"{num}是素数")
else:
    print(f"{num}不是素数")