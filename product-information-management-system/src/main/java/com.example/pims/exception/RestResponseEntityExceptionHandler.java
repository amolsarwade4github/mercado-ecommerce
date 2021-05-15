package com.example.pims.exception;

import com.example.pims.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Amol.Sarwade
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiResponse<Object> apiResponse = new ApiResponse<Object>(
                false,
                ex.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return new ResponseEntity<>(apiResponse, notFound);
    }

    @ExceptionHandler(value = {ProductAlreadyExistsException.class})
    protected ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiResponse<Object> apiResponse = new ApiResponse<Object>(
                false,
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return new ResponseEntity<>(apiResponse, badRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalServerException(Exception ex) {
        HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponse<Object> apiResponse = new ApiResponse<Object>(
                false,
                ex.getMessage(),
                serverError,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return new ResponseEntity<>(apiResponse, serverError);
    }
}
