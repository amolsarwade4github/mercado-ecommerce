package com.example.synchronizer.service;

import com.example.synchronizer.model.RequestAudit;
import com.example.synchronizer.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Amol.Sarwade
 */
@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

    Logger log = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public RequestAudit getAuditByRequestId(String requestId) {
        return auditRepository.findByRequestId(requestId);
    }

    @Override
    @Transactional(readOnly = false)
    public RequestAudit saveAudit(RequestAudit audit) {
        return auditRepository.save(audit);
    }

    @Override
    @Transactional(readOnly = false)
    public RequestAudit updateAudit(RequestAudit audit) {
        return auditRepository.save(audit);
    }

    @Override
    public Iterable<RequestAudit> getAudits() {
        return auditRepository.findAll();
    }

    @Override
    public Iterable<RequestAudit> getAudits(String status) {
        return auditRepository.findByStatus(status);
    }
}
