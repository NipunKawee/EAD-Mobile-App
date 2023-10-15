package com.example.ead_mobile_app;

public class Booking {
    private int bookingId;
    private String userNic;
    private String bookingDate;
    private String startStation;
    private String destinationStation;

    public Booking(int bookingId, String userNic, String bookingDate, String startStation, String destinationStation) {
        this.bookingId = bookingId;
        this.userNic = userNic;
        this.bookingDate = bookingDate;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
    }

    // Getter methods for booking data
}

