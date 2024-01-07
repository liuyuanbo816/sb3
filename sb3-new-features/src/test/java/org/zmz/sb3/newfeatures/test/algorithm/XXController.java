package org.zmz.sb3.newfeatures.test.algorithm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class XXController {

    @Autowired
    XXService xxService;

    @RequestMapping
    public Object test(@RequestBody Obj obj){
        Map<Integer, String> map = xxService.convert(obj.getTimeList());
        List<XXService.XXInnerPair> list = xxService.test(map, Integer.parseInt(obj.getTargetDiff()));
        return list.size();
    }

    @Getter
    @Setter
    static class Obj{
        private List<String> timeList;
        private String targetDiff;
    }
}
