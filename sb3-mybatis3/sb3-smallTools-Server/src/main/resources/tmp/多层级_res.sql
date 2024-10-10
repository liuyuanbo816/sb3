DELETE
FROM nm.dim_index_calculate_m
where index_code in ('dw_smart_microgrid_grid_info_last1Months_COUNT_OPEN_DATE',
                     'dw_smart_microgrid_grid_info_last1Years_COUNT_OPEN_DATE');
/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_50324520_106_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_92625492_107_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_17059091_108_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_77658129_109_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_81481896_110_${acct};

/*skip=true*/DROP TABLE IF EXISTS nm.app_1720753214637;

CREATE TABLE smart_test.tmp_50324520_106_${acct} AS
select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_area_level,
       tb15.count_open_date_1844_SUM                                            as count_open_date_1844_SUM,
       IFNULL((tb15.count_open_date_1844_pp - tb17.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb21.count_open_date_1844_momGrowth = 0 then 0
                   else (tb15.count_open_date_1844_momGrowth - tb21.count_open_date_1844_momGrowth) /
                        tb21.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb18.count_open_date_1844_yoy = 0 then 0
                   else (tb15.count_open_date_1844_yoy - tb18.count_open_date_1844_yoy) /
                        tb18.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb19.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb15.count_open_date_1844_yoyGrowth - tb19.count_open_date_1844_yoyGrowth) /
                        tb19.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb20.count_open_date_1844_yearEnd = 0 then 0
                   else (tb15.count_open_date_1844_yearEnd - tb20.count_open_date_1844_yearEnd) /
                        tb20.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb16.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb15.count_open_date_1844_yearEndGrowth - tb16.count_open_date_1844_yearEndGrowth) /
                        tb16.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb15.count_open_date_1846_SUM                                            as count_open_date_1846_SUM,
       IFNULL((tb15.count_open_date_1846_pp - tb17.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       IFNULL((case
                   when tb21.count_open_date_1846_momGrowth = 0 then 0
                   else (tb15.count_open_date_1846_momGrowth - tb21.count_open_date_1846_momGrowth) /
                        tb21.count_open_date_1846_momGrowth end), 0)            as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb18.count_open_date_1846_yoy = 0 then 0
                   else (tb15.count_open_date_1846_yoy - tb18.count_open_date_1846_yoy) /
                        tb18.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       IFNULL((case
                   when tb19.count_open_date_1846_yoyGrowth = 0 then 0
                   else (tb15.count_open_date_1846_yoyGrowth - tb19.count_open_date_1846_yoyGrowth) /
                        tb19.count_open_date_1846_yoyGrowth end), 0)            as count_open_date_1846_yoyGrowth,
       IFNULL((case
                   when tb20.count_open_date_1846_yearEnd = 0 then 0
                   else (tb15.count_open_date_1846_yearEnd - tb20.count_open_date_1846_yearEnd) /
                        tb20.count_open_date_1846_yearEnd end), 0)              as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb16.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb15.count_open_date_1846_yearEndGrowth - tb16.count_open_date_1846_yearEndGrowth) /
                        tb16.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb2.MICRO_GRID_ID             as consume_area_id,
             tb2.MICRO_GRID_NAME           as consume_area_name,
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
      where (tb1.month_id = ${month_id})
      group by tb2.MICRO_GRID_ID, tb2.MICRO_GRID_NAME) tb15
         left join (select tb4.MICRO_GRID_ID             as consume_area_id,
                           tb4.MICRO_GRID_NAME           as consume_area_name,
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
                    where (tb3.month_id = $add_month(${month_id}, -1, L))
                    group by tb4.MICRO_GRID_ID, tb4.MICRO_GRID_NAME) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb6.MICRO_GRID_ID             as consume_area_id,
                           tb6.MICRO_GRID_NAME           as consume_area_name,
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
                    where (tb5.month_id = $add_month(${month_id}, -1))
                    group by tb6.MICRO_GRID_ID, tb6.MICRO_GRID_NAME) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb8.MICRO_GRID_ID             as consume_area_id,
                           tb8.MICRO_GRID_NAME           as consume_area_name,
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
                    where (tb7.month_id = $add_month(${month_id}, -1, 0))
                    group by tb8.MICRO_GRID_ID, tb8.MICRO_GRID_NAME) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name
         left join (select tb10.MICRO_GRID_ID            as consume_area_id,
                           tb10.MICRO_GRID_NAME          as consume_area_name,
                           5                             as consume_area_level,
                           0                             as count_open_date_1844_SUM,
                           0                             as count_open_date_1844_pp,
                           0                             as count_open_date_1844_momGrowth,
                           0                             as count_open_date_1844_yoy,
                           SUM(tb9.count_open_date_1844) as count_open_date_1844_yoyGrowth,
                           0                             as count_open_date_1844_yearEnd,
                           0                             as count_open_date_1844_yearEndGrowth,
                           0                             as count_open_date_1846_SUM,
                           0                             as count_open_date_1846_pp,
                           0                             as count_open_date_1846_momGrowth,
                           0                             as count_open_date_1846_yoy,
                           SUM(tb9.count_open_date_1846) as count_open_date_1846_yoyGrowth,
                           0                             as count_open_date_1846_yearEnd,
                           0                             as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb9
                             inner join smart_test.dw_smart_microgrid_grid_info tb10
                                        on tb9.MICRO_GRID_ID = tb10.MICRO_GRID_ID
                    where (tb9.month_id = $add_month(${month_id}, -1, 0))
                    group by tb10.MICRO_GRID_ID, tb10.MICRO_GRID_NAME) tb19
                   on tb15.consume_area_id = tb19.consume_area_id and tb15.consume_area_name = tb19.consume_area_name
         left join (select tb12.MICRO_GRID_ID             as consume_area_id,
                           tb12.MICRO_GRID_NAME           as consume_area_name,
                           5                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           SUM(tb11.count_open_date_1844) as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           SUM(tb11.count_open_date_1846) as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb11
                             inner join smart_test.dw_smart_microgrid_grid_info tb12
                                        on tb11.MICRO_GRID_ID = tb12.MICRO_GRID_ID
                    where (tb11.month_id = $add_month(${month_id}, -1, L))
                    group by tb12.MICRO_GRID_ID, tb12.MICRO_GRID_NAME) tb20
                   on tb15.consume_area_id = tb20.consume_area_id and tb15.consume_area_name = tb20.consume_area_name
         left join (select tb14.MICRO_GRID_ID             as consume_area_id,
                           tb14.MICRO_GRID_NAME           as consume_area_name,
                           5                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           SUM(tb13.count_open_date_1844) as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           SUM(tb13.count_open_date_1846) as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb13
                             inner join smart_test.dw_smart_microgrid_grid_info tb14
                                        on tb13.MICRO_GRID_ID = tb14.MICRO_GRID_ID
                    where (tb13.month_id = $add_month(${month_id}, -1))
                    group by tb14.MICRO_GRID_ID, tb14.MICRO_GRID_NAME) tb21
                   on tb15.consume_area_id = tb21.consume_area_id and tb15.consume_area_name = tb21.consume_area_name;


