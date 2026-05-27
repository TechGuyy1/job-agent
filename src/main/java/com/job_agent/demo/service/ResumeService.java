package com.job_agent.demo.service;

import com.job_agent.demo.entity.Resume;
import com.job_agent.demo.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ResumeService {

    private final ResumeRepository repository;

    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }

    public Resume uploadResume(MultipartFile file) throws IOException {

        // Create uploads/resumes directory if it doesn't exist
        Path uploadPath = Paths.get("uploads", "resumes");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Build full file path
        Path filePath = uploadPath.resolve(file.getOriginalFilename());

        System.out.println("Saving file to: " + filePath.toAbsolutePath());

        // Copy uploaded file
        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        // Save metadata in database
        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath.toAbsolutePath().toString());
        resume.setUploadStatus("UPLOADED");

        return repository.save(resume);
    }
}