package com.work_order.service;

import com.work_order.dto.ArticleResponseDTO;
import com.work_order.dto.ArticleRequestDTO;
import com.work_order.dto.ArticleResponseDTO;
import com.work_order.dto.ArticleUpdateDTO;
import com.work_order.entity.*;
import com.work_order.entity.Article;
import com.work_order.exceptions.StatutNotActiveException;
import com.work_order.mapper.ArticleMapper;
import com.work_order.repository.AffaireRepository;
import com.work_order.repository.ArticleMissionRepository;
import com.work_order.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    AffaireRepository affaireRepository;

    ArticleRepository articleRepository;
    ArticleMapper articleMapper;
    ArticleMissionRepository articleMissionRepository;

    public ArticleServiceImpl(AffaireRepository affaireRepository, ArticleRepository articleRepository, ArticleMapper articleMapper, ArticleMissionRepository articleMissionRepository) {
        this.affaireRepository = affaireRepository;
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.articleMissionRepository = articleMissionRepository;
    }

    @Override
    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO) {
        Article article =articleMapper.ArticleRequestDTOArticle(articleRequestDTO);
        article.setCode(UUID.randomUUID().toString());
        Article saveArticle=articleRepository.save(article);
        ArticleResponseDTO articleResponseDTO=articleMapper.ArticleTOArticleResponseDTO(saveArticle);

        return articleResponseDTO;
    }

    @Override
    public ArticleResponseDTO getArticle(String code) {
        Article article =articleRepository.findById(code).get();

        return articleMapper.ArticleTOArticleResponseDTO(article);
    }

    @Override
    public List<ArticleResponseDTO> listArticles() {
        List<Article> articles=articleRepository.findAll();

        List<ArticleResponseDTO>articleResponseDTOS=articles.stream()
                .map(cust->articleMapper.ArticleTOArticleResponseDTO(cust))
                .collect(Collectors.toList());

        return articleResponseDTOS;
    }

    @Override
    public void delete(String code) {
       Article article=articleRepository.findById(code).get();
        List<Article_Mission> article_missions=articleMissionRepository.findAll();


        for (Article_Mission article_mission: article_missions)
        {
            if (article_mission.getArticle().getCode().equals(article.getCode()))
            {
                article_mission.setArticle(null);
            }
        }

        articleRepository.delete(article);
    }
    @Override
    public void updateArticleDTO(ArticleUpdateDTO dto) {
        Article myArticle =articleRepository.findById(dto.getCode()).get();
        articleMapper.updateArticleFromDto(dto, myArticle);
        articleRepository.save(myArticle);
}

    @Override
    public void affecterAffaireToArticle(int affaireid, String code) {
        Affaire affaire=affaireRepository.findById(affaireid).get();
       if (affaire.getStatut().equals(Statut.Activer)){
           Article article=articleRepository.findById(code).get();
           article.setAffaire(affaire);
           articleRepository.save(article);
       }
        else throw new StatutNotActiveException("This Affaire is Desactivated");
    }
    @Override
    public void removeAffaireFromArticle( String code){
        Article article=articleRepository.findById(code).get();
        article.setAffaire(null);
        articleRepository.save(article);
    }

    @Override
    public List<ArticleResponseDTO> listArticlesActive() {
        List<Article>articles=articleRepository.findByActiveArticle();
        List<ArticleResponseDTO>articleResponseDTOS=articles.stream()
                .map(cust->articleMapper.ArticleTOArticleResponseDTO(cust))
                .collect(Collectors.toList());

        return articleResponseDTOS;
    }
}
