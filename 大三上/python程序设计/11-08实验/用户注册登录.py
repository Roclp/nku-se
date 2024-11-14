class User:
    def __init__(self, name, pwd):
        self.name = name
        self.pwd = pwd

class Account:
    def __init__(self):
        self.user_list = []  # 用户列表，数据格式：[User 对象, User 对象, User 对象]

    def login(self):
        """
        用户登录，用户输入用户名和密码并去 self.user_list 中检查用户是否合法
        """
        count = 3
        while count>0:
            name=input("请输入用户名：")
            pwd=input("请输入密码：")

            for i in self.user_list:
                if i.name==name and i.pwd==pwd:
                    print("登录成功")
                    return True
            else:
                count=count-1
                print(f"用户名或密码错误，您还有{count}次机会")


    def register(self):
        """
        用户注册，动态创建 User 对象，并添加到 self.user_list 中
        """
        name=input("请输入用户名：")
        pwd=input("请输入密码：")
        user=User(name,pwd)
        self.user_list.append(user)

    def run(self):
        """
        主程序，先进行 2 次用户注册注册两个不同的用户，再进行用户登录（3 次重试机会）
        """
        self.register()
        self.register()
        self.login()


if __name__ == "__main__":
    obj = Account()
    obj.run()