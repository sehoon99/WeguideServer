package com.example.weguide.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.weguide.auth.JwtUtil;
import com.example.weguide.dao.DownloadhistoryDao;
import com.example.weguide.dao.GuideDao;
import com.example.weguide.dao.LovehistoryDao;
import com.example.weguide.entity.Downloadhistory;

@Service("fileservice")
public class FileServiceImp implements FileService {

	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private GuideDao guideDao;
	
	@Autowired
	private DownloadhistoryDao downloadhistoryDao;
	
	@Autowired
	private LovehistoryDao lovehistoryDao;
	
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
    public boolean updwl(String filename, String token) {
    	String id = jwtutil.extractId(token);
    	System.out.println("이름이!!!!"+id);
    	if(downloadhistoryDao.Isdownloaded(filename,id) != null) {
    		System.out.println("이미 다운받음!");
    		return false;
    	}else {
    		System.out.println("첫다운!!");
    		guideDao.updwl(filename);
    		Downloadhistory dh=new Downloadhistory();
    		dh.setId(id);
    		dh.setGuide_id(filename);
    		downloadhistoryDao.downloaded(dh);
    	}
    	return true;
    }
    
}
