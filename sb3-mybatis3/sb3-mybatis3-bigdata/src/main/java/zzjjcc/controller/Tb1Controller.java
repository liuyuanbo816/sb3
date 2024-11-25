package zzjjcc.controller;

import io.jcz.annotation.DDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;
import zzjjcc.service.my.Tb1Service;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tb1")
public class Tb1Controller {

    @Autowired
    Tb1Service tb1Service;

    @DDS("mytest")
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list() {
        List<Map<String, Object>> mapList = tb1Service.list();
        return R.ok(mapList);
    }

}
