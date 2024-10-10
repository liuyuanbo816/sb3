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

------------------------------------------------------------------------------------------

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
------------------------------------------------------------------------------------------
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
------------------------------------------------------------------------------------------


select tb36.consume_area_id,
       tb36.consume_area_name,
       tb36.consume_area_level,
       tb36.consume_area_id,
       tb36.consume_area_name,
       tb36.consume_area_level,
       tb36.nm_sum_fee_1694                                                       as nm_sum_fee_1694,
       round(COALESCE((tb36.nm_sum_fee_1694_pp - tb38.nm_sum_fee_1694_pp), 0), 2) as nm_sum_fee_1694_pp,
       round(COALESCE((case
                           when tb42.nm_sum_fee_1694_momGrowth = 0 then 0
                           else (tb36.nm_sum_fee_1694_momGrowth - tb42.nm_sum_fee_1694_momGrowth) /
                                tb42.nm_sum_fee_1694_momGrowth end), 0), 2)       as nm_sum_fee_1694_momGrowth,
       round(COALESCE((case
                           when tb39.nm_sum_fee_1694_yoy = 0 then 0
                           else (tb36.nm_sum_fee_1694_yoy - tb39.nm_sum_fee_1694_yoy) / tb39.nm_sum_fee_1694_yoy end),
                      0), 2)                                                      as nm_sum_fee_1694_yoy,
       round(COALESCE((case
                           when tb40.nm_sum_fee_1694_yoyGrowth = 0 then 0
                           else (tb36.nm_sum_fee_1694_yoyGrowth - tb40.nm_sum_fee_1694_yoyGrowth) /
                                tb40.nm_sum_fee_1694_yoyGrowth end), 0), 2)       as nm_sum_fee_1694_yoyGrowth,
       round(COALESCE((case
                           when tb41.nm_sum_fee_1694_yearEnd = 0 then 0
                           else (tb36.nm_sum_fee_1694_yearEnd - tb41.nm_sum_fee_1694_yearEnd) /
                                tb41.nm_sum_fee_1694_yearEnd end), 0), 2)         as nm_sum_fee_1694_yearEnd,
       round(COALESCE((case
                           when tb37.nm_sum_fee_1694_yearEndGrowth = 0 then 0
                           else (tb36.nm_sum_fee_1694_yearEndGrowth - tb37.nm_sum_fee_1694_yearEndGrowth) /
                                tb37.nm_sum_fee_1694_yearEndGrowth end), 0), 2)   as nm_sum_fee_1694_yearEndGrowth
from (select tb23.sum_area_id                                 as consume_area_id,
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
      group by tb23.sum_area_id, tb23.sum_area_name) tb36
         left join (select tb25.sum_area_id                                 as consume_area_id,
                           tb25.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           ROUND(COALESCE(SUM(tb24.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb24
                             inner join test_bl.dim_org tb25 on tb24.area_id = tb25.area_id
                    where (tb24.month_id = $add_month(${month_id}, -1, L))
                    group by tb25.sum_area_id, tb25.sum_area_name) tb37
                   on tb36.consume_area_id = tb37.consume_area_id and tb36.consume_area_name = tb37.consume_area_name
         left join (select tb27.sum_area_id                                 as consume_area_id,
                           tb27.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           ROUND(COALESCE(SUM(tb26.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb26
                             inner join test_bl.dim_org tb27 on tb26.area_id = tb27.area_id
                    where (tb26.month_id = $add_month(${month_id}, -1))
                    group by tb27.sum_area_id, tb27.sum_area_name) tb38
                   on tb36.consume_area_id = tb38.consume_area_id and tb36.consume_area_name = tb38.consume_area_name
         left join (select tb29.sum_area_id                                 as consume_area_id,
                           tb29.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           ROUND(COALESCE(SUM(tb28.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb28
                             inner join test_bl.dim_org tb29 on tb28.area_id = tb29.area_id
                    where (tb28.month_id = $add_month(${month_id}, -1, 0))
                    group by tb29.sum_area_id, tb29.sum_area_name) tb39
                   on tb36.consume_area_id = tb39.consume_area_id and tb36.consume_area_name = tb39.consume_area_name
         left join (select tb31.sum_area_id                                 as consume_area_id,
                           tb31.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           ROUND(COALESCE(SUM(tb30.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb30
                             inner join test_bl.dim_org tb31 on tb30.area_id = tb31.area_id
                    where (tb30.month_id = $add_month(${month_id}, -1, 0))
                    group by tb31.sum_area_id, tb31.sum_area_name) tb40
                   on tb36.consume_area_id = tb40.consume_area_id and tb36.consume_area_name = tb40.consume_area_name
         left join (select tb33.sum_area_id                                 as consume_area_id,
                           tb33.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           0                                                as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           ROUND(COALESCE(SUM(tb32.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb32
                             inner join test_bl.dim_org tb33 on tb32.area_id = tb33.area_id
                    where (tb32.month_id = $add_month(${month_id}, -1, L))
                    group by tb33.sum_area_id, tb33.sum_area_name) tb41
                   on tb36.consume_area_id = tb41.consume_area_id and tb36.consume_area_name = tb41.consume_area_name
         left join (select tb35.sum_area_id                                 as consume_area_id,
                           tb35.sum_area_name                               as consume_area_name,
                           1                                                as consume_area_level,
                           0                                                as nm_sum_fee_1694,
                           0                                                as nm_sum_fee_1694_pp,
                           ROUND(COALESCE(SUM(tb34.nm_sum_fee_1694), 0), 2) as nm_sum_fee_1694_momGrowth,
                           0                                                as nm_sum_fee_1694_yoy,
                           0                                                as nm_sum_fee_1694_yoyGrowth,
                           0                                                as nm_sum_fee_1694_yearEnd,
                           0                                                as nm_sum_fee_1694_yearEndGrowth
                    from test_bl.fct_wid_index tb34
                             inner join test_bl.dim_org tb35 on tb34.area_id = tb35.area_id
                    where (tb34.month_id = $add_month(${month_id}, -1))
                    group by tb35.sum_area_id, tb35.sum_area_name) tb42
                   on tb36.consume_area_id = tb42.consume_area_id and tb36.consume_area_name = tb42.consume_area_name
------------------------------------------------------------------------------------------
CREATE TABLE test_bl.tmp_33421984_43_${acct} AS
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
------------------------------------------------------------------------------------------
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
from (select * from test_bl.tmp_33421984_43_${acct} union all select * from test_bl.tmp_95899495_44_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_area_level
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
