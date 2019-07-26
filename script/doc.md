1.克隆项目到本地环境
    git clone git@github.com:sjlian/springboot-distributed-demo.git

2.导入到ide中
    建议用mvn方式导入。这里省略。不熟悉的同学请移步idea和eclipse怎么导入maven项目的搜索。

3.配置mysql环境
    安装mysql，并配置。[具体请移步mysql官网](https://www.mysql.com/downloads/)。修改配置文件中mysql连接数据。
    执行createTable的sql文件创建库表。

4.配置redis环境
    安装redis，并配置。[具体见redis官网](http://redis.io/)。修改配置文件中的redis连接数据。

5.配置mongo环境
    安装mongo，[详见mongodb官网](www.mongodb.org/)。修改配置文件中的mongo连接数据。
    创建db-demo。命令行：use demo;

6.配置rabbitmq环境
    安装rabbitmq,[详见rabbitmq官网](www.rabbitmq.com/)。修改配置文件中的rabbitmq连接数据。