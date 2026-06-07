package com.job_agent.demo.service;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;

@Service
public class PlaywrightNaukriService {

    public void loginAndReuseProfile() {

        try (Playwright playwright = Playwright.create()) {

            BrowserType.LaunchPersistentContextOptions options =
                    new BrowserType.LaunchPersistentContextOptions()
                            .setHeadless(false)
                            .setSlowMo(500);

            BrowserContext context =
                    playwright.chromium()
                            .launchPersistentContext(
                                    Paths.get("playwright-profile"),
                                    options
                            );

            Page page = context.pages().get(0);

            page.navigate("https://www.naukri.com");

            System.out.println(
                    "Login manually first time if required");

            page.waitForTimeout(6000);

            page.navigate(
                    "https://www.naukri.com/selenium-jobs-in-pune"
            );

            page.waitForTimeout(5000);

            List<String> titles =
                    page.locator("a.title").allTextContents();

            for (String title : titles) {

                System.out.println(title);
            }
            page.getByText("JobsRecommended jobsNVitesApplication statusSaved jobsCompaniesExplore").click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Jobs").setExact(true)).click();

            List<String> recJobs =
                    page.locator("p.title").allTextContents();

            for (String title : recJobs) {

                System.out.println(title);
            }

            context.close();
        }
    }
}