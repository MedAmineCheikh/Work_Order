package com.work_order.service;

import com.work_order.dto.ArticleRequestDTO;
import com.work_order.dto.ArticleResponseDTO;
import com.work_order.dto.ArticleUpdateDTO;
import com.work_order.entity.Article;

import java.util.List;

public interface ArticleService {

    ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO);
    ArticleResponseDTO getArticle(String code);

    List<ArticleResponseDTO> listArticles();
    void delete(String code);
    public void updateArticleDTO(ArticleUpdateDTO dto);
    void affecterAffaireToArticle(int affaireid,String code);
    List<ArticleResponseDTO> listArticlesActive();
    void removeAffaireFromArticle( String code);
}
