package com.example.synchronizer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amol.Sarwade
 */
@Component
public class ProductSynchronizerJobScheduler {

    Logger log = LoggerFactory.getLogger(ProductSynchronizerJobScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(cron = "${product.upload.job.execution.frequency}")
    public void synchronizeProducts() {

        try {

            Map<String, JobParameter> maps = new HashMap<>();
            long timeMillis = System.currentTimeMillis();
            maps.put("time", new JobParameter(timeMillis));
            JobParameters parameters = new JobParameters(maps);

            log.info("Product upload job launched: " + timeMillis);
            jobLauncher.run(job, parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
