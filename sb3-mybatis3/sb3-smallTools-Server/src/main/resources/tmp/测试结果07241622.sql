DELETE
FROM nm.dim_index_calculate_m
where index_code in ('dw_smart_microgrid_grid_info_last1Months_SUM_CONNECT_NUM')
  and month_id = ${acct};
/*skip=true*/ALTER TABLE nm.dim_index_calculate_m
    ADD PARTITION (PARTITION month_id_${acct} VALUES IN (${acct}));
/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_72361449_91_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_81290683_92_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_62400742_93_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_61655139_94_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_13668438_95_${acct};

/*skip=true*/DROP TABLE IF EXISTS nm.app_1721741343969;

CREATE TABLE smart_test.tmp_72361449_91_${acct} AS
select tb15.consume_area_id,
       tb15.consume_area_name,
       tb15.consume_parent_area_id,
       tb15.consume_area_level,
       tb15.sum_connect_num_1951_SUM                                           as sum_connect_num_1951_SUM,
       (tb15.sum_connect_num_1951_pp - tb16.sum_connect_num_1951_pp)           as sum_connect_num_1951_pp,
       (case
            when tb16.sum_connect_num_1951_momGrowth = 0 then 0
            else (tb15.sum_connect_num_1951_momGrowth - tb16.sum_connect_num_1951_momGrowth) /
                 tb16.sum_connect_num_1951_momGrowth end)                      as sum_connect_num_1951_momGrowth,
       (tb15.sum_connect_num_1951_yoy - tb17.sum_connect_num_1951_yoy)         as sum_connect_num_1951_yoy,
       (case
            when tb17.sum_connect_num_1951_yoyGrowth = 0 then 0
            else (tb15.sum_connect_num_1951_yoyGrowth - tb17.sum_connect_num_1951_yoyGrowth) /
                 tb17.sum_connect_num_1951_yoyGrowth end)                      as sum_connect_num_1951_yoyGrowth,
       (tb15.sum_connect_num_1951_yearEnd - tb18.sum_connect_num_1951_yearEnd) as sum_connect_num_1951_yearEnd,
       (case
            when tb18.sum_connect_num_1951_yearEndGrowth = 0 then 0
            else (tb15.sum_connect_num_1951_yearEndGrowth - tb18.sum_connect_num_1951_yearEndGrowth) /
                 tb18.sum_connect_num_1951_yearEndGrowth end)                  as sum_connect_num_1951_yearEndGrowth
