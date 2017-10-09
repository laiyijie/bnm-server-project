# 运行环境配置

JDK版本请选择1.8最新版本
MAVEN请使用3.3.9
git工具推荐[sourcetree](https://www.sourcetreeapp.com/)

1. 安装[IDEA](https://www.jetbrains.com/idea/)，激活请参考[公司jetbrain系列产品激活码](http://git.bangnongmang.cn:8090/pages/viewpage.action?pageId=2949270)
2. 配置MAVEN镜像，请参考[MAVEN 私服配置](http://git.bangnongmang.cn:8090/pages/viewpage.action?pageId=2949267)
3. 安装Redis，windows 请参考[redis-for-windows](https://github.com/MSOpenTech/redis/releases) 密码最好置为空
4. 安装mysql数据库，版本5.7以上，推荐使用[wamp套件](www.wampserver.com/en/)，其包含phpmyadmin，方便操作本地数据库
5. 创建三个空数据库，名字分别为bnm_main,bnm_manager,bnm_notify
6. 请遵循其config/readme.md文档设置系统变量,打开config.properties，修改jdbc.username和jdbc.password，如果有设置redis的密码请修改session.redis.password
7. 克隆本工程，并使用IDEA导入工程
8. 右键本工程RUN ALL TEST
9. 测试用例全部跑过则配置成功

# IDEA内TOMCAT环境配置
本项目为web项目，为了便于调试，在IDEA中配置TOMCAT并运行，将大大提高效率    
1. 下载[tomcat8.0.x](http://tomcat.apache.org/download-80.cgi)并解压到指定目录     
2. 打开IDEA，点击右上角绿三角旁边的下拉菜单>Edit Configurations..     
3. 单击新弹出来的对话框左上角的+号，找到tomcat Server -> local
4. Server选项卡下选择tomcat目录（刚才解压的目录）  
5. deployment选项卡下增加 server.war exploded  
6. Startup/Connection选项卡下Environment variables下面增加两个属性BNM_CONFIG，BNM_LOGPATH 详细请参考config/readme.md
7. 选择配置的tomcat，并run起来


# 主要模块
1. main-dao 用户端数据库对应的DAO层
2. main-service 用户端Service和Middle层，依赖于main-dao层
3. main-web 用户端Web层，依赖于main-service层
4. manager-web 管理端Web层，依赖于main-service


# 如何编译

## 编译打包所有模块
在根目录下执行  
`mvn clean package`

## 编译单个模块
在根目录下执行   
`mvn clean package -pl modules/xxxx -am`

## 编译单个模块和依赖其的模块
`mvn clean package -pl modules/xxxx -am -amd`


