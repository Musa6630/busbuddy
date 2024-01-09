package com.busbuddy.operator.service;

import com.busbuddy.operator.entity.BusOperator;
import com.busbuddy.operator.entity.TicketCost;
import com.busbuddy.operator.repository.TicketCostRepository;
import org.modelmapper.ModelMapper;
import com.busbuddy.operator.payload.BusOperatorDto;
import com.busbuddy.operator.repository.BusOperatorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BusOperatorServiceImpl implements BusOperatorService {

    private BusOperatorRepository busOperatorRepository;

    private TicketCostRepository ticketCostRepository;
    private ModelMapper mapper;

    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository,TicketCostRepository ticketCostRepository, ModelMapper mapper) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository=ticketCostRepository;
        this.mapper = mapper;
    }

    @Override
    public BusOperatorDto scheduleBus(BusOperatorDto busOperatorDto) {
        // Map BusOperatorDto to BusOperator entity
        BusOperator busOperator = mapToEntity(busOperatorDto);

        // Generate a unique busId using UUID
        String busId = UUID.randomUUID().toString();
        busOperator.setBusId(busId);

        // Map TicketCostDto to TicketCost entity
        TicketCost ticketCost = new TicketCost();
        ticketCost.setTicketId(busOperatorDto.getTicketCost().getTicketId());
        ticketCost.setCost(busOperatorDto.getTicketCost().getCost());
        ticketCost.setCode(busOperatorDto.getTicketCost().getCode());
        ticketCost.setDiscountAmount(busOperatorDto.getTicketCost().getDiscountAmount());

        // Set the relationship between BusOperator and TicketCost
        busOperator.setTicketCost(ticketCost);

        // Save BusOperator entity with TicketCost
        BusOperator savedBusSchedule = busOperatorRepository.save(busOperator);

        // Map the saved entity back to DTO
        return mapToDto(savedBusSchedule);
    }

    BusOperator mapToEntity(BusOperatorDto busOperatorDto) {
        return mapper.map(busOperatorDto, BusOperator.class);
    }

    BusOperatorDto mapToDto(BusOperator busOperator) {
        return mapper.map(busOperator, BusOperatorDto.class);
    }
}
