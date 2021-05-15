package com.example.synchronizer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author Amol.Sarwade
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success = Boolean.TRUE;

    private T body;

    private String message;

    private HttpStatus httpStatus;

    private String timestamp;
}
