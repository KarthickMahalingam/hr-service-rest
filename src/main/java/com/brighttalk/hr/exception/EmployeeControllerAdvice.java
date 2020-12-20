package com.brighttalk.hr.exception;

import com.brighttalk.hr.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * The type Employee controller advice.
 */
@ControllerAdvice
public class EmployeeControllerAdvice {

    /**
     * Employee not found exception response entity.
     *
     * @param employeeNotFoundException the employee not found exception
     * @return the response entity
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), new Date(), employeeNotFoundException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
