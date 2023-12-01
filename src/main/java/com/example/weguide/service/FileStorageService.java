package com.example.weguide.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.weguide.exception.FileStorageException;
import com.example.weguide.exception.MyFileNotFoundException;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService() {
    	
    	
        // 파일을 저장할 디렉토리 경로를 설정합니다.
    	this.fileStorageLocation = Paths.get("C:\\Users\\sehoo\\OneDrive\\바탕 화면\\comm")
    		    .toAbsolutePath().normalize();

        try {
            // 디렉토리가 존재하지 않으면 생성합니다.
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("파일을 저장할 디렉토리를 생성할 수 없습니다.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // 파일 이름에서 잘못된 문자를 제거하고 저장합니다.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 파일명에 상대경로나 절대경로를 포함하지 않도록 합니다.
            if (fileName.contains("..")) {
                throw new FileStorageException("파일 이름에 잘못된 경로가 포함되어 있습니다. " + fileName);
            }

            // 파일을 지정된 디렉토리로 복사합니다.
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("파일을 저장할 수 없습니다. 다시 시도해주세요.", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("파일을 찾을 수 없습니다. " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("파일을 찾을 수 없습니다. " + fileName, ex);
        }
    }
}