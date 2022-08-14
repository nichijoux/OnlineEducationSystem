# OnlineEducationSystem
OES在线教育系统是基于[谷粒学苑]进行学习开发的项目。

项目使用了`Spring boot,Spring Security,Mybatis Plus,gateway,mysql`等技术进行开发

完全完成谷粒学苑的所有功能，并添加了部分功能如

- 课程收藏
- 学习记录
- 订单管理
- 课程评论
- ······

## 注意点

> 本项目部份工具使用版本和所讲内容不同（如mybatis使用的是`3.5.1`版本），因此**需要大家重新下载**`pom`依赖。
>
> 项目添加了数据实体校验，课程评论模块以及优化了一些小的显示风格

**OnlineEducationSystem**为后端代码

**OnlineEducationSystem-Admin**为后台代码

**OnlineEducationSystem-Student**为前台代码

## 项目后端结构

![](./pictures/项目结构.jpg)

## 项目功能预览

### 后台管理页面

> 后台管理页面基于最新的`element-admin-template`进行开发，在编写代码过程中，基本由本人自行编写，因此和老师代码可能出入较大；但整体效果仍一致
>
> 后端的``QueryWrapper`查询基本都用的`LambdaQueryWrapper`
>
> 后台的组件基本全是从`element-ui`上的实例代码改的

#### 后台首页

> 后台首页加入了背景图片，以及滑动验证码

![](./pictures/后台首页.jpg)

![](./pictures/后台首页验证码.jpg)

##### #### 动态菜单

> admin用户的菜单

![](./pictures/首页仪表盘.jpg)

> sys用户的菜单

![](./pictures/权限管理动态菜单.jpg)

#### 权限管理

> `vue`权限管理为自己参考网上资料进行编写，因为本人并没有系统学习过`vue`，因此可能存在部分bug，项目部分内容将在以后学习后完善

##### 用户管理

> 用户管理中修改了页面内容，将所有的逻辑操作包括（分配角色、编辑用户、删除角色、添加角色）都放在了该页面中

用户管理页面

![](./pictures/用户管理.jpg)

用户管理中添加用户页面

![](./pictures/用户管理添加用户.jpg)

用户管理中分配角色页面

![](./pictures/用户管理角色分配.jpg)

用户管理中用户信息编辑

> 这部分中的密码为加密后的密码

![](./pictures/用户管理编辑用户.jpg)

用户管理删除用户

> 在删除用户时，可以看到被删除的用户的用户名

![](./pictures/用户管理删除用户.jpg)



##### 角色管理

角色管理首页

![](./pictures/角色管理.jpg)

角色管理添加\编辑角色界面

![](./pictures/角色管理编辑角色.jpg)

角色管理分配权限

![](./pictures/角色管理分配权限.jpg)



##### 菜单管理

> 菜单管理中的[菜单排序]对应你左侧菜单的动态排序

菜单管理

![](./pictures/菜单管理.jpg)

菜单修改

![](./pictures/权限管理菜单修改.jpg)

菜单管理权限值修改

![](./pictures/菜单管理权限值修改.jpg)



#### 讲师管理

> 讲师管理页面进行了部分更改设计

##### 讲师列表

![](./pictures/讲师列表.jpg)

##### 讲师添加、修改

![](./pictures/添加讲师.jpg)

#### 学科管理

> 这部分的添加和编辑也进行了显示优化

![](./pictures/学科管理.jpg)

添加、编辑回显优化

![](./pictures/科目添加.jpg)



#### 课程管理

##### 课程列表

![](./pictures/课程管理.jpg)

##### 课程信息编辑

![](./pictures/课程信息编辑.jpg)

##### 课程大纲

![](./pictures/课程大纲.jpg)

##### 课程发布

![](./pictures/课程发布.jpg)



#### 用户列表

> 后台管理人员可以在这里进行用户的禁启用

![](./pictures/用户列表.jpg)

#### 评论列表

> 管理人员可以在这里进行评论的浏览和删除

![](./pictures/评论列表.jpg)



#### 数据统计

![](./pictures/数据统计.jpg)

![](./pictures/数据统计显示.jpg)



#### banner管理

##### banner列表

> 管理人员可以在这里进行banner的启用和禁用以及修改，修改复用了添加banner的页面

![](./pictures/banner管理.jpg)

##### banner添加

![](./pictures/添加banner.jpg)

#### 订单管理

##### 已支付

![](./pictures/已支付.jpg)

##### 未支付

![](./pictures/未支付.jpg)

#### 学习记录

![](./pictures/studyRecord.jpg)



### 用户前台页面

> 前台界面使用的是提供的`nuxt`模板进行开发，只加入了评论功能和个人信息修改功能

#### 首页

![](./pictures/前台首页.jpg)



#### 课程界面

##### 课程列表页

![](./pictures/课程首页.jpg)

##### 课程详情页

![](./pictures/课程详情1.jpg)

![](./pictures/课程详情2.jpg)



#### 名师界面

##### 名师列表

![](./pictures/名师列表.jpg)

##### 名师详情

![](./pictures/名师详情.jpg)




#### 用户界面

##### 登录

![](./pictures/登录界面.jpg)

##### 登录后的个人中心

![](./pictures/个人中心下拉.jpg)

![](./pictures/基本信息.jpg)

![](./pictures/密码修改.jpg)

![](./pictures/课程收藏.jpg)

![](./pictures/学习记录.jpg)



#### 课程播放

![](./pictures/课程播放.jpg)

#### 课程购买

![](./pictures/课程购买.jpg)

![](./pictures/课程购买二维码.jpg)

![](./pictures/购买后状态.jpg)



## 项目环境搭建

1. 建立自己的数据库`oes`，也可以取其他名字，需要在`application.properties`中进行相应更改
2. 将阿里云`oss`和`vod`修改为自己的`oss`和`vod`账号，并且将`service_sms`模块中的腾讯云账号修改为自己的腾讯云`key`和`secret`
3. 启动`nacos`和`redis`服务
4. 开启`OnlineEducationSystem`项目的所有微服务`CanalApplication`不用启动
5. 后台启动则：到`OnlineEducationSystem-Admin`中`npm install;npm run dev`
6. 同理前台启用也是一样



## 目前已知项目bug点

2. 前后台的提示信息很多时候不显示



## 项目总结

通过这次项目，我学会到了许多东西，比如spring security。

但本项目还有需要不足或者说要优化的地方比如：

1. 在添加章节和小节的时候没有和课时数相判断
2. spring security没有做详细的鉴权
3. 前后台的提示信息很多时候不显示
4. 前台代码还有很多需要修改的地方

这些问题将在后期系统学习了`vue`和`spring框架`之后再回来进行更改