package com.tracksphere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tracksphere.model.Employee;
import com.tracksphere.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting login with email: " + email);  // 🔍 Debug log

        Employee employee = repository.findByEmail(email);
        if (employee == null) {
            System.out.println("User NOT FOUND in DB.");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("User FOUND: " + employee.getEmail());
        return new CustomUserDetails(employee);
    }

}