from (select tb2.MICRO_GRID_ID             as consume_area_id,
             tb2.MICRO_GRID_NAME           as consume_area_name,
             tb2.GRID_ID                   as consume_parent_area_id,
             5                             as consume_area_level,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_SUM,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_pp,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_yoy,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
             SUM(tb1.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
      from smart_test.micro_grid_index1 tb1
               inner join smart_test.dw_smart_microgrid_grid_info tb2 on tb1.MICRO_GRID_ID = tb2.MICRO_GRID_ID
      where (tb1.month_id = ${month_id})
      group by tb2.MICRO_GRID_ID, tb2.MICRO_GRID_NAME, tb2.GRID_ID) tb15
         left join (select tb4.MICRO_GRID_ID             as consume_area_id,
                           tb4.MICRO_GRID_NAME           as consume_area_name,
                           tb4.GRID_ID                   as consume_parent_area_id,
                           5                             as consume_area_level,
                           0                             as sum_connect_num_1951_SUM,
                           SUM(tb3.sum_connect_num_1951) as sum_connect_num_1951_pp,
                           SUM(tb3.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
                           0                             as sum_connect_num_1951_yoy,
                           0                             as sum_connect_num_1951_yoyGrowth,
                           0                             as sum_connect_num_1951_yearEnd,
                           0                             as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb3
                             inner join smart_test.dw_smart_microgrid_grid_info tb4
                                        on tb3.MICRO_GRID_ID = tb4.MICRO_GRID_ID
                    where (tb3.month_id = $add_month(${month_id}, -1))
                    group by tb4.MICRO_GRID_ID, tb4.MICRO_GRID_NAME, tb4.GRID_ID) tb16
                   on tb15.consume_area_id = tb16.consume_area_id and tb15.consume_area_name = tb16.consume_area_name
         left join (select tb8.MICRO_GRID_ID             as consume_area_id,
                           tb8.MICRO_GRID_NAME           as consume_area_name,
                           tb8.GRID_ID                   as consume_parent_area_id,
                           5                             as consume_area_level,
                           0                             as sum_connect_num_1951_SUM,
                           0                             as sum_connect_num_1951_pp,
                           0                             as sum_connect_num_1951_momGrowth,
                           SUM(tb7.sum_connect_num_1951) as sum_connect_num_1951_yoy,
                           SUM(tb7.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
                           0                             as sum_connect_num_1951_yearEnd,
                           0                             as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb7
                             inner join smart_test.dw_smart_microgrid_grid_info tb8
                                        on tb7.MICRO_GRID_ID = tb8.MICRO_GRID_ID
                    where (tb7.month_id = $add_month(${month_id}, -1, 0))
                    group by tb8.MICRO_GRID_ID, tb8.MICRO_GRID_NAME, tb8.GRID_ID) tb17
                   on tb15.consume_area_id = tb17.consume_area_id and tb15.consume_area_name = tb17.consume_area_name
         left join (select tb12.MICRO_GRID_ID             as consume_area_id,
                           tb12.MICRO_GRID_NAME           as consume_area_name,
                           tb12.GRID_ID                   as consume_parent_area_id,
                           5                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           SUM(tb11.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
                           SUM(tb11.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb11
                             inner join smart_test.dw_smart_microgrid_grid_info tb12
                                        on tb11.MICRO_GRID_ID = tb12.MICRO_GRID_ID
                    where (tb11.month_id = $add_month(${month_id}, -1, L))
                    group by tb12.MICRO_GRID_ID, tb12.MICRO_GRID_NAME, tb12.GRID_ID) tb18
                   on tb15.consume_area_id = tb18.consume_area_id and tb15.consume_area_name = tb18.consume_area_name;
CREATE TABLE smart_test.tmp_81290683_92_${acct} AS
select tb33.consume_area_id,
       tb33.consume_area_name,
       tb33.consume_parent_area_id,
       tb33.consume_area_level,
       tb33.sum_connect_num_1951_SUM                                           as sum_connect_num_1951_SUM,
       (tb33.sum_connect_num_1951_pp - tb34.sum_connect_num_1951_pp)           as sum_connect_num_1951_pp,
       (case
            when tb34.sum_connect_num_1951_momGrowth = 0 then 0
            else (tb33.sum_connect_num_1951_momGrowth - tb34.sum_connect_num_1951_momGrowth) /
                 tb34.sum_connect_num_1951_momGrowth end)                      as sum_connect_num_1951_momGrowth,
       (tb33.sum_connect_num_1951_yoy - tb35.sum_connect_num_1951_yoy)         as sum_connect_num_1951_yoy,
       (case
            when tb35.sum_connect_num_1951_yoyGrowth = 0 then 0
            else (tb33.sum_connect_num_1951_yoyGrowth - tb35.sum_connect_num_1951_yoyGrowth) /
                 tb35.sum_connect_num_1951_yoyGrowth end)                      as sum_connect_num_1951_yoyGrowth,
       (tb33.sum_connect_num_1951_yearEnd - tb36.sum_connect_num_1951_yearEnd) as sum_connect_num_1951_yearEnd,
       (case
            when tb36.sum_connect_num_1951_yearEndGrowth = 0 then 0
            else (tb33.sum_connect_num_1951_yearEndGrowth - tb36.sum_connect_num_1951_yearEndGrowth) /
                 tb36.sum_connect_num_1951_yearEndGrowth end)                  as sum_connect_num_1951_yearEndGrowth
from (select tb20.GRID_ID                   as consume_area_id,
             tb20.GRID_NAME                 as consume_area_name,
             tb20.BUSN_DEPT_CODE            as consume_parent_area_id,
             4                              as consume_area_level,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_SUM,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_pp,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_yoy,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
             SUM(tb19.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
      from smart_test.micro_grid_index1 tb19
               inner join smart_test.dw_smart_microgrid_grid_info tb20 on tb19.MICRO_GRID_ID = tb20.MICRO_GRID_ID
      where (tb19.month_id = ${month_id})
      group by tb20.GRID_ID, tb20.GRID_NAME, tb20.BUSN_DEPT_CODE) tb33
         left join (select tb22.GRID_ID                   as consume_area_id,
                           tb22.GRID_NAME                 as consume_area_name,
                           tb22.BUSN_DEPT_CODE            as consume_parent_area_id,
                           4                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           SUM(tb21.sum_connect_num_1951) as sum_connect_num_1951_pp,
                           SUM(tb21.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb21
                             inner join smart_test.dw_smart_microgrid_grid_info tb22
                                        on tb21.MICRO_GRID_ID = tb22.MICRO_GRID_ID
                    where (tb21.month_id = $add_month(${month_id}, -1))
                    group by tb22.GRID_ID, tb22.GRID_NAME, tb22.BUSN_DEPT_CODE) tb34
                   on tb33.consume_area_id = tb34.consume_area_id and tb33.consume_area_name = tb34.consume_area_name
         left join (select tb26.GRID_ID                   as consume_area_id,
                           tb26.GRID_NAME                 as consume_area_name,
                           tb26.BUSN_DEPT_CODE            as consume_parent_area_id,
                           4                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           SUM(tb25.sum_connect_num_1951) as sum_connect_num_1951_yoy,
                           SUM(tb25.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb25
                             inner join smart_test.dw_smart_microgrid_grid_info tb26
                                        on tb25.MICRO_GRID_ID = tb26.MICRO_GRID_ID
                    where (tb25.month_id = $add_month(${month_id}, -1, 0))
                    group by tb26.GRID_ID, tb26.GRID_NAME, tb26.BUSN_DEPT_CODE) tb35
                   on tb33.consume_area_id = tb35.consume_area_id and tb33.consume_area_name = tb35.consume_area_name
         left join (select tb30.GRID_ID                   as consume_area_id,
                           tb30.GRID_NAME                 as consume_area_name,
                           tb30.BUSN_DEPT_CODE            as consume_parent_area_id,
                           4                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           SUM(tb29.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
                           SUM(tb29.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb29
                             inner join smart_test.dw_smart_microgrid_grid_info tb30
                                        on tb29.MICRO_GRID_ID = tb30.MICRO_GRID_ID
                    where (tb29.month_id = $add_month(${month_id}, -1, L))
                    group by tb30.GRID_ID, tb30.GRID_NAME, tb30.BUSN_DEPT_CODE) tb36
                   on tb33.consume_area_id = tb36.consume_area_id and tb33.consume_area_name = tb36.consume_area_name;
CREATE TABLE smart_test.tmp_62400742_93_${acct} AS
select tb51.consume_area_id,
       tb51.consume_area_name,
       tb51.consume_parent_area_id,
       tb51.consume_area_level,
       tb51.sum_connect_num_1951_SUM                                           as sum_connect_num_1951_SUM,
       (tb51.sum_connect_num_1951_pp - tb52.sum_connect_num_1951_pp)           as sum_connect_num_1951_pp,
       (case
            when tb52.sum_connect_num_1951_momGrowth = 0 then 0
            else (tb51.sum_connect_num_1951_momGrowth - tb52.sum_connect_num_1951_momGrowth) /
                 tb52.sum_connect_num_1951_momGrowth end)                      as sum_connect_num_1951_momGrowth,
       (tb51.sum_connect_num_1951_yoy - tb53.sum_connect_num_1951_yoy)         as sum_connect_num_1951_yoy,
       (case
            when tb53.sum_connect_num_1951_yoyGrowth = 0 then 0
            else (tb51.sum_connect_num_1951_yoyGrowth - tb53.sum_connect_num_1951_yoyGrowth) /
                 tb53.sum_connect_num_1951_yoyGrowth end)                      as sum_connect_num_1951_yoyGrowth,
       (tb51.sum_connect_num_1951_yearEnd - tb54.sum_connect_num_1951_yearEnd) as sum_connect_num_1951_yearEnd,
       (case
            when tb54.sum_connect_num_1951_yearEndGrowth = 0 then 0
            else (tb51.sum_connect_num_1951_yearEndGrowth - tb54.sum_connect_num_1951_yearEndGrowth) /
                 tb54.sum_connect_num_1951_yearEndGrowth end)                  as sum_connect_num_1951_yearEndGrowth
from (select tb38.BUSN_DEPT_CODE            as consume_area_id,
             tb38.BUSN_DEPT_NAME            as consume_area_name,
             tb38.CITY_ID                   as consume_parent_area_id,
             3                              as consume_area_level,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_SUM,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_pp,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_yoy,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
             SUM(tb37.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
      from smart_test.micro_grid_index1 tb37
               inner join smart_test.dw_smart_microgrid_grid_info tb38 on tb37.MICRO_GRID_ID = tb38.MICRO_GRID_ID
      where (tb37.month_id = ${month_id})
      group by tb38.BUSN_DEPT_CODE, tb38.BUSN_DEPT_NAME, tb38.CITY_ID) tb51
         left join (select tb40.BUSN_DEPT_CODE            as consume_area_id,
                           tb40.BUSN_DEPT_NAME            as consume_area_name,
                           tb40.CITY_ID                   as consume_parent_area_id,
                           3                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           SUM(tb39.sum_connect_num_1951) as sum_connect_num_1951_pp,
                           SUM(tb39.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb39
                             inner join smart_test.dw_smart_microgrid_grid_info tb40
                                        on tb39.MICRO_GRID_ID = tb40.MICRO_GRID_ID
                    where (tb39.month_id = $add_month(${month_id}, -1))
                    group by tb40.BUSN_DEPT_CODE, tb40.BUSN_DEPT_NAME, tb40.CITY_ID) tb52
                   on tb51.consume_area_id = tb52.consume_area_id and tb51.consume_area_name = tb52.consume_area_name
         left join (select tb44.BUSN_DEPT_CODE            as consume_area_id,
                           tb44.BUSN_DEPT_NAME            as consume_area_name,
                           tb44.CITY_ID                   as consume_parent_area_id,
                           3                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           SUM(tb43.sum_connect_num_1951) as sum_connect_num_1951_yoy,
                           SUM(tb43.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb43
                             inner join smart_test.dw_smart_microgrid_grid_info tb44
                                        on tb43.MICRO_GRID_ID = tb44.MICRO_GRID_ID
                    where (tb43.month_id = $add_month(${month_id}, -1, 0))
                    group by tb44.BUSN_DEPT_CODE, tb44.BUSN_DEPT_NAME, tb44.CITY_ID) tb53
                   on tb51.consume_area_id = tb53.consume_area_id and tb51.consume_area_name = tb53.consume_area_name
         left join (select tb48.BUSN_DEPT_CODE            as consume_area_id,
                           tb48.BUSN_DEPT_NAME            as consume_area_name,
                           tb48.CITY_ID                   as consume_parent_area_id,
                           3                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           SUM(tb47.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
                           SUM(tb47.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb47
                             inner join smart_test.dw_smart_microgrid_grid_info tb48
                                        on tb47.MICRO_GRID_ID = tb48.MICRO_GRID_ID
                    where (tb47.month_id = $add_month(${month_id}, -1, L))
                    group by tb48.BUSN_DEPT_CODE, tb48.BUSN_DEPT_NAME, tb48.CITY_ID) tb54
                   on tb51.consume_area_id = tb54.consume_area_id and tb51.consume_area_name = tb54.consume_area_name;
CREATE TABLE smart_test.tmp_61655139_94_${acct} AS
select tb69.consume_area_id,
       tb69.consume_area_name,
       tb69.consume_parent_area_id,
       tb69.consume_area_level,
       tb69.sum_connect_num_1951_SUM                                           as sum_connect_num_1951_SUM,
       (tb69.sum_connect_num_1951_pp - tb70.sum_connect_num_1951_pp)           as sum_connect_num_1951_pp,
       (case
            when tb70.sum_connect_num_1951_momGrowth = 0 then 0
            else (tb69.sum_connect_num_1951_momGrowth - tb70.sum_connect_num_1951_momGrowth) /
                 tb70.sum_connect_num_1951_momGrowth end)                      as sum_connect_num_1951_momGrowth,
       (tb69.sum_connect_num_1951_yoy - tb71.sum_connect_num_1951_yoy)         as sum_connect_num_1951_yoy,
       (case
            when tb71.sum_connect_num_1951_yoyGrowth = 0 then 0
            else (tb69.sum_connect_num_1951_yoyGrowth - tb71.sum_connect_num_1951_yoyGrowth) /
                 tb71.sum_connect_num_1951_yoyGrowth end)                      as sum_connect_num_1951_yoyGrowth,
       (tb69.sum_connect_num_1951_yearEnd - tb72.sum_connect_num_1951_yearEnd) as sum_connect_num_1951_yearEnd,
       (case
            when tb72.sum_connect_num_1951_yearEndGrowth = 0 then 0
            else (tb69.sum_connect_num_1951_yearEndGrowth - tb72.sum_connect_num_1951_yearEndGrowth) /
                 tb72.sum_connect_num_1951_yearEndGrowth end)                  as sum_connect_num_1951_yearEndGrowth
from (select tb56.CITY_ID                   as consume_area_id,
             tb56.CITY_NAME                 as consume_area_name,
             tb56.PROV_ID                   as consume_parent_area_id,
             2                              as consume_area_level,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_SUM,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_pp,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_yoy,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
             SUM(tb55.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
      from smart_test.micro_grid_index1 tb55
               inner join smart_test.dw_smart_microgrid_grid_info tb56 on tb55.MICRO_GRID_ID = tb56.MICRO_GRID_ID
      where (tb55.month_id = ${month_id})
      group by tb56.CITY_ID, tb56.CITY_NAME, tb56.PROV_ID) tb69
         left join (select tb58.CITY_ID                   as consume_area_id,
                           tb58.CITY_NAME                 as consume_area_name,
                           tb58.PROV_ID                   as consume_parent_area_id,
                           2                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           SUM(tb57.sum_connect_num_1951) as sum_connect_num_1951_pp,
                           SUM(tb57.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb57
                             inner join smart_test.dw_smart_microgrid_grid_info tb58
                                        on tb57.MICRO_GRID_ID = tb58.MICRO_GRID_ID
                    where (tb57.month_id = $add_month(${month_id}, -1))
                    group by tb58.CITY_ID, tb58.CITY_NAME, tb58.PROV_ID) tb70
                   on tb69.consume_area_id = tb70.consume_area_id and tb69.consume_area_name = tb70.consume_area_name
         left join (select tb62.CITY_ID                   as consume_area_id,
                           tb62.CITY_NAME                 as consume_area_name,
                           tb62.PROV_ID                   as consume_parent_area_id,
                           2                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           SUM(tb61.sum_connect_num_1951) as sum_connect_num_1951_yoy,
                           SUM(tb61.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb61
                             inner join smart_test.dw_smart_microgrid_grid_info tb62
                                        on tb61.MICRO_GRID_ID = tb62.MICRO_GRID_ID
                    where (tb61.month_id = $add_month(${month_id}, -1, 0))
                    group by tb62.CITY_ID, tb62.CITY_NAME, tb62.PROV_ID) tb71
                   on tb69.consume_area_id = tb71.consume_area_id and tb69.consume_area_name = tb71.consume_area_name
         left join (select tb66.CITY_ID                   as consume_area_id,
                           tb66.CITY_NAME                 as consume_area_name,
                           tb66.PROV_ID                   as consume_parent_area_id,
                           2                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           SUM(tb65.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
                           SUM(tb65.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb65
                             inner join smart_test.dw_smart_microgrid_grid_info tb66
                                        on tb65.MICRO_GRID_ID = tb66.MICRO_GRID_ID
                    where (tb65.month_id = $add_month(${month_id}, -1, L))
                    group by tb66.CITY_ID, tb66.CITY_NAME, tb66.PROV_ID) tb72
                   on tb69.consume_area_id = tb72.consume_area_id and tb69.consume_area_name = tb72.consume_area_name;
CREATE TABLE smart_test.tmp_13668438_95_${acct} AS
select tb87.consume_area_id,
       tb87.consume_area_name,
       tb87.consume_parent_area_id,
       tb87.consume_area_level,
       tb87.sum_connect_num_1951_SUM                                           as sum_connect_num_1951_SUM,
       (tb87.sum_connect_num_1951_pp - tb88.sum_connect_num_1951_pp)           as sum_connect_num_1951_pp,
       (case
            when tb88.sum_connect_num_1951_momGrowth = 0 then 0
            else (tb87.sum_connect_num_1951_momGrowth - tb88.sum_connect_num_1951_momGrowth) /
                 tb88.sum_connect_num_1951_momGrowth end)                      as sum_connect_num_1951_momGrowth,
       (tb87.sum_connect_num_1951_yoy - tb89.sum_connect_num_1951_yoy)         as sum_connect_num_1951_yoy,
       (case
            when tb89.sum_connect_num_1951_yoyGrowth = 0 then 0
            else (tb87.sum_connect_num_1951_yoyGrowth - tb89.sum_connect_num_1951_yoyGrowth) /
                 tb89.sum_connect_num_1951_yoyGrowth end)                      as sum_connect_num_1951_yoyGrowth,
       (tb87.sum_connect_num_1951_yearEnd - tb90.sum_connect_num_1951_yearEnd) as sum_connect_num_1951_yearEnd,
       (case
            when tb90.sum_connect_num_1951_yearEndGrowth = 0 then 0
            else (tb87.sum_connect_num_1951_yearEndGrowth - tb90.sum_connect_num_1951_yearEndGrowth) /
                 tb90.sum_connect_num_1951_yearEndGrowth end)                  as sum_connect_num_1951_yearEndGrowth
from (select tb74.PROV_ID                   as consume_area_id,
             tb74.PROV_NAME                 as consume_area_name,
             null                           as consume_parent_area_id,
             1                              as consume_area_level,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_SUM,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_pp,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_yoy,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
             SUM(tb73.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
      from smart_test.micro_grid_index1 tb73
               inner join smart_test.dw_smart_microgrid_grid_info tb74 on tb73.MICRO_GRID_ID = tb74.MICRO_GRID_ID
      where (tb73.month_id = ${month_id})
      group by tb74.PROV_ID, tb74.PROV_NAME) tb87
         left join (select tb76.PROV_ID                   as consume_area_id,
                           tb76.PROV_NAME                 as consume_area_name,
                           null                           as consume_parent_area_id,
                           1                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           SUM(tb75.sum_connect_num_1951) as sum_connect_num_1951_pp,
                           SUM(tb75.sum_connect_num_1951) as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb75
                             inner join smart_test.dw_smart_microgrid_grid_info tb76
                                        on tb75.MICRO_GRID_ID = tb76.MICRO_GRID_ID
                    where (tb75.month_id = $add_month(${month_id}, -1))
                    group by tb76.PROV_ID, tb76.PROV_NAME) tb88
                   on tb87.consume_area_id = tb88.consume_area_id and tb87.consume_area_name = tb88.consume_area_name
         left join (select tb80.PROV_ID                   as consume_area_id,
                           tb80.PROV_NAME                 as consume_area_name,
                           null                           as consume_parent_area_id,
                           1                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           SUM(tb79.sum_connect_num_1951) as sum_connect_num_1951_yoy,
                           SUM(tb79.sum_connect_num_1951) as sum_connect_num_1951_yoyGrowth,
                           0                              as sum_connect_num_1951_yearEnd,
                           0                              as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb79
                             inner join smart_test.dw_smart_microgrid_grid_info tb80
                                        on tb79.MICRO_GRID_ID = tb80.MICRO_GRID_ID
                    where (tb79.month_id = $add_month(${month_id}, -1, 0))
                    group by tb80.PROV_ID, tb80.PROV_NAME) tb89
                   on tb87.consume_area_id = tb89.consume_area_id and tb87.consume_area_name = tb89.consume_area_name
         left join (select tb84.PROV_ID                   as consume_area_id,
                           tb84.PROV_NAME                 as consume_area_name,
                           null                           as consume_parent_area_id,
                           1                              as consume_area_level,
                           0                              as sum_connect_num_1951_SUM,
                           0                              as sum_connect_num_1951_pp,
                           0                              as sum_connect_num_1951_momGrowth,
                           0                              as sum_connect_num_1951_yoy,
                           0                              as sum_connect_num_1951_yoyGrowth,
                           SUM(tb83.sum_connect_num_1951) as sum_connect_num_1951_yearEnd,
                           SUM(tb83.sum_connect_num_1951) as sum_connect_num_1951_yearEndGrowth
                    from smart_test.micro_grid_index1 tb83
                             inner join smart_test.dw_smart_microgrid_grid_info tb84
                                        on tb83.MICRO_GRID_ID = tb84.MICRO_GRID_ID
                    where (tb83.month_id = $add_month(${month_id}, -1, L))
                    group by tb84.PROV_ID, tb84.PROV_NAME) tb90
                   on tb87.consume_area_id = tb90.consume_area_id and tb87.consume_area_name = tb90.consume_area_name;
CREATE TABLE nm.app_1721741343969 AS
select ${month_id}                                as month_id,
       t1.consume_area_id                         as consume_area_id/**MICRO_GRID_ID*/,
       t1.consume_area_name                       as consume_area_name/**MICRO_GRID_NAME*/,
       t1.consume_parent_area_id                  as consume_parent_area_id/**MICRO_GRID_NAME*/,
       t1.consume_area_level                      as consume_area_level,
       SUM(t1.sum_connect_num_1951_SUM)           as sum_connect_num_1951_SUM/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_pp)            as sum_connect_num_1951_pp/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_momGrowth)     as sum_connect_num_1951_momGrowth/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_yoy)           as sum_connect_num_1951_yoy/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_yoyGrowth)     as sum_connect_num_1951_yoyGrowth/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_yearEnd)       as sum_connect_num_1951_yearEnd/**过去1月归属微网格收入总和*/,
       SUM(t1.sum_connect_num_1951_yearEndGrowth) as sum_connect_num_1951_yearEndGrowth/**过去1月归属微网格收入总和*/
from (select *
      from smart_test.tmp_72361449_91_${acct}
      union all
      select *
      from smart_test.tmp_81290683_92_${acct}
      union all
      select *
      from smart_test.tmp_62400742_93_${acct}
      union all
      select *
      from smart_test.tmp_61655139_94_${acct}
      union all
      select *
      from smart_test.tmp_13668438_95_${acct}) t1
group by t1.consume_area_id, t1.consume_area_name, t1.consume_parent_area_id, t1.consume_area_level;;
INSERT INTO nm.dim_index_calculate_m (month_id,
                                      dimension_type,
                                      dimension_id,
                                      dimension_name,
                                      parent_dimension_id,
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
       consume_parent_area_id                                     as parent_dimension_id,
       consume_area_level                                         as dimension_level,
       'dw_smart_microgrid_grid_info_last1Months_SUM_CONNECT_NUM' as index_code,
       sum_connect_num_1951_sum                                   as index_value,
       sum_connect_num_1951_pp                                    as index_value_pp,
       sum_connect_num_1951_momgrowth * 100                       as index_value_momgrowth,
       sum_connect_num_1951_yoy                                   as index_value_yoy,
       sum_connect_num_1951_yoygrowth * 100                       as index_value_yoygrowth,
       sum_connect_num_1951_yearend                               as index_value_yearend,
       sum_connect_num_1951_yearendgrowth * 100                   as index_value_yearendgrowth
from nm.app_1721741343969;
/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_72361449_91_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_81290683_92_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_62400742_93_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_61655139_94_${acct};

/*skip=true*/DROP TABLE IF EXISTS smart_test.tmp_13668438_95_${acct};

/*skip=true*/DROP TABLE IF EXISTS nm.app_1721741343969;