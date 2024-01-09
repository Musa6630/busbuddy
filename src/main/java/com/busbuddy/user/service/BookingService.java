package com.busbuddy.user.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.busbuddy.operator.entity.BusOperator;
import com.busbuddy.operator.entity.TicketCost;
import com.busbuddy.operator.repository.BusOperatorRepository;
import com.busbuddy.operator.repository.TicketCostRepository;
import com.busbuddy.user.entity.Booking;
import com.busbuddy.user.payload.BookingDetailsDto;
import com.busbuddy.user.payload.PassengerDetails;
import com.busbuddy.user.repository.BookingRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class BookingService {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    private BusOperatorRepository busOperatorRepository;
    private TicketCostRepository ticketCostRepository;
    private BookingRepository bookingRepository;

    public BookingService(BusOperatorRepository busOperatorRepository,
                          TicketCostRepository ticketCostRepository,
                          BookingRepository bookingRepository
    ) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingDetailsDto createBooking(String busId,
                                           String ticketId,
                                           PassengerDetails passengerDetails
    ){

        BusOperator bus = busOperatorRepository.findById(busId).get();
        TicketCost ticketCost = ticketCostRepository.findById(ticketId).get();

        String paymentIntent = createPaymentIntent((int) ticketCost.getCost());
        if (paymentIntent!=null) {

            Booking booking = new Booking();
            String bookingId = UUID.randomUUID().toString();
            booking.setBookingId(bookingId);
            booking.setBusId(busId);
            booking.setTicketId(ticketId);
            booking.setTo_location(bus.getArrivalCity());
            booking.setFrom_location(bus.getDepartureCity());
            booking.setBusCompany(bus.getBusOperator());
            booking.setPrice(ticketCost.getCost());
            booking.setFirstName(passengerDetails.getFirstName());
            booking.setLastName(passengerDetails.getLastName());
            booking.setEmail(passengerDetails.getEmail());
            booking.setMobile(passengerDetails.getMobile());
            // Assuming BusOperator and TicketCost entities have departureDate and arrivalDate fields
            booking.setDepartureDate(bus.getDepartureDate());
            booking.setArrivalDate(bus.getArrivalDate());


            Booking ticketCreatedDetails = bookingRepository.save(booking);


            BookingDetailsDto dto = new BookingDetailsDto();
            dto.setBookingId(ticketCreatedDetails.getBookingId());
            dto.setFirstName(ticketCreatedDetails.getFirstName());
            dto.setLastName(ticketCreatedDetails.getLastName());
            dto.setTo(ticketCreatedDetails.getTo_location());
            dto.setFrom(ticketCreatedDetails.getFrom_location());
            dto.setPrice(ticketCreatedDetails.getPrice());
            dto.setEmail(ticketCreatedDetails.getEmail());
            dto.setMobile(ticketCreatedDetails.getMobile());
            dto.setBusCompany(ticketCreatedDetails.getBusCompany());
            // Include departureDate and arrivalDate in the BookingDetailsDto
            dto.setDepartureDate(ticketCreatedDetails.getDepartureDate());
            dto.setArrivalDate(ticketCreatedDetails.getArrivalDate());
            dto.setMessage("Booking Confirmed");

            return dto;

        }else {
            System.out.println("Error!!");
        }

        return null;
    }




    public String createPaymentIntent(Integer amount) {
        Stripe.apiKey = stripeSecretKey;

        try {
            PaymentIntent intent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setCurrency("usd")
                            .setAmount((long) amount*100) // Amount in cents, change accordingly
                            .build()
            );

            return generateResponse(intent.getClientSecret());
        } catch (StripeException e) {

            return generateResponse("Error creating PaymentIntent"+e.getMessage());
        }
    }

    private String generateResponse(String clientSecret) {

        return "{\"clientSecret\":\""+clientSecret+"\"}";
    }
}
