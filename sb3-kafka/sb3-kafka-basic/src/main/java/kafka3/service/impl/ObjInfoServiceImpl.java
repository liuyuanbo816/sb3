package kafka3.service.impl;

import jakarta.annotation.Resource;
import kafka3.mapper.dataopen.ObjInfoMapper;
import kafka3.model.ObjInfo;
import kafka3.service.ObjInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ObjInfoServiceImpl implements ObjInfoService {

    @Resource
    ObjInfoMapper objInfoMapper;

    @Override
    public List<ObjInfo> objInfoList() {
        return objInfoMapper.selectList(null);
    }

    @Override
    public List<ObjInfo> selectBatchIds(List<Long> objInfoIds) {
        return objInfoMapper.selectBatchIds(objInfoIds);
    }
}
