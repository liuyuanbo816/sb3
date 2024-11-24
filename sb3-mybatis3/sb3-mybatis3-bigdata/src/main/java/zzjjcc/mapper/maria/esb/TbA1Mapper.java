package zzjjcc.mapper.maria.esb;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbA1Mapper {
    void simpleInsertTbA1(@Param("c1") String c1);

    List<Map<String, Object>> list();
}
