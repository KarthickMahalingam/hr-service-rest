package com.brighttalk.hr.controller;

import com.brighttalk.hr.entity.Employee;
import com.brighttalk.hr.exception.EmployeeNotFoundException;
import com.brighttalk.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

/**
 * The type Employee controller.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * The Employee service.
     */
    @Autowired
    EmployeeService employeeService;

    /**
     * Gets all employee details.
     *
     * @return the all employee details
     */
    @GetMapping("/all")
    public Iterable<Employee> getAllEmployeeDetails() {
        return employeeService.getAllEmployees();
    }

    /**
     * Gets employee detail by id.
     *
     * @param employeeId the employee id
     * @return the employee detail by id
     */
    @GetMapping("/{employeeId}")
    public Employee getEmployeeDetailById(@PathVariable("employeeId") BigInteger employeeId) {
        return employeeService.getEmployeeById(employeeId)
                              .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    /**
     * Delete employee by id.
     *
     * @param employeeId the employee id
     */
    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable BigInteger employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    /**
     * Create new employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Update employee id employee.
     *
     * @param employee   the employee
     * @param employeeId the employee id
     * @return the employee
     */
    @PutMapping("/{employeeId}")
    public Employee updateEmployeeId(@RequestBody Employee employee, @PathVariable BigInteger employeeId) {
        return employeeService.updateEmployeeById(employee, employeeId);
    }

    /**
     * Delete all employee.
     */
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAllEmployee() {
        employeeService.deleteAllEmployees();
    }

}
