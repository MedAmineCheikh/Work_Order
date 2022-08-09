package com.work_order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article_Mission implements Serializable {

    @Id
    private String Id;
    private Double quantite_Mission;
    private  Double quantite_Utilise;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @ManyToOne(cascade =  CascadeType.MERGE)
    @JsonIgnore
    private Work_Order work_order;
    @ManyToOne(cascade = {PERSIST, DETACH,MERGE,REFRESH},fetch = FetchType.LAZY)
    @JsonIgnore
    private Article article;
}
