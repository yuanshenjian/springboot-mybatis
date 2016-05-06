## Spring-boot-mybatis

The project is a demo project,which integrates the mybatis with spring-boot


##Setup Environment(Mac)

####1.安装JDK1.8, Gradle,查看安装信息

    $ java -version
    $ gradle --v

####2.安装MySQL5.6,并重启MySQL服务

    $ brew install mysql
    $ mysql.server restart

####3.初始化数据库, 执行数据库的migration

    $ gradle initDatabase
    $ gradle flywayClean flywayMigrate

####4.启动Spring-boot服务

    $ gradle clean bootRun

####5.发布jar包

    $ gradle clean build
    
