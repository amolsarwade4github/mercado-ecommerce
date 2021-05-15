package com.example.synchronizer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Amol.Sarwade
 */
@Component
public class ProductSynchronizerJobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(ProductSynchronizerJobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("ProductSynchronizerJobCompletionNotificationListener :: COMPLETED");
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("ProductSynchronizerJobCompletionNotificationListener :: STARTED");
    }
}
