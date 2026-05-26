package com.job_agent.demo.controller;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import com.job_agent.demo.service.JobScraperService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.job_agent.demo.service.JobScraperService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository repository;
    private final JobScraperService scraperService;

    public JobController(JobRepository repository, JobScraperService scraperService) {
        this.repository = repository;
        this.scraperService = scraperService;
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
    @GetMapping("/search")
    public List<Job> searchJobs() {
        return scraperService.searchAndSaveJobs();
    }
}