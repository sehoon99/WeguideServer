package com.example.weguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weguide.entity.Guide;
import com.example.weguide.service.GuideService;

//스프링 서버에서 문자열을 받아 검색하고 응답하는 코드

//Spring Boot 컨트롤러
@RestController
public class GuideController {
 @Autowired
 private GuideService guideService;

 @GetMapping("/searchbyapp")
 public ResponseEntity<List<Guide>> searchbyapp(@RequestParam String app_name, @RequestParam int sortnum) {
	 
     List<Guide> foundGuides = guideService.searchbyapp(app_name,sortnum);

     // 검색 결과를 클라이언트로 응답 (JSON 형식)
     return ResponseEntity.ok(foundGuides);
 }
 @GetMapping("/searchbyname")
 public ResponseEntity<List<Guide>> searchbyname(@RequestParam String guide_name, @RequestParam int sortnum) {
	 
     List<Guide> foundGuides = guideService.searchbyname(guide_name,sortnum);

     // 검색 결과를 클라이언트로 응답 (JSON 형식)
     return ResponseEntity.ok(foundGuides);
 }
 @GetMapping("/searchbyand")
 public ResponseEntity<List<Guide>> searchGuidesAnd(@RequestParam("app_name") String appName, @RequestParam("guide_name") String guide_name, @RequestParam int sortnum) {
	 
     List<Guide> foundGuides = guideService.searchGuidesAnd(appName, guide_name,sortnum);

     // 검색 결과를 클라이언트로 응답 (JSON 형식)
     return ResponseEntity.ok(foundGuides);
 }
 @GetMapping("/searchbyor")
 public ResponseEntity<List<Guide>> searchGuidesOr(@RequestParam String keyword, @RequestParam int sortnum) {
	 
     List<Guide> foundGuides = guideService.searchGuidesOr(keyword,sortnum);

     // 검색 결과를 클라이언트로 응답 (JSON 형식)
     return ResponseEntity.ok(foundGuides);
 }
}


