package com.work_order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    private String matricule;
    private String prenom_Nom;
    private Boolean chef_Projet;
    private Date charge_Salariale;
    private String n_Permis;
    private String n_Cin;
    private Statut statut;
}
