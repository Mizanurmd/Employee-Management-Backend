package cns.com.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cns.com.model.Employee;
import cns.com.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		System.out.println("Al employee" + employeeService.getAllEmployees());
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{empId}")
	public Employee getEmployeeById(@PathVariable Long empId) {
		return employeeService.getEmployeeById(empId);
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Employee> createEmployee(@RequestParam("empName") String empName,
			@RequestParam("fname") String fname, @RequestParam("mname") String mname,
			@RequestParam("designation") String designation, @RequestParam("gender") String gender,
			@RequestParam("salary") double salary, @RequestParam("remarks") String remarks,
			@RequestParam(value = "pic", required = false) MultipartFile pic) {

		Employee employee = new Employee();
		employee.setEmpName(empName);
		employee.setFname(fname);
		employee.setMname(mname);
		employee.setDesignation(designation);
		employee.setGender(gender);
		employee.setSalary(salary);
		employee.setRemarks(remarks);

		if (pic != null && !pic.isEmpty()) { // Only set pic if present
			try {
				employee.setPic(pic.getBytes());
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

		Employee savedEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.ok(savedEmployee);
	}

	@PutMapping("/{empId}")
	public Employee updateEmployee(@PathVariable Long empId, @RequestBody Employee employeeDetails) {
		return employeeService.updateEmployee(empId, employeeDetails);
	}

	@DeleteMapping("/{empId}")
	public String deleteEmployee(@PathVariable Long empId) {
		employeeService.deleteEmployee(empId);
		return "Employee deleted with id " + empId;
	}

}
