DROP TABLE IF EXISTS user;

CREATE TABLE user(
                     id    INT(20) NOT NULL COMMENT '主键ID',
                     name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
                     age   INT(11) NULL DEFAULT NULL COMMENT '年龄',
                     gender   INT(2) NULL DEFAULT NULL COMMENT '性别',
                     PRIMARY KEY (id)
);