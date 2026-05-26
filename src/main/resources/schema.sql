-- 创建数据库
CREATE DATABASE IF NOT EXISTS sy4 DEFAULT CHARACTER SET utf8mb4;

USE sy4;

-- 用户表
CREATE TABLE IF NOT EXISTS user (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    login_name  VARCHAR(50)  NOT NULL UNIQUE COMMENT '登录名',
    password    VARCHAR(100) NOT NULL COMMENT '密码',
    name        VARCHAR(50)  NOT NULL COMMENT '真实姓名',
    phone       VARCHAR(20)  COMMENT '电话',
    email       VARCHAR(100) COMMENT '邮箱',
    type        INT DEFAULT 1 COMMENT '0=管理员 1=普通用户'
);

-- 设备表
CREATE TABLE IF NOT EXISTS device (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(50)  NOT NULL UNIQUE COMMENT '设备编码',
    name        VARCHAR(100) NOT NULL COMMENT '设备名称',
    description VARCHAR(500) COMMENT '设备描述',
    user_id     INT COMMENT '领用人ID',
    FOREIGN KEY (user_id) REFERENCES user(id)
);
