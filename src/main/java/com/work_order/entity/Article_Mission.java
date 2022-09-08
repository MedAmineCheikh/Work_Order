package com.work_order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Article_Mission implements Serializable {

    @Id
    private String Id;
    @NotNull
    private Double quantite_Mission;
    @NotNull
    private  Double quantite_Utilise;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Statut statut;
    @ManyToOne(cascade =  CascadeType.MERGE)
    @JsonIgnore
    private Work_Order work_order;
    @ManyToOne(cascade = {PERSIST, DETACH,MERGE,REFRESH})
    @JsonIgnore
    private Article article;
}
