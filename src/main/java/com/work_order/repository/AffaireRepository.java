package com.work_order.repository;

import com.work_order.entity.Affaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffaireRepository extends JpaRepository<Affaire,Integer> {

    @Query("select a from  Affaire a where a.statut=com.work_order.entity.Statut.Activer")
    List<Affaire> findByActiveAffaire();

    @Query("select a from  Affaire a where a.designation_Affaire like :kw")
    Page<Affaire> searchAffaireByDesignation_Affaire(@Param("kw") String Keyword, Pageable pageable);

}
