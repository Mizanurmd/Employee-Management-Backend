package cns.com.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import cns.com.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Save Into DB Handler
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@ModelAttribute EmployeeDto employeeDto) throws IOException {
        Employee employee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // Update data handler
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @ModelAttribute EmployeeDto employeeDto) throws IOException {
        Employee employee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Retrieve single data from DB Handler

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) throws IOException {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Retrieve all data from DB Handler
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Delete singe data from DB Handler

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) throws IOException {
        employeeService.deleteEmployee(id);
    }

}

