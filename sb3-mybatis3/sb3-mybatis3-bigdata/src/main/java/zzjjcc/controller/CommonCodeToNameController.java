package zzjjcc.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;
import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.service.CommonCodeToNameService;

import java.util.Map;

@RestController
public class CommonCodeToNameController {

    @Resource
    CommonCodeToNameService commonCodeToNameService;

    @PostMapping("/commonCodeToNameQuery")
    public R<Map<String, String>> commonCodeToNameQuery(@RequestBody CommonCodeToNameQueryDTO dto) {
        Map<String, String> map = commonCodeToNameService.commonCodeToName(dto);
        return R.ok(map);
    }

}
