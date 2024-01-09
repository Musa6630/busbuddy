package com.busbuddy.operator.entity;

import com.busbuddy.operator.payload.BusOperatorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "ticket_cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_id", unique = true)
    private String ticketId;

    @OneToOne(mappedBy = "ticketCost")
    @JoinColumn(name = "bus_id") // Assuming "bus_id" is the foreign key column in the ticket_cost table
    private BusOperator busOperator;

    private double cost;
    private String code;

    @Column(name = "discount_amount", unique = true)
    private double discountAmount;

    // Getters and setters
}