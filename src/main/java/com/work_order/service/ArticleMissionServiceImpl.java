package com.work_order.service;

import com.work_order.dto.ArticleMissionRequestDTO;
import com.work_order.dto.ArticleMissionResponseDTO;
import com.work_order.dto.ArticleMissionResponseDTO;
import com.work_order.dto.ArticleMissionUpdateDTO;
import com.work_order.entity.Article;
import com.work_order.entity.Article_Mission;
import com.work_order.entity.Article_Mission;
import com.work_order.entity.Work_Order;
import com.work_order.mapper.ArticleMissionMapper;
import com.work_order.repository.ArticleRepository;
import com.work_order.repository.ArticleMissionRepository;
import com.work_order.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleMissionServiceImpl implements ArticleMissionService {


    ArticleMissionRepository articleMissionRepository;
    ArticleMissionMapper articleMissionMapper;

    WorkOrderRepository workOrderRepository;

    ArticleRepository articleRepository;

    public ArticleMissionServiceImpl(ArticleMissionRepository articleMissionRepository, ArticleMissionMapper articleMissionMapper, WorkOrderRepository workOrderRepository, ArticleRepository articleRepository) {
        this.articleMissionRepository = articleMissionRepository;
        this.articleMissionMapper = articleMissionMapper;
        this.workOrderRepository = workOrderRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleMissionResponseDTO save(ArticleMissionRequestDTO articleMissionRequestDTO) {
        Article_Mission articleM =articleMissionMapper.ArticleMissionRequestDTOArticleMission(articleMissionRequestDTO);
           articleM.setId(UUID.randomUUID().toString());
        Article_Mission saveArticleMission=articleMissionRepository.save(articleM);
        ArticleMissionResponseDTO articleMResponseDTO=articleMissionMapper.ArticleMissionTOArticleMissionResponseDTO(saveArticleMission);

        return articleMResponseDTO;
    }

    @Override
    public ArticleMissionResponseDTO getArticlemission(String Id) {
        Article_Mission order =articleMissionRepository.findById(Id).get();

        return articleMissionMapper.ArticleMissionTOArticleMissionResponseDTO(order);
    }

    @Override
    public Article_Mission updateArticlemission(Article_Mission article_mission, String Id) {
        return null;
    }

    @Override
    public List<ArticleMissionResponseDTO> listArticlemissions() {
        List<Article_Mission> article_missions=articleMissionRepository.findAll();
        List<ArticleMissionResponseDTO> articleMissionResponseDTOS=article_missions.stream()
                .map(cust->articleMissionMapper.ArticleMissionTOArticleMissionResponseDTO(cust))
                .collect(Collectors.toList());

        return articleMissionResponseDTOS;
    }

    @Override
    public void delete(String Id) {
        articleMissionRepository.deleteById(Id);

    }

    @Override
    public void updateArticleMissionDTO(ArticleMissionUpdateDTO dto) {
        Article_Mission myArticleMission =articleMissionRepository.findById(dto.getId()).get();
        articleMissionMapper.updateArticleMissionFromDto(dto, myArticleMission);
        articleMissionRepository.save(myArticleMission);
    }

    @Override
    public void affecterWorkOrderToArticleMission(String orderId, String MissionId) {
        Work_Order work_order=workOrderRepository.findById(orderId).get();
        Article_Mission article_mission=articleMissionRepository.findById(MissionId).get();
        article_mission.setWork_order(work_order);
        articleMissionRepository.save(article_mission);
    }

    @Override
    public void affecterArticleToArticleMission(String code, String missionId) {
        Article article=articleRepository.findById(code).get();
        Article_Mission article_mission=articleMissionRepository.findById(missionId).get();
        article_mission.setArticle(article);
        articleMissionRepository.save(article_mission);
    }

    @Override
    public List<ArticleMissionResponseDTO> ListArticleMissionActive() {
        List<Article_Mission> article_missions=articleMissionRepository.findbyActiveMission();
        List<ArticleMissionResponseDTO> articleMissionResponseDTOS=article_missions.stream()
                .map(cust->articleMissionMapper.ArticleMissionTOArticleMissionResponseDTO(cust))
                .collect(Collectors.toList());

        return articleMissionResponseDTOS;
    }
}
