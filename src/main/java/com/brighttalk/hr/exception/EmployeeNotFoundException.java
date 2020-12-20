package com.brighttalk.hr.exception;

import java.math.BigInteger;

/**
 * The type Employee not found exception.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Employee not found exception.
     *
     * @param employeeId the employee id
     */
    public EmployeeNotFoundException(BigInteger employeeId) {
        super("Couldn't find the employee: " + employeeId);
    }
}
