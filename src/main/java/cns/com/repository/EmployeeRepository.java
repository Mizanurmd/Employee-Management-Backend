package cns.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cns.com.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
