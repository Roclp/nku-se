# 利用面向对象的思想，设计一个培训机构管理系统，并设计控制台交互系统。

# 1. 总部与分校区模块
#   1. 设立一个总部和三个分校区
#   2. 查看任意校区的员工数量，教师数量，学生数量
#   3. 查看任意校区的收入

class Campus:
    def __init__(self, name, address):
        self.name = name
        self.address = address
        self.staffs = []
        self.students = []
        self.teachers = []
        self.income = 0
        self.courses=[]

    def add_staff(self, staff):
        self.staffs.append(staff)

    def add_student(self, student):
        self.students.append(student)

    def add_teacher(self, teacher):
        self.teachers.append(teacher)

    def get_staffs(self):
        return self.staffs

    def get_students(self):
        return self.students

    def get_teachers(self):
        return self.teachers

    def get_income(self):
        return self.income

    def set_income(self, income):
        self.income = income

    def add_course(self,course):
        self.courses.append(course)

# 2. 学生模块
#   1. 填写学生信息（姓名，电话，校区，学号）
#   2. 加入到班级
#   3. 记录缴纳学费
#   4. 查看学生所在班级，所在校区，参与的课程
#   5. 学生退学
#   6. 查看学生的个人信息（姓名，电话）

class Student:
    def __init__(self, name, phone, campus,classes, student_id):
        self.name = name
        self.phone = phone
        self.campus = campus
        self.student_id = student_id
        self.classes =classes
        self.courses = []
        self.campus.add_student(self)

    def get_name(self):
        return self.name

    def get_phone(self):
        return self.phone

    def get_campus(self):
        return self.campus

    def get_student_id(self):
        return self.student_id

    def get_classes(self):
        return self.classes

    def get_courses(self):
        return self.courses

    def set_class(self, classes):
        self.classes=classes

    def add_course(self, course):
        self.courses.append(course)

    def quit(self):
        self.campus.get_students().remove(self)
        self.campus = None

# 3. 教师模块
#   1. 填写并查看新教师基本信息（姓名，电话，校区，工号）
#   2. 设置并查看教师的授课课程（可以有多门，请展示授课教师列表让用户选择）
class Teacher:
    def __init__(self, name, phone, campus, teacher_id):
        self.name = name
        self.phone = phone
        self.campus = campus
        self.teacher_id = teacher_id
        self.courses = []
        self.campus.add_teacher(self)

    def get_name(self):
        return self.name

    def get_phone(self):
        return self.phone

    def get_campus(self):
        return self.campus

    def get_teacher_id(self):
        return self.teacher_id

    def get_courses(self):
        return self.courses

    def add_course(self, course):
        self.courses.append(course)

    def set_course(self, courses):
        self.courses = courses

# 4. 课程模块
#   1. 添加新课程，输入基本信息（名称，价格，校区）
#   2. 为课程指定授课教师（一名，请展示授课教师列表让用户选择）
#   3. 查看该课程的学生列表
class Course:
    def __init__(self, name, price, campus):
        self.name = name
        self.price = price
        self.campus = campus
        self.students = []
        self.teacher = None
        self.campus.set_income(self.campus.get_income()+price)
        self.campus.add_course(self)

    def get_name(self):
        return self.name

    def get_price(self):
        return self.price

    def get_campus(self):
        return self.campus

    def get_students(self):
        return self.students

    def get_teacher(self):
        return self.teacher

    def set_teacher(self, teacher):
        self.teacher = teacher

    def add_student(self, student):
        self.students.append(student)

# 5. 员工模块
#   1. 员工有后勤，财务，行政三种类型
#   2. 添加新员工，在添加时就需要指定员工类型（利用继承），包含员工的个人信息（姓名，工号，校区）
#   3. 展示员工信息
class Staff:
    def __init__(self, name, staff_id, campus):
        self.name = name
        self.staff_id = staff_id
        self.campus = campus
        self.campus.add_staff(self)

    def get_name(self):
        return self.name

    def get_staff_id(self):
        return self.staff_id

    def get_campus(self):
        return self.campus

def print_campuses_info(campuses):
    print("\n校区信息：")
    for campus in campuses:
        print(f"{campus.name} - 员工数量: {len(campus.get_staffs())}, 教师数量: {len(campus.get_teachers())}, 学生数量: {len(campus.get_students())}, 收入: {campus.get_income()}")

