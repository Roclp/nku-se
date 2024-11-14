class Campus:
    def __init__(self, name, address):
        self.name = name
        self.address = address
        self.staffs = []
        self.students = []
        self.teachers = []
        self.income = 0
        self.courses = []

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

    def add_course(self, course):
        self.courses.append(course)


class Student:
    def __init__(self, name, phone, campus, classes, student_id):
        self.name = name
        self.phone = phone
        self.campus = campus
        self.student_id = student_id
        self.classes = classes
        try:
            self.campus.add_student(self)
        except Exception as e:
            print(f"添加学生出错：{e}")

    def show_info(self):
        print(f"姓名：{self.name} 电话：{self.phone} 学号：{self.student_id} 校区：{self.campus.name} 班级：{self.classes}")

    def enroll_course(self, course):
        try:
            if self in course.students:
                print("您已经报名了该课程。")
            else:
                course.students.append(self)
                self.courses.append(course)
                print(f"您已成功报名课程《{course.name}》。")
        except Exception as e:
            print(f"报名课程出错：{e}")



class Teacher:
    def __init__(self, name, phone, campus, teacher_id):
        self.name = name
        self.phone = phone
        self.campus = campus
        self.teacher_id = teacher_id
        self.courses = []
        try:
            self.campus.add_teacher(self)
        except Exception as e:
            print(f"添加教师出错：{e}")

    def show_info(self):
        print(f"姓名：{self.name} 电话：{self.phone} 工号：{self.teacher_id} 校区：{self.campus.name}")

    def get_courses(self):
        return self.courses

    def assign_score(self, course, student, score):
        try:
            if course in self.courses and student in course.students:
                student.score[course.name] = score
                print(f"{student.name} 的成绩已经更新为 {score} 分。")
            else:
                print("该教师没有教授该课程，或该学生没有报名该课程。")
        except Exception as e:
            print(f"指定成绩出错：{e}")



class Course:
    def __init__(self, name, price, campus):
        self.name = name
        self.price = price
        self.campus = campus
        self.students = []
        self.teacher = None
        try:
            self.campus.set_income(self.campus.get_income() + price)
            self.campus.add_course(self)
        except Exception as e:
            print(f"添加课程出错：{e}")

    def show_info(self):
        print(f"名称：{self.name} 价格：{self.price} 校区：{self.campus.name} 学生人数：{len(self.students)}")

    def set_teacher(self, teacher):
        try:
            if self.teacher is None:
                self.teacher = teacher
                teacher.courses.append(self)
                print(f"{teacher.name} 成功被指定为《{self.name}》的授课教师。")
            else:
                print("该课程已经有授课教师了。")
        except Exception as e:
            print(f"指定授课教师出错：{e}")



class Staff:
    def __init__(self, name, staff_id, campus):
        self.name = name
        self.staff_id = staff_id
        self.campus = campus
        try:
            self.campus.add_staff(self)
        except Exception as e:
            print(f"添加员工出错：{e}")

    def show_info(self):
        print(f"姓名：{self.name} 工号：{self.staff_id} 校区：{self.campus.name}")



if __name__ == '__main__':
    # 创建总部和分校区
    headquarter = Campus("总部", "北京市海淀区")
    campus1 = Campus("分校区1", "上海市浦东新区")
    campus2 = Campus("分校区2", "广州市天河区")

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
            # 查看校区信息
            print("总部信息：")
            print(f"名称：{headquarter.name} 地址：{headquarter.address} 员工人数：{len(headquarter.get_staffs())} 学生人数：{len(headquarter.get_students())} 教师人数：{len(headquarter.get_teachers())} 收入：{headquarter.get_income()}")
            print("分校区1信息：")
            print(f"名称：{campus1.name} 地址：{campus1.address} 员工人数：{len(campus1.get_staffs())} 学生人数：{len(campus1.get_students())} 教师人数：{len(campus1.get_teachers())} 收入：{campus1.get_income()}")
            print("分校区2信息：")
            print(f"名称：{campus2.name} 地址：{campus2.address} 员工人数：{len(campus2.get_staffs())} 学生人数：{len(campus2.get_students())} 教师人数：{len(campus2.get_teachers())} 收入：{campus2.get_income()}")

        elif choice == '2':
            # 添加学生
            name = input("请输入学生姓名：")
            phone = input("请输入学生电话号码：")
            student_id = input("请输入学生学号：")
            campus_name = input("请输入所在校区名称（总部/分校区1/分校区2）：")
            if campus_name == "总部":
                campus = headquarter
            elif campus_name == "分校区1":
                campus = campus1
            elif campus_name == "分校区2":
                campus = campus2
            else:
                print("输入错误，请重新选择。")
                continue
            classes = input("请输入所在班级：")
            new_student = Student(name, phone, campus, classes, student_id)
            new_student.show_info()

        elif choice == '3':
            # 添加教师
            name = input("请输入教师姓名：")
            phone = input("请输入教师电话号码：")
            teacher_id = input("请输入教师工号：")
            campus_name = input("请输入所在校区名称（总部/分校区1/分校区2）：")
            if campus_name == "总部":
                campus = headquarter
            elif campus_name == "分校区1":
                campus = campus1
            elif campus_name == "分校区2":
                campus = campus2
            else:
                print("输入错误，请重新选择。")
                continue
            new_teacher = Teacher(name, phone, campus, teacher_id)
            new_teacher.show_info()

        elif choice == '4':
            # 添加课程
            name = input("请输入课程名称：")
            price = input("请输入课程价格：")
            campus_name = input("请输入所在校区名称（总部/分校区1/分校区2）：")
            if campus_name == "总部":
                campus = headquarter
            elif campus_name == "分校区1":
                campus = campus1
            elif campus_name == "分校区2":
                campus = campus2
            else:
                print("输入错误，请重新选择。")
                continue
            new_course = Course(name, int(price), campus)
            new_course.show_info()

        elif choice == '5':
            # 添加员工
            name = input("请输入员工姓名：")
            staff_id = input("请输入员工工号：")
            campus_name = input("请输入所在校区名称（总部/分校区1/分校区2）：")
            if campus_name == "总部":
                campus = headquarter
            elif campus_name == "分校区1":
                campus = campus1
            elif campus_name == "分校区2":
                campus = campus2
            else:
                print("输入错误，请重新选择。")
                continue
            new_staff = Staff(name, staff_id, campus)
            new_staff.show_info()

        elif choice == '6':
            print("感谢使用培训机构管理系统，再见！")
            break

        else:
            print("输入错误，请重新输入。")
