package zzjjcc.service.my;

import io.jcz.annotation.DDS;
import io.jcz.annotation.DDSTransactional;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.maria.eqb.TbB2Mapper;

import java.util.List;
import java.util.Map;

@Service
@DDS("eqb")
public class MyEqbService {

    @Resource
    TbB2Mapper tbB2Mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @DDSTransactional
    public void simpleInsertTbB2(String col) {
        //tbB2Mapper.simpleInsertTbB2(col);
        int i = jdbcTemplate.update("insert into tb_b2 (c2) values (?)", col);
        System.out.println(i);
        //int i = 1 / 0;
    }

    public List<Map<String, Object>> list() {
        return tbB2Mapper.list();
    }

}
