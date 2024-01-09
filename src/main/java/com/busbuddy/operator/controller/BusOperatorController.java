package com.busbuddy.operator.controller;

import com.busbuddy.operator.entity.BusOperator;
import com.busbuddy.operator.payload.BusOperatorDto;
import com.busbuddy.operator.service.BusOperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bus-operator")
public class BusOperatorController {
    private BusOperatorService busOperatorService;

    public BusOperatorController(BusOperatorService busOperatorService){
        this.busOperatorService=busOperatorService;
    }

    @PostMapping
    public ResponseEntity<BusOperatorDto> scheduleBus(@RequestBody BusOperatorDto busOperatorDto){
        BusOperatorDto dto=busOperatorService.scheduleBus(busOperatorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
