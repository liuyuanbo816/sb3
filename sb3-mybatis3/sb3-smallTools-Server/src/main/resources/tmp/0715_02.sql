select tb3.consume_area_id,
       tb3.consume_area_name,
       tb3.consume_area_level,
       tb3.consume_area_id,
       tb3.consume_area_name,
       tb3.consume_area_level,
       tb3.nm_sum_fee_1694               as nm_sum_fee_1694,
       tb3.nm_sum_fee_1694_pp            as nm_sum_fee_1694_pp,
       tb3.nm_sum_fee_1694_momGrowth     as nm_sum_fee_1694_momGrowth,
       tb3.nm_sum_fee_1694_yoy           as nm_sum_fee_1694_yoy,
       tb3.nm_sum_fee_1694_yoyGrowth     as nm_sum_fee_1694_yoyGrowth,
       tb3.nm_sum_fee_1694_yearEnd       as nm_sum_fee_1694_yearEnd,
       tb3.nm_sum_fee_1694_yearEndGrowth as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb3



select tb18.consume_area_id,
       tb18.consume_area_name,
       tb18.consume_area_level,
       tb18.consume_area_id,
       tb18.consume_area_name,
       tb18.consume_area_level,
       tb18.nm_sum_fee_1694               as nm_sum_fee_1694,
       tb18.nm_sum_fee_1694_pp            as nm_sum_fee_1694_pp,
       tb18.nm_sum_fee_1694_momGrowth     as nm_sum_fee_1694_momGrowth,
       tb18.nm_sum_fee_1694_yoy           as nm_sum_fee_1694_yoy,
       tb18.nm_sum_fee_1694_yoyGrowth     as nm_sum_fee_1694_yoyGrowth,
       tb18.nm_sum_fee_1694_yearEnd       as nm_sum_fee_1694_yearEnd,
       tb18.nm_sum_fee_1694_yearEndGrowth as nm_sum_fee_1694_yearEndGrowth
from (select tb5.sum_area_id                                 as consume_area_id,
             tb5.sum_area_name                               as consume_area_name,
             1                                               as consume_area_level,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
             ROUND(COALESCE(SUM(tb4.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
      from test_bl.fct_wid_index tb4
               inner join test_bl.dim_org tb5 on tb4.area_id = tb5.area_id
      where (tb4.month_id = ${month_id})
      group by tb5.sum_area_id, tb5.sum_area_name) tb18



select tb3.consume_area_id,
       tb3.consume_area_name,
       tb3.consume_area_level,
       tb3.consume_area_id,
       tb3.consume_area_name,
       tb3.consume_area_level,
       tb3.nm_sum_fee_1694               as nm_sum_fee_1694,
       tb3.nm_sum_fee_1694_pp            as nm_sum_fee_1694_pp,
       tb3.nm_sum_fee_1694_momGrowth     as nm_sum_fee_1694_momGrowth,
       tb3.nm_sum_fee_1694_yoy           as nm_sum_fee_1694_yoy,
       tb3.nm_sum_fee_1694_yoyGrowth     as nm_sum_fee_1694_yoyGrowth,
       tb3.nm_sum_fee_1694_yearEnd       as nm_sum_fee_1694_yearEnd,
       tb3.nm_sum_fee_1694_yearEndGrowth as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb3


select
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb15

select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.nm_sum_fee_1694               as nm_sum_fee_1694,
       tb15.nm_sum_fee_1694_pp            as nm_sum_fee_1694_pp,
       tb15.nm_sum_fee_1694_momGrowth     as nm_sum_fee_1694_momGrowth,
       tb15.nm_sum_fee_1694_yoy           as nm_sum_fee_1694_yoy,
       tb15.nm_sum_fee_1694_yoyGrowth     as nm_sum_fee_1694_yoyGrowth,
       tb15.nm_sum_fee_1694_yearEnd       as nm_sum_fee_1694_yearEnd,
       tb15.nm_sum_fee_1694_yearEndGrowth as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb15



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


CREATE TABLE test_bl.tmp_24335614_5_${acct} AS
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
group by tb2.area_id, tb2.area_name;
CREATE TABLE test_bl.tmp_48169410_6_${acct} AS
select tb4.sum_area_id                                 as consume_area_id,
       tb4.sum_area_name                               as consume_area_name,
       1                                               as consume_area_level,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
       ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
from test_bl.fct_wid_index tb3
         inner join test_bl.dim_org tb4 on tb3.area_id = tb4.area_id
where (tb3.month_id = ${month_id})
group by tb4.sum_area_id, tb4.sum_area_name;
select ${month_id}                           as month_id,
       t1.consume_area_id                    as consume_area_id/**area_id*/,
       t1.consume_area_name                  as consume_area_name/**area_name*/,
       t1.consume_area_level                 as consume_area_level,
       SUM(t1.nm_sum_fee_1694)               as nm_sum_fee_1694/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_pp)            as nm_sum_fee_1694_pp/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_momGrowth)     as nm_sum_fee_1694_momGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoy)           as nm_sum_fee_1694_yoy/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoyGrowth)     as nm_sum_fee_1694_yoyGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEnd)       as nm_sum_fee_1694_yearEnd/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEndGrowth) as nm_sum_fee_1694_yearEndGrowth/**区域nm支出求和*/
