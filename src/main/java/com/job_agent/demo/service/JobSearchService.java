package com.job_agent.demo.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class JobSearchService {

    public String searchJobs() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.linkedin.com/jobs/");

        String title = driver.getTitle();

        driver.quit();

        return title;
    }
}