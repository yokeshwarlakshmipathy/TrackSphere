package com.tracksphere.service;

import com.tracksphere.model.Employee;
import com.tracksphere.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Encryptor object
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register with encrypted password
    public Employee register(Employee e) {
        // Encrypt password before saving
        e.setPassword(passwordEncoder.encode(e.getPassword()));
        return repository.save(e);
    }

    public Employee findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }
}

