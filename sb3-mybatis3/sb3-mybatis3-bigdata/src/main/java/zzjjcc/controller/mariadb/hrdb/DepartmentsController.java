package zzjjcc.controller.mariadb.hrdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.service.mariadb.hrdb.DepartmentsService;

@RestController
public class DepartmentsController {

    DepartmentsService departmentsService;

    @Autowired
    public void setDepartmentsService(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

}
