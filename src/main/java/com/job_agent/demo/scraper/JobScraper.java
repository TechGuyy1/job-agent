package com.job_agent.demo.scraper;

import com.job_agent.demo.config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class JobScraper {

    public String openGoogle() {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://www.google.com");

        String title = driver.getTitle();

        driver.quit();

        return title;
    }
}
