package com.work_order.controller;


import com.work_order.dto.AffaireRequestDTO;
import com.work_order.dto.AffaireResponseDTO;
import com.work_order.dto.AffaireUpdateDTO;
import com.work_order.entity.Affaire;
import com.work_order.entity.Article;
import com.work_order.service.AffaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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
    @ApiOperation(value = "Récupérer la liste des Affaires")
    @GetMapping(path="/searchaffaires")
    public List<AffaireResponseDTO> searchAffaires(
            @RequestParam(name = "Keyword",defaultValue = "")String kw,
            @RequestParam (name = "page",defaultValue = "0")int page
            ,@RequestParam(name = "size",defaultValue = "10")int size)
    {

        return affaireService.searchAffaire("%"+kw+"%",page,size);
    }
    @ApiOperation(value = "ajoute Affaire")
    @PostMapping(path="/saveaffaire")
    public AffaireResponseDTO save(@RequestBody AffaireRequestDTO affaireRequestDTO){
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

  /*  @ApiOperation(value = "Affecter Article")
    @PutMapping("/affecterArticleToAffaire/{code}/{affaireId}")
    public void affecterArticleToAffaire(@PathVariable String code, @PathVariable int affaireId) {
        affaireService.affecterArticleToAffaire(code,affaireId);
    }*/

    @ApiOperation(value = "Récupérer la liste des Affaires Active")
    @GetMapping(path="/affairesActive")
    public List<AffaireResponseDTO> ListAffaireActive() {
        return affaireService.ListAffaireActive();
    }

    @PutMapping("/removeArticle/{code}/{affaireId}")
    public void removeArticle(@PathVariable String code,@PathVariable int affaireId ){
        affaireService.removeArticle(code,affaireId);
    }
    @GetMapping(path="/affaireArticle/{id}")
    public List<Article> getaffaireArticle(@PathVariable int id){
        return  affaireService.getaffaireArticle(id);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandlerStatut(Exception s)
    {
        return new ResponseEntity<>(s.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
