package kafka3.service;

import kafka3.model.ObjInfo;

import java.time.LocalDate;
import java.util.List;

public interface ObjInfoService {
    LocalDate selectNow();

    List<ObjInfo> objInfoList();

    List<ObjInfo> selectBatchIds(List<Long> objInfoIds);
}
