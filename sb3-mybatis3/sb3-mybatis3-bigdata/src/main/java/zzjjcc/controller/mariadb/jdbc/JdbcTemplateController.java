package zzjjcc.controller.mariadb.jdbc;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateController {

    @Resource
    List<DataSource> dataSources;

    @PostMapping("/all")
    public R<?> all(@RequestBody Map<String, Object> params) throws SQLException {
        Integer dbIndex = MapUtil.getInt(params, "dbIndex", 1);
        String rawSql = MapUtil.getStr(params, "rawSql");
        if (StrUtil.isNotBlank(rawSql)) {
            Connection conn = DataSourceUtils.getConnection(dataSources.get(dbIndex));
            String schema = conn.getSchema();
            DatabaseMetaData metaData = conn.getMetaData();
            PreparedStatement preparedStatement = conn.prepareStatement(rawSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSources.get(dbIndex));
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(rawSql);
            return R.ok(mapList);
        }
        return R.ok();
    }

}
