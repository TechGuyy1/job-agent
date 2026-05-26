package com.job_agent.demo.service;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobScraperService {

    private final JobRepository repository;

    public JobScraperService(JobRepository repository) {
        this.repository = repository;
    }

    public List<Job> searchAndSaveJobs() {

        List<Job> jobs = new ArrayList<>();

        Job job1 = new Job();
        job1.setTitle("Senior Selenium Automation Engineer");
        job1.setCompany("Infosys");
        job1.setLocation("Pune");
        job1.setJobUrl("https://example.com/job1");
        job1.setStatus("NEW");

        jobs.add(repository.save(job1));

        return jobs;
    }
}