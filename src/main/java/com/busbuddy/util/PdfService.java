package com.busbuddy.util;

import com.busbuddy.user.payload.BookingDetailsDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdf(BookingDetailsDto bookingDetails) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Booking ID: " + bookingDetails.getBookingId()));
            document.add(new Paragraph("Bus Company: " + bookingDetails.getBusCompany()));
            document.add(new Paragraph("From: " + bookingDetails.getFrom()));

            document.add(new Paragraph("To: " + bookingDetails.getTo()));
            document.add(new Paragraph("First Name: " + bookingDetails.getFirstName()));
            document.add(new Paragraph("Last Name: " + bookingDetails.getLastName()));
            document.add(new Paragraph("Email: " + bookingDetails.getEmail()));
            document.add(new Paragraph("Mobile: " + bookingDetails.getMobile()));
            document.add(new Paragraph("Price: " + bookingDetails.getPrice()));
            document.add(new Paragraph("Departure Date: "+ bookingDetails.getDepartureDate()));
            document.add(new Paragraph("Arrival Date: " + bookingDetails.getArrivalDate()));

            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return new byte[0];
        }
    }
}