from (select * from test_bl.tmp_24335614_5_${acct} union all select * from test_bl.tmp_48169410_6_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_area_level;
test_bl
.
tmp_24335614_5_
${acct}
,test_bl.tmp_48169410_6_
${acct}



select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.nm_sum_fee_1694                                                       as nm_sum_fee_1694,
       round(COALESCE((tb15.nm_sum_fee_1694_pp - tb17.nm_sum_fee_1694_pp), 0), 2) as nm_sum_fee_1694_pp,
       round(COALESCE((case
                           when tb21.nm_sum_fee_1694_momGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_momGrowth - tb21.nm_sum_fee_1694_momGrowth) /
                                tb21.nm_sum_fee_1694_momGrowth end), 0), 2)       as nm_sum_fee_1694_momGrowth,
       round(COALESCE((case
                           when tb18.nm_sum_fee_1694_yoy = 0 then 0
                           else (tb15.nm_sum_fee_1694_yoy - tb18.nm_sum_fee_1694_yoy) / tb18.nm_sum_fee_1694_yoy end),
                      0), 2)                                                      as nm_sum_fee_1694_yoy,
       round(COALESCE((case
                           when tb19.nm_sum_fee_1694_yoyGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_yoyGrowth - tb19.nm_sum_fee_1694_yoyGrowth) /
                                tb19.nm_sum_fee_1694_yoyGrowth end), 0), 2)       as nm_sum_fee_1694_yoyGrowth,
       round(COALESCE((case
                           when tb20.nm_sum_fee_1694_yearEnd = 0 then 0
                           else (tb15.nm_sum_fee_1694_yearEnd - tb20.nm_sum_fee_1694_yearEnd) /
                                tb20.nm_sum_fee_1694_yearEnd end), 0), 2)         as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb16.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_yearEndGrowth - tb16.nm_sum_fee_1694_yearEndGrowth) /
                                tb16.nm_sum_fee_1694_yearEndGrowth end), 0), 2)   as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb15
         left join (select tb4.area_id                                     as consume_area_id,
                           tb4.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb3
                             inner join test_bl.dim_org tb4 on tb3.area_id = tb4.area_id
                    where (tb3.month_id = $add_month(${month_id}, -1, L))
                    group by tb4.area_id, tb4.area_name) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb6.area_id                                     as consume_area_id,
                           tb6.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb5.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb5
                             inner join test_bl.dim_org tb6 on tb5.area_id = tb6.area_id
                    where (tb5.month_id = $add_month(${month_id}, -1))
                    group by tb6.area_id, tb6.area_name) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb8.area_id                                     as consume_area_id,
                           tb8.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb7.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb7
                             inner join test_bl.dim_org tb8 on tb7.area_id = tb8.area_id
                    where (tb7.month_id = $add_month(${month_id}, -1, 0))
                    group by tb8.area_id, tb8.area_name) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name
         left join (select tb10.area_id                                    as consume_area_id,
                           tb10.area_name                                  as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           ROUND(COALESCE(SUM(tb9.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb9
                             inner join test_bl.dim_org tb10 on tb9.area_id = tb10.area_id
                    where (tb9.month_id = $add_month(${month_id}, -1, 0))
                    group by tb10.area_id, tb10.area_name) tb19
                   on tb15.consume_area_id = tb19.consume_area_id and tb15.consume_area_name = tb19.consume_area_name
         left join (select tb12.area_id                                     as consume_area_id,
                           tb12.area_name                                   as consume_area_name,
                           2                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           ROUND(COALESCE(SUM(tb11.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb11
                             inner join test_bl.dim_org tb12 on tb11.area_id = tb12.area_id
                    where (tb11.month_id = $add_month(${month_id}, -1, L))
                    group by tb12.area_id, tb12.area_name) tb20
                   on tb15.consume_area_id = tb20.consume_area_id and tb15.consume_area_name = tb20.consume_area_name
         left join (select tb14.area_id                                     as consume_area_id,
                           tb14.area_name                                   as consume_area_name,
                           2                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           ROUND(COALESCE(SUM(tb13.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb13
                             inner join test_bl.dim_org tb14 on tb13.area_id = tb14.area_id
                    where (tb13.month_id = $add_month(${month_id}, -1))
                    group by tb14.area_id, tb14.area_name) tb21
                   on tb15.consume_area_id = tb21.consume_area_id and tb15.consume_area_name = tb21.consume_area_name


------------------------------------------------------------------------------------------------------------------------
CREATE TABLE test_bl.tmp_25291667_37_${acct} AS
select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.nm_sum_fee_1694                                                       as nm_sum_fee_1694,
       round(COALESCE((tb15.nm_sum_fee_1694_pp - tb17.nm_sum_fee_1694_pp), 0), 2) as nm_sum_fee_1694_pp,
       0                                                                          as nm_sum_fee_1694_momGrowth,
       round(COALESCE((case
                           when tb18.nm_sum_fee_1694_yoy = 0 then 0
                           else (tb15.nm_sum_fee_1694_yoy - tb18.nm_sum_fee_1694_yoy) / tb18.nm_sum_fee_1694_yoy end),
                      0), 2)                                                      as nm_sum_fee_1694_yoy,
       0                                                                          as nm_sum_fee_1694_yoyGrowth,
       0                                                                          as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb16.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_yearEndGrowth - tb16.nm_sum_fee_1694_yearEndGrowth) /
                                tb16.nm_sum_fee_1694_yearEndGrowth end), 0), 2)   as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
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
      group by tb2.area_id, tb2.area_name) tb15
         left join (select tb4.area_id                                     as consume_area_id,
                           tb4.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb3
                             inner join test_bl.dim_org tb4 on tb3.area_id = tb4.area_id
                    where (tb3.month_id = $add_month(${month_id}, -1, L))
                    group by tb4.area_id, tb4.area_name) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb6.area_id                                     as consume_area_id,
                           tb6.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb5.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb5
                             inner join test_bl.dim_org tb6 on tb5.area_id = tb6.area_id
                    where (tb5.month_id = $add_month(${month_id}, -1))
                    group by tb6.area_id, tb6.area_name) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb8.area_id                                     as consume_area_id,
                           tb8.area_name                                   as consume_area_name,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb7.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb7
                             inner join test_bl.dim_org tb8 on tb7.area_id = tb8.area_id
                    where (tb7.month_id = $add_month(${month_id}, -1, 0))
                    group by tb8.area_id, tb8.area_name) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name;

CREATE TABLE test_bl.tmp_72079450_38_${acct} AS
select tb33.consume_area_id,
       tb33.consume_area_name,
       tb33.consume_area_level,
       tb33.consume_area_id,
       tb33.consume_area_name,
       tb33.consume_area_level,
       tb33.nm_sum_fee_1694                                                       as nm_sum_fee_1694,
       round(COALESCE((tb33.nm_sum_fee_1694_pp - tb35.nm_sum_fee_1694_pp), 0), 2) as nm_sum_fee_1694_pp,
       0                                                                          as nm_sum_fee_1694_momGrowth,
       round(COALESCE((case
                           when tb36.nm_sum_fee_1694_yoy = 0 then 0
                           else (tb33.nm_sum_fee_1694_yoy - tb36.nm_sum_fee_1694_yoy) / tb36.nm_sum_fee_1694_yoy end),
                      0), 2)                                                      as nm_sum_fee_1694_yoy,
       0                                                                          as nm_sum_fee_1694_yoyGrowth,
       0                                                                          as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb34.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb33.nm_sum_fee_1694_yearEndGrowth - tb34.nm_sum_fee_1694_yearEndGrowth) /
                                tb34.nm_sum_fee_1694_yearEndGrowth end), 0), 2)   as nm_sum_fee_1694_yearEndGrowth
