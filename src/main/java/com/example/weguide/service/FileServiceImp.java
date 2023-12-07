package com.example.weguide.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.weguide.auth.JwtUtil;
import com.example.weguide.dao.DownloadhistoryDao;
import com.example.weguide.dao.GuideDao;
import com.example.weguide.dao.LovehistoryDao;
import com.example.weguide.entity.Downloadhistory;
import com.example.weguide.entity.Guide;
import com.example.weguide.entity.Lovehistory;
import com.example.weguide.entity.Member;

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
	
	@Autowired
	private MemberServiceImp memberService;
	
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
    
    public boolean updatelike(String guide_id, String token) {
    	String id = jwtutil.extractId(token);
    	System.out.println("아이디가"+id+"  가이드 이름이 :" +guide_id);
		Lovehistory lh=new Lovehistory();
		lh.setId(id);
		lh.setGuide_id(guide_id);
    	if(lovehistoryDao.Isloved(lh) != null) {
    		guideDao.downLike(guide_id);
    		lovehistoryDao.nolove(lh);
    		return false;
    	}else {
    		guideDao.upLike(guide_id);
    		lovehistoryDao.loved(lh);
    	}
    	return true;
    }
    public boolean updwl(String guide_id, String token) {
    	String id = jwtutil.extractId(token);
    	System.out.println("아이디가"+id+"  가이드 이름이 :" +guide_id);
		Downloadhistory dh=new Downloadhistory();
		dh.setId(id);
		dh.setGuide_id(guide_id);
		
    	if(downloadhistoryDao.Isdownloaded(dh) != null) {
    		System.out.println("이미 다운받음!");
    		return false;
    	}else {
    		System.out.println("첫다운!!");
    		guideDao.updwl(guide_id);

    		System.out.println(downloadhistoryDao.downloaded(dh));
    	}
    	return true;
    }
    public void uploadGuide(String filename,String guidename, String token) {
    	Guide guide=new Guide();
    	String id=jwtutil.extractId(token);
    	Member member = memberService.getMemberById(id);
    	guide.setGuide_id(filename);
    	guide.setGuide_name(guidename);
    	guide.setApp_name("앱이름");
    	guide.setRegistrant(member.getUsername());
    	guide.setRegistDate(getCurrentSqlDate());
    	guide.setDownload(0);
    	guide.setLove(0);
    	guideDao.insertGuide(guide);
    }
    private static Date getCurrentSqlDate() {
        // 현재 날짜를 LocalDate 객체로 얻고 java.sql.Date로 변환
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }
    
}
