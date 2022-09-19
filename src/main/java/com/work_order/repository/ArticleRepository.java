package com.work_order.repository;

import com.work_order.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {


    @Query("SELECT a FROM Article a where a.statut=com.work_order.entity.Statut.Activer")
    List<Article> findByActiveArticle();
    @Query("SELECT a from Article a where a.designation like :kw")
    List<Article> searchArticle(@Param("kw")String Keyword);

    @Override
    Page<Article> findAll(Pageable pageable);

    @Query("SELECT a from Article a where a.designation like :kw")
    Page<Article> searchArticleByDesignation(@Param("kw") String Keyword,Pageable pageable);

}
