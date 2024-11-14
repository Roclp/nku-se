class Circle:
    def __init__(self, r):
        self.r = r

    def get_area(self):
        area=3.14 * self.r ** 2
        return round(area,2)

    def get_perimeter(self):
        perimeter=2 * 3.14 * self.r
        return round(perimeter,2)

if __name__=='__main__':
    circle=Circle(8)
    print(circle.get_area())
    print(circle.get_perimeter())