package com.job_agent.demo.controller;

import com.job_agent.demo.entity.Resume;
import com.job_agent.demo.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public Resume uploadResume(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        return service.uploadResume(file);
    }

    @GetMapping("/test")
    public String test() {
        return "Resume Upload API Working";
    }
}