CREATE TABLE smart_test.tmp_92625492_107_${acct} AS
select tb36.consume_area_id,
       tb36.consume_area_name,
       tb36.consume_area_level,
       tb36.count_open_date_1844_SUM                                            as count_open_date_1844_SUM,
       IFNULL((tb36.count_open_date_1844_pp - tb38.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb42.count_open_date_1844_momGrowth = 0 then 0
                   else (tb36.count_open_date_1844_momGrowth - tb42.count_open_date_1844_momGrowth) /
                        tb42.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb39.count_open_date_1844_yoy = 0 then 0
                   else (tb36.count_open_date_1844_yoy - tb39.count_open_date_1844_yoy) /
                        tb39.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb40.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb36.count_open_date_1844_yoyGrowth - tb40.count_open_date_1844_yoyGrowth) /
                        tb40.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb41.count_open_date_1844_yearEnd = 0 then 0
                   else (tb36.count_open_date_1844_yearEnd - tb41.count_open_date_1844_yearEnd) /
                        tb41.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb37.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb36.count_open_date_1844_yearEndGrowth - tb37.count_open_date_1844_yearEndGrowth) /
                        tb37.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb36.count_open_date_1846_SUM                                            as count_open_date_1846_SUM,
       IFNULL((tb36.count_open_date_1846_pp - tb38.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       IFNULL((case
                   when tb42.count_open_date_1846_momGrowth = 0 then 0
                   else (tb36.count_open_date_1846_momGrowth - tb42.count_open_date_1846_momGrowth) /
                        tb42.count_open_date_1846_momGrowth end), 0)            as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb39.count_open_date_1846_yoy = 0 then 0
                   else (tb36.count_open_date_1846_yoy - tb39.count_open_date_1846_yoy) /
                        tb39.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       IFNULL((case
                   when tb40.count_open_date_1846_yoyGrowth = 0 then 0
                   else (tb36.count_open_date_1846_yoyGrowth - tb40.count_open_date_1846_yoyGrowth) /
                        tb40.count_open_date_1846_yoyGrowth end), 0)            as count_open_date_1846_yoyGrowth,
       IFNULL((case
                   when tb41.count_open_date_1846_yearEnd = 0 then 0
                   else (tb36.count_open_date_1846_yearEnd - tb41.count_open_date_1846_yearEnd) /
                        tb41.count_open_date_1846_yearEnd end), 0)              as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb37.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb36.count_open_date_1846_yearEndGrowth - tb37.count_open_date_1846_yearEndGrowth) /
                        tb37.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb23.GRID_ID                   as consume_area_id,
             tb23.GRID_NAME                 as consume_area_name,
             4                              as consume_area_level,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_SUM,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_pp,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_momGrowth,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_yoy,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_yoyGrowth,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_yearEnd,
             SUM(tb22.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_SUM,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_pp,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_momGrowth,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_yoy,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_yoyGrowth,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_yearEnd,
             SUM(tb22.count_open_date_1846) as count_open_date_1846_yearEndGrowth
      from smart_test.fct_wid_micro_grid_index tb22
               inner join smart_test.dw_smart_microgrid_grid_info tb23 on tb22.MICRO_GRID_ID = tb23.MICRO_GRID_ID
      where (tb22.month_id = ${month_id})
      group by tb23.GRID_ID, tb23.GRID_NAME) tb36
         left join (select tb25.GRID_ID                   as consume_area_id,
                           tb25.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           SUM(tb24.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           SUM(tb24.count_open_date_1846) as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb24
                             inner join smart_test.dw_smart_microgrid_grid_info tb25
                                        on tb24.MICRO_GRID_ID = tb25.MICRO_GRID_ID
                    where (tb24.month_id = $add_month(${month_id}, -1, L))
                    group by tb25.GRID_ID, tb25.GRID_NAME) tb37
                   on tb36.consume_area_id = tb37.consume_area_id and tb36.consume_area_name = tb37.consume_area_name
         left join (select tb27.GRID_ID                   as consume_area_id,
                           tb27.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           SUM(tb26.count_open_date_1844) as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           SUM(tb26.count_open_date_1846) as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb26
                             inner join smart_test.dw_smart_microgrid_grid_info tb27
                                        on tb26.MICRO_GRID_ID = tb27.MICRO_GRID_ID
                    where (tb26.month_id = $add_month(${month_id}, -1))
                    group by tb27.GRID_ID, tb27.GRID_NAME) tb38
                   on tb36.consume_area_id = tb38.consume_area_id and tb36.consume_area_name = tb38.consume_area_name
         left join (select tb29.GRID_ID                   as consume_area_id,
                           tb29.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           SUM(tb28.count_open_date_1844) as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           SUM(tb28.count_open_date_1846) as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb28
                             inner join smart_test.dw_smart_microgrid_grid_info tb29
                                        on tb28.MICRO_GRID_ID = tb29.MICRO_GRID_ID
                    where (tb28.month_id = $add_month(${month_id}, -1, 0))
                    group by tb29.GRID_ID, tb29.GRID_NAME) tb39
                   on tb36.consume_area_id = tb39.consume_area_id and tb36.consume_area_name = tb39.consume_area_name
         left join (select tb31.GRID_ID                   as consume_area_id,
                           tb31.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           SUM(tb30.count_open_date_1844) as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           SUM(tb30.count_open_date_1846) as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb30
                             inner join smart_test.dw_smart_microgrid_grid_info tb31
                                        on tb30.MICRO_GRID_ID = tb31.MICRO_GRID_ID
                    where (tb30.month_id = $add_month(${month_id}, -1, 0))
                    group by tb31.GRID_ID, tb31.GRID_NAME) tb40
                   on tb36.consume_area_id = tb40.consume_area_id and tb36.consume_area_name = tb40.consume_area_name
         left join (select tb33.GRID_ID                   as consume_area_id,
                           tb33.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           SUM(tb32.count_open_date_1844) as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           SUM(tb32.count_open_date_1846) as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb32
                             inner join smart_test.dw_smart_microgrid_grid_info tb33
                                        on tb32.MICRO_GRID_ID = tb33.MICRO_GRID_ID
                    where (tb32.month_id = $add_month(${month_id}, -1, L))
                    group by tb33.GRID_ID, tb33.GRID_NAME) tb41
                   on tb36.consume_area_id = tb41.consume_area_id and tb36.consume_area_name = tb41.consume_area_name
         left join (select tb35.GRID_ID                   as consume_area_id,
                           tb35.GRID_NAME                 as consume_area_name,
                           4                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           SUM(tb34.count_open_date_1844) as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           SUM(tb34.count_open_date_1846) as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb34
                             inner join smart_test.dw_smart_microgrid_grid_info tb35
                                        on tb34.MICRO_GRID_ID = tb35.MICRO_GRID_ID
                    where (tb34.month_id = $add_month(${month_id}, -1))
                    group by tb35.GRID_ID, tb35.GRID_NAME) tb42
                   on tb36.consume_area_id = tb42.consume_area_id and tb36.consume_area_name = tb42.consume_area_name;
CREATE TABLE smart_test.tmp_17059091_108_${acct} AS
select tb57.consume_area_id,
       tb57.consume_area_name,
       tb57.consume_area_level,
       tb57.count_open_date_1844_SUM                                            as count_open_date_1844_SUM,
       IFNULL((tb57.count_open_date_1844_pp - tb59.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb63.count_open_date_1844_momGrowth = 0 then 0
                   else (tb57.count_open_date_1844_momGrowth - tb63.count_open_date_1844_momGrowth) /
                        tb63.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb60.count_open_date_1844_yoy = 0 then 0
                   else (tb57.count_open_date_1844_yoy - tb60.count_open_date_1844_yoy) /
                        tb60.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb61.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb57.count_open_date_1844_yoyGrowth - tb61.count_open_date_1844_yoyGrowth) /
                        tb61.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb62.count_open_date_1844_yearEnd = 0 then 0
                   else (tb57.count_open_date_1844_yearEnd - tb62.count_open_date_1844_yearEnd) /
                        tb62.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb58.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb57.count_open_date_1844_yearEndGrowth - tb58.count_open_date_1844_yearEndGrowth) /
                        tb58.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb57.count_open_date_1846_SUM                                            as count_open_date_1846_SUM,
       IFNULL((tb57.count_open_date_1846_pp - tb59.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       IFNULL((case
                   when tb63.count_open_date_1846_momGrowth = 0 then 0
                   else (tb57.count_open_date_1846_momGrowth - tb63.count_open_date_1846_momGrowth) /
                        tb63.count_open_date_1846_momGrowth end), 0)            as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb60.count_open_date_1846_yoy = 0 then 0
                   else (tb57.count_open_date_1846_yoy - tb60.count_open_date_1846_yoy) /
                        tb60.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       IFNULL((case
                   when tb61.count_open_date_1846_yoyGrowth = 0 then 0
                   else (tb57.count_open_date_1846_yoyGrowth - tb61.count_open_date_1846_yoyGrowth) /
                        tb61.count_open_date_1846_yoyGrowth end), 0)            as count_open_date_1846_yoyGrowth,
       IFNULL((case
                   when tb62.count_open_date_1846_yearEnd = 0 then 0
                   else (tb57.count_open_date_1846_yearEnd - tb62.count_open_date_1846_yearEnd) /
                        tb62.count_open_date_1846_yearEnd end), 0)              as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb58.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb57.count_open_date_1846_yearEndGrowth - tb58.count_open_date_1846_yearEndGrowth) /
                        tb58.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb44.BUSN_DEPT_CODE            as consume_area_id,
             tb44.BUSN_DEPT_NAME            as consume_area_name,
             3                              as consume_area_level,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_SUM,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_pp,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_momGrowth,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_yoy,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_yoyGrowth,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_yearEnd,
             SUM(tb43.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_SUM,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_pp,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_momGrowth,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_yoy,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_yoyGrowth,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_yearEnd,
             SUM(tb43.count_open_date_1846) as count_open_date_1846_yearEndGrowth
      from smart_test.fct_wid_micro_grid_index tb43
               inner join smart_test.dw_smart_microgrid_grid_info tb44 on tb43.MICRO_GRID_ID = tb44.MICRO_GRID_ID
      where (tb43.month_id = ${month_id})
      group by tb44.BUSN_DEPT_CODE, tb44.BUSN_DEPT_NAME) tb57
         left join (select tb46.BUSN_DEPT_CODE            as consume_area_id,
                           tb46.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           SUM(tb45.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           SUM(tb45.count_open_date_1846) as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb45
                             inner join smart_test.dw_smart_microgrid_grid_info tb46
                                        on tb45.MICRO_GRID_ID = tb46.MICRO_GRID_ID
                    where (tb45.month_id = $add_month(${month_id}, -1, L))
                    group by tb46.BUSN_DEPT_CODE, tb46.BUSN_DEPT_NAME) tb58
                   on tb57.consume_area_id = tb58.consume_area_id and tb57.consume_area_name = tb58.consume_area_name
         left join (select tb48.BUSN_DEPT_CODE            as consume_area_id,
                           tb48.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           SUM(tb47.count_open_date_1844) as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           SUM(tb47.count_open_date_1846) as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb47
                             inner join smart_test.dw_smart_microgrid_grid_info tb48
                                        on tb47.MICRO_GRID_ID = tb48.MICRO_GRID_ID
                    where (tb47.month_id = $add_month(${month_id}, -1))
                    group by tb48.BUSN_DEPT_CODE, tb48.BUSN_DEPT_NAME) tb59
                   on tb57.consume_area_id = tb59.consume_area_id and tb57.consume_area_name = tb59.consume_area_name
         left join (select tb50.BUSN_DEPT_CODE            as consume_area_id,
                           tb50.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           SUM(tb49.count_open_date_1844) as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           SUM(tb49.count_open_date_1846) as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb49
                             inner join smart_test.dw_smart_microgrid_grid_info tb50
                                        on tb49.MICRO_GRID_ID = tb50.MICRO_GRID_ID
                    where (tb49.month_id = $add_month(${month_id}, -1, 0))
                    group by tb50.BUSN_DEPT_CODE, tb50.BUSN_DEPT_NAME) tb60
                   on tb57.consume_area_id = tb60.consume_area_id and tb57.consume_area_name = tb60.consume_area_name
         left join (select tb52.BUSN_DEPT_CODE            as consume_area_id,
                           tb52.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           SUM(tb51.count_open_date_1844) as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           SUM(tb51.count_open_date_1846) as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb51
                             inner join smart_test.dw_smart_microgrid_grid_info tb52
                                        on tb51.MICRO_GRID_ID = tb52.MICRO_GRID_ID
                    where (tb51.month_id = $add_month(${month_id}, -1, 0))
                    group by tb52.BUSN_DEPT_CODE, tb52.BUSN_DEPT_NAME) tb61
                   on tb57.consume_area_id = tb61.consume_area_id and tb57.consume_area_name = tb61.consume_area_name
         left join (select tb54.BUSN_DEPT_CODE            as consume_area_id,
                           tb54.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           SUM(tb53.count_open_date_1844) as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           SUM(tb53.count_open_date_1846) as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb53
                             inner join smart_test.dw_smart_microgrid_grid_info tb54
                                        on tb53.MICRO_GRID_ID = tb54.MICRO_GRID_ID
                    where (tb53.month_id = $add_month(${month_id}, -1, L))
                    group by tb54.BUSN_DEPT_CODE, tb54.BUSN_DEPT_NAME) tb62
                   on tb57.consume_area_id = tb62.consume_area_id and tb57.consume_area_name = tb62.consume_area_name
         left join (select tb56.BUSN_DEPT_CODE            as consume_area_id,
                           tb56.BUSN_DEPT_NAME            as consume_area_name,
                           3                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           SUM(tb55.count_open_date_1844) as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           SUM(tb55.count_open_date_1846) as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb55
                             inner join smart_test.dw_smart_microgrid_grid_info tb56
                                        on tb55.MICRO_GRID_ID = tb56.MICRO_GRID_ID
                    where (tb55.month_id = $add_month(${month_id}, -1))
                    group by tb56.BUSN_DEPT_CODE, tb56.BUSN_DEPT_NAME) tb63
                   on tb57.consume_area_id = tb63.consume_area_id and tb57.consume_area_name = tb63.consume_area_name;
CREATE TABLE smart_test.tmp_77658129_109_${acct} AS
select tb78.consume_area_id,
       tb78.consume_area_name,
       tb78.consume_area_level,
       tb78.count_open_date_1844_SUM                                            as count_open_date_1844_SUM,
       IFNULL((tb78.count_open_date_1844_pp - tb80.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb84.count_open_date_1844_momGrowth = 0 then 0
                   else (tb78.count_open_date_1844_momGrowth - tb84.count_open_date_1844_momGrowth) /
                        tb84.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb81.count_open_date_1844_yoy = 0 then 0
                   else (tb78.count_open_date_1844_yoy - tb81.count_open_date_1844_yoy) /
                        tb81.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb82.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb78.count_open_date_1844_yoyGrowth - tb82.count_open_date_1844_yoyGrowth) /
                        tb82.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb83.count_open_date_1844_yearEnd = 0 then 0
                   else (tb78.count_open_date_1844_yearEnd - tb83.count_open_date_1844_yearEnd) /
                        tb83.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb79.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb78.count_open_date_1844_yearEndGrowth - tb79.count_open_date_1844_yearEndGrowth) /
                        tb79.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb78.count_open_date_1846_SUM                                            as count_open_date_1846_SUM,
       IFNULL((tb78.count_open_date_1846_pp - tb80.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       IFNULL((case
                   when tb84.count_open_date_1846_momGrowth = 0 then 0
                   else (tb78.count_open_date_1846_momGrowth - tb84.count_open_date_1846_momGrowth) /
                        tb84.count_open_date_1846_momGrowth end), 0)            as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb81.count_open_date_1846_yoy = 0 then 0
                   else (tb78.count_open_date_1846_yoy - tb81.count_open_date_1846_yoy) /
                        tb81.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       IFNULL((case
                   when tb82.count_open_date_1846_yoyGrowth = 0 then 0
                   else (tb78.count_open_date_1846_yoyGrowth - tb82.count_open_date_1846_yoyGrowth) /
                        tb82.count_open_date_1846_yoyGrowth end), 0)            as count_open_date_1846_yoyGrowth,
       IFNULL((case
                   when tb83.count_open_date_1846_yearEnd = 0 then 0
                   else (tb78.count_open_date_1846_yearEnd - tb83.count_open_date_1846_yearEnd) /
                        tb83.count_open_date_1846_yearEnd end), 0)              as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb79.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb78.count_open_date_1846_yearEndGrowth - tb79.count_open_date_1846_yearEndGrowth) /
                        tb79.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb65.CITY_ID                   as consume_area_id,
             tb65.CITY_NAME                 as consume_area_name,
             2                              as consume_area_level,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_SUM,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_pp,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_momGrowth,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_yoy,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_yoyGrowth,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_yearEnd,
             SUM(tb64.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_SUM,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_pp,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_momGrowth,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_yoy,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_yoyGrowth,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_yearEnd,
             SUM(tb64.count_open_date_1846) as count_open_date_1846_yearEndGrowth
      from smart_test.fct_wid_micro_grid_index tb64
               inner join smart_test.dw_smart_microgrid_grid_info tb65 on tb64.MICRO_GRID_ID = tb65.MICRO_GRID_ID
      where (tb64.month_id = ${month_id})
      group by tb65.CITY_ID, tb65.CITY_NAME) tb78
         left join (select tb67.CITY_ID                   as consume_area_id,
                           tb67.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           SUM(tb66.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           SUM(tb66.count_open_date_1846) as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb66
                             inner join smart_test.dw_smart_microgrid_grid_info tb67
                                        on tb66.MICRO_GRID_ID = tb67.MICRO_GRID_ID
                    where (tb66.month_id = $add_month(${month_id}, -1, L))
                    group by tb67.CITY_ID, tb67.CITY_NAME) tb79
                   on tb78.consume_area_id = tb79.consume_area_id and tb78.consume_area_name = tb79.consume_area_name
         left join (select tb69.CITY_ID                   as consume_area_id,
                           tb69.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           SUM(tb68.count_open_date_1844) as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           SUM(tb68.count_open_date_1846) as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb68
                             inner join smart_test.dw_smart_microgrid_grid_info tb69
                                        on tb68.MICRO_GRID_ID = tb69.MICRO_GRID_ID
                    where (tb68.month_id = $add_month(${month_id}, -1))
                    group by tb69.CITY_ID, tb69.CITY_NAME) tb80
                   on tb78.consume_area_id = tb80.consume_area_id and tb78.consume_area_name = tb80.consume_area_name
         left join (select tb71.CITY_ID                   as consume_area_id,
                           tb71.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           SUM(tb70.count_open_date_1844) as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           SUM(tb70.count_open_date_1846) as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb70
                             inner join smart_test.dw_smart_microgrid_grid_info tb71
                                        on tb70.MICRO_GRID_ID = tb71.MICRO_GRID_ID
                    where (tb70.month_id = $add_month(${month_id}, -1, 0))
                    group by tb71.CITY_ID, tb71.CITY_NAME) tb81
                   on tb78.consume_area_id = tb81.consume_area_id and tb78.consume_area_name = tb81.consume_area_name
         left join (select tb73.CITY_ID                   as consume_area_id,
                           tb73.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           SUM(tb72.count_open_date_1844) as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           SUM(tb72.count_open_date_1846) as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb72
                             inner join smart_test.dw_smart_microgrid_grid_info tb73
                                        on tb72.MICRO_GRID_ID = tb73.MICRO_GRID_ID
                    where (tb72.month_id = $add_month(${month_id}, -1, 0))
                    group by tb73.CITY_ID, tb73.CITY_NAME) tb82
                   on tb78.consume_area_id = tb82.consume_area_id and tb78.consume_area_name = tb82.consume_area_name
         left join (select tb75.CITY_ID                   as consume_area_id,
                           tb75.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           SUM(tb74.count_open_date_1844) as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           SUM(tb74.count_open_date_1846) as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb74
                             inner join smart_test.dw_smart_microgrid_grid_info tb75
                                        on tb74.MICRO_GRID_ID = tb75.MICRO_GRID_ID
                    where (tb74.month_id = $add_month(${month_id}, -1, L))
                    group by tb75.CITY_ID, tb75.CITY_NAME) tb83
                   on tb78.consume_area_id = tb83.consume_area_id and tb78.consume_area_name = tb83.consume_area_name
         left join (select tb77.CITY_ID                   as consume_area_id,
                           tb77.CITY_NAME                 as consume_area_name,
                           2                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           SUM(tb76.count_open_date_1844) as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           SUM(tb76.count_open_date_1846) as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb76
                             inner join smart_test.dw_smart_microgrid_grid_info tb77
                                        on tb76.MICRO_GRID_ID = tb77.MICRO_GRID_ID
                    where (tb76.month_id = $add_month(${month_id}, -1))
                    group by tb77.CITY_ID, tb77.CITY_NAME) tb84
                   on tb78.consume_area_id = tb84.consume_area_id and tb78.consume_area_name = tb84.consume_area_name;
CREATE TABLE smart_test.tmp_81481896_110_${acct} AS
select tb99.consume_area_id,
       tb99.consume_area_name,
       tb99.consume_area_level,
       tb99.count_open_date_1844_SUM                                             as count_open_date_1844_SUM,
       IFNULL((tb99.count_open_date_1844_pp - tb101.count_open_date_1844_pp), 0) as count_open_date_1844_pp,
       IFNULL((case
                   when tb105.count_open_date_1844_momGrowth = 0 then 0
                   else (tb99.count_open_date_1844_momGrowth - tb105.count_open_date_1844_momGrowth) /
                        tb105.count_open_date_1844_momGrowth end), 0)            as count_open_date_1844_momGrowth,
       IFNULL((case
                   when tb102.count_open_date_1844_yoy = 0 then 0
                   else (tb99.count_open_date_1844_yoy - tb102.count_open_date_1844_yoy) /
                        tb102.count_open_date_1844_yoy end), 0)                  as count_open_date_1844_yoy,
       IFNULL((case
                   when tb103.count_open_date_1844_yoyGrowth = 0 then 0
                   else (tb99.count_open_date_1844_yoyGrowth - tb103.count_open_date_1844_yoyGrowth) /
                        tb103.count_open_date_1844_yoyGrowth end), 0)            as count_open_date_1844_yoyGrowth,
       IFNULL((case
                   when tb104.count_open_date_1844_yearEnd = 0 then 0
                   else (tb99.count_open_date_1844_yearEnd - tb104.count_open_date_1844_yearEnd) /
                        tb104.count_open_date_1844_yearEnd end), 0)              as count_open_date_1844_yearEnd,
       IFNULL((case
                   when tb100.count_open_date_1844_yearEndGrowth = 0 then 0
                   else (tb99.count_open_date_1844_yearEndGrowth - tb100.count_open_date_1844_yearEndGrowth) /
                        tb100.count_open_date_1844_yearEndGrowth end), 0)        as count_open_date_1844_yearEndGrowth,
       tb99.count_open_date_1846_SUM                                             as count_open_date_1846_SUM,
       IFNULL((tb99.count_open_date_1846_pp - tb101.count_open_date_1846_pp), 0) as count_open_date_1846_pp,
       IFNULL((case
                   when tb105.count_open_date_1846_momGrowth = 0 then 0
                   else (tb99.count_open_date_1846_momGrowth - tb105.count_open_date_1846_momGrowth) /
                        tb105.count_open_date_1846_momGrowth end), 0)            as count_open_date_1846_momGrowth,
       IFNULL((case
                   when tb102.count_open_date_1846_yoy = 0 then 0
                   else (tb99.count_open_date_1846_yoy - tb102.count_open_date_1846_yoy) /
                        tb102.count_open_date_1846_yoy end), 0)                  as count_open_date_1846_yoy,
       IFNULL((case
                   when tb103.count_open_date_1846_yoyGrowth = 0 then 0
                   else (tb99.count_open_date_1846_yoyGrowth - tb103.count_open_date_1846_yoyGrowth) /
                        tb103.count_open_date_1846_yoyGrowth end), 0)            as count_open_date_1846_yoyGrowth,
       IFNULL((case
                   when tb104.count_open_date_1846_yearEnd = 0 then 0
                   else (tb99.count_open_date_1846_yearEnd - tb104.count_open_date_1846_yearEnd) /
                        tb104.count_open_date_1846_yearEnd end), 0)              as count_open_date_1846_yearEnd,
       IFNULL((case
                   when tb100.count_open_date_1846_yearEndGrowth = 0 then 0
                   else (tb99.count_open_date_1846_yearEndGrowth - tb100.count_open_date_1846_yearEndGrowth) /
                        tb100.count_open_date_1846_yearEndGrowth end), 0)        as count_open_date_1846_yearEndGrowth
from (select tb86.PROV_ID                   as consume_area_id,
             tb86.PROV_NAME                 as consume_area_name,
             1                              as consume_area_level,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_SUM,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_pp,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_momGrowth,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_yoy,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_yoyGrowth,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_yearEnd,
             SUM(tb85.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_SUM,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_pp,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_momGrowth,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_yoy,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_yoyGrowth,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_yearEnd,
             SUM(tb85.count_open_date_1846) as count_open_date_1846_yearEndGrowth
      from smart_test.fct_wid_micro_grid_index tb85
               inner join smart_test.dw_smart_microgrid_grid_info tb86 on tb85.MICRO_GRID_ID = tb86.MICRO_GRID_ID
      where (tb85.month_id = ${month_id})
      group by tb86.PROV_ID, tb86.PROV_NAME) tb99
         left join (select tb88.PROV_ID                   as consume_area_id,
                           tb88.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           SUM(tb87.count_open_date_1844) as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           SUM(tb87.count_open_date_1846) as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb87
                             inner join smart_test.dw_smart_microgrid_grid_info tb88
                                        on tb87.MICRO_GRID_ID = tb88.MICRO_GRID_ID
                    where (tb87.month_id = $add_month(${month_id}, -1, L))
                    group by tb88.PROV_ID, tb88.PROV_NAME) tb100
                   on tb99.consume_area_id = tb100.consume_area_id and tb99.consume_area_name = tb100.consume_area_name
         left join (select tb90.PROV_ID                   as consume_area_id,
                           tb90.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           SUM(tb89.count_open_date_1844) as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           SUM(tb89.count_open_date_1846) as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb89
                             inner join smart_test.dw_smart_microgrid_grid_info tb90
                                        on tb89.MICRO_GRID_ID = tb90.MICRO_GRID_ID
                    where (tb89.month_id = $add_month(${month_id}, -1))
                    group by tb90.PROV_ID, tb90.PROV_NAME) tb101
                   on tb99.consume_area_id = tb101.consume_area_id and tb99.consume_area_name = tb101.consume_area_name
         left join (select tb92.PROV_ID                   as consume_area_id,
                           tb92.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           SUM(tb91.count_open_date_1844) as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           SUM(tb91.count_open_date_1846) as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb91
                             inner join smart_test.dw_smart_microgrid_grid_info tb92
                                        on tb91.MICRO_GRID_ID = tb92.MICRO_GRID_ID
                    where (tb91.month_id = $add_month(${month_id}, -1, 0))
                    group by tb92.PROV_ID, tb92.PROV_NAME) tb102
                   on tb99.consume_area_id = tb102.consume_area_id and tb99.consume_area_name = tb102.consume_area_name
         left join (select tb94.PROV_ID                   as consume_area_id,
                           tb94.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           SUM(tb93.count_open_date_1844) as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           SUM(tb93.count_open_date_1846) as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb93
                             inner join smart_test.dw_smart_microgrid_grid_info tb94
                                        on tb93.MICRO_GRID_ID = tb94.MICRO_GRID_ID
                    where (tb93.month_id = $add_month(${month_id}, -1, 0))
                    group by tb94.PROV_ID, tb94.PROV_NAME) tb103
                   on tb99.consume_area_id = tb103.consume_area_id and tb99.consume_area_name = tb103.consume_area_name
         left join (select tb96.PROV_ID                   as consume_area_id,
                           tb96.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           0                              as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           SUM(tb95.count_open_date_1844) as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           0                              as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           SUM(tb95.count_open_date_1846) as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb95
                             inner join smart_test.dw_smart_microgrid_grid_info tb96
                                        on tb95.MICRO_GRID_ID = tb96.MICRO_GRID_ID
                    where (tb95.month_id = $add_month(${month_id}, -1, L))
                    group by tb96.PROV_ID, tb96.PROV_NAME) tb104
                   on tb99.consume_area_id = tb104.consume_area_id and tb99.consume_area_name = tb104.consume_area_name
         left join (select tb98.PROV_ID                   as consume_area_id,
                           tb98.PROV_NAME                 as consume_area_name,
                           1                              as consume_area_level,
                           0                              as count_open_date_1844_SUM,
                           0                              as count_open_date_1844_pp,
                           SUM(tb97.count_open_date_1844) as count_open_date_1844_momGrowth,
                           0                              as count_open_date_1844_yoy,
                           0                              as count_open_date_1844_yoyGrowth,
                           0                              as count_open_date_1844_yearEnd,
                           0                              as count_open_date_1844_yearEndGrowth,
                           0                              as count_open_date_1846_SUM,
                           0                              as count_open_date_1846_pp,
                           SUM(tb97.count_open_date_1846) as count_open_date_1846_momGrowth,
                           0                              as count_open_date_1846_yoy,
                           0                              as count_open_date_1846_yoyGrowth,
                           0                              as count_open_date_1846_yearEnd,
                           0                              as count_open_date_1846_yearEndGrowth
                    from smart_test.fct_wid_micro_grid_index tb97
                             inner join smart_test.dw_smart_microgrid_grid_info tb98
                                        on tb97.MICRO_GRID_ID = tb98.MICRO_GRID_ID
                    where (tb97.month_id = $add_month(${month_id}, -1))
                    group by tb98.PROV_ID, tb98.PROV_NAME) tb105
                   on tb99.consume_area_id = tb105.consume_area_id and tb99.consume_area_name = tb105.consume_area_name;
CREATE TABLE nm.app_1720753214637 AS
select ${month_id}                                as month_id,
       t1.consume_area_id                         as consume_area_id/**MICRO_GRID_ID*/,
       t1.consume_area_name                       as consume_area_name/**MICRO_GRID_NAME*/,
       t1.consume_area_level                      as consume_area_level,
       SUM(t1.count_open_date_1844_SUM)           as count_open_date_1844_SUM/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_pp)            as count_open_date_1844_pp/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_momGrowth)     as count_open_date_1844_momGrowth/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_yoy)           as count_open_date_1844_yoy/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_yoyGrowth)     as count_open_date_1844_yoyGrowth/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_yearEnd)       as count_open_date_1844_yearEnd/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1844_yearEndGrowth) as count_open_date_1844_yearEndGrowth/**过去1月归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_SUM)           as count_open_date_1846_SUM/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_pp)            as count_open_date_1846_pp/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_momGrowth)     as count_open_date_1846_momGrowth/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_yoy)           as count_open_date_1846_yoy/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_yoyGrowth)     as count_open_date_1846_yoyGrowth/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_yearEnd)       as count_open_date_1846_yearEnd/**过去1年归属微网格开户时间计数*/,
       SUM(t1.count_open_date_1846_yearEndGrowth) as count_open_date_1846_yearEndGrowth/**过去1年归属微网格开户时间计数*/
from (select *
      from smart_test.tmp_50324520_106_${acct}
      union all
      select *
      from smart_test.tmp_92625492_107_${acct}
      union all
      select *
      from smart_test.tmp_17059091_108_${acct}
      union all
      select *
      from smart_test.tmp_77658129_109_${acct}
      union all
      select *
      from smart_test.tmp_81481896_110_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_area_level;;
INSERT INTO nm.dim_index_calculate_m (month_id,
                                      dimension_type,
                                      dimension_id,
                                      dimension_name,
                                      dimension_level,
                                      index_code,
                                      index_value,
                                      index_value_pp,
                                      index_value_momgrowth,
                                      index_value_yoy,
                                      index_value_yoygrowth,
                                      index_value_yearend,
                                      index_value_yearendgrowth)
select ${month_id}                                                as month_id,
       'dw_smart_microgrid_grid_info'                             as dimension_type,
       consume_area_id                                            as dimension_id,
       consume_area_name                                          as dimension_name,
       consume_area_level                                         as dimension_level,
       'dw_smart_microgrid_grid_info_last1Months_COUNT_OPEN_DATE' as index_code,
       count_open_date_1844_sum                                   as index_value,
       count_open_date_1844_pp                                    as index_value_pp,
       count_open_date_1844_momgrowth                             as index_value_momgrowth,
       count_open_date_1844_yoy                                   as index_value_yoy,
       count_open_date_1844_yoygrowth                             as index_value_yoygrowth,
       count_open_date_1844_yearend                               as index_value_yearend,
       count_open_date_1844_yearendgrowth                         as index_value_yearendgrowth
from nm.app_1720753214637;
INSERT INTO nm.dim_index_calculate_m (month_id,
                                      dimension_type,
                                      dimension_id,
                                      dimension_name,
                                      dimension_level,
                                      index_code,
                                      index_value,
                                      index_value_pp,
                                      index_value_momgrowth,
                                      index_value_yoy,
                                      index_value_yoygrowth,
                                      index_value_yearend,
                                      index_value_yearendgrowth)
select ${month_id}                                               as month_id,
       'dw_smart_microgrid_grid_info'                            as dimension_type,
       consume_area_id                                           as dimension_id,
       consume_area_name                                         as dimension_name,
       consume_area_level                                        as dimension_level,
       'dw_smart_microgrid_grid_info_last1Years_COUNT_OPEN_DATE' as index_code,
       count_open_date_1846_sum                                  as index_value,
       count_open_date_1846_pp                                   as index_value_pp,
       count_open_date_1846_momgrowth                            as index_value_momgrowth,
       count_open_date_1846_yoy                                  as index_value_yoy,
       count_open_date_1846_yoygrowth                            as index_value_yoygrowth,
       count_open_date_1846_yearend                              as index_value_yearend,
       count_open_date_1846_yearendgrowth                        as index_value_yearendgrowth
from nm.app_1720753214637;
/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_50324520_106_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_92625492_107_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_17059091_108_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_77658129_109_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_81481896_110_${acct};

/*skip=true*/DROP TABLE IF EXISTS nm.app_1720753214637;