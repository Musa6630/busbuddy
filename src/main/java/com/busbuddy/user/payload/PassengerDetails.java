package com.busbuddy.user.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private Date departureDate;
    private Date arrivalDate;
}
