package com.work_order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Work_Order implements Serializable {

    @Id
    private String id;
    @NotNull
    private String demandeur;
    private  String employeId;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date debut_Previsionnel;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private  Date fin_Previsionnel;
    @Temporal(TemporalType.TIMESTAMP)
    private Date debut_Reel;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin_Reel;
    private  String remarque;
    private BigDecimal n_fiche_Intervention;
    private Boolean fichier;
    private BigDecimal frais_Mission;
    private BigDecimal hebergement;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Statut statut;
    @ManyToOne(cascade ={PERSIST, DETACH,MERGE,REFRESH})
    @JsonIgnore
    private Affaire affaire;
    @ManyToMany(cascade ={PERSIST, DETACH,MERGE,REFRESH})
    @JsonIgnore
    private List<Article> articles;
    @Transient
    private Employe employe;

}
