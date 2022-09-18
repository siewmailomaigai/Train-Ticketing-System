/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnal;

/**
 *
 * @author KUNG WEI XIN
 */
public class Driver extends Person {

    String driverID;
    private static int nextID = 100;

    public Driver(String name) {
        super(name);
        this.driverID = "BD" + nextID;
        ++nextID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Driver.nextID = nextID;
    }

    
    
    public String toString() {
        return "Driver Id: " + driverID + "\n";
    }
}
