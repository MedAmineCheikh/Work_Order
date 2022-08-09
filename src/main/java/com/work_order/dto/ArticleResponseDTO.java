package com.work_order.dto;

import com.work_order.entity.Affaire;
import com.work_order.entity.Statut;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleResponseDTO {
    private String code;
    private String designation;
    private BigDecimal quantite_Contractuelle;
    private BigDecimal quantite_Realisee;
    private Affaire affaire;
    private Statut statut;
}
