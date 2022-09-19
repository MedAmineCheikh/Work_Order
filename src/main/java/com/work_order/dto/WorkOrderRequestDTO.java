package com.work_order.dto;

import com.work_order.entity.Affaire;
import com.work_order.entity.Article;
import com.work_order.entity.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderRequestDTO {
    private String demandeur;
    private  String employeId;
    private Date debut_Previsionnel;
    private  Date fin_Previsionnel;
    private Date debut_Reel;
    private Date fin_Reel;
    private  String remarque;
    private BigDecimal n_fiche_Intervention;
    private Boolean fichier;
    private BigDecimal frais_Mission;
    private BigDecimal hebergement;
    private Affaire affaire;
    private List<Article> articles;
    private Statut statut;
}
