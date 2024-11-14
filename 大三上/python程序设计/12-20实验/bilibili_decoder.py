import os
import re
import json
import shutil
import numpy as np

video_dir="597606094"

'''
使用Python删去开头的三个十六进制字符，以便视频能够在其它播放器上正常播放
使用Python创建一个新的文件夹，命名为下载项目的名字（视频合集名字可以从缓存的相关文件内获取）。
使用Python将每一集的视频文件导入到文件夹中，视频的名称从配置文件（XXX.info）读取。
使用Python在文件夹下创建一个info.html文件，html的内容是每一个分集的名称和长度，以及其他你想添加的信息。
'''

# 创建新文件夹（如果不存在）
with open(os.path.join(video_dir,"597606094.dvi"),'rt',encoding="utf-8")as f:
    # print(f.read())
    file_content = f.read()
    print(file_content)
    title_match = re.search(r'"Title":"([^"]+)"', file_content)
    if title_match:
        title = title_match.group(1)
        print(title)
dir_name = title
if not os.path.exists(dir_name):
    os.mkdir(dir_name)

# info,html
file_1="info.html"
for f in os.listdir(video_dir):
    video_info_path = os.path.join(video_dir, f, "597606094.info")
    if os.path.exists(video_info_path):
        # print(os.listdir(os.path.join(video_dir, f)))
        with open(os.path.join(video_dir, f, "597606094.info"), 'rt', encoding="utf-8") as f:
            # print(f.read())
            file_content = f.read()
            data = json.loads(file_content)
            title = data['Title']
            uploader = data['Uploader']
            description = data['Description']
            cover_url = data['CoverURL']
            part_name = data['PartName']
            total_time = data['TotalTime']

            print("Title:", title)
            print("Uploader:", uploader)
            print("Description:", description)
            print("Cover URL:", cover_url)
            print("Part Name:", part_name)
            print("Total Time:", total_time)

            with open(os.path.join(dir_name, file_1), 'a', encoding='utf-8') as html_file:
                html_file.write("<html><head><meta charset='utf-8'>")
                html_file.write("<title>{}</title>".format(title))
                html_file.write("<style>body {font-family: Arial, sans-serif; margin: 20px;}</style>")
                html_file.write("</head><body>")
                html_file.write("<h1>{}</h1>".format(title))
                html_file.write("<p><strong>Uploader:</strong> {}</p>".format(uploader))
                html_file.write("<p><strong>Description:</strong> {}</p>".format(description))
                html_file.write("<p><strong>Cover URL:</strong> {}</p>".format(cover_url))
                html_file.write("<p><strong>Part Name:</strong> {}</p>".format(part_name))
                html_file.write("<p><strong>Total Time:</strong> {}</p>".format(total_time))
                html_file.write("</body></html>")

# mp4文件的拷贝 命名
for f in os.listdir(video_dir):
    video_info_path = os.path.join(video_dir, f, "597606094.info")
    if os.path.exists(video_info_path):
        # print(os.listdir(os.path.join(video_dir, f)))
        with open(os.path.join(video_dir, f, "597606094.info"), 'rt', encoding="utf-8") as file:
            # print(f.read())
            file_content = file.read()
            data = json.loads(file_content)
            part_name = data['PartName']
            match = re.search(r'^\d+\.?\s*', part_name)
            p = part_name.replace(match.group(), '')
            print(p)

            # os.mkdir(os.path.join(dir_name,f"{p}.mp4"))
            for file in os.listdir(os.path.join(video_dir,f)):
                    if file.endswith(".mp4"):
                        source_file_path = os.path.join(video_dir, f,file)
                        destination_file_path = os.path.join(dir_name, f"{p}.mp4")
                        shutil.copy2(source_file_path, destination_file_path)

# mp4解密：去掉开头的三个十六进制字符
for file_name in os.listdir(dir_name):
    if file_name.endswith(".mp4"):
        mp4_path = os.path.join(dir_name, file_name)

        with open(mp4_path, 'rb') as file:
            read = np.fromfile(file, dtype=np.uint8)

            if all(read[0:3] == [255, 255, 255]):
                modified_content = read[3:]
                with open(mp4_path, 'wb') as outfile:
                    modified_content.tofile(outfile)

                print(f"解密成功: {file_name}")