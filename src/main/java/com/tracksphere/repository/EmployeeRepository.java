package com.tracksphere.repository;

import com.tracksphere.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom finder method to be used in login
    Employee findByEmail(String email);
}
