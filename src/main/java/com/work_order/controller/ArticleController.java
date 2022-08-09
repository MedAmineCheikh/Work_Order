package com.work_order.controller;


import com.work_order.dto.ArticleRequestDTO;
import com.work_order.dto.ArticleResponseDTO;
import com.work_order.dto.ArticleUpdateDTO;
import com.work_order.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Article management")
public class ArticleController {

    ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation(value = "Récupérer la liste des Articles")
    @GetMapping(path="/articles")
    public List<ArticleResponseDTO> allArticles()
    {

        return articleService.listArticles();
    }
    @ApiOperation(value = "ajoute Article")
    @PostMapping(path="/savearticle")
    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO){
        return articleService.save(articleRequestDTO);
    }
    @ApiOperation(value = "Récupérer Article")
    @GetMapping(path = "/article/{code}")
    public ArticleResponseDTO getArticle(@PathVariable String code){

        return articleService.getArticle(code);
    }
   /* @ApiOperation(value = "Update Article")
    @PutMapping("/update-article/{matricule}")
    @ResponseBody
    public Article UpdateArticle(@RequestBody Article article,@PathVariable String matricule) {
        return articleService.updateArticle(article,matricule);
    }*/

    @ApiOperation(value = "Delete Article")
    @DeleteMapping("/remove-article/{article-id}")
    @ResponseBody
    public void delete(@PathVariable("article-id") String matricule) {
        articleService.delete(matricule);
    }
   /* @ApiOperation(value = "Affecter activite TO article")
    @PutMapping(value = "/affecterArticleADepartement/{code-activite}/{matricule}")
    public void affecterActiviteTOArticle(@PathVariable("code-activite")String code, @PathVariable("matricule")String matricule) {
        articleService.affecterActiviteToArticle(code,matricule);
    }*/
   /* @PutMapping(value = "/desaffecterArticleDuDepartement/{idemp}/{iddept}")
    public void desaffecterArticleDuDepartement(@PathVariable("idemp")String articleId, @PathVariable("iddept")String depId)
    {
        articleService.desaffecterArticleDuActivite(articleId, depId);
    }*/

    @ApiOperation(value = "Update Article")
    @PutMapping("/update-articledto/")
    @ResponseBody
    public void UpdateArticleDTO(@RequestBody ArticleUpdateDTO dto) {
        articleService.updateArticleDTO(dto);
    }
    @ApiOperation(value = "affecter Affaire")
    @PutMapping("/affecterAffaireToArticle/{affaireid}/{code}")
    public void affecterAffaireToArticle(@PathVariable int affaireid,@PathVariable String code) {
        articleService.affecterAffaireToArticle(affaireid,code);
    }
    @ApiOperation(value = "Récupérer la liste des Articles Active")
    @GetMapping(path="/articlesActive")
    public List<ArticleResponseDTO> listArticlesActive(){
        return articleService.listArticlesActive();
    }
}
