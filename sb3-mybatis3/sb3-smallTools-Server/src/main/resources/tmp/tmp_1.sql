select tb4.area_id                                     as consume_area_id,
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
group by tb4.area_id, tb4.area_name