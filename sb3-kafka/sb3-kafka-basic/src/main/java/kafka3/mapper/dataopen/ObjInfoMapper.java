package kafka3.mapper.dataopen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kafka3.model.ObjInfo;

import java.time.LocalDate;

public interface ObjInfoMapper extends BaseMapper<ObjInfo> {
    LocalDate selectNow();
}
