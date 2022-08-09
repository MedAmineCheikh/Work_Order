package com.work_order.controller;


import com.work_order.dto.AffaireRequestDTO;
import com.work_order.dto.AffaireResponseDTO;
import com.work_order.dto.AffaireUpdateDTO;
import com.work_order.entity.Affaire;
import com.work_order.service.AffaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Affaire management")
public class AffaireController {
    
    AffaireService affaireService;

    public AffaireController(AffaireService affaireService) {
        this.affaireService = affaireService;
    }

    @ApiOperation(value = "Récupérer la liste des Affaires")
    @GetMapping(path="/affaires")
    public List<AffaireResponseDTO> allAffaires()
    {

        return affaireService.listAffaires();
    }
    @ApiOperation(value = "ajoute Affaire")
    @PostMapping(path="/saveaffaire")
    public AffaireResponseDTO save(AffaireRequestDTO affaireRequestDTO){
        return affaireService.save(affaireRequestDTO);
    }
    @ApiOperation(value = "Récupérer Affaire")
    @GetMapping(path = "/affaire/{n_Affiaire}")
    public AffaireResponseDTO getAffaire(@PathVariable int n_Affiaire){

        return affaireService.getAffaire(n_Affiaire);
    }


    @ApiOperation(value = "Delete Affaire")
    @DeleteMapping("/remove-affaire/{affaire-id}")
    @ResponseBody
    public void delete(@PathVariable("affaire-id") int n_Affiaire) {
        affaireService.delete(n_Affiaire);
    }


    @ApiOperation(value = "Update Affaire")
    @PutMapping("/update-affairedto/")
    @ResponseBody
    public void UpdateAffaireDTO(@RequestBody AffaireUpdateDTO dto) {
        affaireService.updateAffaireDTO(dto);
    }
   /* @ApiOperation(value = "Affecter Article")
    @PutMapping("/affecterArticleToAffaire/{code}/{affaireId}")
    public void affecterArticleToAffaire(@PathVariable String code, @PathVariable int affaireId) {
        affaireService.affecterArticleToAffaire(code,affaireId);
    }*/

    @ApiOperation(value = "Récupérer la liste des Affaires Active")
    @GetMapping(path="/affairesActive")
    public List<AffaireResponseDTO> ListAffaireActive() {
        return affaireService.ListAffaireActive();
    }
}
