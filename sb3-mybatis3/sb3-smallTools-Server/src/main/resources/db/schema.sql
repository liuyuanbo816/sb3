DROP TABLE IF EXISTS tb_person;
CREATE TABLE tb_person
(
    id    bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    name  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '名字',
    age   int(3) NOT NULL COMMENT '年龄',
    email varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;