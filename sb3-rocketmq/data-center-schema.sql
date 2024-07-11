use datacenter;

drop table if exists t_data_center_metadata;
create table t_data_center_metadata
(
    id            bigint(20)   not null auto_increment comment '主键id',
    rule_id       bigint(20)   not null comment '规则表主键id',
    en_name       varchar(64)  not null comment '指标英文标识',
    zh_name       varchar(64)  not null comment '指标中文标识',
    default_show  bit(1)       not null default b'1' comment '指标默认是否展示',
    storage       bit(1)       not null default b'1' comment '是否存储当前指标',
    dimension     bit(1)       not null default b'1' comment '当前指标是否和维度有关',
    index_period  bit(1)       not null default b'1' comment '指标周期,当前指标是否和统计周期有关',
    data_type     varchar(64)  not null comment '指标数据类型',
    source_type   tinyint(4)   not null comment '指标类型 1:抽取类型 2:计算类型',
    life_cycle_id int(11)      not null comment '生命周期规则id',
    trace_id      varchar(64)  not null comment '跟踪uuid',
    data_security int(11)      not null comment '数据安全信息字段id',
    extra_width   smallint(11) not null default '0' comment '前端展示宽度',
    description   varchar(64)  not null comment '指标描述',
    index_sort    smallint(11) not null default '0' comment '指标排序',
    base_value    int(11)      not null default '10' comment '基准值,用来做展示数据',
    gmt_create    datetime     not null default current_timestamp comment '创建时间',
    gmt_modified  datetime     not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    status        tinyint(1)   not null default '1' comment '1-未删除 0-已删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
    comment '元数据表';

drop table if exists t_data_center_collect;
create table t_data_center_collect
(
    id             bigint(20)   not null auto_increment comment '主键id',
    rule_id        bigint(20)   not null comment '规则表主键id',
    en_name        varchar(64)  not null comment '指标英文标识',
    data_source    varchar(128) not null comment '采集数据源',
    collect_way    tinyint(1)   not null default '1' comment '采集方式,1-sql方式采集,2-接口方式采集',
    content        varchar(255) not null comment '根据 collect_way配置,1-sql方式采集 则表示sql脚本,2-接口方式采集,表示接口url',
    duplicate_flag bit(1)       not null default b'0' comment '是否去重 1-去重,0-不去重',
    period_format  varchar(64)  not null comment '统计周期格式',
    gmt_create     datetime     not null default current_timestamp comment '创建时间',
    gmt_modified   datetime     not null default current_timestamp ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    status         tinyint(1)   not null default '1' comment '1-未删除 0-已删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
    comment '采集表';


drop table if exists t_data_center_calculate;
create table t_data_center_calculate
(
    id           bigint(20)   not null auto_increment comment '主键id',
    rule_id      bigint(20)   not null comment '规则表主键id',
    en_name      varchar(64)  not null comment '指标英文标识',
    parameters   varchar(255) NOT NULL DEFAULT '' COMMENT '指标计算时的参数，可以指定多个en_name，用英文逗号分隔',
    func         varchar(64)  NOT NULL DEFAULT '' COMMENT '指标计算函数，内置几个 1：sum 累加 2：deduction 相减 3.multiply 相乘 4：ratio 相除 5: avg 平均数',
    expression   varchar(255) NOT NULL COMMENT '指标计算表达式',
    gmt_create   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    status       tinyint(4)   NOT NULL DEFAULT '1' COMMENT '状态：1未删除 0删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
    comment '计算表';

drop table if exists t_data_center_storage;
CREATE TABLE t_data_center_storage
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT,
    rule_id      bigint(20)   NOT NULL COMMENT '规则表主键id',
    data_source  varchar(128) NOT NULL DEFAULT 'data-center' COMMENT '存储当前规则下的指标所用的数据源，默认是data-center',
    table_prefix varchar(64)  NOT NULL DEFAULT 't_generate_' COMMENT '自动生成表前缀，默认为t_generate_，生成表的规则：t_generate_{en_name}_{dimension}_{date}',
    gmt_create   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    status       tinyint(4)   NOT NULL DEFAULT '1' COMMENT '状态 1未删除 0 删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
    comment '存储表';

drop table if exists t_data_center_organ;
CREATE TABLE t_data_center_organ
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT,
    rule_id      bigint(20)   NOT NULL COMMENT '规则表主键id',
    collect_way  tinyint(4)   NOT NULL DEFAULT '1' COMMENT '抽取组织方式 1：sql抽取 2：接口抽取',
    content      varchar(255) NOT NULL DEFAULT '1' COMMENT 'collec_way抽取组织方式 1：sql抽取  sql脚本 2：接口抽取 url http地址',
    data_source  varchar(64)  NOT NULL DEFAULT '' COMMENT '抽取组织的数据源',
    gmt_create   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    status       tinyint(4)   NOT NULL DEFAULT '1' COMMENT '状态 1未删除 0删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 comment '组织表';


drop table if exists t_data_center_rule;
CREATE TABLE t_data_center_rule
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键id',
    business_id   varchar(255) NOT NULL COMMENT '业务id，兼容不同系统生成id的规则可能是不一样的',
    business_name varchar(128) NOT NULL COMMENT '业务名称',
    en_name       varchar(64)  NOT NULL COMMENT '业务英文标识',
    rule_name     varchar(64)  NOT NULL COMMENT '当前规则名称',
    rule_type     varchar(64)  NOT NULL COMMENT '规则类型，兼容采集规则、查询规则',
    version       varchar(64)  NOT NULL COMMENT '版本好，如果需要规则变更，直接创建一个新规则，将版本号递增',
    gmt_create    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    status        tinyint(4)   NOT NULL DEFAULT '1' COMMENT '1未删除 0删除',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 comment '规则表';