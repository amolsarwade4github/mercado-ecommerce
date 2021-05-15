package com.example.synchronizer.model;

import com.example.synchronizer.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Amol.Sarwade
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request_audit")
public class RequestAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private HttpMethod requestType;

    @Column(length = 5000)
    private String request;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(length = 5000)
    private String response;

    @Column(name = "response_date")
    private Date responseDate;

    @Enumerated(EnumType.STRING)
    private ResponseStatus status;




}
