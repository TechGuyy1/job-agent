package com.job_agent.demo.service;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobScraperService {

    private final JobRepository repository;

    public JobScraperService(JobRepository repository) {
        this.repository = repository;
    }

    public Job saveSampleJob() {

        Job job = new Job();

        job.setTitle("SDET Automation Engineer");
        job.setCompany("Infosys");
        job.setLocation("Pune");
        job.setJobUrl("https://company-careers.example/job1");
        job.setStatus("NEW");

        return repository.save(job);
    }

}