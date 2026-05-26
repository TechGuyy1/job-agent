package com.job_agent.demo.controller;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository repository;

    public JobController(JobRepository repository) {
        this.repository = repository;
    }

//    @PostMapping("/sample")
    @GetMapping ("/sample")
    public Job createSampleJob() {

        Job job = new Job();

        job.setTitle("Senior Selenium Automation Engineer");
        job.setCompany("TCS");
        job.setLocation("Pune");
        job.setJobUrl("https://example.com/job/123");
        job.setStatus("NEW");

        return repository.save(job);
    }
    @GetMapping
    public java.util.List<Job> getAllJobs() {
        return repository.findAll();
    }
}