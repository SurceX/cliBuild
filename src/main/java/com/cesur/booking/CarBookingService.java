package com.cesur.booking;

import com.cesur.car.Car;

import com.cesur.car.CarService;
import com.cesur.user.User;
import com.cesur.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingDao carBookingDao =
            new CarBookingDao();

    private final CarService carService = new CarService();
    private final UserService userService = new UserService();


    public CarBooking[] getBookings() {
        return carBookingDao.getBookings();
    }

    public CarBooking findBookingById(UUID bookingId){
        return carBookingDao.findBookingById(bookingId);
    }

    public void deleteBooking(UUID bookingId){
        carBookingDao.deleteBooking(bookingId);
    }

    public CarBooking[] getBookingsByUserId(UUID userId){
        CarBooking[] bookings = carBookingDao.getBookings();
        int count = 0;

        for (CarBooking booking : bookings) {
            if (booking.getUser().getId().equals(userId)) {
                count++;
            }
        }

        CarBooking[]  userBookings = new CarBooking[count];

        int index = 0;

        for (CarBooking booking : bookings) {
            if (booking.getUser().getId().equals(userId)) {

                userBookings[index] = booking;

                index++;
            }
        }
        return userBookings;
    }

    public Car[] getAvailableCars() {
        Car[] cars = carService.getCars();
        CarBooking[] bookings = carBookingDao.getBookings();
        int count = 0;

        for (Car car : cars) {
            boolean isBooked = false;

            for (CarBooking booking : bookings) {
                if (booking.getCar().getId().equals(car.getId())) {
                    isBooked = true;
                    break;
                }
            }

            if (!isBooked) {
                count++;
            }
        }

        Car[] availableCars = new Car[count];
        int index = 0;

        for (Car car : cars) {
            boolean isBooked = false;

            for (CarBooking booking : bookings) {

                if (booking.getCar().getId().equals(car.getId())) {
                    isBooked = true;
                    break;
                }
            }

            if (!isBooked) {
                availableCars[index] = car;
                index++;
            }
        }

        return availableCars;
    }


    public Car[] getAvailableElectricCars() {

        Car[] availableCars = getAvailableCars();

        int count = 0;

        for (Car car : availableCars) {
            if (car.isElectric()) {
                count++;

            }
        }

        Car[] electricCars = new Car[count];

        int index = 0;

        for (Car car : availableCars) {
            if (car.isElectric()) {
                electricCars[index] = car;
                index++;
            }
        }

        return electricCars;
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        User user = userService.findUserById(userId); // I already create throw function on UserDao class,
        Car car = carService.findCarById(carId); // I already create throw function on CarDao class

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        CarBooking[] bookings = carBookingDao.getBookings();
        for (CarBooking booking : bookings) {
            if (booking.getCar().getId().equals(carId)) {
                throw new IllegalArgumentException("Car is already booked");
            }
        }

        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        BigDecimal price = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(numberOfDays));

        CarBooking booking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                startDate,
                endDate,
                price,
                BookingStatus.ACTIVE,

                LocalDateTime.now()
        );

        carBookingDao.saveBooking(booking);
        return booking;
    }
}
