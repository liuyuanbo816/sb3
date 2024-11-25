package zzjjcc.service.my;

import io.jcz.annotation.DDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DDS("mytest")
public class Tb1Service {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> list() {
        return jdbcTemplate.queryForList("select * from tb1");
    }

}
