package com.example.synchronizer.helper;

import com.example.synchronizer.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

/**
 * @author Amol.Sarwade
 */
public class ApiResponseHelper {

    static Logger log = LoggerFactory.getLogger(ApiResponseHelper.class);

    public static ApiResponse getFailureResponse(Exception ex) {
        String failureResponseBody = null;
        if (isValidHttpClientErrorException(ex)) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            failureResponseBody = httpClientErrorException.getResponseBodyAsString();
        } else if (isValidHttpServerErrorException(ex)) {
            HttpServerErrorException httpServerErrorException = (HttpServerErrorException) ex;
            failureResponseBody = httpServerErrorException.getResponseBodyAsString();
        }

        if (!StringUtils.isEmpty(failureResponseBody)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(failureResponseBody, ApiResponse.class);
            } catch (IOException e) {
                log.error("Error in parsing Api response to object");
            }
        }
        return null;
    }

    public static boolean isValidHttpClientErrorException(Exception ex) {
        if (ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            return !StringUtils.isEmpty(httpClientErrorException.getResponseBodyAsString());
        }
        return false;
    }

    public static boolean isValidHttpServerErrorException(Exception ex) {
        if (ex instanceof HttpServerErrorException) {
            HttpServerErrorException httpServerErrorException = (HttpServerErrorException) ex;
            return !StringUtils.isEmpty(httpServerErrorException.getResponseBodyAsString());
        }
        return false;
    }
}
