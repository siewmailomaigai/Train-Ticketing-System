/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;

import java.util.ArrayList;
import Personnal.Driver;
import Personnal.Customer;

/**
 *
 * @author JEhew
 */
enum BusStatus {
    Early,
    OnTime,
    Delay,
}

public class Bus {

    private int busNo;
    private Driver driver;
    private ArrayList<Customer> customerList;

    private static int nextBusID = 1000;

    public Bus(int busNo, Driver driver) {
        this.busNo = busNo;
        this.driver = driver;
    }

    public Bus(Driver driver) {
        this.busNo = Bus.nextBusID;
        this.driver = driver;
        this.customerList = new ArrayList<>();
    }

    public int getBusNo() {
        return busNo;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public static int getNextBusID() {
        return nextBusID;
    }

    public static void setNextBusID(int nextBusID) {
        Bus.nextBusID = nextBusID;
    }

    
    public String toString() {
        return String.format("%-5d %-15s %-5d ", busNo, driver);

    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerList.remove(customer);
    }
}
