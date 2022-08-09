package com.work_order.repository;

import com.work_order.entity.Affaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffaireRepository extends JpaRepository<Affaire,Integer> {

    @Query("select a from  Affaire a where a.statut=com.work_order.entity.Statut.Activer")
    List<Affaire> findByActiveAffaire();
}
