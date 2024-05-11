package zzjjcc.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.mapper.mysql.CommonCodeToNameMapper;
import zzjjcc.service.CommonCodeToNameService;

import java.util.Map;

@Service
public class CommonCodeToNameServiceImpl implements CommonCodeToNameService {

    @Resource
    CommonCodeToNameMapper commonCodeToNameMapper;

    @Override
    public Map<String, String> commonCodeToName(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO) {
        return commonCodeToNameMapper.commonCodeToName(commonCodeToNameQueryDTO);
    }
}
