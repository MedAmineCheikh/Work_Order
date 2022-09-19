package com.work_order.controller;

import com.work_order.dto.ArticleMissionRequestDTO;
import com.work_order.dto.ArticleMissionResponseDTO;
import com.work_order.dto.ArticleMissionUpdateDTO;
import com.work_order.service.ArticleMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@Api(tags = "ArticleMission management")
public class ArticleMissionController {

    ArticleMissionService articleMissionService;

    public ArticleMissionController(ArticleMissionService articleMissionService) {
        this.articleMissionService = articleMissionService;
    }

    @ApiOperation(value = "Récupérer la liste des ArticleMissions")
    @GetMapping(path="/articleMissions")
    public List<ArticleMissionResponseDTO> allArticleMissions()
    {

        return articleMissionService.listArticlemissions();
    }
    @ApiOperation(value = "ajoute ArticleMission")
    @PostMapping(path="/savearticleMission")
    public ArticleMissionResponseDTO save(@RequestBody ArticleMissionRequestDTO articleMissionRequestDTO){
        return articleMissionService.save(articleMissionRequestDTO);
    }
    @ApiOperation(value = "Récupérer ArticleMission")
    @GetMapping(path = "/articleMission/{Id}")
    public ArticleMissionResponseDTO getArticleMission(@PathVariable String Id){

        return articleMissionService.getArticlemission(Id);
    }
    /*@ApiOperation(value = "Update ArticleMission")
    @PutMapping("/update-articleMission/{matricule}")
    @ResponseBody
    public ArticleMission UpdateArticleMission(@RequestBody ArticleMission articleMission,@PathVariable String matricule) {
        return articleMissionService.updateArticleMission(articleMission,matricule);
    }*/

    @ApiOperation(value = "Delete ArticleMission")
    @DeleteMapping("/remove-articleMission/{articleMission-id}")
    @ResponseBody
    public void delete(@PathVariable("articleMission-id")  String Id) {
        articleMissionService.delete(Id);
    }
   /* @ApiOperation(value = "Affecter activite TO articleMission")
    @PutMapping(value = "/affecterArticleMissionADepartement/{code-activite}/{matricule}")
    public void affecterActiviteTOArticleMission(@PathVariable("code-activite")String code, @PathVariable("matricule")String matricule) {
        articleMissionService.affecterActiviteToArticleMission(code,matricule);
    }*/
   /* @PutMapping(value = "/desaffecterArticleMissionDuDepartement/{idemp}/{iddept}")
    public void desaffecterArticleMissionDuDepartement(@PathVariable("idemp")String articleMissionId, @PathVariable("iddept")String depId)
    {
        articleMissionService.desaffecterArticleMissionDuActivite(articleMissionId, depId);
    }*/

    @ApiOperation(value = "Update ArticleMission")
    @PutMapping("/update-articleMissiondto/")
    @ResponseBody
    public void UpdateArticleMissionDTO(@RequestBody ArticleMissionUpdateDTO dto) {
        articleMissionService.updateArticleMissionDTO(dto);
    }
    @ApiOperation(value = "Affecter Article")
    @PutMapping("/affecterArticleToArticleMission/{code}/{missionId]")
     public void affecterArticleToArticleMission(@PathVariable String code,@PathVariable String missionId){
        articleMissionService.affecterArticleToArticleMission(code,missionId);
    }
    @ApiOperation(value = "Affecter WorkOrder")
    @PutMapping("/affecterWorkOrderToArticleMission/{orderId}/{MissionId}")
    public void affecterWorkOrderToArticleMission(@PathVariable String orderId,@PathVariable String MissionId){
        articleMissionService.affecterWorkOrderToArticleMission(orderId,MissionId);
    }
    @ApiOperation(value = "Récupérer la liste des ArticleMissions Active")
    @GetMapping(path="/articleMissionsActive")
    public List<ArticleMissionResponseDTO> ListArticleMissionActive() {
        return articleMissionService.ListArticleMissionActive();

    }
    @ApiOperation( value = "Remove Work Order")
    @PutMapping("/removeWorkorder/{orderId}")
    public void removeWorkOrder(@PathVariable String orderId){
        articleMissionService.removeWorkOrder(orderId);
    }
    @ApiOperation( value = "Remove Article")
    @PutMapping("/removeArticle/{MissionId}")
    public void removeArticle(@PathVariable String MissionId){
        articleMissionService.removeArticle(MissionId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandlerStatut(Exception s)
    {
        return new ResponseEntity<>(s.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
