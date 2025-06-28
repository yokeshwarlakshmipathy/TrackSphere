package com.tracksphere.controller;

import com.tracksphere.model.Employee;
import com.tracksphere.security.CustomUserDetails;
import com.tracksphere.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private PasswordEncoder encoder;

    // Show Register Page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "register"; // returns register.html
    }

    // Handle Register Form Submit
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("employee") Employee employee, Model model) {
        if (service.findByEmail(employee.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        employee.setPassword(encoder.encode(employee.getPassword()));
        service.register(employee);
        return "redirect:/login";
    }

    // Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // returns login.html
    }

    // After successful login → show dashboard based on role
    @GetMapping("/dashboard")
    public String showDashboard(Authentication auth, Model model) {
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("name", user.getName());

        String role = user.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ADMIN")) return "admin-dashboard";
        else if (role.equals("MANAGER")) return "manager-dashboard";
        else return "employee-dashboard";
    }
}

