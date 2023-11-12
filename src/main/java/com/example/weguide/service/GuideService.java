package com.example.weguide.service;

import java.util.List;

import com.example.weguide.entity.Guide;

public interface GuideService {
	public List<Guide> searchGuides(String app_name,String searchString);
	public List<Guide> searchbyapp(String app_name);
}
