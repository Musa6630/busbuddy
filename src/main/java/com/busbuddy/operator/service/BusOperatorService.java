package com.busbuddy.operator.service;

import com.busbuddy.operator.payload.BusOperatorDto;
import org.springframework.web.bind.annotation.RequestBody;


public interface BusOperatorService {
    BusOperatorDto scheduleBus(@RequestBody BusOperatorDto busOperatorDto);
}
