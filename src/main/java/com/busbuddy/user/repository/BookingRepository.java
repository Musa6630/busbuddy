package com.busbuddy.user.repository;

import com.busbuddy.user.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking,String> {

}