from (select tb20.sum_area_id                                 as consume_area_id,
             tb20.sum_area_name                               as consume_area_name,
             1                                                as consume_area_level,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
             ROUND(COALESCE(SUM(tb19.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
      from test_bl.fct_wid_index tb19
               inner join test_bl.dim_org tb20 on tb19.area_id = tb20.area_id
      where (tb19.month_id = ${month_id})
      group by tb20.sum_area_id, tb20.sum_area_name) tb33
         left join (select tb22.sum_area_id                                 as consume_area_id,
                           tb22.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb21.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb21
                             inner join test_bl.dim_org tb22 on tb21.area_id = tb22.area_id
                    where (tb21.month_id = $add_month(${month_id}, -1, L))
                    group by tb22.sum_area_id, tb22.sum_area_name) tb34
                   on tb33.consume_area_id = tb34.consume_area_id and tb33.consume_area_name = tb34.consume_area_name
         left join (select tb24.sum_area_id                                 as consume_area_id,
                           tb24.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb23.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb23
                             inner join test_bl.dim_org tb24 on tb23.area_id = tb24.area_id
                    where (tb23.month_id = $add_month(${month_id}, -1))
                    group by tb24.sum_area_id, tb24.sum_area_name) tb35
                   on tb33.consume_area_id = tb35.consume_area_id and tb33.consume_area_name = tb35.consume_area_name
         left join (select tb26.sum_area_id                                 as consume_area_id,
                           tb26.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb25.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb25
                             inner join test_bl.dim_org tb26 on tb25.area_id = tb26.area_id
                    where (tb25.month_id = $add_month(${month_id}, -1, 0))
                    group by tb26.sum_area_id, tb26.sum_area_name) tb36
                   on tb33.consume_area_id = tb36.consume_area_id and tb33.consume_area_name = tb36.consume_area_name;
select ${month_id}                           as month_id,
       t1.consume_area_id                    as consume_area_id/**area_id*/,
       t1.consume_area_name                  as consume_area_name/**area_name*/,
       t1.consume_area_level                 as consume_area_level,
       SUM(t1.nm_sum_fee_1694)               as nm_sum_fee_1694/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_pp)            as nm_sum_fee_1694_pp/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_momGrowth)     as nm_sum_fee_1694_momGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoy)           as nm_sum_fee_1694_yoy/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoyGrowth)     as nm_sum_fee_1694_yoyGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEnd)       as nm_sum_fee_1694_yearEnd/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEndGrowth) as nm_sum_fee_1694_yearEndGrowth/**区域nm支出求和*/
from (select * from test_bl.tmp_25291667_37_${acct} union all select * from test_bl.tmp_72079450_38_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_area_level;
test_bl
.
tmp_25291667_37_
${acct}
,test_bl.tmp_72079450_38_
${acct}