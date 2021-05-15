package com.example.synchronizer.job;

import com.example.synchronizer.dto.Product;
import com.example.synchronizer.dto.ProductInput;
import com.example.synchronizer.listener.ProductSynchronizerJobCompletionNotificationListener;
import com.example.synchronizer.processor.ProductDataProcessor;
import com.example.synchronizer.writer.ProductWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.client.RestTemplate;

/**
 * @author Amol.Sarwade
 */
@Configuration
@EnableBatchProcessing
public class ProductSynchronizerJobConfig {

    public static final Logger log = LoggerFactory.getLogger(ProductSynchronizerJobConfig.class);

    public static final String[] FIELD_NAMES = {"sku", "title", "description", "price", "quantity"};

    @Value("${product.data.file.path}")
    private String productDataFilePath;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importProductJob(ProductSynchronizerJobCompletionNotificationListener listener, Step step1) {
        log.info("Initializing product data importer job");
        return jobBuilderFactory.get("importProductJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1).end().build();
    }

    @Bean
    public Step step1(ItemWriter<Product> writer) {
        log.info("Initializing product data importer job step1");
        return stepBuilderFactory.get("step1")
                .<ProductInput, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer).build();
    }

    //Reader
    @Bean
    public FlatFileItemReader<ProductInput> reader() {
        log.info("Initialing product data importer job reader");
        return new FlatFileItemReaderBuilder<ProductInput>()
                .name("productItemReader")
                .resource(new FileSystemResource(productDataFilePath))
                .linesToSkip(1)
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProductInput>() {
                    { setTargetType(ProductInput.class);}
                }).build();
    }
    //Processor
    @Bean
    public ProductDataProcessor processor() {
        log.info("Initialing product data importer job processor");
        return new ProductDataProcessor();
    }
    //Writer
    @Bean
    public ItemWriter<Product> writer() {
        log.info("Initialing product data importer job Item writer");
        return new ProductWriter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
