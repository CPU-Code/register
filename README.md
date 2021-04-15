# 网上医院预约挂号系统

# 1. 项目介绍

网上医院预约挂号系统是近年来开展的一项便民就医服务，
旨在缓解看病难、挂号难的就医难题，许多患者为看一次病要跑很多次医院，最终还不一定能保证看得上医生。
网上预约挂号全面提供的预约挂号业务从根本上解决了这一就医难题。随时随地轻松挂号！不用排长队！

其中包含**后台管理系统**和**前台用户系统**，采用**前后端分离开发模式**。
项目技术应用广泛，涵盖微服务、全栈、集群、分布式、高并发；


# 2.总体流程


<img src="https://gitee.com/cpu_code/picture_bed/raw/master/20210415095114.png"/>



# 3. 功能摘要

## 项目功能总结（后台管理系统）

### 医院设置管理

* 医院设置列表、添加、锁定、删除
* 医院列表、详情、排班、下线

### 数据管理
* 数据字典树形显示、导入、导出

### 用户管理

* 用户列表、查看、锁定
* 认证用户审批

### 订单管理

* 订单列表、详情

### 统计管理

* 预约统计

## 项目功能总结（前台用户系统）

### 1、首页数据显示
* 医院列表

### 2、医院详情显示
* 医院科室显示

### 3、用户登录功能
* 手机号登录（短信验证码发送）
* 微信扫描登录

### 4、用户实名认证

### 5、就诊人管理
* 列表、添加、详情、删除

### 6、预约挂号功能
* 排班和挂号详情信息
* 确认挂号信息
* 生成预约挂号订单
* 挂号订单支付（微信）
* 取消预约订单

### 7、就医提醒功能


# 项目技术点摘要

## 项目技术点总结（后端技术）


### 1、SpringBoot

简化新Spring应用的初始搭建以及开发过程

### 2、SpringCloud

基于Spring Boot实现的云原生应用开发工具，
SpringCloud使用的技术：（
SpringCloudGateway、
Spring Cloud Alibaba Nacos、
Spring Cloud Alibaba Sentinel、
SpringCloud Task和
SpringCloudFeign等）

* Nacos注册中心
* Feign
* GateWay

### 3、Redis

内存缓存

* 使用Redis作为缓存
* 验证码有效时间、支付二维码有效时间


### Mysql

关系型数据库

### Swagger2

Api接口文档工具

### HTTPClient:

Http协议客户端

### Lombok


### 4、MongoDB

面向文档的NoSQL数据库

* 使用MongoDB存储 医院相关数据

### 5、EasyExcel
* 操作excel表格，进行读和写操作

### 6、MyBatisPlus

持久层框架

### 7、RabbitMQ

消息中间件

* 订单相关操作，发送mq消息

### 8、Docker

容器技术

* 下载镜像 docker pull 
* 创建容器 docker run

### 9、阿里云OSS

### 10、阿里云短信服务

### 11、微信登录/支付

### 12、定时任务


## 项目技术点总结（前端技术）

### 1、vue

web 界面的渐进式框架

* 指令

### 2、Element-ui

### 3、nuxt

### 4、npm

### 5、ECharts

### Node.js

JavaScript 运行环境

### Axios

Axios 是一个基于 promise 的 HTTP 库

### NPM

包管理器

### Babel

转码器

### Webpack

打包工具

### Nginx

负载均衡

### Git

代码管理工具 