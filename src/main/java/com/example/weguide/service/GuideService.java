package com.example.weguide.service;

import java.util.List;

import com.example.weguide.entity.Guide;

public interface GuideService {
	public List<Guide> searchGuidesAnd(String app_name,String guide_name);
	public List<Guide> searchGuidesOr(String keyword);
	public List<Guide> searchbyapp(String app_name);
	public List<Guide> searchbyname(String guide_name);
}
