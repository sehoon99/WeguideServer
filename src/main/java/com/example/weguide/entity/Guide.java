package com.example.weguide.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Guide {
	@Id
	private String guide_id;
	private String guide_name;
	private String app_name;
	private String registrant;
	private java.sql.Date regist_date;
	private int numofdownload;
	private int love; 
}