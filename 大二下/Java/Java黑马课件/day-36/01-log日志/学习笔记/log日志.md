# 日志

## 1.1 作用：

​	跟输出语句一样，可以把程序在运行过程中的详细信息都打印在控制台上。

​	利用log日志还可以把这些详细信息保存到文件和数据库中。

## 1.2 使用步骤：

​	不是java的，也不是自己写的，是第三方提供的代码，所以我们要导入jar包。

* 把第三方的代码导入到当前的项目当中

  新建lib文件夹，把jar粘贴到lib文件夹当中，全选后右键点击选择add as a ....

  检测导入成功：导入成功后jar包可以展开。在项目重构界面可以看到导入的内容

* 把配置文件粘贴到src文件夹下

* 在代码中获取日志对象

* 调用方法打印日志

## 1.3 日志级别

```
TRACE, DEBUG, INFO, WARN, ERROR
```

还有两个特殊的：

​	ALL：输出所有日志

​	OFF：关闭所有日志

日志级别从小到大的关系：

​	TRACE < DEBUG < INFO < WARN < ERROR

## 1.4 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--
        CONSOLE ：表示当前的日志信息是可以输出到控制台的。
    -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--输出流对象 默认 System.out 改为 System.err-->
        <target>System.out</target>
        <encoder>
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度
                %msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5level]  %c [%thread] : %msg%n</pattern>
        </encoder>
    </appender>

    <!-- File是输出的方向通向文件的 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!--日志输出路径-->
        <file>C:/code/itheima-data.log</file>
        <!--指定日志文件拆分和压缩规则-->
        <rollingPolicy
                       class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--通过指定压缩文件名称，来确定分割文件方式-->
            <fileNamePattern>C:/code/itheima-data2-%d{yyyy-MMdd}.log%i.gz</fileNamePattern>
            <!--文件拆分大小-->
            <maxFileSize>1MB</maxFileSize>
        </rollingPolicy>
    </appender>

    <!--

    level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF
   ， 默认debug
    <root>可以包含零个或多个<appender-ref>元素，标识这个输出位置将会被本日志级别控制。
    -->
    <root level="info">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

