'''用于系统操作，打开文件等'''
import os
'''解密'''
import sys
from pathlib import Path
from numpy import fromfile, uint8   # pip install numpy
'''----------------若下载路径发生变化，更改此处----------------'''
download_dir = "D:\python\Scripts\python程序设计\12-20实验\597606094"
download_dir = Path(sys.argv[0]).resolve().parent  # 脚本文件的父文件夹的绝对路径


'''----------------若前缀所在位置发生变化，更改此处----------------'''
start_sequence_of_title = '"PartNo":'
end_sequence_of_title = '","PartName"'

'''----------------若标题所在位置发生变化，更改此处----------------'''
start_label_of_title = '"PartName":'
end_label_of_title = '","Format"'

'''os.walk遍历该路径下所有路径和文件'''
cnt = 0
for f_path, dir_name, f_names in os.walk(download_dir):
    print("当前路径:", end='')
    print(f_path)  # 当前路径
    # print(dir_name)    #
    print("当前路径下的文件:", end='')  # 当前扫描的文件
    print(f_names)  # 当前扫描的文件

    '''在当前目录下寻找MP4文件'''
    for f_name in f_names:
        if 'mp4' in f_name:
            mp4_name = f_name
            cnt = cnt+1
            '''保存MP4绝对路径'''
            mp4_path = f_path + '/' + mp4_name
            print('found one mp4 in:  ' + mp4_path)

            '''解密操作'''
            read = fromfile(mp4_path, dtype=uint8)
            if all(read[0:3] == [255, 255, 255]):
                outfile = f"str(cnt).mp4"
                read[3:].tofile(mp4_path)
                print(outfile)

            '''根据.info文件，找到标题内容'''
            for f_name in f_names:
                if 'info' in f_name:
                    info_path = f_path + '/' + f_name
                    print('found one info:  ' + info_path)

                    '''打开.info文件,编码方式可在VS code中打开查看'''
                    with open(info_path, encoding='utf-8') as f:
                        '''读取.info文件中所有内容'''
                        text_content = f.read()
                        # print(text_content)

                        '''找到sequence位置'''
                        sequence_start = text_content.find(start_sequence_of_title) + len(start_sequence_of_title)
                        sequence_end = text_content.find(end_sequence_of_title, sequence_start)  # 字符结束的位置

                        '''找到标题位置'''
                        '''find:在字符串中查找指定子串，返回标题第一个字符的位置（0，1，2...）'''
                        title_numb_start = text_content.find(start_label_of_title) + len(
                            start_label_of_title)  # + len(start_label_of_title)为标题第一个字符的位置
                        title_numb_end = text_content.find(end_label_of_title, title_numb_start)  # 字符结束的位置
                        # print( str(title_numb_start) + "  " + str(title_numb_end) )

                        '''new_name = P + sequence + title'''
                        new_name = "P" + text_content[sequence_start + 1:sequence_end] + " " + text_content[
                            title_numb_start + 1:title_numb_end] + ".mp4"
                        new_name = new_name.replace('\\', '')  # \\表示\
                        new_name = new_name.replace(' ', '_')
                        new_name = new_name.replace('/', '')
                        new_name = new_name.replace(':', '')
                        new_name = new_name.replace('*', '')
                        new_name = new_name.replace('?', '')
                        new_name = new_name.replace('<', '')
                        new_name = new_name.replace('>', '')
                        new_name = new_name.replace('|', '')
                        print(new_name, end='')
                        f.close()
                        new_name_path = f_path + '/' + new_name

                        '''try以避免报错'''
                        try:
                            '''重命名'''
                            os.rename(mp4_path, new_name_path)

                            print(' successfully renamed')

                        except:
                            print(' failed')

        else:
            print('not mp4 file,continuing...')

    print('\n')