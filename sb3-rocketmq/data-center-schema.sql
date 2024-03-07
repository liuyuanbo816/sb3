create table t_data_center_metadata
(
    id            bigint(20) not null primary key auto_increment comment '主键id',
    rule_id       bigint(20) not null comment '规则表主键id',
    en_name       varchar(64) not null comment '指标英文标识',
    zh_name       varchar(64) not null comment '指标中文标识',
    default_show  bit(1)      not null default b'1' comment '指标默认是否展示',
    storage       bit(1)      not null default b'1' comment '是否存储当前指标',
    dimension     bit(1)      not null default b'1' comment '当前指标是否和维度有关',
    period bit (1) not null default b'1' comment '当前指标是否和统计周期有关',
    data_type     varchar(64) not null comment '指标数据类型',
    source_type   tinyint(4) not null comment '指标类型 1:抽取类型 2:计算类型',
    life_cycle_id int(11) not null comment '生命周期规则id',
    trace_id      varchar(64) not null comment '跟踪uuid',
    data_security int(11) not null comment '数据安全信息字段id',
    extra_width   smallint(11) not null default '0' comment '前端展示宽度',
    description   varchar(64) not null comment '指标描述',
    rank          smallint(11) not null default '0' comment '指标排序',
    base_value    int(11) not null default '10' comment '基准值,用来做展示数据',
    gmt_created   datetime    not null default current_timestamp comment '创建时间',
    gmt_modified  datetime    not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    status        tinyint(1) not null default '1' comment '1-未删除 0-已删除'
);

create table t_data_center_collect
(
    id             bigint(20) not null primary key auto_increment comment '主键id',
    rule_id        bigint(20) not null comment '规则表主键id',
    en_name        varchar(64)  not null comment '指标英文标识',
    data_source    varchar(128) not null comment '采集数据源',
    collect_way    tinyint(1) not null default '1' comment '采集方式,1-sql方式采集,2-接口方式采集',
    content        varchar(255) not null comment '根据 collect_way配置,1-sql方式采集 则表示sql脚本,2-接口方式采集,表示接口url',
    duplicate_flag bit(1)       not null default b'0' comment '是否去重 1-去重,0-不去重',
    period_format  varchar(64)  not null comment '统计周期格式',
    gmt_created    datetime     not null default current_timestamp comment '创建时间',
    gmt_modified   datetime     not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    status         tinyint(1) not null default '1' comment '1-未删除 0-已删除'
);