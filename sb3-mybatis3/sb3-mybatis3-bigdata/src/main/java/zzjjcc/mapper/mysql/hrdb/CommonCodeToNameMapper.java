package zzjjcc.mapper.mysql.hrdb;

import zzjjcc.dto.CommonCodeToNameQueryDTO;

import java.util.Map;

public interface CommonCodeToNameMapper {
    Map<String, String> commonCodeToName(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO);
}
