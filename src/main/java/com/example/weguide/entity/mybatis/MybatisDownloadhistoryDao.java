package com.example.weguide.entity.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.weguide.dao.DownloadhistoryDao;
import com.example.weguide.entity.Downloadhistory;

@Repository("downloadhistoryDao")
@Transactional
public class MybatisDownloadhistoryDao implements DownloadhistoryDao{

	private DownloadhistoryDao mapper;

    @Autowired
    public MybatisDownloadhistoryDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(DownloadhistoryDao.class);
    }
    @Override
	public Downloadhistory Isdownloaded(Downloadhistory dh){
		return mapper.Isdownloaded(dh);
	}
    @Override
	public boolean downloaded(Downloadhistory downloadhistory){
    	return mapper.downloaded(downloadhistory);
	}
}
