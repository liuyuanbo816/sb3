package zzjjcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zzjjcc.model.Employee;

import java.util.List;

@Controller
public class EmployeeController {

    @GetMapping(value = {"/", "/index"})
    public String toExecPage() {
        return "index";
    }

    @GetMapping("/getEmployees")
    @ResponseBody
    public List<Employee> getEmployees() {
        return null;
    }

}
