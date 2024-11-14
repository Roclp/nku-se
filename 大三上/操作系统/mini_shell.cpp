#include <dirent.h>
#include <libgen.h>
#include <iostream>
#include <vector>
#include <cstring>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <cstdlib>
#include <sstream>
#include <pwd.h>
#include <fcntl.h>

using namespace std;

#define SUCCESS 0
#define ERROR -1

int reflag = 0;
char current_dir[100];
char user_dir[100];
vector<string> re;

int eval(vector<string> res);

vector<string> split(const string &s, const string &separator);

int eval(vector<string> res) {
    if (res[0] == "cd") {
        if (res.size() == 1) {
            strcpy(current_dir, user_dir);
        } else {
            const char *rest = res[1].c_str();
            if (res[1] == "/") {
                opendir(rest);
                strcpy(current_dir, rest);
            } else if (res[1] == "..") {
                char *parent_dir = dirname(current_dir);
                strcpy(current_dir, parent_dir);
            } else if (res[1] == "~") {
                strcpy(current_dir, user_dir);
            } else {
                char target_path[100];
                cout << "current dir: " << current_dir << endl;
                if (strcmp(current_dir, "/") == 0) {
                    snprintf(target_path, 1024, "%s%s", current_dir, rest);
                } else {
                    snprintf(target_path, 1024, "%s/%s", current_dir, rest);
                }
                if (opendir(target_path) == NULL) {
                    cout << "cd: " << rest << ":";
                    printf("\033[31m  没有那个文件或目录.\n\033[0m");
                    return ERROR;
                }
                strcpy(current_dir, target_path);
            }
            cout << current_dir << endl;
            return ERROR;
        }
    } else if (res[0] == "pwd") {
        char buf[300];
        cout << current_dir << endl;
    } else if (res[0] == "ls") {
        int pid = fork(), wpid;
        int status;
        int count = 0;
        const char *rest = res[0].c_str();

        if (pid == 0) {
            char *env[] = {0, NULL};
            for (int i = 0; i < res.size(); i++) {
                if (res[i] == ">") {
                    reflag = 1;
                    count = i;
                }
                if (res[i] == ">>") {
                    reflag = 2;
                    count = i;
                }
            }
            if (reflag != 0) {
                char **cmd_temp = new char *[count];

                for (int i = 0; i < count; i++) {
                    cmd_temp[i] = new char[500];
                    memset(cmd_temp[i], 0, sizeof(cmd_temp[i]));
                }
                for (int i = 0; i < count; i++) {
                    strcpy(cmd_temp[i], res[i].c_str());
                }
                cmd_temp[count] = current_dir;
                cmd_temp[count + 1] = NULL;

                int fd = 1;
                if (reflag == 1)
                    fd = open(res[count + 1].c_str(), O_CREAT | O_WRONLY | O_TRUNC, 0664);
                else if (reflag == 2)
                    fd = open(res[count + 1].c_str(), O_CREAT | O_WRONLY | O_APPEND, 0664);
                dup2(fd, 1);
                if (execvp(rest, cmd_temp) < 0) {
                    printf("\033[31m%s:command not found.\n\033[0m", res[0].c_str());
                }
            } else {
                char **cmd_temp = new char *[res.size() + 1];
                for (int i = 0; i < res.size(); i++) {
                    cmd_temp[i] = new char[500];
                    memset(cmd_temp[i], 0, sizeof(cmd_temp[i]));
                }
                for (int i = 0; i < res.size(); i++) {
                    strcpy(cmd_temp[i], res[i].c_str());
                }
                cmd_temp[res.size()] = current_dir;
                cmd_temp[res.size() + 1] = NULL;
                if (execvp(rest, cmd_temp) < 0) {
                    printf("\033[31m%s:command not found.\n\033[0m", res[0].c_str());
                }
            }
        } else if (pid > 0) {
            do {
                wpid = waitpid(pid, &status, WUNTRACED);
            } while (!WIFEXITED(status) && !WIFSIGNALED(status));
        }
    } else {
        int pid = fork(), wpid;
        int status;
        const char *rest = res[0].c_str();

        if (pid == 0) {
            char **cmd_temp = new char *[res.size()];
            char *env[] = {0, NULL};
            for (int i = 0; i < res.size(); i++) {
                cmd_temp[i] = new char[500];
                memset(cmd_temp[i], 0, sizeof(cmd_temp[i]));
            }
            for (int i = 0; i < res.size(); i++) {
                strcpy(cmd_temp[i], res[i].c_str());
            }
            cmd_temp[res.size()] = NULL;
            if (execvp(rest, cmd_temp) < 0) {
                printf("\033[31m%s:command not found.\n\033[0m", res[0].c_str());
            }
        } else if (pid > 0) {
            do {
                wpid = waitpid(pid, &status, WUNTRACED);
            } while (!WIFEXITED(status) && !WIFSIGNALED(status));
        }
    }
    return SUCCESS;
}

vector<string> split(const string &s, const string &separator) {
    vector<string> result;
    typedef string::size_type string_size;
    string_size i = 0;
    while (i != s.size()) {
        int flag = 0;
        while (i != s.size() && flag == 0) {
            flag = 1;
            for (string_size x = 0; x < separator.size(); ++x)
                if (s[i] == separator[x]) {
                    ++i;
                    flag = 0;
                    break;
                }
        }
        flag = 0;
        string_size j = i;
        while (j != s.size() && flag == 0) {
            for (string_size x = 0; x < separator.size(); ++x)
                if (s[j] == separator[x]) {
                    flag = 1;
                    break;
                }
            if (flag == 0)
                ++j;
        }
        if (i != j) {
            result.push_back(s.substr(i, j - i));
            i = j;
        }
    }
    return result;
}

int main() {
    string cmdstring;
    printf("\033[32m************** welcome to 李鹏2113850 mini shell ************** \n\033[0m");

    strcpy(current_dir, getpwuid(getuid())->pw_dir);
    strcpy(user_dir, getpwuid(getuid())->pw_dir);
    printf("\033[92m%s@MINISHELL\033[0m:\033[34m%s\033[0m$", getlogin(), current_dir);

    while (1) {
        for (int i = 0; i < re.size(); i++) {
            re[i].clear();
        }

        getline(cin, cmdstring);
        string result;
        vector<string> v = split(cmdstring, ";");

        for (int i = 0; i < v.size(); i++) {
            re.clear();
            stringstream input2(v[i]);
            while (input2 >> result) {
                re.push_back(result);
            }

            if (result == "exit") {
                printf("\033[32m****************** mini shell exit ******************\n\033[0m");
                return 0;
            }

            if (re.size()) {
                eval(re);
            }
        }

        printf("\033[92m%s@MINISHELL\033[0m:\033[34m%s\033[0m$", getlogin(), current_dir);
    }

    return 0;
}
