package com.work_order.mapper;


import com.work_order.dto.AffaireRequestDTO;
import com.work_order.dto.AffaireResponseDTO;

import com.work_order.dto.AffaireUpdateDTO;
import com.work_order.entity.Affaire;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AffaireMapper {

    AffaireResponseDTO AffaireTOAffaireResponseDTO(Affaire affaire);
    Affaire AffaireRequestDTOAffaire(AffaireRequestDTO affaireRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAffaireFromDto(AffaireUpdateDTO dto, @MappingTarget Affaire entity);
}
