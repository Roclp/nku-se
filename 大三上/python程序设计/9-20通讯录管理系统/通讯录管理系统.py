import re
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


if __name__ == '__main__':
    address=[['test','111111111','13638750465','11111111@qq.com']]
    while True:
        num = input("请输入功能对应的代号：")
        if num=='a':
            name = input("请输入姓名：")
            qq = input("请输入QQ：")
            tel = input("请输入电话：")
            email = input("请输入邮箱：")
            insert(address,name,qq,tel,email)
        elif num=='d':
            no=input("请输入要删除的记录序号：")
            delete(address,no)
        elif num=='c':
            no=input("请输入待修改的记录序号：")
            modify(address,no)
        elif num=='f':
            no = input("请输入要查找的记录序号：")
            find(address,no)
        elif num=='s':
            show(address)
        elif num=='q':
            print("已退出，通讯录结果如下：")
            show(address)
            break
        else:
            print("暂无此功能，请重新输入")
            continue
