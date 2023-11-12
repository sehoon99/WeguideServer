package com.example.weguide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weguide.dao.GuideDao;
import com.example.weguide.entity.Guide;
import com.example.weguide.repository.GuideRepository;

//가이드 서비스 (GuideService)에서 검색 로직 구현
@Service("guideservice")
public class GuideServiceImp implements GuideService {

	@Autowired
	private GuideDao guideDao;

	public List<Guide> searchbyapp(String app_name) {
		List<Guide> foundGuides = guideDao.getGuideByapp(app_name);
		return foundGuides;
			}
	
	public List<Guide> searchGuides(String app_name,String searchString) {
		List<Guide> foundGuides = guideDao.searchGuides(app_name,searchString);
		return foundGuides;
	}
}