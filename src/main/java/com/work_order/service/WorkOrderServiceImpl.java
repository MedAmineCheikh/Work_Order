package com.work_order.service;

import com.work_order.dto.WorkOrderUpdateDTO;
import com.work_order.entity.*;
import com.work_order.exceptions.StatutNotActiveException;
import com.work_order.openFeign.EmployeRestController;
import com.work_order.dto.WorkOrderRequestDTO;
import com.work_order.dto.WorkOrderResponseDTO;
import com.work_order.exceptions.EmployeNotFoundException;
import com.work_order.mapper.WorkOrderMapper;
import com.work_order.repository.AffaireRepository;
import com.work_order.repository.ArticleMissionRepository;
import com.work_order.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkOrderServiceImpl implements WorkOrderService {

    WorkOrderRepository workOrderRepository;
    WorkOrderMapper workOrderMapper;
    AffaireRepository affaireRepository;
    EmployeRestController employeRestController;
    ArticleMissionRepository articleMissionRepository;

    public WorkOrderServiceImpl(WorkOrderRepository workOrderRepository, WorkOrderMapper workOrderMapper, AffaireRepository affaireRepository, EmployeRestController employeRestController, ArticleMissionRepository articleMissionRepository) {
        this.workOrderRepository = workOrderRepository;
        this.workOrderMapper = workOrderMapper;
        this.affaireRepository = affaireRepository;
        this.employeRestController = employeRestController;
        this.articleMissionRepository = articleMissionRepository;
    }

    @Override
    public WorkOrderResponseDTO save(WorkOrderRequestDTO workorderRequestDTO,int affaireid) {
            if (workorderRequestDTO.getEmployeId()!=null){
       try {
           Employe employe= employeRestController.getEmploye(workorderRequestDTO.getEmployeId());

       } catch (Exception e){
           throw  new EmployeNotFoundException("Employe Not Found") ;
       }
        try {
            Employe employe= employeRestController.getEmploye(workorderRequestDTO.getEmployeId());
            if (employe.getStatut().equals(Statut.Desactiver)){
                throw new RuntimeException();
            }
        } catch (Exception s){
            throw new StatutNotActiveException("This Employe is Desactivated");
        }}

        Work_Order order =workOrderMapper.WorkOrderRequestDTOWorkOrder(workorderRequestDTO);
        order.setId(UUID.randomUUID().toString());

        Work_Order saveWorkOrder=workOrderRepository.save(order);
        affecterAffaireToWorkOrder(affaireid, order.getId());

        WorkOrderResponseDTO orderResponseDTO=workOrderMapper.WorkOrderTOWorkOrderResponseDTO(saveWorkOrder);

        return orderResponseDTO;
    }
    @Override
    public void updateWorkOrderDTO(WorkOrderUpdateDTO dto) {
        Work_Order myOrder =workOrderRepository.findById(dto.getId()).get();
        if (myOrder.getEmployeId()!=null){
        try {
            Employe employe= employeRestController.getEmploye(dto.getEmployeId());

        } catch (Exception e){
            throw  new EmployeNotFoundException("Employe Not Found") ;
        }}
        workOrderMapper.updateWorkOrderFromDto(dto, myOrder);
        workOrderRepository.save(myOrder);
    }
    @Override
    public WorkOrderResponseDTO getWorkorder(String Id) {

        Work_Order order =workOrderRepository.findById(Id).get();
        if(order.getEmployeId()!=null) {
            try {
                Employe employe = employeRestController.getEmploye(order.getEmployeId());
                order.setEmploye(employe);
            } catch (Exception e) {
                throw new EmployeNotFoundException("Employe Not Found");
            }
        }

        return workOrderMapper.WorkOrderTOWorkOrderResponseDTO(order);
    }


    @Override
    public List<WorkOrderResponseDTO> listWorkorders() {

        List<Work_Order>orders=workOrderRepository.findAll();
        for (Work_Order order: orders){
            if(order.getEmployeId()!=null){
                Employe employe= employeRestController.getEmploye(order.getEmployeId());

                order.setEmploye(employe);
            }

            }
        List<WorkOrderResponseDTO> workorderResponseDTOS=orders.stream()
                .map(cust->workOrderMapper.WorkOrderTOWorkOrderResponseDTO(cust))
                .collect(Collectors.toList());

        return workorderResponseDTOS;
    }

    @Override
    public void delete(String Id) {
     Work_Order work_order= workOrderRepository.findById(Id).get();
        List<Article_Mission> article_missions=articleMissionRepository.findAll();

        for (Article_Mission article_mission: article_missions)
        {
           if (article_mission.getWork_order().getId().equals(work_order.getId()))
           {
               article_mission.setWork_order(null);
           }
        }
        workOrderRepository.delete(work_order);
    }



    @Override
    public List<WorkOrderResponseDTO> listWorkordersByEmployeId(String employeId) {
        List<Work_Order> work_orders=workOrderRepository.findByEmployeId(employeId);
        for (Work_Order order: work_orders){
            Employe employe= employeRestController.getEmploye(order.getEmployeId());
            order.setEmploye(employe);
        }

        return work_orders.stream()
                .map(work_order -> workOrderMapper.WorkOrderTOWorkOrderResponseDTO(work_order))
                .collect(Collectors.toList());
    }

    @Override
    public void affecterAffaireToWorkOrder(int affaireid, String order) {

        Affaire affaire=affaireRepository.findById(affaireid).get();
        if (affaire.getStatut().equals(Statut.Activer)){
            Work_Order work_order=workOrderRepository.findById(order).get();
            work_order.setAffaire(affaire);
            workOrderRepository.save(work_order);
        }
        else throw new StatutNotActiveException("This Affaire is Desactivated");

    }
    @Override
    public void removeEmployeFromWorkOrder(String order){
        Work_Order work_order=workOrderRepository.findById(order).get();
        work_order.setEmployeId(null);
        workOrderRepository.save(work_order);

    }
    @Override
    public void removeAffaireFromWorkOrder(String order){
        Work_Order work_order=workOrderRepository.findById(order).get();
        work_order.setAffaire(null);
        workOrderRepository.save(work_order);
    }
    @Override
    public List<WorkOrderResponseDTO> findByActiveWorkOrder() {
        List<Work_Order> work_orders=workOrderRepository.findByActiveWorkOrder();
        for (Work_Order order: work_orders){

            Employe employe= employeRestController.getEmploye(order.getEmployeId());

            order.setEmploye(employe);
        }
        List<WorkOrderResponseDTO> workorderResponseDTOS=work_orders.stream()
                .map(cust->workOrderMapper.WorkOrderTOWorkOrderResponseDTO(cust))
                .collect(Collectors.toList());

        return workorderResponseDTOS;
    }
}
