package com.work_order.mapper;


import com.work_order.dto.*;
import com.work_order.entity.Affaire;
import com.work_order.entity.Article;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")

public interface ArticleMapper {
    ArticleResponseDTO ArticleTOArticleResponseDTO(Article article);
    Article ArticleRequestDTOArticle(ArticleRequestDTO articleRequestDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateArticleFromDto(ArticleUpdateDTO dto, @MappingTarget Article entity);
}
