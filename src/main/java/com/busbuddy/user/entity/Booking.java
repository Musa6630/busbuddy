package com.busbuddy.user.entity;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "bus_id")
    private String busId;
    @Column(name = "ticket_id")
    private String ticketId;
    @Column(name = "bus_company")
    private String busCompany;

    private String to_location;
    private String from_location;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String mobile;
    private double price;

    @Temporal(TemporalType.DATE)
    private Date departureDate; // Include departure date
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;   // Include arrival date
}
