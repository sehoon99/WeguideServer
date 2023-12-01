package com.example.weguide.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.weguide.entity.Guide;

@Service("guidesorter")
public class GuideSorter {
	 public static List<Guide> sortByDownload(List<Guide> foundGuides) {
	        List<Guide> sortedList = new ArrayList<>(foundGuides);
	        Collections.sort(sortedList, Comparator.comparingInt(Guide::getDownload).reversed());
	        return sortedList;
	    }

	    public static List<Guide> sortByLove(List<Guide> foundGuides) {
	        List<Guide> sortedList = new ArrayList<>(foundGuides);
	        Collections.sort(sortedList, Comparator.comparingInt(Guide::getLove).reversed());
	        return sortedList;
	    }

	    // 최신순으로 정렬하는 메서드
	    public static List<Guide> sortByLatestDate(List<Guide> foundGuides) {
	        List<Guide> sortedList = new ArrayList<>(foundGuides);
	        Collections.sort(sortedList, Comparator.comparing(Guide::getRegistDate).reversed());
	        return sortedList;
	    }

	    public static List<Guide> sortByOldestDate(List<Guide> foundGuides) {
	        List<Guide> sortedList = new ArrayList<>(foundGuides);
	        Collections.sort(sortedList, Comparator.comparing(Guide::getRegistDate));
	        return sortedList;
	    }
	//public static void sortByAccuracy(List<Guide> foundGuides) {
	//    Collections.sort(foundGuides, Comparator.comparingInt(Guide::getMatchPriority));
	 //   }
	    public List<Guide> sort(List<Guide> foundGuides, int sortnum){
	    	System.out.println("어디서?");
	    	switch(sortnum) {
	    	case 1: return sortByDownload(foundGuides);
	    	case 2: return sortByLove(foundGuides);
	    	case 3: return sortByLatestDate(foundGuides);
	    	case 4: return sortByOldestDate(foundGuides);
	    	default : return sortByDownload(foundGuides);
	    	}
	    }
}
