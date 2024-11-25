package io.jcz.controller;

import io.jcz.common.R;
import io.jcz.mapper.Tb1Mapper;
import io.jcz.model.Tb1;
import io.jcz.service.Tb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tb1")
public class Tb1Controller {

    @Autowired
    private Tb1Service tb1Service;

    @Autowired
    Tb1Mapper tb1Mapper;

    @GetMapping("/list")
    public R<List<Tb1>> list() {
        return R.ok(tb1Service.findAll());
    }

    @GetMapping("/insert")
    public R<String> insert() {
        Tb1 tb1 = new Tb1();
        tb1.setName("haha");
        tb1.setBirth(new Date());
        tb1Mapper.insert(tb1);

        Tb1 tb2 = new Tb1();
        tb2.setName("gege");
        tb2.setBirth(new Date());
        tb1Mapper.insert(tb2);
        return R.ok("插入成功");
    }

}
