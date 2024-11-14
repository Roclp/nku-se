# globals() ：以dict的方式存储所有全局变量
def foo():
    print("I am a func")


def bar():
    foo = "I am a string"
    foo_dup = globals().get("foo")
    foo_dup()


bar()

# locals()：以dict的方式存储所有局部变量
other = "test"


def foobar():
    name = "MING"
    gender = "male"
    for key, value in locals().items():
        print(key, "=", value)


foobar()
