package com.busbuddy.user.controller;

import com.busbuddy.user.payload.BookingDetailsDto;
import com.busbuddy.user.payload.PassengerDetails;
import com.busbuddy.user.service.BookingService;
import com.busbuddy.util.EmailService;
import com.busbuddy.util.PdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    private EmailService emailService;
    private PdfService pdfService;

    public BookingController(BookingService bookingService, EmailService emailService, PdfService pdfService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
        this.pdfService = pdfService;
    }

    // http://localhost:8080/api/bookings?busId=&ticketId=
    @PostMapping
    public ResponseEntity<BookingDetailsDto> bookBus(
            @RequestParam("busId") String busId,
            @RequestParam("ticketId") String ticketId,
            @RequestBody PassengerDetails passengerDetails
    ) {
        BookingDetailsDto booking = bookingService.createBooking(busId, ticketId, passengerDetails);

        if (booking != null) {

            byte[] pdfBytes= pdfService.generatePdf(booking);

            // Send confirmation email with PDF attachment
            sendConfirmationEmailWithAttachment(passengerDetails,booking, pdfBytes);
        }

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    private void sendConfirmationEmailWithAttachment(
            PassengerDetails passengerDetails, BookingDetailsDto booking, byte[] pdfBytes) {

        String emailSubject="Booking is Confirmed. Booking Id: "+ booking.getBookingId();
        String emailBody=String.format("Your Booking is Confirmed  \n Name: %s %S",
                passengerDetails.getFirstName(), passengerDetails.getLastName());

        //Attach  PDF to the Email
        emailService.sendEmail(
                passengerDetails.getEmail(),emailSubject,emailBody,pdfBytes,"BookingConfirmation.pdf"
        );
    }
}
