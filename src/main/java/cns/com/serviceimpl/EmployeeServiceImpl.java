package cns.com.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import cns.com.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cns.com.model.Employee;
import cns.com.repository.EmployeeRepository;
import cns.com.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws IOException {
        Employee employee = new Employee();
        // Basic Handling
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setFatherName(employeeDto.getFatherName());
        employee.setMotherName(employeeDto.getMotherName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setGender(employeeDto.getGender());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setRemarks(employeeDto.getRemarks());
        employee.setSalary(employeeDto.getSalary());
        employee.setBonus(employeeDto.getBonus());

        // Image Handling
        if (employeeDto.getImage() != null && !employeeDto.getImage().isEmpty()) {
            MultipartFile image = employeeDto.getImage();
            employee.setImage(image.getBytes());
            employee.setImageName(image.getOriginalFilename());
            employee.setImageType(image.getContentType());
            employee.setImageSize(image.getSize());

        }

        // Active Flag Handling
        employee.setActiveYn(employeeDto.isActiveYn());

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(long id, EmployeeDto employeeDto) throws IOException {
        Optional<Employee> employeeId = employeeRepository.findById(id);
        if (employeeId.isPresent()) {
            Employee employee = employeeId.get();
            employee.setEmployeeName(employeeDto.getEmployeeName());
            employee.setFatherName(employeeDto.getFatherName());
            employee.setMotherName(employeeDto.getMotherName());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setGender(employeeDto.getGender());
            employee.setAddress(employeeDto.getAddress());
            employee.setPhone(employeeDto.getPhone());
            employee.setEmail(employeeDto.getEmail());
            employee.setDateOfBirth(employeeDto.getDateOfBirth());
            employee.setHireDate(employeeDto.getHireDate());
            employee.setRemarks(employeeDto.getRemarks());
            employee.setSalary(employeeDto.getSalary());
            employee.setBonus(employeeDto.getBonus());

            // File Handling
            if (employeeDto.getImage() != null && !employeeDto.getImage().isEmpty()) {
                MultipartFile image = employeeDto.getImage();
                employee.setImage(image.getBytes());
                employee.setImageName(image.getOriginalFilename());
                employee.setImageType(image.getContentType());
                employee.setImageSize(image.getSize());

            }

            // Active Flag Handling
            employee.setActiveYn(employeeDto.isActiveYn());
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee with id " + id + " not found");
        }

    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
