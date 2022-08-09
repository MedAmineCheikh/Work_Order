package com.work_order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Affaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int n_Affiaire;
    private String designation_Affaire;
    private String bon_de_Commande;
    private BigDecimal montant;
    @Temporal(TemporalType.DATE)
    private Date date_Debut;
    @Temporal(TemporalType.DATE)
    private Date date_Cloture;
    @Temporal(TemporalType.DATE)
    private Date date_passage_Execution;
    private Boolean lieu_multiple_par_Mission;
    private BigDecimal montant_Vente;
    private BigDecimal montant_budgetaire_Matriel;
    private BigDecimal montant_budgetaire_Service;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @OneToMany(cascade={PERSIST, DETACH,MERGE,REFRESH})
    @JsonIgnore
    private List<Article> articles;


}
