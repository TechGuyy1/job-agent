package com.job_agent.demo.service;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobSearchService {

    private final JobRepository repository;

    public JobSearchService(JobRepository repository) {
        this.repository = repository;
    }

    public List<Job> searchJobs(
            String keyword,
            String location) {

        List<Job> jobs = new ArrayList<>();

        Job job1 = new Job();
        job1.setTitle(keyword + " Engineer");
        job1.setCompany("Infosys");
        job1.setLocation(location);
        job1.setStatus("NEW");
        job1.setJobUrl(
                "https://careers.infosys.com");

        Job job2 = new Job();
        job2.setTitle(keyword + " Automation Tester");
        job2.setCompany("TCS");
        job2.setLocation(location);
        job2.setStatus("NEW");
        job2.setJobUrl(
                "https://www.tcs.com/careers");

        jobs.add(repository.save(job1));
        jobs.add(repository.save(job2));

        return jobs;
    }
}