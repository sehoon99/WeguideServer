package com.example.weguide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weguide.dao.GuideDao;
import com.example.weguide.entity.Guide;

//가이드 서비스 (GuideService)에서 검색 로직 구현
@Service("guideservice")
public class GuideServiceImp implements GuideService {

	@Autowired
	private GuideDao guideDao;

	public List<Guide> searchbyapp(String app_name) {
		List<Guide> foundGuides = guideDao.getGuideByapp(app_name);
		return foundGuides;
			}
	
	public List<Guide> searchGuidesAnd(String app_name, String guide_name) {
		List<Guide> foundGuides = guideDao.searchGuidesAnd(app_name,guide_name);
		return foundGuides;
	}
	
	public List<Guide> searchGuidesOr(String keyword) {

		List<Guide> foundGuides = guideDao.searchGuidesOr(keyword);
		return foundGuides;
	}
	public List<Guide> searchbyname(String guide_name) {
		List<Guide> foundGuides = guideDao.getGuideByname(guide_name);
		return foundGuides;
			}
}