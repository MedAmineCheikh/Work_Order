package com.work_order.mapper;

import com.work_order.dto.ArticleMissionRequestDTO;
import com.work_order.dto.ArticleMissionResponseDTO;

import com.work_order.dto.ArticleMissionUpdateDTO;
import com.work_order.entity.Article_Mission;
import com.work_order.entity.Work_Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ArticleMissionMapper {

    ArticleMissionResponseDTO ArticleMissionTOArticleMissionResponseDTO(Article_Mission article_mission);
    Article_Mission ArticleMissionRequestDTOArticleMission(ArticleMissionRequestDTO articleMissionRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateArticleMissionFromDto(ArticleMissionUpdateDTO dto, @MappingTarget Article_Mission entity);
}
