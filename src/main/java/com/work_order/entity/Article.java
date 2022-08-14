package com.work_order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.CascadeType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Article implements Serializable {
    @Id
    private String code;
    private String designation;
    private BigDecimal quantite_Contractuelle;
    private BigDecimal quantite_Realisee;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @ManyToOne(cascade={PERSIST, DETACH,MERGE,REFRESH})
    @JsonIgnore
    private Affaire affaire;

}
