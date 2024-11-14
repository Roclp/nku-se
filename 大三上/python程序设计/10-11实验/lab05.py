def q1(lst):
    n=len(lst)
    for i in range(n):
        flag = True
        for j in range(i):
            if lst[j]>lst[i]:
                flag=False
        for j in range(i+1,n):
            if lst[j]<lst[i]:
                flag=False
        if flag==True:
            print(i)
            return
    print(-1)
    return
l1=[6, 3, 4, 9, 1]
l2=[4,3,6,9,7]
q1(l1)
q1(l2)

def q2(lst):
    '''
    方案一：使用集合对列表元素去重
    l=list(set(lst))
    return l
    '''
    # 方案二：将无重复的元素添加到新列表中
    l = []
    for item in lst:
        if item not in l:
            l.append(item)
    return l
l1=[6, 3, 4, 9, 1,1,1,1,1]
l2=[4,3,6,9,7,4,3,6,9,7,4,3,6,9,7]
print(q2(l1))
print(q2(l2))


def q3():
    # 元组基本操作
    # 创建一个元组
    my_tuple = (1, 2, 3, 4, 5)
    # 索引操作
    print(my_tuple[2])  # 输出: 3
    # 长度计算
    print(len(my_tuple))  # 输出: 5
    # 切片操作
    print(my_tuple[:3])  # 输出: (1, 2, 3)
    # 添加元素 - 合并元组
    another_tuple = (6, 7, 8)
    merged_tuple = my_tuple + another_tuple
    print(merged_tuple)  # 输出: (1, 2, 3, 4, 5, 6, 7, 8)
    # 元组连接操作
    tuple1 = (1, 2, 3)
    tuple2 = (4, 5, 6)
    merged_tuple = tuple1 + tuple2
    print(merged_tuple)  # 输出: (1, 2, 3, 4, 5, 6)

    # 列表和元组连接
    my_list = [1, 2, 3]
    my_tuple = (4, 5, 6)

    # 将列表和元组进行连接
    merged_list = my_list + list(my_tuple)
    print(merged_list)  # 输出: [1, 2, 3, 4, 5, 6]
q3()

def q4():
    '''
    {,}表示集合set,自动去重，32和’32‘类型不同，是不同的元素
    '''
    s1 = {32, 5, 'c', '32', '11'}
    print(s1)  # 输出: {32, 5, 'c', '11', '32'}
    '''
    {,}表示集合，set()把集合中的元素放到集合中，集合的集合
    '''
    s2 = set({32, '46', 32, 'aa'})
    print(s2)  # 输出: {32, '46', 'aa'}
    '''
    "4,32,46,11,32"是字符串，set()把字符串中的元素（逐个字符）放到集合中，并去重
    '''
    s3 = set('4,32,46,11,32')
    print(s3)  # 输出: {'4', ',', '3', '2', '6', '1'}
    '''
    将列表[1, 2, 3]转换为集合
    '''
    s4 = set([1, 2, 3])
    print(s4)  # 输出: {1, 2, 3}
    '''
    将元组(1, 2, 3)转换为集合
    '''
    s5 = set((1, 2, 3))
    print(s5)  # 输出: {1, 2, 3}
    ''' 
    字典的集合，但集合只会包含字典的键
    '''
    s6 = set({'a': 1, 'b': 2, 'c': 3})
    print(s6)  # 输出: {'a', 'b', 'c'}
q4()

def q5():
    lst=[11,22,33,44,55,66,77,88,99,100,110,200,230,330]
    print(f"原始列表：{lst}")
    dic={'k1':[],'k2':[]}
    for i in lst:
        if i >66:
            dic['k1'].append(i)
        else:
            dic['k2'].append(i)
    print(f"转化后的字典：{dic}")
q5()

def q6(l1,l2):
    dic={}
    for i in range(len(l1)):
        dic    [l1[i]]=l2[i]
    print(f"l1为：{l1}")
    print(f"l2为：{l2}")
    print(f"l1为键，l2为值的字典：{dic}")
q6(l1,l2)



import re

def count_words(filename, top_n):
    # 打开文件，读取文件内容
    with open(filename, 'r') as file:
        content = file.read()

    # 将文件内容转换为小写形式，剔除特殊符号
    content = content.lower()
    content = re.sub(r'[^\w\s]', '', content)

    # 将文本内容按空格进行分割得到单词列表
    words = content.split()

    # 统计单词频次
    word_count = {}
    for word in words:
        if word not in word_count:
            word_count[word] = 0
        word_count[word] += 1

    # 获取出现次数最多的top_n个单词及其次数，按次数降序排列
    top_words = sorted(word_count.items(), key=lambda x: x[1], reverse=True)[:top_n]

    return top_words

def q7():
    filename = 'hamlet.txt'
    top_n = 10
    result = count_words(filename, top_n)
    for word, count in result:
        print(word, count)


def q8(l1, l2):
    index = 0
    for i in l2:
        if i == l1[index]:
            index += 1
            if index == len(l1):
                break

    if index == len(l1):
        print(f"TRUE,{l1}是{l2}的子序列")
        return True
    else:
        print(f"FALSE,{l1}不是{l2}的子序列")
        return False

q8("a", "b")
q8("aa","ab")
q8("aa","aa")
q8("aa","aab")



print({32, '46', 32, 'aa'})


if __name__ == '__main__':
    while(True):
        num=input("请输入题号1-8：")
        if num in r'12345678':
            print(f"question{num}:")
        else:
            break
        if num=='1':
            l1 = [6, 3, 4, 9, 1]
            l2 = [4, 3, 6, 9, 7]
            q1(l1)
            q1(l2)
        elif num=='2':
            l1 = [6, 3, 4, 9, 1, 1, 1, 1, 1]
            l2 = [4, 3, 6, 9, 7, 4, 3, 6, 9, 7, 4, 3, 6, 9, 7]
            print(q2(l1))
            print(q2(l2))
        elif num=='3':
            q3()
        elif num=='4':
            q4()
        elif num=='5':
            q5()
        elif num=='6':
            l1 = [6, 3, 4, 9, 1, 1, 1, 1, 1]
            l2 = [4, 3, 6, 9, 7, 4, 3, 6, 9, 7, 4, 3, 6, 9, 7]
            q6(l1, l2)
        elif num=='7':
            q7()
        elif num=='8':
            q8("a", "b")
            q8("aa", "ab")
            q8("aa", "aa")
            q8("aa", "aab")

