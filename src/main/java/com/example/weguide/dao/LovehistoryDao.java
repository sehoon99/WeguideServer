package com.example.weguide.dao;

import com.example.weguide.entity.Lovehistory;

public interface LovehistoryDao {

	Lovehistory Isloved(Lovehistory lh);
	boolean loved(Lovehistory lh);
	boolean nolove(Lovehistory lh);
	}
