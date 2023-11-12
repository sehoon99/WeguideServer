package com.example.weguide.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

public interface FileService {
	 Resource loadFileAsResource(Path filePath) throws IOException;
	 boolean updatelike(String filename,boolean clicked);
	 boolean updwl(String filename, boolean downloaded);
}
