package bilibili.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/foo")
    public Object foo(String foo){
        return foo;
    }

}
