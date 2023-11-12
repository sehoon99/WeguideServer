package com.example.weguide.entity.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void insertGuide(Guide guide) {
		
	}
	@Override
	public void updateGuide(Guide guide) {
		
	}
	@Override
	public void deleteGuide(String guide_id) {
		
	}
	@Override
	public void upLike(String guide_id) {
		
	}
	@Override
	public void downLike(String guide_id) {
		
	}
	@Override
	public boolean updwl(String guide_id) {
		return mapper.updwl(guide_id);
	}
	@Override
	public List<Guide> searchGuides(String app_name, String searchKeyword){
		return mapper.searchGuides(app_name, searchKeyword);
	}
	@Override
	public List<Guide> getGuideByapp(String app_name){
		return mapper.getGuideByapp(app_name);
	}
}
