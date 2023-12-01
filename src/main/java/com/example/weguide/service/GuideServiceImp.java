package com.example.weguide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weguide.dao.GuideDao;
import com.example.weguide.entity.Guide;

//가이드 서비스 (GuideService)에서 검색 로직 구현
@Service("guideservice")
public class GuideServiceImp implements GuideService {

	@Autowired
	private GuideDao guideDao;
	@Autowired
	private GuideSorter guidesoter;
	
	public List<Guide> searchbyapp(String app_name,int sortnum) {
		List<Guide> foundGuides = guideDao.getGuideByapp(app_name);
		return guidesoter.sort(foundGuides, sortnum);
			}
	
	public List<Guide> searchGuidesAnd(String app_name, String guide_name,int sortnum) {
		List<Guide> foundGuides = guideDao.searchGuidesAnd(app_name,guide_name);
		return guidesoter.sort(foundGuides, sortnum);
	}
	
	public List<Guide> searchGuidesOr(String keyword,int sortnum) {

		List<Guide> foundGuides = guideDao.searchGuidesOr(keyword);
		return guidesoter.sort(foundGuides, sortnum);
	}
	public List<Guide> searchbyname(String guide_name,int sortnum) {
		List<Guide> foundGuides = guideDao.getGuideByname(guide_name);
		System.out.println("어디서?"+ sortnum);
		return guidesoter.sort(foundGuides, sortnum);
			}
}