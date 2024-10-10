CREATE TABLE smart_test.tmp_68586771_91_202406 AS
select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_parent_area_id,
       tb15.consume_area_level,
       tb15.count_open_date_1844_SUM                                            as count_open_date_1844_SUM,
       IFNULL((tb15.count_open_date_1844_pp - tb17.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb17.count_open_date_1844_momGrowth = 0 then 0
                   else (tb15.count_open_date_1844_momGrowth - tb17.count_open_date_1844_momGrowth) /
                        tb17.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb18.count_open_date_1844_yoy = 0 then 0
                   else (tb15.count_open_date_1844_yoy - tb18.count_open_date_1844_yoy) /
                        tb18.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb18.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb15.count_open_date_1844_yoyGrowth - tb18.count_open_date_1844_yoyGrowth) /
                        tb18.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb16.count_open_date_1844_yearEnd = 0 then 0
                   else (tb15.count_open_date_1844_yearEnd - tb16.count_open_date_1844_yearEnd) /
                        tb16.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb16.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb15.count_open_date_1844_yearEndGrowth - tb16.count_open_date_1844_yearEndGrowth) /
                        tb16.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb15.count_open_date_1846_SUM                                            as count_open_date_1846_SUM,
       IFNULL((tb15.count_open_date_1846_pp - tb17.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       0                                                                        as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb18.count_open_date_1846_yoy = 0 then 0
                   else (tb15.count_open_date_1846_yoy - tb18.count_open_date_1846_yoy) /
                        tb18.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       0                                                                        as count_open_date_1846_yoyGrowth,
       0                                                                        as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb16.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb15.count_open_date_1846_yearEndGrowth - tb16.count_open_date_1846_yearEndGrowth) /
                        tb16.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb2.MICRO_GRID_ID             as consume_area_id,
             tb2.MICRO_GRID_NAME           as consume_area_name,
             tb2.GRID_ID                   as consume_parent_area_id,
             5                             as consume_area_level,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_SUM,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_pp,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_momGrowth,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_yoy,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_yoyGrowth,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_yearEnd,
             SUM(tb1.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_SUM,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_pp,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_momGrowth,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_yoy,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_yoyGrowth,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_yearEnd,
             SUM(tb1.count_open_date_1846) as count_open_date_1846_yearEndGrowth
      from smart_test.fct_wid_micro_grid_index tb1
               inner join smart_test.dw_smart_microgrid_grid_info tb2 on tb1.MICRO_GRID_ID = tb2.MICRO_GRID_ID
      where (tb1.month_id = 202406)
      group by tb2.MICRO_GRID_ID, tb2.MICRO_GRID_NAME) tb15
         left join (select tb4.MICRO_GRID_ID             as consume_area_id,
                           tb4.MICRO_GRID_NAME           as consume_area_name,
                           tb4.GRID_ID                   as consume_parent_area_id,
                           5                             as consume_area_level,
                           0                             as count_open_date_1844_SUM,
                           0                             as count_open_date_1844_pp,
                           0                             as count_open_date_1844_momGrowth,
                           0                             as count_open_date_1844_yoy,
                           0                             as count_open_date_1844_yoyGrowth,
                           0                             as count_open_date_1844_yearEnd,
                           SUM(tb3.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
                           0                             as count_open_date_1846_SUM,
                           0                             as count_open_date_1846_pp,
                           0                             as count_open_date_1846_momGrowth,
                           0                             as count_open_date_1846_yoy,
                           0                             as count_open_date_1846_yoyGrowth,
                           0                             as count_open_date_1846_yearEnd,
                           SUM(tb3.count_open_date_1846) as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb3
                             inner join smart_test.dw_smart_microgrid_grid_info tb4
                                        on tb3.MICRO_GRID_ID = tb4.MICRO_GRID_ID
                    where (tb3.month_id = 202312)
                    group by tb4.MICRO_GRID_ID, tb4.MICRO_GRID_NAME) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb6.MICRO_GRID_ID             as consume_area_id,
                           tb6.MICRO_GRID_NAME           as consume_area_name,
                           tb6.GRID_ID                   as consume_parent_area_id,
                           5                             as consume_area_level,
                           0                             as count_open_date_1844_SUM,
                           SUM(tb5.count_open_date_1844) as count_open_date_1844_pp,
                           0                             as count_open_date_1844_momGrowth,
                           0                             as count_open_date_1844_yoy,
                           0                             as count_open_date_1844_yoyGrowth,
                           0                             as count_open_date_1844_yearEnd,
                           0                             as count_open_date_1844_yearEndGrowth,
                           0                             as count_open_date_1846_SUM,
                           SUM(tb5.count_open_date_1846) as count_open_date_1846_pp,
                           0                             as count_open_date_1846_momGrowth,
                           0                             as count_open_date_1846_yoy,
                           0                             as count_open_date_1846_yoyGrowth,
                           0                             as count_open_date_1846_yearEnd,
                           0                             as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb5
                             inner join smart_test.dw_smart_microgrid_grid_info tb6
                                        on tb5.MICRO_GRID_ID = tb6.MICRO_GRID_ID
                    where (tb5.month_id = 202405)
                    group by tb6.MICRO_GRID_ID, tb6.MICRO_GRID_NAME) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb8.MICRO_GRID_ID             as consume_area_id,
                           tb8.MICRO_GRID_NAME           as consume_area_name,
                           tb8.GRID_ID                   as consume_parent_area_id,
                           5                             as consume_area_level,
                           0                             as count_open_date_1844_SUM,
                           0                             as count_open_date_1844_pp,
                           0                             as count_open_date_1844_momGrowth,
                           SUM(tb7.count_open_date_1844) as count_open_date_1844_yoy,
                           0                             as count_open_date_1844_yoyGrowth,
                           0                             as count_open_date_1844_yearEnd,
                           0                             as count_open_date_1844_yearEndGrowth,
                           0                             as count_open_date_1846_SUM,
                           0                             as count_open_date_1846_pp,
                           0                             as count_open_date_1846_momGrowth,
                           SUM(tb7.count_open_date_1846) as count_open_date_1846_yoy,
                           0                             as count_open_date_1846_yoyGrowth,
                           0                             as count_open_date_1846_yearEnd,
                           0                             as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb7
                             inner join smart_test.dw_smart_microgrid_grid_info tb8
                                        on tb7.MICRO_GRID_ID = tb8.MICRO_GRID_ID
                    where (tb7.month_id = 202306)
                    group by tb8.MICRO_GRID_ID, tb8.MICRO_GRID_NAME) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name