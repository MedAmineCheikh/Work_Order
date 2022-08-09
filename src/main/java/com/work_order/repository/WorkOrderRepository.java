package com.work_order.repository;

import com.work_order.entity.Work_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<Work_Order,String> {
    List<Work_Order> findByEmployeId(String employeId);
    @Query("SELECT w FROM Work_Order w where w.statut= com.work_order.entity.Statut.Activer")
    List<Work_Order> findByActiveWorkOrder();
}
