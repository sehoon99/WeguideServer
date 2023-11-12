package com.example.weguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weguide.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    List<Guide> findByGuideNameIgnoreCaseContaining(String searchString);
}