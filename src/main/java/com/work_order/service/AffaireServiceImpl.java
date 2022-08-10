package com.work_order.service;

import com.work_order.dto.AffaireRequestDTO;
import com.work_order.dto.AffaireResponseDTO;
import com.work_order.dto.AffaireUpdateDTO;
import com.work_order.entity.Affaire;
import com.work_order.entity.Article;
import com.work_order.entity.Statut;
import com.work_order.entity.Work_Order;
import com.work_order.exceptions.StatutNotActiveException;
import com.work_order.mapper.AffaireMapper;
import com.work_order.repository.AffaireRepository;
import com.work_order.repository.ArticleRepository;
import com.work_order.repository.WorkOrderRepository;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AffaireServiceImpl implements AffaireService {
    AffaireRepository affaireRepository;
    AffaireMapper affaireMapper;

    ArticleRepository articleRepository;

    WorkOrderRepository workOrderRepository;

    public AffaireServiceImpl(AffaireRepository affaireRepository, AffaireMapper affaireMapper, ArticleRepository articleRepository, WorkOrderRepository workOrderRepository) {
        this.affaireRepository = affaireRepository;
        this.affaireMapper = affaireMapper;
        this.articleRepository = articleRepository;
        this.workOrderRepository = workOrderRepository;
    }

    @Override
    public AffaireResponseDTO save(AffaireRequestDTO affaireRequestDTO) {
        Affaire affaire = affaireMapper.AffaireRequestDTOAffaire(affaireRequestDTO);

        Affaire saveAffaire = affaireRepository.save(affaire);
        AffaireResponseDTO affaireResponseDTO = affaireMapper.AffaireTOAffaireResponseDTO(saveAffaire);

        return affaireResponseDTO;
    }

    @Override
    public AffaireResponseDTO getAffaire(int n_Affiaire) {

        Affaire affaire = affaireRepository.findById(n_Affiaire).get();

        return affaireMapper.AffaireTOAffaireResponseDTO(affaire);
    }


    @Override
    public List<AffaireResponseDTO> listAffaires() {

        List<Affaire> affaires = affaireRepository.findAll();
        List<AffaireResponseDTO> affaireResponseDTOS = affaires.stream()
                .map(cust -> affaireMapper.AffaireTOAffaireResponseDTO(cust))
                .collect(Collectors.toList());

        return affaireResponseDTOS;
    }

    @Override
    public void delete(int n_Affiaire) {
        Affaire affaire = affaireRepository.findById(n_Affiaire).get();
        List<Work_Order> work_orders = workOrderRepository.findAll();
        for (Article article : affaire.getArticles()) {
            article.setAffaire(null);

        }
        for (Work_Order order : work_orders) {
            if (order.getAffaire().getN_Affiaire() == affaire.getN_Affiaire()) {
                order.setAffaire(null);

            }
        }

        affaireRepository.delete(affaire);

    }

    @Override
    public void updateAffaireDTO(AffaireUpdateDTO dto) {
        Affaire myAffaire = affaireRepository.findById(dto.getN_Affiaire()).get();
        affaireMapper.updateAffaireFromDto(dto, myAffaire);
        affaireRepository.save(myAffaire);
    }

    @Override
    public void affecterArticleToAffaire(String code, int affaireId) {


        Article article = articleRepository.findById(code).get();
        if (article.getStatut().equals(Statut.Activer)) {
            Affaire affaire = affaireRepository.findById(affaireId).get();
            affaire.getArticles().add(article);
            affaireRepository.save(affaire);
        } else throw new StatutNotActiveException("This Article is Desactivated");

    }

    @Override
    public List<AffaireResponseDTO> ListAffaireActive() {
        List<Affaire> affaires = affaireRepository.findByActiveAffaire();
        List<AffaireResponseDTO> affaireResponseDTOS = affaires.stream()
                .map(cust -> affaireMapper.AffaireTOAffaireResponseDTO(cust))
                .collect(Collectors.toList());

        return affaireResponseDTOS ;
    }

    @Override
    public void removeArticle(String code, int affaireId) {
        Affaire affaire = affaireRepository.findById(affaireId).get();
        Article article = articleRepository.findById(code).get();

        for (Article articles : affaire.getArticles()) {
            if (articles.getCode().equals(article.getCode())) {
                affaire.getArticles().remove(article);
                affaireRepository.save(affaire);
            }

        }

    }
}