package io.jcz.controller;

import io.jcz.common.R;
import io.jcz.model.Tb1;
import io.jcz.service.Tb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tb1")
public class Tb1Controller {

    @Autowired
    private Tb1Service tb1Service;

    @GetMapping("/list")
    public R<List<Tb1>> list() {
        return R.ok(tb1Service.findAll());
    }

}
