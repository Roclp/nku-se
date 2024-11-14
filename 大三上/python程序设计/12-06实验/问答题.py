# 单例模式的函数装饰器实现
def singleton(cls, *args, **kw):
    instance = {}

    def _singleton():
        if cls not in instance:
            instance[cls] = cls(*args, **kw)
        return instance[cls]

    return _singleton


@singleton
class test_singleton(object):
    def __init__(self):
        self.num_sum = 0

    def add(self):
        self.num_sum = 100

single1 = test_singleton()
single2 = test_singleton()
print(id(single1) == id(single2))


# 单例模式的类装饰器实现
class Singleton(object):
    def __init__(self, cls):
        self._cls = cls
        self._instance = {}

    def __call__(self, *args, **kwargs):
        if self._cls not in self._instance:
            self._instance[self._cls] = self._cls(*args, **kwargs)
        return self._instance[self._cls]

@Singleton
class test_singleton(object):
    def __init__(self):
        self.num_sum = 0

    def add(self):
        self.num_sum = 100

single1 = test_singleton()
single2 = test_singleton()
print(id(single1) == id(single2))



# 静态方法的描述器实现
class StaticMethod:
    """Emulate PyStaticMethod_Type() in Objects/funcobject.c"""
    def __init__(self, f):
        self.f = f

    def __get__(self, obj, objtype=None):
        return self.f

    def __call__(self, *args, **kwds):
        return self.f(*args, **kwds)


class E:
    @StaticMethod
    def f(x):
        return x * 10



# 类方法的描述器实现
class MethodType:
    """Emulate PyMethod_Type in Objects/classobject.c"""
    def __init__(self, func, obj):
        self.__func = func
        self.__self = obj

    def __call__(self, *args, **kwargs):
        func = self.__func
        obj = self.__self
        return func(obj, *args, **kwargs)


class ClassMethod:
    """Emulate PyClassMethod_Type() in objects/funcobject.c"""
    def __init__(self, f):
        self.f = f

    def __get__(self, obj, cls=None):
        if cls is None:
            cls = type(obj)
        if hasattr(type(self.f), '__get__'):
            return self.f.__get__(cls, cls)
        return MethodType(self.f, cls)


class F:
    @ClassMethod
    def f(cls, x):
        return cls.__name__,x