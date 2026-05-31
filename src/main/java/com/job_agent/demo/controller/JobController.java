package com.job_agent.demo.controller;

import com.job_agent.demo.dto.MatchResponse;
import com.job_agent.demo.entity.Job;
import com.job_agent.demo.entity.Resume;
import com.job_agent.demo.repository.JobRepository;
import com.job_agent.demo.repository.ResumeRepository;
import com.job_agent.demo.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.job_agent.demo.service.JobScraperService;
import com.job_agent.demo.dto.JobSearchRequest;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository repository;
    private final JobScraperService scraperService;
    private final JobSearchService jobSearchService;
    private final ResumeRepository resumeRepository;
    private final PdfParserService pdfParserService;
    private final SkillExtractorService skillExtractorService;
    private final JobMatcherService jobMatcherService;
    private final IndeedScraperService indeedScraperService;

    public JobController(JobRepository repository, JobScraperService scraperService, JobSearchService jobSearchService, ResumeRepository resumeRepository, PdfParserService pdfParserService, SkillExtractorService skillExtractorService, JobMatcherService jobMatcherService,  IndeedScraperService indeedScraperService) {
        this.repository = repository;
        this.scraperService = scraperService;
        this.jobSearchService = jobSearchService;
        this.resumeRepository = resumeRepository;
        this.pdfParserService = pdfParserService;
        this.skillExtractorService = skillExtractorService;
        this.jobMatcherService = jobMatcherService;
        this.indeedScraperService =  indeedScraperService;
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
@PostMapping("/search")
    public List<Job> searchJobs(
            @RequestBody JobSearchRequest request) {

        return jobSearchService.searchJobs(
                request.getKeyword(),
                request.getLocation());
    }
    @GetMapping("/match/{resumeId}")
    public List<MatchResponse> matchJobs(
            @PathVariable Long resumeId)
            throws Exception {

        Resume resume = resumeRepository
                .findById(resumeId)
                .orElseThrow();

        String resumeText =
                pdfParserService.extractText(
                        resume.getFilePath());

        List<String> skills =
                skillExtractorService
                        .extractSkills(resumeText);

        List<Job> jobs = repository.findAll();

        return jobMatcherService
                .matchJobs(skills, jobs);
    }
    @GetMapping("/scrape")
    public String scrapeJobs() throws Exception {

        indeedScraperService.scrapeJobs();

        return "Scraping Completed";
    }
}