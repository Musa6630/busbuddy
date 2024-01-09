package com.busbuddy.user.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsDto {
    private String bookingId;
    private String busCompany;
    private String to;
    private String from;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private double price;


    private Date departureDate; // Include departure date
    private Date arrivalDate;   // Include arrival date

    private String message;
}
