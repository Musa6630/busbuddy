package com.busbuddy.user.controller;

import com.busbuddy.operator.entity.BusOperator;
import com.busbuddy.user.payload.BusListDto;
import com.busbuddy.user.service.SearchBusesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FindBusesController {
    private SearchBusesService searchBusesService;

    public FindBusesController(SearchBusesService searchBusesService) {
        this.searchBusesService = searchBusesService;
    }
    //    http://localhost:8080/api/user/searchBuses?departureCity=CityA&arrivalCity=CityB&departureDate=2023-01-15

    @GetMapping("/searchBuses")
    public List<BusListDto> searchBuses(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate){

        List<BusListDto> busListDtos = searchBusesService.searchBusBy(departureCity, arrivalCity, departureDate);
        return busListDtos;
    }
}
