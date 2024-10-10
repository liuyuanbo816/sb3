select tb20.consume_org_id          as consume_org_id,
       tb20.consume_path_code       as consume_path_code,
       tb20.team_name               as team_name,
       tb20.lant_name               as lant_name,
       tb20.sum_charge_flh_1590_sum as sum_charge_flh_1590_sum,
       tb20.call_cnt_sum            as call_cnt_sum
from (select tb19.consume_org_id,
             tb19.consume_path_code                                                as consume_path_code,
             tb15.team_name                                                        as team_name,
             tb18.lant_name                                                        as lant_name,
             ROUND(COALESCE(SUM(CAST(tb13.SUM_charge_flh_1590 AS decimal)), 0), 2) as sum_charge_flh_1590_sum,
             0                                                                     as call_cnt_sum
      from test_bl.fct_wid_0013050586 tb13
               inner join test_bl.dim_zd_org_branch_tree_path_cude tb14 on tb13.team_id = tb14.area_id
               inner join test_bl.dim_org_branch_team_cube as tb15 on tb13.team_id = tb15.team_id
               inner join test_bl.dim_zd_org_branch_tree_path_cude tb16
                          on tb15.team_id = tb16.area_id test_bl.dim_org_branch_sup_cube tb17 inner join test_bl.dim_org_branch_lant_cube as tb18
      on tb17.lant_id = tb18.lant_id inner join test_bl.tmp_46369266_6 tb19 on tb13.team_id = tb19.team_id
      where tb14.path_code like '%$orgId%' and tb16.path_code like '%$orgId%' and ( tb13.month_id =${month_id} )
      group by tb19.consume_org_id, tb19.consume_path_code, tb15.team_name, tb18.lant_name) tb20



