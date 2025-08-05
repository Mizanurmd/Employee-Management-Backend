package cns.com.service;

import java.io.IOException;
import java.util.List;

import cns.com.dto.EmployeeDto;
import cns.com.model.Employee;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto) throws IOException;

    Employee updateEmployee(long id,EmployeeDto employeeDto) throws IOException;

    Employee getEmployeeById(long id);

    List<Employee> getAllEmployees();

    void deleteEmployee(long id);

}
