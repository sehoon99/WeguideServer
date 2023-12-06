package com.example.weguide.controller;

import java.util.HashMap;
import java.util.Map;

//JwtController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.weguide.auth.JwtUtil;
import com.example.weguide.entity.LoginForm;
import com.example.weguide.entity.Member;
import com.example.weguide.service.MemberService;

@RestController
@RequestMapping("/member")
public class JwtController {

 private final JwtUtil jwtUtil;

 	@Autowired
 	private MemberService memberService;
 
 	@Autowired
 	public JwtController(JwtUtil jwtUtil) {
 		this.jwtUtil = jwtUtil;
 }

 	@PostMapping("/join")
    public String join(@RequestBody Member member) {
    // 파라미터: id, pwd, username

    	int isJoin = memberService.join(member);
    	
        if (isJoin == 0) {
            return "result : JOIN_SUCCESS";
        } else if (isJoin == 1) {
            return "result : JOIN_FAILURE_ID_DUPLICATE";
        } else if (isJoin == 2) {
        	return "result : JOIN_FAILURE_NICKNAME_DUPLICATE";
        }
        
        return "result : JOIN_FAILURE";
    }
 	
 	
 @PostMapping("/login")
 public ResponseEntity<Map<String, String>> login(@RequestBody LoginForm loginform) {
	 System.out.println("로그인 로직 시작");
     String id = loginform.getId();
     String password = loginform.getPassword();
     System.out.println(id+" , "+password);

     Member member= memberService.getMemberToLogin(loginform);
     Map<String, String> response = new HashMap<>();
     if(member != null && member.getId() != null && member.getId() != "") {
    	 String username=member.getUsername();	 
    	 String token = jwtUtil.generateToken(id);
         response.put("token", token);
         response.put("success", "true");
         response.put("id", id);
         response.put("username", username);
         System.out.println("로그인 로직 종료");
     } else {
    	 response.put("token", "null");
     }
     return ResponseEntity.ok(response);
 }
 
 @PostMapping("/valid")
 public int tokenvalid(String token) {
	 System.out.println(token);
	 return jwtUtil.validateToken(token);
 }
}
