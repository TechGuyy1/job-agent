package com.job_agent.demo.controller;

import com.job_agent.demo.scraper.JobScraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeleniumController {

    private final JobScraper scraper;

    public SeleniumController(JobScraper scraper) {
        this.scraper = scraper;
    }

    @GetMapping("/test-browser")
    public String testBrowser() {
        return scraper.openGoogle();
    }
}