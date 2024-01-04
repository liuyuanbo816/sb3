create table tb_eqb_test
(
    id   bigint(11) primary key auto_increment comment '主键',
    name varchar(60) not null comment '名字'
);

create table tb_isale_test
(
    id  bigint(11) primary key auto_increment comment '主键',
    age int not null comment '年龄'
);

create table tb_esb_test
(
    id    bigint(11) primary key auto_increment comment '主键',
    email varchar(60) not null comment '邮箱'
);