CREATE TABLE `meta_dataset`
(
    `I_ID`                       bigint(20) NOT NULL COMMENT '编号',
    `S_META_CODE`                varchar(200) NOT NULL COMMENT '元数据编码，非空唯一',
    `S_META_VERSION`             varchar(64)    DEFAULT NULL COMMENT '元数据版本，每次更新后自动更新',
    `I_TEMPLET_ID`               bigint(20) DEFAULT NULL COMMENT '元数据类型，关联META_TEMPLET表主键',
    `S_TEMPLET_CODE`             varchar(128)   DEFAULT NULL COMMENT '元数据类型编码，关联META_TEMPLET表TEMPLET_CODE字段',
    `S_TEMPLET_SMALL_TYPE`       varchar(32)    DEFAULT NULL COMMENT '元数据类型子分类，用于区分同元数据类型的子分类',
    `S_META_PARENT_CODE`         varchar(200)   DEFAULT NULL,
    `S_NAME`                     varchar(1024)  DEFAULT NULL COMMENT '元数据中文名称',
    `S_EN_NAME`                  varchar(256)   DEFAULT NULL COMMENT '元数据英文名称，非空',
    `I_ASSET_TYPE`               bigint(20) DEFAULT NULL COMMENT '目录主键',
    `S_LAYER`                    varchar(32)    DEFAULT NULL COMMENT '元数据分层',
    `S_DOMAIN`                   varchar(32)    DEFAULT NULL COMMENT 'S_DOMAIN',
    `S_SUB_DOMAIN`               varchar(32)    DEFAULT NULL COMMENT '元数据子域',
    `D_META_CREATE_TIME`         datetime       DEFAULT NULL COMMENT '实际创建时间',
    `D_META_UPDATE_TIME`         datetime       DEFAULT NULL COMMENT '实际更新时间',
    `S_DESC`                     varchar(1024)  DEFAULT NULL COMMENT '元数据描述',
    `I_STATUS`                   decimal(2, 0)  DEFAULT NULL COMMENT '状态 0：正常；1：删除；2：下线',
    `I_CREATE_STAFF_ID`          decimal(16, 0) DEFAULT NULL COMMENT '创建人员ID',
    `S_CREATE_STAFF_NAME`        varchar(64)    DEFAULT NULL COMMENT '创建人员名称',
    `D_CREATE_TIME`              datetime       DEFAULT NULL COMMENT '创建时间',
    `I_UPDATE_STAFF_ID`          decimal(16, 0) DEFAULT NULL COMMENT '更新人员ID',
    `S_UPDATE_STAFF_NAME`        varchar(64)    DEFAULT NULL COMMENT '更新人员名称',
    `D_UPDATE_TIME`              datetime       DEFAULT NULL COMMENT '更新时间',
    `I_MODIFY_STATUS`            decimal(2, 0)  DEFAULT NULL COMMENT '修改状态。1：新增；2：修改；3：删除',
    `S_COLLECT_ID`               varchar(255) NOT NULL COMMENT '元数据采集标识，唯一',
    `S_PARENT_COLLECT_ID`        varchar(512)   DEFAULT NULL COMMENT '元数据采集父标识，用于父子元数据关联',
    `I_SOURCE_TYPE`              decimal(1, 0)  DEFAULT NULL COMMENT '数据来源，标识元数据录入方式。1：模型设计工具；2：采集；',
    `S_SOURCE_META_CODE`         varchar(128)   DEFAULT NULL COMMENT '来源数据编码',
    `S_SYSTEM_CODE`              varchar(128)   DEFAULT NULL COMMENT '归属系统',
    `S_DEMANDER`                 varchar(64)    DEFAULT NULL COMMENT '需求者',
    `S_MANAGER`                  varchar(64)    DEFAULT NULL COMMENT '管理者',
    `S_DEVELOPER`                varchar(64)    DEFAULT NULL COMMENT '开发者',
    `S_PUBLISHER`                varchar(64)    DEFAULT NULL COMMENT '发布者',
    `S_OPERATOR`                 varchar(64)    DEFAULT NULL COMMENT '运维者',
    `I_ACC_COM_ID`               decimal(10, 0) DEFAULT NULL COMMENT '用于关联企业用户ID过滤数据',
    `S_EXTEND_FIELD`             longtext COMMENT '元数据扩展字段',
    `S_EXTEND_STRING1`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING2`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING3`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING4`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING5`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING6`           char(32)       DEFAULT NULL COMMENT 'S_META_PARENT_CODE(MD5值)',
    `S_EXTEND_STRING7`           varchar(255)   DEFAULT NULL COMMENT '备份字段字符串',
    `S_EXTEND_STRING8`           char(32)       DEFAULT NULL COMMENT 'S_META_CODE(MD5值)',
    `S_EXTEND_STRING9`           varchar(255)   DEFAULT NULL COMMENT '目录转换md5值',
    `I_EXTEND_NUMBER1`           bigint(20) DEFAULT NULL,
    `I_EXTEND_NUMBER2`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER3`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER4`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER5`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER6`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER7`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER8`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `I_EXTEND_NUMBER9`           decimal(15, 0) DEFAULT NULL COMMENT '备份字段数字',
    `D_EXTEND_DATE1`             datetime       DEFAULT NULL COMMENT '备份字段时间',
    `D_EXTEND_DATE2`             datetime       DEFAULT NULL COMMENT '备份字段时间',
    `D_EXTEND_DATE3`             datetime       DEFAULT NULL COMMENT '备份字段时间',
    `D_EXTEND_DATE4`             datetime       DEFAULT NULL COMMENT '备份字段时间',
    `D_EXTEND_DATE5`             datetime       DEFAULT NULL COMMENT '备份字段时间',
    `meta_from`                  char(1)        DEFAULT NULL COMMENT '0元数据录入',
    `grading_filing_system_name` varchar(255)   DEFAULT NULL COMMENT '定级备案系统名称',
    `professional_code`          varchar(20)    DEFAULT NULL COMMENT '所属专业',
    `i_lost_field`               int(11) DEFAULT '0' COMMENT '必填字段是否缺失： 0否，1是',
    `s_lost_field_detail`        text COMMENT '必填字段缺失明细',
    `data_warehouse_id`          int(10) DEFAULT NULL COMMENT '数仓id',
    `data_warehouse_name`        varchar(255)   DEFAULT NULL COMMENT '数仓名称',
    `I_SOURE_PK_ID`              varchar(128)   DEFAULT NULL COMMENT '源系统业务主键',
    `I_ASSET_TYPE_LOGIC`         bigint(20) DEFAULT NULL COMMENT '逻辑目录',
    `I_ASSET_TYPE_SDEFINE`       bigint(20) DEFAULT NULL COMMENT '自定义目录',
    PRIMARY KEY (`I_ID`),
    KEY                          `asset_type` (`I_ASSET_TYPE`) COMMENT '数据目录id',
    KEY                          `TEMPLET_CODE` (`S_TEMPLET_CODE`) USING BTREE COMMENT '元数据类型',
    KEY                          `IDX_dataset_META_CODE` (`S_META_CODE`(191)),
    KEY                          `IDX_dataset_META_PARENT_CODE` (`S_META_PARENT_CODE`(191)),
    KEY                          `IDX_dataset_COLLECT_ID` (`S_COLLECT_ID`(191)),
    KEY                          `IDX_dataset_PARENT_COLLECT_ID` (`S_PARENT_COLLECT_ID`(191)),
    KEY                          `S_EXTEND_STRING8_INDEX` (`S_EXTEND_STRING8`),
    KEY                          `S_EXTEND_STRING6_INDEX` (`S_EXTEND_STRING6`),
    KEY                          `idx_asset_code_status_acc` (`I_ASSET_TYPE`,`S_TEMPLET_CODE`,`I_STATUS`,`I_ACC_COM_ID`),
    KEY                          `IDX_dataset_I_TEMPLET_ID` (`I_TEMPLET_ID`),
    KEY                          `S_EXTEND_STRING1_INDEX` (`S_EXTEND_STRING1`(191)),
    KEY                          `S_EXTEND_STRING9_INDEX` (`S_EXTEND_STRING9`(191)),
    KEY                          `I_EXTEND_NUMBER1_INDEX` (`I_EXTEND_NUMBER1`),
    KEY                          `idx_dataset_datawid` (`data_warehouse_id`),
    KEY                          `idx_status_com_type` (`I_STATUS`,`I_ACC_COM_ID`,`I_ASSET_TYPE`),
    KEY                          `IDX_S_SYSTEM_CODE` (`S_SYSTEM_CODE`),
    KEY                          `IDX_CREATE_STAFF` (`I_CREATE_STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元数据统一库（正式）'