def print_teachers_info(campus):
    print("\n教师信息：")
    for i, teacher in enumerate(campus.get_teachers(), start=1):
        print(f"{i}. {teacher.get_name()} - 工号: {teacher.get_teacher_id()}")

def print_courses_info(campus):
    print("\n课程信息：")
    for i, course in enumerate(campus.courses, start=1):
        print(f"{i}. {course.get_name()} - 价格: {course.get_price()}")

def print_students_info(campus):
    print("\n学生信息：")
    for i, student in enumerate(campus.students, start=1):
        print(f"{i}. {student.get_name()} - 学号: {student.get_student_id()}")

if __name__ == '__main__':
    # 创建总部和分校区
    headquarter = Campus('总部', '北京市海淀区')
    campus1 = Campus('分校区1', '北京市朝阳区')
    campus2 = Campus('分校区2', '北京市丰台区')
    campus3 = Campus('分校区3', '北京市石景山区')

    campuses = [headquarter, campus1, campus2, campus3]

    while True:
        print("\n====== 培训机构管理系统 ======")
        print("1. 总部与分校区模块")
        print("2. 学生模块")
        print("3. 教师模块")
        print("4. 课程模块")
        print("5. 员工模块")
        print("6. 退出系统")

        choice = input("请输入数字选择功能：")

        if choice == '1':
            print_campuses_info(campuses)

        elif choice == '2':
            print("请选择校区：")
            for i, campus in enumerate(campuses, start=1):
                print(f"{i}. {campus.name}")

            campus_choice = int(input("请输入校区编号：")) - 1
            selected_campus = campuses[campus_choice]

            student_name = input("请输入学生姓名：")
            student_phone = input("请输入学生电话：")
            student_classes = input("请输入学生所在班级：")
            student_id = input("请输入学生学号：")

            student = Student(student_name, student_phone, selected_campus, student_classes, student_id)
            print(f"学生 {student_name} 添加成功！")

        elif choice == '3':
            print("请选择校区：")
            for i, campus in enumerate(campuses, start=1):
                print(f"{i}. {campus.name}")

            campus_choice = int(input("请输入校区编号：")) - 1
            selected_campus = campuses[campus_choice]

            teacher_name = input("请输入教师姓名：")
            teacher_phone = input("请输入教师电话：")
            teacher_id = input("请输入教师工号：")

            teacher = Teacher(teacher_name, teacher_phone, selected_campus, teacher_id)
            print(f"教师 {teacher_name} 添加成功！")

            # 设置并查看教师的授课课程
            print_courses_info(selected_campus)
            course_choice = int(input("请选择授课课程编号：")) - 1
            selected_course = selected_campus.courses[course_choice]
            teacher.add_course(selected_course)
            print(f"教师 {teacher_name} 授课课程 {selected_course.get_name()} 添加成功！")

        elif choice == '4':
            print("请选择校区：")
            for i, campus in enumerate(campuses, start=1):
                print(f"{i}. {campus.name}")

            campus_choice = int(input("请输入校区编号：")) - 1
            selected_campus = campuses[campus_choice]

            course_name = input("请输入课程名称：")
            course_price = int(input("请输入课程学费："))

            course = Course(course_name, course_price, selected_campus)
            print(f"课程 {course_name} 添加成功！")

            # 为课程指定授课教师
            print_teachers_info(selected_campus)
            teacher_choice = int(input("请选择授课教师编号：")) - 1
            selected_teacher = selected_campus.teachers[teacher_choice]
            course.set_teacher(selected_teacher)
            print(f"课程 {course_name} 授课教师 {selected_teacher.get_name()} 指定成功！")

        elif choice == '5':
            print("请选择校区：")
            for i, campus in enumerate(campuses, start=1):
                print(f"{i}. {campus.name}")

            campus_choice = int(input("请输入校区编号：")) - 1
            selected_campus = campuses[campus_choice]

            staff_name = input("请输入员工姓名：")
            staff_id = input("请输入员工工号：")

            staff = Staff(staff_name, staff_id, selected_campus)
            print(f"员工 {staff_name} 添加成功！")

        elif choice == '6':
            print("感谢使用培训机构管理系统，再见！")
            break

        else:
            print("输入错误，请重新输入。")
