package com.example.weguide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Guide {
	@Id
	private String guide_id;
	
	@Column(name = "guide_name")
    private String guideName;
	
	 public void setGuide_name(String guide_name) {
	        this.guideName = guide_name;
	    }
	
	private String app_name;
	private String registrant;
	private java.sql.Date regist_date;
	private int download;
	private int love; 
}
