package zzjjcc.controller.mariadb.jdbc;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateController {

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @Resource
    List<DataSource> dataSources;

    @PostMapping("/all")
    public R<?> all(@RequestParam Map<String, Object> params) {
        Integer dbIndex = MapUtil.getInt(params, "dbIndex", 1);
        String rawSql = MapUtil.getStr(params, "rawSql");
        if (StrUtil.isNotBlank(rawSql)) {
            jdbcTemplate.setDataSource(dataSources.get(dbIndex));
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(rawSql);
            return R.ok(mapList);
        }
        return R.ok();
    }

}
