package com.example.weguide.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Downloadhistory {
	@Id
	private String download_id;
	private String id;
	private String guide_id;
}