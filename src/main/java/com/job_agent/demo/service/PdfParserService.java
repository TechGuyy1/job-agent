package com.job_agent.demo.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;

@Service
public class PdfParserService {

    public String extractText(String filePath)
            throws IOException {

        File pdfFile = new File(filePath);

        try (PDDocument document =
                     Loader.loadPDF(pdfFile)) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}