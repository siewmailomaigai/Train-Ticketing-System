/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import Asset.Ticket;
import Personnel.Customer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 60192
 */

public class Receipt extends Transaction{

   private Date datePaid;
    private boolean paidStatus;
    private double amountPaid;

    public Receipt(Ticket ticket) {
        super(ticket);
    }
    
    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }
   
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
   
    public double getAmountPaid() {
        return amountPaid;
    }
    
    public void setDate(Date datePaid){
        this.datePaid = datePaid;
    }
    
    public Date getDate(){
        return this.datePaid;
    }
    public static String printReceipt(Ticket ticket, Customer customer) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return   "\t\t\t\t\t\t ===========================\n"
                +"\t\t\t\t\t\t          Receipt           \n"
                +"\t\t\t\t\t\t ===========================\n"
                +"\t\t\t\t\t\t Customer Name  :"+customer.getName()+"\n"
                +"\t\t\t\t\t\t Date Paid      :"+dtf.format(now)+      "\n"
                +"\t\t\t\t\t\t Paid Status    :"+ticket.isPaidStatus()+"\n"
                +"\t\t\t\t\t\t No.of tickets  :"+ticket.getQuantity() +"\n"
                +"\t\t\t\t\t\t ===========================\n"
                +"\t\t\t\t\t\t Amount paid(RM): " + ticket.calTotal()+"\n"
                +"\t\t\t\t\t\t ===========================\n\n"
                +"\t\t\t\t\tThank you and till next time:)";
    }

}
