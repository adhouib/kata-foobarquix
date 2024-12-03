package com.dhouib.foobarquix.batch;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job myJob;

    @Override
    public void run(String... args)  {
        if (args.length < 1) {
            System.err.println("Please provide an input file path as an argument.");
            return;
        }

        String inputFilePath = args[0];

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("inputFilePath", inputFilePath)
                .toJobParameters();

        try {
            jobLauncher.run(myJob, jobParameters);
        } catch (JobExecutionException e) {
            log.error(e.getMessage());
        }
    }
}
