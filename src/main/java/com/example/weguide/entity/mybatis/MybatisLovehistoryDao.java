package com.example.weguide.entity.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.weguide.dao.LovehistoryDao;
import com.example.weguide.entity.Lovehistory;

@Repository("lovehistoryDao")
@Transactional
public class MybatisLovehistoryDao implements LovehistoryDao{


	private LovehistoryDao mapper;

    @Autowired
    public MybatisLovehistoryDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(LovehistoryDao.class);
    }
    @Override
	public Lovehistory Isloved(String filename, String username){
		return mapper.Isloved(filename, username);
	}
    @Override
    public void loved(String id) {
    }
}