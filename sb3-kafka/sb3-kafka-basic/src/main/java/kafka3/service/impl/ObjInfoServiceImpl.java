package kafka3.service.impl;

import jakarta.annotation.Resource;
import kafka3.mapper.dataopen.ObjInfoMapper;
import kafka3.service.ObjInfoService;
import org.springframework.stereotype.Service;

@Service
public class ObjInfoServiceImpl implements ObjInfoService {

    @Resource
    ObjInfoMapper objInfoMapper;

}
