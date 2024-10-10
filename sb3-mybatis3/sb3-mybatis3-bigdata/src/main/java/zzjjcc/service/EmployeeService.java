package zzjjcc.service;

import jakarta.servlet.ServletOutputStream;
import zzjjcc.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee queryEmployeeById(int id);
    void insertEmployee(Employee employee);
    void updateEmployee(int id);
    void deleteEmployee(int id);
    List<Employee> getEmployees();

    void downloadExcel(ServletOutputStream outputStream);
}
