package kafka3.service;

import kafka3.model.ObjInfo;

import java.util.List;

public interface ObjInfoService {
    List<ObjInfo> objInfoList();

    List<ObjInfo> selectBatchIds(List<Long> objInfoIds);
}
