# 体脂率的判断

gender = input("请输入性别（男：1，女：0）：")
age = int(input("请输入年龄："))
height = float(input("请输入身高(m)："))
weight = float(input("请输入体重(kg)："))

# 判断性别是否合法
if gender not in ["0", "1"]:
    print("输入中的性别项输出有误，请检查后再次输入")
else:
    # 判断身高范围是否合法
    if height < 0.5 or height > 2.5:
        print("疑似输入身高有误，请检查后再次输入")
    else:
        # 计算BMI
        bmi = weight / (height * height)

        # 根据性别计算体脂率
        if gender == "1":
            body_fat_percentage = 1.2 * bmi + 0.23 * age - 5.4 - 10.8
            gender_str = "先生"
        else:
            body_fat_percentage = 1.2 * bmi + 0.23 * age - 5.4
            gender_str = "女士"

        # 判断体脂率是否在正常范围内
        if gender == "1" and 15 <= body_fat_percentage <= 18:
            result_str = "正常"
        elif gender == "0" and 25 <= body_fat_percentage <= 28:
            result_str = "正常"
        else:
            result_str = "不正常"

        # 输出体脂率结果，保留2位小数
        print(f"{body_fat_percentage:.2f}  {gender_str}你好，体脂率{result_str}")
