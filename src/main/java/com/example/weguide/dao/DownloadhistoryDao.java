package com.example.weguide.dao;

import com.example.weguide.entity.Downloadhistory;

public interface DownloadhistoryDao {

	
	Downloadhistory Isdownloaded(Downloadhistory dh);
    boolean downloaded(Downloadhistory dh);
}
