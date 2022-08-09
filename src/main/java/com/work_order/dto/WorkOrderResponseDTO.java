package com.work_order.dto;

import com.work_order.entity.Affaire;
import com.work_order.entity.Employe;
import com.work_order.entity.Statut;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WorkOrderResponseDTO {
    private String Id;
    private String demandeur;
    private Date debut_Previsionnel;
    private  Date fin_Previsionnel;
    private Date debut_Reel;
    private Date fin_Reel;
    private  String remarque;
    private BigDecimal n_fiche_Intervention;
    private Boolean fichier;
    private BigDecimal frais_Mission;
    private BigDecimal hebergement;
    private Statut statut;
    private Affaire affaire;
    private Employe employe;
}
