package cns.com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cns.com.model.Employee;
import cns.com.repository.EmployeeRepository;
import cns.com.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Al employee"+ employeeRepository.findAll());
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		return employeeRepository.findById(empId)
				.orElseThrow(() -> new RuntimeException("Employee not found with id " + empId));
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long empId, Employee employeeDetails) {
		Employee employee = getEmployeeById(empId);
		employee.setEmpName(employeeDetails.getEmpName());
		employee.setFname(employeeDetails.getFname());
		employee.setMname(employeeDetails.getMname());
		employee.setDesignation(employeeDetails.getDesignation());
		employee.setGender(employeeDetails.getGender());
		employee.setSalary(employeeDetails.getSalary());
		employee.setRemarks(employeeDetails.getRemarks());
		employee.setPic(employeeDetails.getPic());
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long empId) {
		employeeRepository.deleteById(empId);
	}

}
