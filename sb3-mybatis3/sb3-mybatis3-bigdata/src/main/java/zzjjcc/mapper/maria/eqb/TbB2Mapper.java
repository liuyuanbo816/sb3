package zzjjcc.mapper.maria.eqb;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbB2Mapper {
    void simpleInsertTbB2(@Param("c2") String c2);

    List<Map<String, Object>> list();
}
