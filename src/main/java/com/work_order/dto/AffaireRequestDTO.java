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
public class AffaireRequestDTO {

    private String designation_Affaire;
    private String bon_de_Commande;
    private BigDecimal montant;
    private Date date_Debut;
    private Date date_Cloture;
    private Date date_passage_Execution;
    private Boolean lieu_multiple_par_Mission;
    private BigDecimal montant_Vente;
    private BigDecimal montant_budgetaire_Matriel;
    private BigDecimal montant_budgetaire_Service;
    private Statut statut;

}
