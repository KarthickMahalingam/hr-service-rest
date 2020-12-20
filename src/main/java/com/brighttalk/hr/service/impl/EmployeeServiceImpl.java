package com.brighttalk.hr.service.impl;

import com.brighttalk.hr.entity.Employee;
import com.brighttalk.hr.exception.EmployeeNotFoundException;
import com.brighttalk.hr.repository.EmployeeRepository;
import com.brighttalk.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * The type Employee service.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * The Employee repository.
     */
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(BigInteger employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();

    }

    @Override
    public void deleteEmployeeById(BigInteger employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee updateEmployeeById(Employee newEmployee, BigInteger employeeId) {
        return employeeRepository.findById(employeeId)
                                 .map(employee -> {
                                     employee.setFirstName(newEmployee.getFirstName());
                                     employee.setLastName(newEmployee.getLastName());
                                     employee.setEmail(newEmployee.getEmail());
                                     employee.setPhone(newEmployee.getPhone());
                                     return employeeRepository.save(employee);
                                 }).orElseGet(() -> {
                                     newEmployee.setId(employeeId);
                                     return employeeRepository.save(newEmployee);
                });
    }


}
