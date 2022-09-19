package com.work_order.dto;

import com.work_order.entity.Article;
import com.work_order.entity.Statut;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AffaireResponseDTO {
    private int n_Affiaire;
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
    private int totalPages;
    private List<Article> articles;
}
