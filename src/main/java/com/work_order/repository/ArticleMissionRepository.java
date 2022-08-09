package com.work_order.repository;

import com.work_order.entity.Article_Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMissionRepository extends JpaRepository<Article_Mission,String> {
    @Query("select m from Article_Mission m where m.statut= com.work_order.entity.Statut.Activer")
    List<Article_Mission> findbyActiveMission();
}
