package com.example.pims.util;

import com.example.pims.dto.ApiResponse;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Amol.Sarwade
 */
public class ApiUtility {

    public static <T> ApiResponse<T> buildResponse(boolean success, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<T>(
                success,
                body,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return apiResponse;
    }

    public static ApiResponse buildResponse(boolean success, String message) {
        ApiResponse apiResponse = new ApiResponse(
                success,
                message,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildResponse(boolean success, T body, String message) {
        ApiResponse<T> apiResponse = new ApiResponse<T>(
                success,
                body,
                message,
                ZonedDateTime.now(ZoneId.of("Asia/Dubai"))
        );
        return apiResponse;
    }

}
