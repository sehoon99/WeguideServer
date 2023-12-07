package com.example.weguide.entity.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.weguide.dao.GuideDao;
import com.example.weguide.entity.Guide;

@Repository("guideDao")
@Transactional
public class MybatisGuideDao implements GuideDao{

	private GuideDao mapper;

    @Autowired
    public MybatisGuideDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(GuideDao.class);
    }
	
	@Override
	public List<Guide> getAllGuides(){
		return mapper.getAllGuides();
	}
	@Override
	public Guide getGuideById(String guide_id) {
		return mapper.getGuideById(guide_id);
	}
	
	@Override
	public boolean insertGuide(Guide guide) {
		System.out.println("저장중");
		return mapper.insertGuide(guide);
	}
	@Override
	public void updateGuide(Guide guide) {
		
	}
	@Override
	public void deleteGuide(String guide_id) {
		
	}
	@Override
	@Async
	public void upLike(String guide_id) {
		
	}
	@Override
	@Async
	public void downLike(String guide_id) {
		
	}
	@Override
	@Async
	public boolean updwl(String guide_id) {
		return mapper.updwl(guide_id);
	}
	@Override
	public List<Guide> searchGuidesAnd(@Param("app_name") String appName, @Param("guide_name") String guide_name){
		return mapper.searchGuidesAnd(appName, guide_name);
	}
	@Override
	public List<Guide> searchGuidesOr(String keyword){
		return mapper.searchGuidesOr(keyword);
	}
	@Override
	public List<Guide> getGuideByapp(String app_name){
		return mapper.getGuideByapp(app_name);
	}
	@Override
	public List<Guide> getGuideByname(String guide_name){
		return mapper.getGuideByname(guide_name);
	}
}
