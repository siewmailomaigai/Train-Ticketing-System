/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;

import java.util.ArrayList;
import Personnel.Driver;
import Personnel.Customer;


enum TrainStatus {
    Early,
    OnTime,
    Delay,
}

public class Train {

    private int trainNo;
    private Driver driver;
    private ArrayList<Customer> customerList;

    private static int nextTrainID = 1000;

    public Bus(int trainNo, Driver driver) {
        this.trainNo = trainNo;
        this.driver = driver;
    }

    public Train(Driver driver) {
        this.trainNo = Train.nextTrainID;
        this.driver = driver;
        this.customerList = new ArrayList<>();
    }

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public static int getNextTrainID() {
        return nextTrainID;
    }

    public static void setNextTrainID(int nextTrainID) {
        Train.nextTrainID = nextTrainID;
    }

    
    public String toString() {
        return String.format("%-5d %-15s %-5d ", trainNo, driver);

    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerList.remove(customer);
    }
}
