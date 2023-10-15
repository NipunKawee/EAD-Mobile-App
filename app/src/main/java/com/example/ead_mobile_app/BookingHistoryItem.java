package com.example.ead_mobile_app;

public class BookingHistoryItem {
    private int bookingId;
    private String bookingDate;
    private String startStation;
    private String destinationStation;

    public BookingHistoryItem(int bookingId, String bookingDate, String startStation, String destinationStation) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }
}

