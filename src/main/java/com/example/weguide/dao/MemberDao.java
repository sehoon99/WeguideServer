package com.example.weguide.dao;

import com.example.weguide.entity.LoginForm;
import com.example.weguide.entity.Member;

public interface MemberDao {

    Member getMemberByLoginForm(LoginForm loginForm);
    
    Member getMemberById(String id);

    boolean insertMember(Member member);

    String getIdById(String id);
    
    String getIdByUsername(String username);

}
