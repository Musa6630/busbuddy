package com.busbuddy.operator.repository;

import com.busbuddy.operator.entity.TicketCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCostRepository extends JpaRepository<TicketCost, String> {

}
