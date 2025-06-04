/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rentalagencytest;

/**
 *
 * @author user
 */
public class RentalAgencyTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        RentalAgency agency = new RentalAgency();
        Car car = new Car("C001", "Toyota");
        Customer customer = new Customer("CU001", "John");

        // Test 1: Add car and customer
        agency.addCar(car);
        agency.addCustomer(customer);
        System.out.println("Test 1: Added car and customer.");

        // Test 2: Rent a car
        agency.rentCar("C001", "CU001");

        // Test 3: Return a car
        agency.returnCar("C001");
    }
}
    }
    
}
