package com.brighttalk.hr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * The type Error details.
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private int httpStatus;

    private Date timestamp;

    private String message;

}
