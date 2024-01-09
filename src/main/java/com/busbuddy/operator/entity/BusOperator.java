package com.busbuddy.operator.entity;

import com.busbuddy.operator.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "bus_operators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusOperator {

    @Id
    @Column(name = "bus_id")
    private String busId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id") // Assuming "bus_id" is the foreign key column in the ticket_cost table
    private TicketCost ticketCost;

    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "bus_operator_company_name")
    private String busOperator;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "support_staff")
    private String supportStaff;
    @Column(name = "number_of_seats")
    private String numberSeats;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;


    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

//    @JsonFormat(pattern = "dd/MM/yyyy")
  //  @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

//    @JsonFormat(pattern = "dd/MM/yyyy")
   // @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Column(name = "total_travel_time")
    private double totalTravelTime;
    @Column(name = "bus_type")
    private String busType;
    @Column(name = "amenities")
    private String amenities;


}
