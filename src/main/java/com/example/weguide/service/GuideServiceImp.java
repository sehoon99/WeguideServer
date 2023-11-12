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
	
	public List<Guide> searchGuides(String app_name, String searchString) {
		Map<String, String> params = new HashMap<>();
		params.put("appName", app_name);
		params.put("searchString", searchString);

		guideDao.searchGuides(params);
		List<Guide> foundGuides = guideDao.searchGuides(params);
		System.out.println("이건 됨");
		return foundGuides;
	}
}