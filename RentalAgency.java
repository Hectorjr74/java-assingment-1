/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalagencytest;
   import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
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
}
