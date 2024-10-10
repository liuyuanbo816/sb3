package zzjjcc.service;

import zzjjcc.dto.CommonCodeToNameQueryDTO;

import java.util.Map;

public interface CommonCodeToNameService {
    Map<String, String> commonCodeToName(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO);
}
