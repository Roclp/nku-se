def hanoi(n, a, b, c):
    if n == 1:
        print(f"{a}——>{c}")
    else:
        hanoi(n - 1, a, c, b)
        print(f"{a}——>{c}")
        hanoi(n - 1, b, a, c)
n = int(input("请输入圆盘数量："))
hanoi(n, 'A', 'B', 'C')
