package com.work_order.controller;

import com.work_order.dto.WorkOrderRequestDTO;
import com.work_order.dto.WorkOrderResponseDTO;
import com.work_order.dto.WorkOrderUpdateDTO;
import com.work_order.entity.Work_Order;
import com.work_order.service.WorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "WorkOrder management")
public class WorkorderController {

    WorkOrderService workOrderService;


    public WorkorderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }
    @ApiOperation(value = "Récupérer la liste des WorkOrder")
    @GetMapping(path="/workorders")
    public List<WorkOrderResponseDTO> allWorkOrders()
    {

        return workOrderService.listWorkorders();
    }
    @ApiOperation(value = "ajoute WorkOrder")
    @PostMapping(path="/saveworkorder")
    public WorkOrderResponseDTO save(WorkOrderRequestDTO workorderRequestDTO){
        return workOrderService.save(workorderRequestDTO);
    }
    @ApiOperation(value = "Récupérer WorkOrder")
    @GetMapping(path = "/workorder/{id}")
    public WorkOrderResponseDTO getWorkOrder(@PathVariable String id){

        return workOrderService.getWorkorder(id);
    }
/*    @ApiOperation(value = "Update WorkOrder")
    @PutMapping("/update-workorder/{Id}")
    public Work_Order UpdateWorkOrder(@RequestBody Work_Order work_order, @PathVariable String Id) {
        return workOrderService.updateWorkorder(work_order,Id);
    }*/

    @ApiOperation(value = "Delete WorkOrder")
    @DeleteMapping("/remove-workorder/{workorder-id}")
    @ResponseBody
    public void delete(@PathVariable("workorder-id") String Id) {
        workOrderService.delete(Id);
    }


    @ApiOperation(value = "Update WorkOrder")
    @PutMapping("/update-workorderdto/")
    @ResponseBody
    public void UpdateWorkOrderDTO(@RequestBody WorkOrderUpdateDTO workorder) {
        workOrderService.updateWorkOrderDTO(workorder);
    }

    @ApiOperation(value = "Récupérer WorkOrder par Employe Id")
    @GetMapping(path = "/workorderbyemploye/{employeId}")
    public List<WorkOrderResponseDTO> getWorkOrderByEmploye(@PathVariable String employeId){

        return workOrderService.listWorkordersByEmployeId(employeId);
    }
    @ApiOperation(value = "Récupérer la liste des WorkOrder by Employe Id")
    @GetMapping(path="/workordersbyEmployeId/{employeId}")
    public List<WorkOrderResponseDTO> allWorkOrdersByEmployeId(@PathVariable String employeId)
    {

        return workOrderService.listWorkordersByEmployeId(employeId);
    }
    @PutMapping(value = "/affecterAffaireToWorkOrder/{affaireid}/{order}")
    public void affecterAffaireToWorkOrder(@PathVariable int affaireid,@PathVariable String order){

        workOrderService.affecterAffaireToWorkOrder(affaireid,order);
    }
    @ApiOperation(value = "Récupérer la liste des WorkOrder Active")
    @GetMapping(path="/workordersActive")
    public List<WorkOrderResponseDTO> findByActiveWorkOrder()
    {
        return workOrderService.findByActiveWorkOrder();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandlerStatut(Exception s)
    {
        return new ResponseEntity<>(s.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
