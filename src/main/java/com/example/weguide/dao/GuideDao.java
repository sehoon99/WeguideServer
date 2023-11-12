package com.example.weguide.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.example.weguide.entity.Guide;

public interface GuideDao {
    List<Guide> getAllGuides();
    Guide getGuideById(String guide_id);
    void insertGuide(Guide guide);
    void updateGuide(Guide guide);
    void deleteGuide(String guide_id);
    void upLike(String guide_id);
    void downLike(String guide_id);
    boolean updwl(String guide_id);
    List<Guide> searchGuides(String app_name, String searchKeyword);
    List<Guide> getGuideByapp(String app_name);
}
