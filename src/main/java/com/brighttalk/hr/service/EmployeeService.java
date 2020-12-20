package com.brighttalk.hr.service;

import com.brighttalk.hr.entity.Employee;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * The interface Employee service.
 */
public interface EmployeeService {
    /**
     * Gets all employees.
     *
     * @return the all employees
     */
    List<Employee> getAllEmployees();

    /**
     * Gets employee by id.
     *
     * @param employeeId the employee id
     * @return the employee by id
     */
    Optional<Employee> getEmployeeById(BigInteger employeeId);

    /**
     * Create employee employee.
     *
     * @param employeeDto the employee dto
     * @return the employee
     */
    Employee createEmployee(Employee employeeDto);

    /**
     * Delete all employees.
     */
    void deleteAllEmployees();

    /**
     * Delete employee by id.
     *
     * @param employeeId the employee id
     */
    void deleteEmployeeById(BigInteger employeeId);

    /**
     * Update employee by id employee.
     *
     * @param employeeDto the employee dto
     * @param employeeId  the employee id
     * @return the employee
     */
    Employee updateEmployeeById(Employee employeeDto, BigInteger employeeId);

}
