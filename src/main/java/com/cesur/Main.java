package com.cesur;
import com.cesur.booking.CarBooking;
import com.cesur.booking.CarBookingService;
import com.cesur.car.Car;
import com.cesur.user.User;
import com.cesur.user.UserService;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

// TODO 1. create a new branch called initial-implementation
// TODO 2. create a package with your name. i.e com.franco and move this file inside the new package
// TODO 3. implement https://amigoscode.com/learn/java-cli-build/lectures/3a83ecf3-e837-4ae5-85a8-f8ae3f60f7f5



public class Main {

    public static void main(String[] args) {
        System.out.println("Java Master Class");

        CarBookingService carBookingService = new CarBookingService();
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;

        while (loop){
            System.out.println("1- Book Car");
            System.out.println("2- Delete Booking");
            System.out.println("3- View All User Booked Cars");
            System.out.println("4- View All Bookings");
            System.out.println("5- View Avaliable Cars");
            System.out.println("6- View Avaliable Electric Cars");
            System.out.println("7- View All Users");
            System.out.println("8- Exit");
            System.out.println("Select option: ");

            String input = scanner.nextLine();

            try {
                int option = Integer.parseInt(input);

                switch (option){
                    case 1:
                        System.out.print("Enter user id: ");
                        UUID userId = UUID.fromString(scanner.nextLine());

                        System.out.print("Enter car id: ");
                        UUID carId = UUID.fromString(scanner.nextLine());

                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        LocalDate startDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        LocalDate endDate = LocalDate.parse(scanner.nextLine());

                        CarBooking newBooking = carBookingService.bookCar(userId, carId, startDate, endDate);
                        System.out.println("Booking created successfully:");
                        System.out.println(newBooking);

                        break;

                    case 2:
                        System.out.print("Enter booking id: ");
                        UUID bookingId = UUID.fromString(scanner.nextLine());

                        carBookingService.deleteBooking(bookingId);
                        System.out.println("Booking deleted successfully.");

                        break;


                    case 3:
                        System.out.print("Enter user id: ");
                        UUID bookingUserId = UUID.fromString(scanner.nextLine());

                        CarBooking[] userBookings = carBookingService.getBookingsByUserId(bookingUserId);
                        for (CarBooking booking : userBookings) {
                            System.out.println(booking);
                        }
                        break;

                    case 4:
                        CarBooking[] bookings = carBookingService.getBookings();
                        for (CarBooking booking : bookings){
                            System.out.println(booking);
                        }
                        break;

                    case 5:
                        Car[] avaliableCars = carBookingService.getAvailableCars();
                        for (Car normalCar : avaliableCars){
                            System.out.println(normalCar);
                        }
                        break;

                    case 6:
                        Car[] electricCars = carBookingService.getAvailableElectricCars();
                        for (Car electricCar : electricCars ) {
                            System.out.println(electricCar);
                        }
                        break;

                    case 7:
                        User[] users = userService.getUsers();
                        for (User user: users){
                            System.out.println(user);
                        }
                        break;

                    case 8:
                        System.out.println("Exiting");
                        loop = false;
                        break;

                    default:
                        System.out.println("Invalid option please try again");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("*****************************");

        }




    }


}


