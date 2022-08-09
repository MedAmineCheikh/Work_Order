package com.work_order.mapper;

import com.work_order.dto.WorkOrderRequestDTO;
import com.work_order.dto.WorkOrderResponseDTO;
import com.work_order.dto.WorkOrderUpdateDTO;
import com.work_order.entity.Work_Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface WorkOrderMapper {
    WorkOrderResponseDTO WorkOrderTOWorkOrderResponseDTO(Work_Order work_order);
    Work_Order WorkOrderRequestDTOWorkOrder(WorkOrderRequestDTO workOrderRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateWorkOrderFromDto(WorkOrderUpdateDTO dto, @MappingTarget Work_Order entity);
}
