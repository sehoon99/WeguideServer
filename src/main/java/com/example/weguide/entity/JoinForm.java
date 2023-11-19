package com.example.weguide.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class JoinForm {


	private String id;
	private String username;
	private String pwd;
	private String checkpwd;
	private String tel;
}
