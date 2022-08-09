package com.work_order.service;

import com.work_order.dto.*;
import com.work_order.entity.Affaire;
import com.work_order.entity.Work_Order;

import java.util.List;

public interface AffaireService {
    AffaireResponseDTO save(AffaireRequestDTO affaireRequestDTO);
    AffaireResponseDTO getAffaire(int n_Affiaire);

    List<AffaireResponseDTO> listAffaires();
    void delete(int n_Affiaire);

    void updateAffaireDTO(AffaireUpdateDTO dto);

    void affecterArticleToAffaire(String code,int affaireId);

    List<AffaireResponseDTO> ListAffaireActive();
}
