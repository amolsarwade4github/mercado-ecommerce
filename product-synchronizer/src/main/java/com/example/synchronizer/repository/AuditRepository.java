package com.example.synchronizer.repository;

import com.example.synchronizer.model.RequestAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amol.Sarwade
 */
@Repository
public interface AuditRepository extends CrudRepository<RequestAudit, Long> {

    RequestAudit findByRequestId(String requestId);

    List<RequestAudit> findByStatus(String status);
}
