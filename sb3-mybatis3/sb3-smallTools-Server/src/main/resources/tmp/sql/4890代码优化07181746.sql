CREATE TABLE test_bl.tmp_32787238_37_${acct} AS
select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_parent_area_id,
       tb15.consume_area_level,
       tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_parent_area_id,
       tb15.consume_area_level,
       tb15.nm_sum_fee_1694                                                                 as nm_sum_fee_1694,
       round(COALESCE((tb15.nm_sum_fee_1694_pp - tb16.nm_sum_fee_1694_pp), 0), 2)           as nm_sum_fee_1694_pp,
       round(COALESCE((case
                           when tb16.nm_sum_fee_1694_momGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_momGrowth - tb16.nm_sum_fee_1694_momGrowth) /
                                tb16.nm_sum_fee_1694_momGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_momGrowth,
       round(COALESCE((tb15.nm_sum_fee_1694_yoy - tb17.nm_sum_fee_1694_yoy), 0), 2)         as nm_sum_fee_1694_yoy,
       round(COALESCE((case
                           when tb17.nm_sum_fee_1694_yoyGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_yoyGrowth - tb17.nm_sum_fee_1694_yoyGrowth) /
                                tb17.nm_sum_fee_1694_yoyGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_yoyGrowth,
       round(COALESCE((tb15.nm_sum_fee_1694_yearEnd - tb18.nm_sum_fee_1694_yearEnd), 0), 2) as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb18.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb15.nm_sum_fee_1694_yearEndGrowth - tb18.nm_sum_fee_1694_yearEndGrowth) /
                                tb18.nm_sum_fee_1694_yearEndGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_yearEndGrowth
from (select tb2.area_id                                     as consume_area_id,
             tb2.area_name                                   as consume_area_name,
             tb2.sum_area_id                                 as consume_parent_area_id,
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
      group by tb2.area_id, tb2.area_name, tb2.sum_area_id) tb15
         left join (select tb4.area_id                                     as consume_area_id,
                           tb4.area_name                                   as consume_area_name,
                           tb4.sum_area_id                                 as consume_parent_area_id,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           ROUND(COALESCE(SUM(tb3.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
                           0                                               as nm_sum_fee_1694_yoy,
                           0                                               as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb3
                             inner join test_bl.dim_org tb4 on tb3.area_id = tb4.area_id
                    where (tb3.month_id = $add_month(${month_id}, -1))
                    group by tb4.area_id, tb4.area_name, tb4.sum_area_id) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb8.area_id                                     as consume_area_id,
                           tb8.area_name                                   as consume_area_name,
                           tb8.sum_area_id                                 as consume_parent_area_id,
                           2                                               as consume_area_level,
                           0                                               as nm_sum_fee_1694,
                           0                                               as nm_sum_fee_1694_pp,
                           0                                               as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb7.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           ROUND(COALESCE(SUM(tb7.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
                           0                                               as nm_sum_fee_1694_yearEnd,
                           0                                               as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb7
                             inner join test_bl.dim_org tb8 on tb7.area_id = tb8.area_id
                    where (tb7.month_id = $add_month(${month_id}, -1, 0))
                    group by tb8.area_id, tb8.area_name, tb8.sum_area_id) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb12.area_id                                     as consume_area_id,
                           tb12.area_name                                   as consume_area_name,
                           tb12.sum_area_id                                 as consume_parent_area_id,
                           2                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           ROUND(COALESCE(SUM(tb11.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb11.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb11
                             inner join test_bl.dim_org tb12 on tb11.area_id = tb12.area_id
                    where (tb11.month_id = $add_month(${month_id}, -1, L))
                    group by tb12.area_id, tb12.area_name, tb12.sum_area_id) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name;
CREATE TABLE test_bl.tmp_13374047_38_${acct} AS
select tb33.consume_area_id,
       tb33.consume_area_name,
       tb33.consume_parent_area_id,
       tb33.consume_area_level,
       tb33.consume_area_id,
       tb33.consume_area_name,
       tb33.consume_parent_area_id,
       tb33.consume_area_level,
       tb33.nm_sum_fee_1694                                                                 as nm_sum_fee_1694,
       round(COALESCE((tb33.nm_sum_fee_1694_pp - tb34.nm_sum_fee_1694_pp), 0), 2)           as nm_sum_fee_1694_pp,
       round(COALESCE((case
                           when tb34.nm_sum_fee_1694_momGrowth = 0 then 0
                           else (tb33.nm_sum_fee_1694_momGrowth - tb34.nm_sum_fee_1694_momGrowth) /
                                tb34.nm_sum_fee_1694_momGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_momGrowth,
       round(COALESCE((tb33.nm_sum_fee_1694_yoy - tb35.nm_sum_fee_1694_yoy), 0), 2)         as nm_sum_fee_1694_yoy,
       round(COALESCE((case
                           when tb35.nm_sum_fee_1694_yoyGrowth = 0 then 0
                           else (tb33.nm_sum_fee_1694_yoyGrowth - tb35.nm_sum_fee_1694_yoyGrowth) /
                                tb35.nm_sum_fee_1694_yoyGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_yoyGrowth,
       round(COALESCE((tb33.nm_sum_fee_1694_yearEnd - tb36.nm_sum_fee_1694_yearEnd), 0), 2) as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb36.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb33.nm_sum_fee_1694_yearEndGrowth - tb36.nm_sum_fee_1694_yearEndGrowth) /
                                tb36.nm_sum_fee_1694_yearEndGrowth end), 0),
             2)                                                                             as nm_sum_fee_1694_yearEndGrowth
from (select tb20.sum_area_id                                 as consume_area_id,
             tb20.sum_area_name                               as consume_area_name,
             null                                             as consume_parent_area_id,
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
                           null                                             as consume_parent_area_id,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb21.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           ROUND(COALESCE(SUM(tb21.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb21
                             inner join test_bl.dim_org tb22 on tb21.area_id = tb22.area_id
                    where (tb21.month_id = $add_month(${month_id}, -1))
                    group by tb22.sum_area_id, tb22.sum_area_name) tb34
                   on tb33.consume_area_id = tb34.consume_area_id and tb33.consume_area_name = tb34.consume_area_name
         left join (select tb26.sum_area_id                                 as consume_area_id,
                           tb26.sum_area_name                               as consume_area_name,
                           null                                             as consume_parent_area_id,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb25.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           ROUND(COALESCE(SUM(tb25.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb25
                             inner join test_bl.dim_org tb26 on tb25.area_id = tb26.area_id
                    where (tb25.month_id = $add_month(${month_id}, -1, 0))
                    group by tb26.sum_area_id, tb26.sum_area_name) tb35
                   on tb33.consume_area_id = tb35.consume_area_id and tb33.consume_area_name = tb35.consume_area_name
         left join (select tb30.sum_area_id                                 as consume_area_id,
                           tb30.sum_area_name                               as consume_area_name,
                           null                                             as consume_parent_area_id,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           ROUND(COALESCE(SUM(tb29.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb29.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb29
                             inner join test_bl.dim_org tb30 on tb29.area_id = tb30.area_id
                    where (tb29.month_id = $add_month(${month_id}, -1, L))
                    group by tb30.sum_area_id, tb30.sum_area_name) tb36
                   on tb33.consume_area_id = tb36.consume_area_id and tb33.consume_area_name = tb36.consume_area_name;
select ${month_id}                           as month_id,
       t1.consume_area_id                    as consume_area_id/**area_id*/,
       t1.consume_area_name                  as consume_area_name/**area_name*/,
       t1.consume_parent_area_id             as consume_parent_area_id/**area_name*/,
       t1.consume_area_level                 as consume_area_level,
       SUM(t1.nm_sum_fee_1694)               as nm_sum_fee_1694/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_pp)            as nm_sum_fee_1694_pp/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_momGrowth)     as nm_sum_fee_1694_momGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoy)           as nm_sum_fee_1694_yoy/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yoyGrowth)     as nm_sum_fee_1694_yoyGrowth/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEnd)       as nm_sum_fee_1694_yearEnd/**区域nm支出求和*/,
       SUM(t1.nm_sum_fee_1694_yearEndGrowth) as nm_sum_fee_1694_yearEndGrowth/**区域nm支出求和*/
from (select * from test_bl.tmp_32787238_37_${acct} union all select * from test_bl.tmp_13374047_38_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_parent_area_id, t1.consume_area_level;
test_bl
.
tmp_32787238_37_
${acct}
,test_bl.tmp_13374047_38_
${acct}