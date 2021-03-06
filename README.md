## SpringBoot构建电商基础秒杀项目
### 项目简介

**通过SpringBoot快速搭建的前后端分离的电商基础秒杀项目。项目实现：用户otp注册、登陆、查看、商品列表、进入商品详情以及倒计时秒杀开始后下单购买的基本流程**。


### 使用到的外部依赖

* org.springframework.boot:spring-boot-starter-web
* mysql:mysql-connector-java
* com.alibaba:druid
* org.mybatis.spring.boot:mybatis-spring-boot-starter
* org.apache.commons:commons-lang3
* org.hibernate:hibernate-validator
* joda-time:joda-time
* junit:junit
* org.springframework:spring-test
* org.mybatis.generator：mybatis-generator-maven-plugin （插件）

### 项目要点
* **在mybatis-generator.xml配置文件中在对应生成表类名配置中加入**
`enableCountByExample="false"enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false"selectByExampleQueryId="false"`
**避免生成不常用方法**

&nbsp;
 * **前端 ajax 调用接口获取验证码 html/getotp.html，出现跨域请求问题**
`解决方法：@CrossOrigin(origins = {"*"}, allowCredentials = "true")`
*allowedHeaders 允许前端将 token 放入 header 做 session 共享的跨域请求。
allowCredentials 授信后，需前端也设置 xhfFields 授信才能实现跨域 session 共享。
xhrFields: {withCredentials: true},*

&nbsp;
* **统一前端返回格式CommonReturnType**
*{status: xx ,object:xx}*
*dataobject -> 与数据库对应的映射对象
model -> 用于业务逻辑service的领域模型对象
viewobject -> 用于前端交互的模型对象*

&nbsp;
* **使用 hibernate-validator 通过注解来完成模型参数校验**

&nbsp;
* **insertSelective 中设置 keyProperty="id" useGeneratedKeys="true" 使得插入完后的 DO 生成自增 id 。**
*insertSelective与insert区别：
insertSelective对应的sql语句加入了NULL校验，即只会插入数据不为null的字段值（null的字段依赖于数据库字段默认值）insert则会插入所有字段，会插入null。*

&nbsp;

* **数据库设计规范，设计时字段要设置为not null，并设置默认值，避免唯一索引在null情况下失效等类似场景**

