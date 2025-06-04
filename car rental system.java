/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carrentalsystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Login Manager Class
class LoginManager {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass123";
    private static final int MAX_ATTEMPTS = 3;

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password (shown as *): ");
            StringBuilder inputPassword = new StringBuilder();
            String input = scanner.nextLine();

            // Display * for each character
            for (char c : input.toCharArray()) {
                inputPassword.append(c);
                System.out.print("*");
            }
            System.out.println();

            if (username.equals(USERNAME) && inputPassword.toString().equals(PASSWORD)) {
                System.out.println("Login successful!");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid credentials. " + (MAX_ATTEMPTS - attempts) + " attempts left.");
            }
        }
        System.out.println("Max attempts reached. Access denied.");
        return false;
    }
}

// Car Class
class Car {
    private String carId;
    private String model;
    private boolean isAvailable;

    public Car(String carId, String model) {
        this.carId = carId;
        this.model = model;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Car [ID=" + carId + ", Model=" + model + "]";
    }
}

// Customer Class
class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + name + "]";
    }
}

// Rental Class
class Rental {
    private Car car;
    private Customer customer;

    public Rental(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }
}

// RentalAgency Class
class RentalAgency {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(String carId, String customerId) {
        Car car = findCar(carId);
        Customer customer = findCustomer(customerId);

        if (car == null || customer == null) {
            System.out.println("Car or customer not found.");
            return;
        }
        if (!car.isAvailable()) {
            System.out.println("Car is not available.");
            return;
        }
        car.setAvailable(false);
        rentals.add(new Rental(car, customer));
        System.out.println("Car rented successfully!");
    }

    public void returnCar(String carId) {
        Car car = findCar(carId);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        Rental rental = findRental(carId);
        if (rental == null) {
            System.out.println("Car is not rented.");
            return;
        }
        car.setAvailable(true);
        rentals.remove(rental);
        System.out.println("Car returned successfully!");
    }

    private Car findCar(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }

    private Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private Rental findRental(String carId) {
        for (Rental rental : rentals) {
            if (rental.getCar().getCarId().equals(carId)) {
                return rental;
            }
        }
        return null;
    }
}

// Main Class
public class CarRentalSystem {
    public static void main(String[] args) {
        LoginManager loginManager = new LoginManager();
        if (!loginManager.login()) {
            System.out.println("Exiting system.");
            return;
        }

        RentalAgency agency = new RentalAgency();
        agency.addCar(new Car("C001", "Toyota"));
        agency.addCustomer(new Customer("CU001", "John"));

        agency.rentCar("C001", "CU001");
        agency.returnCar("C001");
    }
}