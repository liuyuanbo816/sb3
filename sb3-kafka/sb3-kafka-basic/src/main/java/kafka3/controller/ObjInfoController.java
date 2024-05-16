package kafka3.controller;

import kafka3.service.ObjInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/objInfo")
public class ObjInfoController {

    ObjInfoService objInfoServiceImpl;

    @Autowired
    public void setObjInfoServiceImpl(ObjInfoService objInfoServiceImpl) {
        this.objInfoServiceImpl = objInfoServiceImpl;
    }
}
