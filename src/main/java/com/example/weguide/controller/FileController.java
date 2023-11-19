package com.example.weguide.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.weguide.auth.JwtUtil;
import com.example.weguide.entity.Withtoken;
import com.example.weguide.service.FileServiceImp;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
    private final FileServiceImp fileService;
	
	 private final JwtUtil jwtUtil;

	private Map<String, byte[]> fileStorage = new HashMap<>();
	
    public FileController(FileServiceImp fileService, JwtUtil jwtUtil) {
        this.fileService = fileService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/download")    //get 으로 ~~/file/download/111_KAKAO_1.txt
    public ResponseEntity<Resource> downloadFile(@RequestBody Withtoken withtoken) throws IOException {
        // 파일 경로 설정 (여기서는 상대 경로로 설정하였습니다)
        Path fileDirectory = Paths.get("C:\\Users\\sehoo\\바탕 화면\\comm")
    		    .toAbsolutePath().normalize().resolve(withtoken.getFile_name());
        // 파일을 Resource로 로드
        Resource resource = fileService.loadFileAsResource(fileDirectory);
        
        // 다운로드할 파일명 설정
        String contentDisposition = "attachment; filename=" + resource.getFilename();
        System.out.println("다운로드 성공");

        System.out.println(fileService.updwl(withtoken.getFile_name(), withtoken.getToken()));
        
        
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource); 
        
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
         try {
             String fileName = file.getOriginalFilename();
             Path filePath = Paths.get("C:\\Users\\sehoo\\OneDrive\\바탕 화면\\comm")
         		    .toAbsolutePath().normalize().resolve(fileName);
             Files.write(filePath, file.getBytes()); // 파일 저장
             
             return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully: " + fileName);
         } catch (IOException e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
         }
    }
    
    @GetMapping("/like/{fileName:.+}")
    public void LikeFile(@PathVariable String fileName) throws IOException {
    	/*
    	 
    	  */
    }
}
