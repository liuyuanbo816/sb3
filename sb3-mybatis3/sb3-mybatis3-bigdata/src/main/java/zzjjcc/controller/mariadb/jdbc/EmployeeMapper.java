package zzjjcc.controller.mariadb.jdbc;

import org.springframework.jdbc.core.RowMapper;
import zzjjcc.model.mariadb.hrdb.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                .employeeId(rs.getInt("employee_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone_number"))
                .hireDate(rs.getDate("hire_date").toLocalDate())
                .jobId(rs.getString("job_id"))
                .salary(rs.getDouble("salary"))
                .commissionPct(rs.getDouble("commission_pct"))
                .managerId(rs.getInt("manager_id"))
                .departmentId(rs.getInt("department_id"))
                .build();
    }
}
