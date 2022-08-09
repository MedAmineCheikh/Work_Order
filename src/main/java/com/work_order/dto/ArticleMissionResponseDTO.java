package com.work_order.dto;

import com.work_order.entity.Article;
import com.work_order.entity.Statut;
import com.work_order.entity.Work_Order;
import lombok.Data;

@Data
public class ArticleMissionResponseDTO {
    private String Id;
    private Double quantite_Mission;
    private  Double quantite_Utilise;
    private Work_Order work_order;
    private Article article;
    private Statut statut;
}
