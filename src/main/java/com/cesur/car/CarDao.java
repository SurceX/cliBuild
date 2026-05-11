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
                new Car(UUID.fromString("55555555-5555-5555-5555-555555555555"), "BYD-5", new BigDecimal("50"), Brand.BYD, true),
                new Car(UUID.fromString("66666666-6666-6666-6666-666666666666"), "BNTLY-6", new BigDecimal("1000"), Brand.BENTLEY, false),
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

        throw new IllegalArgumentException("Car with id: " + carId + " not found");
    }



}
