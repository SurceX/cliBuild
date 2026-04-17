package com.cesur.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {

    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(UUID.fromString("11111111-1111-1111-1111-111111111111"), "TSL-1", new BigDecimal("20"), Brand.TESLA, true),
                new Car(UUID.fromString("22222222-2222-2222-2222-222222222222"), "AUD-2", new BigDecimal("40"), Brand.AUDI, false),
                new Car(UUID.fromString("33333333-3333-3333-3333-333333333333"), "MER-3", new BigDecimal("100"), Brand.MERCEDES, false),
                new Car(UUID.fromString("44444444-4444-4444-4444-444444444444"), "TOY-4", new BigDecimal("15"), Brand.TOYOTA, false),
        };
    }
    public Car[] getCars() {
        return cars;
    }

    public Car findCarById(UUID carId) {
        for (Car car : cars) {
            if (car.getId().equals(carId)) {
                return car;
            }
        }
        return null;
    }



}
