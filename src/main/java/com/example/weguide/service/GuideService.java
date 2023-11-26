package com.example.weguide.service;

import java.util.List;

import com.example.weguide.entity.Guide;

public interface GuideService {
	public List<Guide> searchGuidesAnd(String app_name,String guide_name,int sortnum);
	public List<Guide> searchGuidesOr(String keyword,int sortnum);
	public List<Guide> searchbyapp(String app_name,int sortnum);
	public List<Guide> searchbyname(String guide_name,int sortnum);
}
