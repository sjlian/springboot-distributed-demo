# xyb
以最优雅的方式示例了springboot最简单的例子。
     
    mvc交互      
    注解数据校验    
    统一接口返回（异常拦截与正常返回统一）
    日期交互优雅处理 
    返回数据序列化与非空化处理
    分布式session
    文件上传下载
    拦截器，监听器，过滤器示例。
    更换jetty
    
    shiro安全校验
    CSRF/XSS拦截 
    单点登录
    jsonwebtoken 
    
    JPA数据持久
    数据库优雅分页
    注解缓存
    分布式缓存 
    druid数据库连接池  
    主从读写分离
    数据分片
    注解式事务
    jta分布式事务
    乐观锁
    
    
    注解定时任务   
    注解异步
    
    系统监控    
    在线文档    
    多环境
    日志系统  
    
    常用工具包：
        二维码，EXCEL导入导出，CSV导入导出，反射工具包，加密工具包    
        
       
        
    对于org.springframework.boot.bind包的说明：    
    
    该包用于解决sharding-jdbc和spring版本不一致问题。
    io.shardingjdbc.spring.boot.SpringBootConfiguration第49行的RelaxedPropertyResolver所在包已经被spring删除。
    spring做法是Environment直接继承RelaxedPropertyResolver，可以直接用Environment对象调用getProperty，无需再new一个对象。
    这里给出该package的原因是为了优雅的使用sharding-jdbc，待sharding-jdbc升级改掉该issue后删去该package即可
                
12.27日更新

        所有依赖更新为最新版本
        更改tomcat为jetty
        集成redis，并和cache注解结合。
        加入XSS、sql注入拦截。
        加入分页查询示例
        加入常用工具包：二维码，随机验证码
        文件上传下载
        分布式session共享
        
19.01.02日更新
    
        引入shiro权限控制。用户-角色-权限 三级级联
        修复部分小问题
        
19.01.08日更新
    
        加入sharding-jdbc 分库分表，1主库，2从库，且对t_order表进行了数据分片
        加入注解式事务管理
        加入jta分布式事务
        加入单元测试
        美化日志输出（主要是加了颜色，文件卷积日期）
        修复部分小问题