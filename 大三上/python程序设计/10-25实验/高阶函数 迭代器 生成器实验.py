'''
使用生成器编写代码，代码实现功能：不断生成杨辉三角的每一行。
'''
def triangles():
    L = [1]
    while len(L) < 10:
        yield L
        L = [1] + [L[i] + L[i+1] for i in range(len(L)-1)] + [1]
g=triangles()
print(g)
for i in g:
    print(i)




from collections.abc import Iterable, Iterator
def g():
    yield 1
    yield 2
    yield 3
print('Iterable? [1, 2, 3]:', isinstance([1, 2, 3], Iterable))
print('Iterable? \'abc\':', isinstance('abc', Iterable))
print('Iterable? 123:', isinstance(123, Iterable))
print('Iterable? g():', isinstance(g(), Iterable))

print('Iterator? [1, 2, 3]:', isinstance([1, 2, 3], Iterator))
print('Iterator? iter([1, 2, 3]):', isinstance(iter([1, 2, 3]), Iterator))
print('Iterator? \'abc\':', isinstance('abc', Iterator))
print('Iterator? 123:', isinstance(123, Iterator))
print('Iterator? g():', isinstance(g(), Iterator))


l = [1,2,3,4]
result = filter(lambda x: x % 2 == 1,l)
print(type(result))
print(list(result))
print(list(result))