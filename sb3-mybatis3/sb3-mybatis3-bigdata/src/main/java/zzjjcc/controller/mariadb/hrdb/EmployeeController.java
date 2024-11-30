package zzjjcc.controller.mariadb.hrdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import zzjjcc.service.mariadb.hrdb.EmployeeService;

@Controller
@Slf4j
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

}
