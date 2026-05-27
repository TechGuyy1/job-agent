package com.job_agent.demo.service;

import com.job_agent.demo.dto.MatchResponse;
import com.job_agent.demo.entity.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobMatcherService {

    public List<MatchResponse> matchJobs(
            List<String> resumeSkills,
            List<Job> jobs) {

        List<MatchResponse> responses =
                new ArrayList<>();

        for (Job job : jobs) {

            int score = 0;

            String text =
                    (job.getTitle() + " "
                            + job.getCompany() + " "
                            + job.getDescription())
                            .toLowerCase();

            List<String> matchedSkills =
                    new ArrayList<>();

            for (String skill : resumeSkills) {

                if (text.contains(
                        skill.toLowerCase())) {

                    score++;

                    matchedSkills.add(skill);
                }
            }

            int percentage = score * 20;

            if (percentage > 100) {
                percentage = 100;
            }

            MatchResponse response =
                    new MatchResponse();

            response.setJobTitle(job.getTitle());
            response.setCompany(job.getCompany());
            response.setMatchPercentage(percentage);

            responses.add(response);
        }

        return responses;
    }
}