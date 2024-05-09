package zzjjcc.service;

import zzjjcc.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee queryEmployeeById(int id);
    void insertEmployee(Employee employee);
    void updateEmployee(int id);
    void deleteEmployee(int id);
    List<Employee> getEmployees();
}
