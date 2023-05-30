package com.chatop.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class MultipartFileService {

    private  String uploadDir  = new File(".").getAbsolutePath() + "/images/" ;
    public String uploadFile(MultipartFile file) {
        try {
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = normalizeFileName(Objects.requireNonNull(file.getOriginalFilename()));

            Path filePath = Paths.get(uploadDir, fileName);

            file.transferTo(filePath.toFile());

            return "http://localhost:3001/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier.", e);
        }
    }

    public String normalizeFileName(String originalFileName) {

        String normalizedFileName = originalFileName.toLowerCase();

        normalizedFileName = normalizedFileName.replace(" ", "-");

        normalizedFileName = normalizedFileName.replaceAll("[^a-z0-9\\-\\.]", "");

        return normalizedFileName;
    }

}
