package com.work_order.dto;

import com.work_order.entity.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDTO {
    private String code;
    private String designation;
    private BigDecimal quantite_Contractuelle;
    private BigDecimal quantite_Realisee;
    private Statut statut;
}
