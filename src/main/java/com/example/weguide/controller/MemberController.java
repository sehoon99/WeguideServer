package com.example.weguide.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.weguide.entity.LoginForm;
import com.example.weguide.entity.Member;
import com.example.weguide.service.MemberService;

@RestController
public class MemberController {
	
	 @Autowired
	 private MemberService memberService;
	 
	@PostMapping("/join")
    public String join(@RequestBody Member member) {
    // 파라미터: id, pwd, username

    	int isJoin = memberService.join(member);
    	
        if (isJoin == 0) {
            return "{\"result\" : \"JOIN_SUCCESS\"}";
        } else if (isJoin == 1) {
            return "{\"result\" : \"JOIN_FAILURE_ID_DUPLICATE\"}";
        } else if (isJoin == 2) {
        	return "{\"result\" : \"JOIN_FAILURE_NICKNAME_DUPLICATE\"}";
        }
        
        return "{\"result\" : \"JOIN_FAILURE\"}";
    }
	
	
}
