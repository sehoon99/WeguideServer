package com.example.weguide.dao;

import com.example.weguide.entity.Lovehistory;

public interface LovehistoryDao {

	Lovehistory Isloved(String filename, String username);
	void loved(String id);
	}
