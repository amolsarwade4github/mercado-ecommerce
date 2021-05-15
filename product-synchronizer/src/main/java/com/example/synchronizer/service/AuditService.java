package com.example.synchronizer.service;

import com.example.synchronizer.model.RequestAudit;

/**
 * @author Amol.Sarwade
 */
public interface AuditService {

    RequestAudit getAuditByRequestId(String requestId);

    RequestAudit saveAudit(RequestAudit audit);

    RequestAudit updateAudit(RequestAudit audit);

    Iterable<RequestAudit> getAudits();

    Iterable<RequestAudit> getAudits(String status);

}
