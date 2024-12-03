package com.dhouib.foobarquix.batch;

import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

@Component
public class StepParameterListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        String filePath = jobParameters.getString("filePath");
        System.out.println("Job parameters (file path): " + filePath);
    }
}
