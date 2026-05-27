package com.job_agent.demo.controller;

import com.job_agent.demo.entity.Resume;
import com.job_agent.demo.repository.ResumeRepository;
import com.job_agent.demo.service.PdfParserService;
import com.job_agent.demo.service.ResumeService;
import com.job_agent.demo.service.SkillExtractorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService service;
    private final ResumeRepository resumeRepository;
    private final PdfParserService pdfParserService;
    private final SkillExtractorService skillExtractorService;

    public ResumeController(
            ResumeService service,
            ResumeRepository resumeRepository,
            PdfParserService pdfParserService,
            SkillExtractorService skillExtractorService) {

        this.service = service;
        this.resumeRepository = resumeRepository;
        this.pdfParserService = pdfParserService;
        this.skillExtractorService = skillExtractorService;
    }

    @PostMapping("/upload")
    public Resume uploadResume(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        return service.uploadResume(file);
    }

    @GetMapping("/parse/{id}")
    public String parseResume(@PathVariable Long id) throws IOException {

        Resume resume = resumeRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        return pdfParserService.extractText(
                resume.getFilePath());
    }

    @GetMapping("/test")
    public String test() {
        return "Resume Upload API Working";
    }
    @GetMapping("/skills/{id}")
    public List<String> extractSkills(
            @PathVariable Long id) throws Exception {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow();

        String text = pdfParserService.extractText(
                resume.getFilePath());

        return skillExtractorService.extractSkills(text);
    }
}