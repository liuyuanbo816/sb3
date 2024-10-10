package zzjjcc.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Employee {
    @ExcelProperty("employeeId")
    private Integer employeeId;
    @ExcelProperty("firstName")
    private String firstName;
    @ExcelProperty("lastName")
    private String lastName;
    @ExcelProperty("email")
    private String email;
    @ExcelProperty("phoneNumber")
    private String phoneNumber;
    @ExcelProperty("hireDate")
    private LocalDate hireDate;
    @ExcelProperty("jobId")
    private String jobId;
    @ExcelProperty("salary")
    private Double salary;
    @ExcelProperty("commissionPct")
    private Double commissionPct;
    @ExcelProperty("managerId")
    private Integer managerId;
    @ExcelProperty("departmentId")
    private Integer departmentId;
}
