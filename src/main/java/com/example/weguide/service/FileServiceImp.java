package com.example.weguide.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.weguide.dao.GuideDao;

@Service("fileservice")
public class FileServiceImp implements FileService {

	@Autowired
	private GuideDao guideDao;
	
    public Resource loadFileAsResource(Path filePath) throws IOException {
    	try {
    	    Resource resource = new UrlResource(filePath.toUri());
    	    if (resource.exists() || resource.isReadable()) {
    	        return resource;
    	    } else {
    	        throw new FileNotFoundException("File not found: " + filePath);
    	    }
    	} catch (MalformedURLException ex) {
    	    throw new FileNotFoundException("File not found: " + filePath);
    	}
    }
    
    public boolean updatelike(String filename, boolean clicked) {
    	if(clicked) {
    		guideDao.downLike(filename);
    	}else {
    		guideDao.upLike(filename);
    	}
    	return true;
    }
    public boolean updwl(String filename, boolean downloaded) {
    	if(downloaded) {
    		return false;
    	}else {
    		guideDao.updwl(filename);
    		System.out.println(filename);
    	}
    	return true;
    }
    
}
