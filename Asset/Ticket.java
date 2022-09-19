/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;


/**
 *
 * @author 60192
 */

public class Ticket {

    private final static double DEFAULT_PRICE = 10;
    private double amount;
    private int quantity;
    private boolean paidStatus;

    public Ticket(double amount, int quantity, boolean paidStatus) {
        this.amount = amount;
        this.quantity = quantity;
        this.paidStatus = paidStatus;
    }

    public Ticket(int quantity, boolean paidStatus) {
        this.amount = DEFAULT_PRICE;
        this.quantity = quantity;
        this.paidStatus = paidStatus;
    }

    public void verifyPaidStatus() {
        if (paidStatus = true) {
            System.out.println("Payment has been made!");
        } else {
            System.out.println("Payment invalid!");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double calTotal() {
        return this.amount * this.quantity;
    }

    @Override
    public String toString() {
        return "\t\t\t\t\tAmount = " + amount 
              +"\n\t\t\t\t\tQuantity = " + quantity;
    }

}
