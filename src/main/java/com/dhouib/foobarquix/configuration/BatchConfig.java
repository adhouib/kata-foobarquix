package com.dhouib.foobarquix.configuration;

import com.dhouib.foobarquix.service.NumberParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager batchTransactionManager;
    private static final int BATCH_SIZE = 5;

    private final NumberParserService numberParserService;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager batchTransactionManager, NumberParserService numberParserService) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.numberParserService = numberParserService;
    }

    @Bean
    public Job firstJob() {
        return new JobBuilder("numberProcessingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return new StepBuilder("numberProcessingStep", jobRepository)
                .<String, String>chunk(BATCH_SIZE, batchTransactionManager)
                .reader(fileReader(null)) // Input file path to be dynamically set via JobParameters
                .processor(numberProcessor())
                .writer(fileWriter())
                .build();
    }

    @Bean
    public ItemReader<String> fileReader(String inputFilePath) {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputFilePath));
        reader.setLineMapper(new PassThroughLineMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<String, String> numberProcessor() {
        return item -> {
            try {
                int number = Integer.parseInt(item.trim());
                String transformedNumber = numberParserService.parse(number);
                return String.valueOf(transformedNumber);
            } catch (NumberFormatException e) {
                log.warn("Invalid number format: {}", item);
                return null; // Skip invalid lines
            }
        };
    }

    @Bean
    public ItemWriter<String> fileWriter() {
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output.txt"));
        LineAggregator<String> aggregator = new PassThroughLineAggregator<>();
        writer.setLineAggregator(aggregator);
        return writer;
    }
}
