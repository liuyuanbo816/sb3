create table test_bl.test_bl.tmp_22299170_19_1_${acct}

-- 所有层级当月
select tb2.area_id                                     as consume_area_id,
       tb2.area_name                                   as consume_area_name,
       2                                               as consume_area_level,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(SUM(tb1.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb1
         inner join test_bl.dim_org tb2 on tb1.area_id = tb2.area_id
where (tb1.month_id = ${month_id})
group by tb2.area_id, tb2.area_name

union all

select tb23.sum_area_id                                 as consume_area_id,
       tb23.sum_area_name                               as consume_area_name,
       1                                                as consume_area_level,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(SUM(tb22.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb22
         inner join test_bl.dim_org tb23 on tb22.area_id = tb23.area_id
where (tb22.month_id = ${month_id})
group by tb23.sum_area_id, tb23.sum_area_name

-- 所有层级上月
create table test_bl.test_bl.tmp_22299170_19_2_${acct

select tb4.area_id   as consume_area_id,
       tb4.area_name as consume_area_name,
       2             as consumne_area_level,
       ROUND(coalesce(SUM(tb1.income),
                      0),
             2)      as income_sum,
       ROUND(coalesce(SUM(tb1.income),
                      0),
             2)      as income_pp1,
       ROUND(coalesce(SUM(tb1.income),
                      0),
             2)      as income_momgrowth1,
       ROUND(coalesce(SUM(tb1.salary),
                      0),
             2)      as salary_sum,
       ROUND(coalesce(SUM(tb1.salary),
                      0),
             2)      as salary_pp1,
       ROUND(coalesce(SUM(tb1.salary),
                      0),
             2)      as salary_momgrowth1
from test_bl.wid_org_index tb3
         inner join test_bl.dim_org tb4 on
    tb3.team_id = tb4.area_id
where (cast(to_char(to_date(cast(tb3.month_id as varchar),
                            'yyyyMM') - interval '-1 month',
                    'yyyyMM') as VARCHAR) = '${month_id}')
group by tb4.area_id,
         tb4.area_name
union all
select tb13.sum_area_id   as consume_area_id,
       tb13.sum_area_name as consume_area_name,
       1                  as consumne_area_level,
       ROUND(coalesce(SUM(tb10.income),
                      0),
             2)           as income_sum,
       ROUND(coalesce(SUM(tb10.income),
                      0),
             2)           as income_pp1,
       ROUND(coalesce(SUM(tb10.income),
                      0),
             2)           as income_momgrowth1,
       ROUND(coalesce(SUM(tb10.salary),
                      0),
             2)           as salary_sum,
       ROUND(coalesce(SUM(tb10.salary),
                      0),
             2)           as salary_pp1,
       ROUND(coalesce(SUM(tb10.salary),
                      0),
             2)           as salary_momgrowth1
from test_bl.wid_org_index tb12
         inner join test_bl.dim_org tb13 on
    tb12.team_id = tb13.area_id
where (cast(to_char(to_date(cast(tb12.month_id as varchar),
                            'yyyyMM') - interval '-1 month',
                    'yyyyMM') as VARCHAR) = '${month_id}')
group by tb13.sum_area_id,
         tb13.sum_area_name;
-- 所有层级汇总
create table test_bl.tmp_22299170_19_${acct} as
select tb7.consume_area_id,
       tb7.consume_area_name,
       tb7.consume_area_level,
       tb7.income_sum as income_sum,
       round(coalesce((tb7.income_pp1 - tb8.income_pp1),
                      0),
             2)       as income_pp1,
       round(coalesce((case
                           when tb9.income_momgrowth1 = 0 then 0
                           else (tb7.income_momgrowth1 - tb9.income_momgrowth1) / tb9.income_momgrowth1
           end),
                      0),
             2)       as income_momgrowth1,
       tb7.salary_sum as salary_sum,
       round(coalesce((tb7.salary_pp1 - tb8.salary_pp1),
                      0),
             2)       as salary_pp1,
       round(coalesce((case
                           when tb9.salary_momgrowth1 = 0 then 0
                           else (tb7.salary_momgrowth1 - tb9.salary_momgrowth1) / tb9.salary_momgrowth1
           end),
                      0),
             2)       as salary_momgrowth1
from test_bl.tmp_22299170_19_1_${acct} tb7
         left join test_bl.tmp_22299170_19_1_${acct} tb8 on
    tb7.consume_area_id = tb8.consume_area_id
        and tb7.consume_area_name = tb8.consume_area_name;
-- 所有层级指标1写入指标衍生纵表
insert into table test_bl.dim_index_calculate_m (month_id,dimension_id,dimension_name,dimension_level,index_code, index_value,index_value_pp,index_value_momgrowth)
select ${month_id}        as month_id,
       t1.consume_area_id as consume_area_id /*area_id/
,
t1.consume_area_name as consume_area_name /*area_name/
,
t1.consume_area_level as consume_area_level /*area_level/
,
'income',
SUM(t1.income_sum) as income_sum /*收支/
,
SUM(t1.income_pp1) as income_pp1 /*收支1/
,
SUM(t1.income_momgrowth1) as income_momgrowth1 /*收支2/
from test_bl.tmp_22299170_19_${acct} t1
group by
t1.consume_area_id,
t1.consume_area_name;
-- 所有层级指标2写入指标衍生纵表
insert into table test_bl.dim_index_calculate_m
(month_id,dimension_id,dimension_name,dimension_level,index_code, index_value,index_value_pp,index_value_momgrowth)
select
${month_id} as month_id,
t1.consume_area_id as consume_area_id /*area_id/
,
t1.consume_area_name as consume_area_name /*area_name/
,
t1.consume_area_level as consume_area_level /*area_level/
,
'salary',
SUM(t1.salary_sum) as salary_sum /*收入/
,
SUM(t1.salary_pp1) as salary_pp1 /*收入1/
,
SUM(t1.salary_momgrowth1) as salary_momgrowth1 /*收入2/
from test_bl.tmp_22299170_19_${acct} t1
group by
t1.consume_area_id,
t1.consume_area_name;