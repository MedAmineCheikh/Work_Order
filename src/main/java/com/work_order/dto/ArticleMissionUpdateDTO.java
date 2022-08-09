package com.work_order.dto;

import com.work_order.entity.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleMissionUpdateDTO {
    private String Id;
    private Double quantite_Mission;
    private  Double quantite_Utilise;
    private Statut statut;
}
