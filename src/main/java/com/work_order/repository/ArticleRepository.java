package com.work_order.repository;

import com.work_order.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {


    @Query("SELECT a FROM Article a where a.statut=com.work_order.entity.Statut.Activer")
    List<Article> findByActiveArticle();
}
