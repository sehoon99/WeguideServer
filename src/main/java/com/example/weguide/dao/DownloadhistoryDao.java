package com.example.weguide.dao;

import com.example.weguide.entity.Downloadhistory;

public interface DownloadhistoryDao {

	
	Downloadhistory Isdownloaded(String filename, String username);
    void downloaded(Downloadhistory downloadhistory);
}
