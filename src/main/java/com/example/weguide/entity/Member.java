package com.example.weguide.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	private String id;
	
	private String username;

	private String password;
	
	private String tel;
}