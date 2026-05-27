package com.job_agent.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillExtractorService {

    private static final String[] KNOWN_SKILLS = {
            "Java",
            "Selenium",
            "TestNG",
            "Cucumber",
            "Rest Assured",
            "API Testing",
            "Postman",
            "Maven",
            "Git",
            "Jenkins",
            "SQL",
            "PostgreSQL",
            "Spring Boot",
            "JUnit",
            "Agile",
            "BDD"
    };

    public List<String> extractSkills(String resumeText) {

        List<String> skills = new ArrayList<>();

        for (String skill : KNOWN_SKILLS) {

            if (resumeText.toLowerCase()
                    .contains(skill.toLowerCase())) {

                skills.add(skill);
            }
        }

        return skills;
    }
}