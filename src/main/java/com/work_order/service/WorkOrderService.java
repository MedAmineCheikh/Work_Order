package com.work_order.service;

import com.work_order.dto.WorkOrderRequestDTO;
import com.work_order.dto.WorkOrderResponseDTO;
import com.work_order.dto.WorkOrderUpdateDTO;

import java.util.List;

public interface WorkOrderService {
    WorkOrderResponseDTO save(WorkOrderRequestDTO workorderRequestDTO);

    WorkOrderResponseDTO getWorkorder(String Id);

    List<WorkOrderResponseDTO> listWorkorders();
    void delete(String Id);
    void updateWorkOrderDTO(WorkOrderUpdateDTO dto);

    List<WorkOrderResponseDTO> listWorkordersByEmployeId(String employeId);

    void affecterAffaireToWorkOrder(int affaireid,String order);

     List<WorkOrderResponseDTO> findByActiveWorkOrder();

}
