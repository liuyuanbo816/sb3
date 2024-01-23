package org.zjc.smalltools.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zjc.smalltools.common.R;
import org.zjc.smalltools.components.SwitchDataSourceComponent;
import org.zjc.smalltools.mapper.PersonMapper;
import org.zjc.smalltools.model.Person;
import org.zjc.smalltools.vo.request.BaseRequest;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    SwitchDataSourceComponent switchDataSourceComponent;

    @PostMapping("/list")
    public R<List<Person>> list(@Validated @RequestBody BaseRequest baseRequest) {
        try {
            Method method = PersonMapper.class.getMethod("list");
            List<Person> res = switchDataSourceComponent.executeMapperMethod(baseRequest, PersonMapper.class, method);
            return R.ok(res);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("list 不方法不存在 PersonMapper ");
        }
    }

}
