create table tb_person
(
    id    bigint(11) primary key auto_increment comment '主键',
    name  varchar(60)  not null comment '名字',
    age   int(3) not null comment '年龄',
    email varchar(120) not null comment '邮箱'
);

