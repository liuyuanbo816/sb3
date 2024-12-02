package zzjjcc.controller.mariadb.mytest;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;
import zzjjcc.mapper.mariadb.mytest.BigPersonMapper;
import zzjjcc.model.mariadb.mytest.BigPerson;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bigPerson")
@Slf4j
public class BigPersonController {

    @Resource
    BigPersonMapper bigPersonMapper;

    @PostMapping("/lst1")
    public R<List<BigPerson>> lst1(@RequestBody Map<String, Object> param) {
        long start = System.currentTimeMillis();
        List<BigPerson> lst1 = bigPersonMapper.lst1(param);
        long end = System.currentTimeMillis();
        log.info("耗时: {} ", end - start);
        return R.ok(lst1);
    }

}
