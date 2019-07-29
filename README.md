# springboot-distributed-demo
[以最优雅的方式示例了springboot最简单的最全集成的例子](https://github.com/sjlian/springboot-distributed-demo)
[使用文档请点击](https://github.com/sjlian/springboot-distributed-demo/blob/master/script/doc.md)

核心技术

    控制层：
        mvc交互，注解数据校验,统一接口返回（异常拦截与正常返回统一）,日期交互优雅处理,返回数据序列化与非空化处理,分布式session, 文件上传下载，
        拦截器，监听器，过滤器示例，更换jetty，在线文档
    安全层：
        shiro安全校验，CSRF/XSS拦截 ，单点登录，jsonwebtoken
    持久层:
        JPA数据持久,数据库优雅分页,注解缓存,分布式缓存 redis,druid数据库连接池,lettuce缓存连接池，mysql主从读写分离，数据分片，乐观锁，
        mongodb，rabbitmq
    业务层：
        注解式事务，jta分布式事务，注解定时任务，注解异步，多环境
    监控层：
        系统监控，日志系统
    常用工具包：
        二维码，EXCEL导入导出，CSV导入导出，反射工具包，加密工具包
        
    对于org.springframework.boot.bind包的说明：
        该包用于解决sharding-jdbc和spring版本不一致问题。
        io.shardingjdbc.spring.boot.SpringBootConfiguration第49行的RelaxedPropertyResolver所在包已经被spring删除。
        spring做法是Environment直接继承RelaxedPropertyResolver，可以直接用Environment对象调用getProperty，无需再new一个对象。
        这里给出该package的原因是为了优雅的使用sharding-jdbc，待sharding-jdbc升级改掉该issue后删去该package即可

更新日志

18.12.27 更新

        所有依赖更新为最新版本
        更改tomcat为jetty
        集成redis，并和cache注解结合。
        加入XSS、sql注入拦截。
        加入分页查询示例
        加入常用工具包：二维码，随机验证码
        文件上传下载
        分布式session共享
        
19.01.02 更新
    
        引入shiro权限控制。用户-角色-权限 三级级联
        修复部分小问题
        
19.01.08 更新
    
        加入sharding-jdbc 分库分表，1主库，2从库，且对t_order表进行了数据分片
        加入注解式事务管理
        加入jta分布式事务
        加入单元测试
        美化日志输出（主要是加了颜色，文件卷积日期）
        修复部分小问题


19.07.26 更新

        1.加入了使用文档
        2.升级所有依赖为最新稳定版本.
            springboot:2.1.6
            mysql-connector-java 8.0.17
            druid   1.1.19
            javamelody 1.78.0
            zxing   3.4.0
            commons-lang3   3.9
            shiro   1.4.1
        3.修改项目名称，groupId和package，使其保持一致。
        4.加入mongodb增删改查demo

19.07.29 更新

         1.解决所有依赖冲突,以及依赖冲突引起的问题
         2.解决shardingsphere自动事务配置错误的问题
         3.调整package更清晰


