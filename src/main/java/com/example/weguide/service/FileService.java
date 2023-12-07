package com.example.weguide.service;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;

import org.springframework.core.io.Resource;

public interface FileService {
	 Resource loadFileAsResource(Path filePath) throws IOException;
	 boolean updatelike(String guide_id,String token);
	 boolean updwl(String guide_id, String token);
	 int uploadGuide(String filename,String guidename, String token);
	 Date getCurrentSqlDate();
}
