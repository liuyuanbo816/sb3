use mytest;

create table tb1(
    id bigint(20) not null primary key auto_increment,
    name varchar(120) not null,
    birth datetime not null
);