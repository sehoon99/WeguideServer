package com.example.weguide.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.weguide.auth.JwtUtil;
import com.example.weguide.entity.Withtoken;
import com.example.weguide.service.FileServiceImp;
import com.example.weguide.service.JsonValidator;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
    private final FileServiceImp fileService;
	
	private final JwtUtil jwtUtil;

	private final JsonValidator jv;
	
	private Map<String, byte[]> fileStorage = new HashMap<>();
	
    public FileController(FileServiceImp fileService, JwtUtil jwtUtil, JsonValidator jv) {
        this.fileService = fileService;
        this.jwtUtil = jwtUtil;
        this.jv=jv;
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestBody Withtoken withtoken) {
        try {
            // 파일 경로 설정 (여기서는 상대 경로로 설정하였습니다)
            Path fileDirectory = Paths.get("C:\\Users\\sehoo\\바탕 화면\\comm")
                    .toAbsolutePath().normalize().resolve(withtoken.getGuide_id());
            
            // 파일을 Resource로 로드
            Resource resource = fileService.loadFileAsResource(fileDirectory);

            // 다운로드할 파일명 설정
            String contentDisposition = "attachment; filename=" + resource.getFilename();
            System.out.println("다운로드 성공");

            System.out.println(fileService.updwl(withtoken.getGuide_id(), withtoken.getToken()));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(resource);
        } catch (IOException e) {
            // 파일 다운로드 실패 시 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file,@RequestPart("guidename") String guidename,@RequestPart("token") String token ) {
    	String id=jwtUtil.extractId(token);
    	boolean isValid;
        try {
            // 원본 파일 경로
            String originalFilePath = "C:\\Users\\sehoo\\바탕 화면\\local\\" + file.getOriginalFilename();
            String fileContent = jv.readFileToString(originalFilePath);

            // Json 형식 유효성 검사 
            isValid = jv.isValidJson(fileContent);
            System.out.println("File content is valid JSON: " + isValid);   
            if(isValid) {
            // 업로드할 경로
            String targetFolderPath = "C:\\Users\\sehoo\\바탕 화면\\comm\\";

            // 원본 파일을 대상 폴더로 복사
            Path originalPath = FileSystems.getDefault().getPath(originalFilePath);
            Path targetPath = FileSystems.getDefault().getPath(targetFolderPath, file.getOriginalFilename());
            
            fileService.uploadGuide(file.getOriginalFilename(),guidename,token);
            // 파일 복사
            Files.copy(originalPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded and moved successfully: " + file.getOriginalFilename());
            }else {
            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and JSON invalid");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and move file");
        }
    }
    
    @PostMapping("/like")
    public void LikeFile(@RequestBody Withtoken withtoken) throws IOException {
    	System.out.println(fileService.updatelike(withtoken.getGuide_id(), withtoken.getToken()));
    	
    }
}
