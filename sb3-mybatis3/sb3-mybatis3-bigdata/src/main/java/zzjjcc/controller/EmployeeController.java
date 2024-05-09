package zzjjcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import zzjjcc.model.Employee;
import zzjjcc.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    EmployeeService employeeServiceImpl;

    @Autowired
    public void setEmployeeServiceImpl(EmployeeService employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping(value = {"/", "/index"})
    public String toExecPage() {
        return "index";
    }

    @GetMapping("/queryEmployeeById/{id}")
    @ResponseBody
    public Employee queryEmployeeById(@PathVariable("id") int id) {
        return employeeServiceImpl.queryEmployeeById(id);
    }

    @GetMapping("/getEmployees")
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeServiceImpl.getEmployees();
    }

}
