package zzjjcc.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.EmployeeMapper;
import zzjjcc.model.Employee;
import zzjjcc.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public Employee queryEmployeeById(int id) {
        return employeeMapper.queryEmployeeById(id);
    }

    @Override
    public void insertEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(int id) {

    }

    @Override
    public void deleteEmployee(int id) {

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }
}
