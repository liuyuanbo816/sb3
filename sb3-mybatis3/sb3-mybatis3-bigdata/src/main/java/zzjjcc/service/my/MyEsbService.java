package zzjjcc.service.my;

import io.jcz.annotation.DDS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.maria.esb.TbA1Mapper;

import java.util.List;
import java.util.Map;

@Service
@DDS("esb")
public class MyEsbService {

    @Resource
    TbA1Mapper tbA1Mapper;

    public void simpleInsertTbA1(String col) {
        tbA1Mapper.simpleInsertTbA1(col);
    }

    public List<Map<String, Object>> list() {
        return tbA1Mapper.list();
    }

}
