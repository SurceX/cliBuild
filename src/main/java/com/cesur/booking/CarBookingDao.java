package com.cesur.booking;

import java.util.UUID;

public class CarBookingDao {

    private CarBooking[] bookings = new CarBooking[0];

    public CarBooking[] getBookings(){ //Default
        return bookings;
    }


    // I look the booking with using UUID format according to bookingID
    public CarBooking findBookingById(UUID bookingId){
        for (CarBooking carBooking: bookings){
            if (carBooking.getId().equals(bookingId)){
                return carBooking;
            }
        }
        return null;
    }

    public void saveBooking(CarBooking booking) {
        CarBooking[] newBookings = new CarBooking[bookings.length + 1];
        // I save booking and ı need to add extra area on array

        for (int i = 0; i < bookings.length; i++) {
            newBookings[i] = bookings[i];
        }

        newBookings[newBookings.length - 1] = booking;
        bookings = newBookings;
    }

    public void deleteBooking(UUID bookingId) {
        int count = 0 ;

        //  count remaining booking
        for (CarBooking booking : bookings) {
            if (  !booking.getId().equals(bookingId)) {
                count++;
            }
        }

        // I create new array for remaining booking
        CarBooking[] newBookings = new CarBooking[count];
        int index = 0;

        // I copy booking without deleted booking
        for (CarBooking booking : bookings) {
            if ( !booking.getId().equals(bookingId)) {
                newBookings[index] = booking;
                index++;
            }
        }
        // I update booking array
        bookings = newBookings;
    }


}
