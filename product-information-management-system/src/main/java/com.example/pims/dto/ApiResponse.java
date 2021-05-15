package com.example.pims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Amol.Sarwade
 */
@Data
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success = Boolean.TRUE;

    @JsonInclude(Include.NON_NULL)
    private T body;

    @JsonInclude(Include.NON_EMPTY)
    private String message;

    @JsonInclude(Include.NON_NULL)
    private HttpStatus httpStatus;

    private ZonedDateTime timestamp;

    public ApiResponse(Boolean success, T body, ZonedDateTime timestamp) {
        this.success = success;
        this.body = body;
        this.timestamp = timestamp;
    }

    public ApiResponse(Boolean success, T body, String message, ZonedDateTime timestamp) {
        this.success = success;
        this.body = body;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiResponse(Boolean success, String message, ZonedDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiResponse(Boolean success, String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
