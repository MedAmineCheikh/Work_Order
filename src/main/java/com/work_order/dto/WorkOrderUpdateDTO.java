package com.work_order.dto;

import com.work_order.entity.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderUpdateDTO {
    private String Id;
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
    private Statut statut;
}
