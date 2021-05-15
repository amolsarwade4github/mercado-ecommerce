package com.example.synchronizer.writer;

import com.example.synchronizer.constants.ResponseStatus;
import com.example.synchronizer.dto.ApiResponse;
import com.example.synchronizer.dto.Product;
import com.example.synchronizer.helper.ApiResponseHelper;
import com.example.synchronizer.helper.AuditRequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Amol.Sarwade
 */
public class ProductWriter implements ItemWriter<Product> {

    public static final Logger log = LoggerFactory.getLogger(ProductWriter.class);

    @Value("${product.url.bulk.upload}")
    protected String productBulkUploadUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuditRequestHelper auditRequestHelper;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        if (CollectionUtils.isEmpty(products)) {
            log.info("No products found to write");
            return;
        }

        HttpEntity<List<Product>> httpEntity = new HttpEntity(products, getHeaders());
        final String requestId = UUID.randomUUID().toString();
        try {
            auditRequestHelper.save(requestId, productBulkUploadUrl, HttpMethod.POST, objectMapper.writeValueAsString(products));
            ResponseEntity<ApiResponse> result = restTemplate.postForEntity(productBulkUploadUrl, httpEntity, ApiResponse.class);
            ApiResponse apiResponse = result.getBody();
            if (apiResponse.getSuccess()) {
                auditRequestHelper.update(requestId, objectMapper.writeValueAsString(apiResponse), ResponseStatus.SUCCESS);
            } else {
                auditRequestHelper.update(requestId, objectMapper.writeValueAsString(apiResponse), ResponseStatus.FAILED);
            }
        } catch (Exception ex) {
            ApiResponse failureResponse = ApiResponseHelper.getFailureResponse(ex);
            auditRequestHelper.update(requestId, objectMapper.writeValueAsString(failureResponse), ResponseStatus.FAILED);
            log.error(failureResponse.toString());
        }

    }

    private MultiValueMap<String, String> getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

}
