package com.example.synchronizer.helper;

import com.example.synchronizer.constants.ResponseStatus;
import com.example.synchronizer.model.RequestAudit;
import com.example.synchronizer.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Amol.Sarwade
 */
@Component
public class AuditRequestHelper {

    Logger log = LoggerFactory.getLogger(AuditRequestHelper.class);

    @Autowired
    private AuditService auditService;

    public void save(String requestId, String requestUrl, HttpMethod requestType, String request) {
        RequestAudit requestAudit = RequestAudit.builder()
                .requestId(requestId)
                .requestUrl(requestUrl)
                .requestType(requestType)
                .request(request)
                .requestDate(new Date()).build();
        try {
            auditService.saveAudit(requestAudit);
        } catch (Exception ex) {
            log.error("Failed to log the request audit", ex);
        }
    }

    public void update(String requestId, String response, ResponseStatus status) {
        try {
            RequestAudit requestAudit = auditService.getAuditByRequestId(requestId);
            requestAudit.setResponse(response);
            requestAudit.setStatus(status);
            requestAudit.setResponseDate(new Date());

            auditService.updateAudit(requestAudit);
        } catch (Exception ex) {
            log.error("Failed to update the request audit for request id " + requestId);
        }
    }
}
