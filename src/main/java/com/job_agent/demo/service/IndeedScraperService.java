package com.job_agent.demo.service;

import com.job_agent.demo.entity.Job;
import com.job_agent.demo.repository.JobRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndeedScraperService {

    private final JobRepository repository;

    public IndeedScraperService(JobRepository repository) {
        this.repository = repository;
    }

    public List<Job> scrapeJobs() {

        List<Job> jobs = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");

        options.setExperimentalOption(
                "excludeSwitches",
                new String[]{"enable-automation"});

        options.setExperimentalOption(
                "useAutomationExtension",
                false);

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.get(
                    "https://www.naukri.com/automation-tester-jobs-in-pune?k=automation%20tester&l=pune&experience=10&nignbevent_src=jobsearchDeskGNB");

            Thread.sleep(3000);

            List<WebElement> links =
                    driver.findElements(By.tagName("h2"));

            for (WebElement link : links) {

                String title = link.getText();

                if (!title.isEmpty()) {

                    Job job = new Job();

                    job.setTitle(title);
                    job.setCompany("Google Search");
                    job.setLocation("Pune");
                    job.setStatus("NEW");
                    job.setJobUrl(driver.getCurrentUrl());

                    job.setDescription(
                            "Selenium Java Automation Testing API SQL");

                    jobs.add(repository.save(job));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.quit();
        }

        return jobs;
    }
}