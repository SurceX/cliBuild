package com.cesur.booking;

import com.cesur.car.CarService;
import com.cesur.user.UserService;

public class CarBookingService {

    private final CarBookingDao carBookingDao =
            new CarBookingDao();

    private final CarService carService = new CarService();
    private final UserService userService = new UserService();


}
