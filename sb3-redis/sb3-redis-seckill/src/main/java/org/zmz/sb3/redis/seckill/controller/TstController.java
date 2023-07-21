package org.zmz.sb3.redis.seckill.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.zmz.sb3.redis.seckill.common.R;
import org.zmz.sb3.redis.seckill.service.RedisUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.zmz.sb3.redis.seckill.common.RedisConstants.TST;

@RestController
public class TstController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/tst1")
    public R<Tst1Response> tst1(HttpServletRequest httpServletRequest) throws Exception {
        HandlerExecutionChain handlerExecutionChain = requestMappingHandlerMapping.getHandler(httpServletRequest);
        assert handlerExecutionChain != null;
        HandlerMethod handlerMethod = (HandlerMethod) handlerExecutionChain.getHandler();
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();

        Tst1Response rs = new Tst1Response();
        rs.setName("哈哈");
        rs.setAge(1);
        rs.setHobbies(List.of("乒乓", "baseball", "游泳"));
        Cat cat = new Cat();
        cat.setCatName("咖啡猫");
        Fish fish = new Fish();
        fish.setFishName("鲢鱼");
        cat.setFish(fish);

        rs.setCatMap(Map.of("kaiFei", cat));

        redisUtil.set(TST + methodName, rs);
        return R.success(rs);
    }

    @GetMapping("/tst2")
    public R<Tst1Response> tst2() {
        Tst1Response rs = redisUtil.get("rds:tst:tst1", Tst1Response.class);
        return R.success(rs);
    }


    @Getter
    @Setter
    @ToString
    static class Tst1Response {
        private String name;
        private int age;
        private List<String> hobbies;
        private Map<String, Cat> catMap;
    }

    @Getter
    @Setter
    @ToString
    static class Cat {
        private String catName;
        private Fish fish;
    }

    @Getter
    @Setter
    @ToString
    static class Fish {
        private String fishName;
    }

}
