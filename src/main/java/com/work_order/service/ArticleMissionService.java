package com.work_order.service;

import com.work_order.dto.ArticleMissionRequestDTO;
import com.work_order.dto.ArticleMissionResponseDTO;
import com.work_order.dto.ArticleMissionUpdateDTO;
import com.work_order.entity.Article_Mission;

import java.util.List;

public interface ArticleMissionService {

    ArticleMissionResponseDTO save(ArticleMissionRequestDTO articleMissionRequestDTO);

    ArticleMissionResponseDTO getArticlemission(String Id);
    Article_Mission updateArticlemission(Article_Mission article_mission, String Id);
    List<ArticleMissionResponseDTO> listArticlemissions();
    void delete(String Id);
    void updateArticleMissionDTO(ArticleMissionUpdateDTO dto);

    void affecterWorkOrderToArticleMission(String orderId,String MissionId);
    void affecterArticleToArticleMission(String code,String missionId);

    List<ArticleMissionResponseDTO> ListArticleMissionActive();
}
