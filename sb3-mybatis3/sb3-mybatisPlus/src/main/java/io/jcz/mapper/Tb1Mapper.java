package io.jcz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.jcz.model.Tb1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Tb1Mapper extends BaseMapper<Tb1> {
    void batchInsert(@Param("list") List<Tb1> list);
}
