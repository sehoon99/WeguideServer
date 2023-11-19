package com.example.weguide.entity.mybatis;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.weguide.dao.MemberDao;
import com.example.weguide.entity.LoginForm;
import com.example.weguide.entity.Member;

@Repository("memberDao")
@Transactional
public class MybatisMemberDao implements MemberDao {

    private MemberDao mapper;

    @Autowired
    public MybatisMemberDao(SqlSession sqlSession) {
        mapper = sqlSession.getMapper(MemberDao.class);
    }


    @Override
    public Member getMemberByLoginForm(LoginForm loginForm) {
        return mapper.getMemberByLoginForm(loginForm);
    }
    
    @Override
	public Member getMemberById(String id) {
		return mapper.getMemberById(id);
	}

    @Override
    public boolean insertMember(Member member) {
        return mapper.insertMember(member);
    }

    @Override
    public String getIdById(String id) {
        return mapper.getIdById(id);
    }

    @Override
    public String getIdByUsername(String username) {
        return mapper.getIdByUsername(username);
    }
}