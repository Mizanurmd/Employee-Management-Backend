package cns.com.service;

import java.util.List;

import cns.com.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long empId);

	Employee createEmployee(Employee employee);

	Employee updateEmployee(Long empId, Employee employeeDetails);

	void deleteEmployee(Long empId);

}
