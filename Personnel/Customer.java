/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnal;

import Management.BusTicketingSystem;
import Asset.Schedule;
import Asset.Ticket;
import Asset.Bus;
import payment.Card;
import payment.Payment;
import Reservation.Reservation;

import Asset.Bus;
import Personnal.Person;
import Personnal.Driver;
import Asset.Bus;
import Reservation.Reservation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author JEhew
 */
public class Customer extends Person implements Reservation {

    private String custName;
    private String custID;
    private String password;
    private int reserveSeatNo;


    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static int nextID = 1000;
    private Ticket ticket;
    private Bus bus;
    private Card card;
    private ArrayList<Schedule> scheduleList;

    public Customer(Person person, String password, Card card) {
        this(person.getName(), password, card);
    }

    public Customer(String name, String password, Card card) {
        super(name);
        this.custID = "CD" + nextID;
        this.password = password;
        this.card = card;
        ++nextID;
    }

    public static void add(Customer customer) {
        customerList.add(customer);
    }

    public static Customer search(String custName, String password) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getName().equals(custName) && customerList.get(i).getPassword().equals(password)) {
                return customerList.get(i);
            }

        }
        return null;
    }

    public static void edit(Customer oldCustomer, Customer newCustomer) {
        int oldCustomerIndex = customerList.indexOf(oldCustomer);
        customerList.set(oldCustomerIndex, newCustomer);
    }

    public static void deleteAccount(Customer customer) {

        Scanner scanner = new Scanner(System.in);
        char yesOrno;


            System.out.printf("\t\t\t\t\tAre you sure you want to delete your account?(y/n)");
            yesOrno = scanner.next().charAt(0);

            if (yesOrno == 'Y' || yesOrno == 'y') {
                System.out.println("\n\t\t\t\t\tYour account has been deleted\n");
                customerList.remove(customer);

            }  
            else {
                System.out.println("\n\t\t\t\t\tInvalid Choice, please enter again.\n");
                
            }
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReserveSeatNo() {
        return reserveSeatNo;
    }

    public void setReserveSeatNo(int reserveSeatNo) {
        this.reserveSeatNo = reserveSeatNo;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Card getCard() {
        return this.card;
    }

    public String toString() {
        return String.format("%-30s %-10s %-5d %-15s %-15s",
                custName, custID, reserveSeatNo, bus, ticket);

    }

    @Override
    public void reserveTicket() {
        Scanner scanner = new Scanner(System.in);
        int selection;
        int destination = 0;
        char matrix[][] = new char[11][10];

        do {
            System.out.println("\t\t\t\t\t\t *=====================*");
            System.out.println("\t\t\t\t\t\t |     Reservation     |");
            System.out.println("\t\t\t\t\t\t *=====================*");
            System.out.println("\t\t\t\t\t\t | [1] Destination     |");
            System.out.println("\t\t\t\t\t\t | [2] Booking Ticket  |");
            System.out.println("\t\t\t\t\t\t | [3] Exit            |");
            System.out.println("\t\t\t\t\t\t *=====================*");
            System.out.println(""); // new line
            System.out.print(" \t\t\t\t\t Enter your choice : ");
            selection = scanner.nextInt();

            Ticket ticket = parseTicketUserChoice(selection, destination, matrix);

            if (ticket != null) {
                this.setTicket(ticket);
            }
        } while (selection != 3);

    }

    public static Ticket parseTicketUserChoice(int selection, int destination, char matrix[][]) {

        Scanner scanner = new Scanner(System.in);
        Ticket ticket = null;
        switch (selection) {
            case 1:
                System.out.println("\t\t\t *====================================================================================================================*");
                System.out.println("\t\t\t |                        Destination  List                                                                           |");
                System.out.println("\t\t\t *====================================================================================================================*");
                System.out.println("\t\t\t |     Destination                                    |     Time      |     Price     |      Driver     |     Bus     |");
                System.out.println("\t\t\t *====================================================================================================================*");
                for (int i = 0; i < Schedule.scheduleList.size(); i++) {
                    Schedule schedule = Schedule.scheduleList.get(i);
                    System.out.printf("\t\t\t |  [%d] %-20s --> %-20s |    %-5s      |    RM%d       |      BD%-5s    |  %-10d |\n", i+1, schedule.getStartLocation(), schedule.getDestination(), schedule.getDepartureTime(), 10, Driver.getNextID()+i, Bus.getNextBusID()+i);
                }
                System.out.println("\t\t\t *=====================================================================================================================*");
                System.out.println(""); // new line

                break;

            case 2:
                System.out.println("\t\t\t\t\t *============================*");
                System.out.println("\t\t\t\t\t |       Booking Ticket       *");
                System.out.println("\t\t\t\t\t *============================*");
                System.out.println(""); // new line
                System.out.print(" \t\t\t\t\t Enter Destination : ");
                destination = scanner.nextInt();

                ticket = destination(destination, matrix);

            case 3:
                System.out.println("\t\t\t\t\tThank You and BYE !");
                System.out.println("\t\t\t\t\tHave A Nice Day ! ");
                break;

        }
        return ticket;
    }

    public static Ticket destination(int destination, char matrix[][]) {

        if (destination < 0 || destination > Schedule.scheduleList.size() ) {
            return null;
        }
        System.out.println("\t\t\t\t\t *============================*");
        System.out.printf("\t\t\t\t\t *      %15s       *\n", Schedule.scheduleList.get(destination - 1).getDestination());
        System.out.println("\t\t\t\t\t *============================*");
        return bookingSeat(matrix);
    }

    public static Ticket bookingSeat(char matrix[][]) {
        int ticketCount = 1;
        Scanner scanner = new Scanner(System.in);
        
        //loop for column
        for (int colNum = 1; colNum <= 2; colNum++) {
            System.out.print("\t\t\t\t\t" + " Column " + (colNum));
        }
        System.out.println();

        //loop for row
        for (int rowNum = 1; rowNum < 10; rowNum++) {
            System.out.println("\t\t\t\t\t" + "Row    " + (rowNum) + "\t");
            //loop for printing row and col
            for (int col = 1; col <= 2; col++) {
                matrix[rowNum][col] = '*';
                System.out.print("\t\t\t\t\t" + matrix[rowNum][col] + "\t\t");

            }
            System.out.println();
        }

        while (true) {
            System.out.print("\t\t\t\t\tEnter row and column seperated by space : ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println("\t\t\t\t\tRow : " + x);
            System.out.println("\t\t\t\t\tColumn : " + y);

            if ((x > 0 && y > 0)) {
                System.out.println("\n \t\t\t\t\t\t Bus Seat Reservation ");
                System.out.println("\t\t\t\t\t-------------------------------------------------");

                for (int colNum = 1; colNum <= 2; colNum++) {
                    System.out.print("\t\t\t\t\t\t" + "Column " + (colNum));
                }

                for (int rowNum = 1; rowNum <= 10; rowNum++) {
                    System.out.print("\n\t\t\t\t\tRow " + (rowNum) + "\t");
                    for (int col = 1; col <= 2; col++) {
                        matrix[x][y] = 'X';
                        System.out.print("\t\t\t\t" + matrix[rowNum][col] + "\t\t");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("\t\t\t\t\tBooking Failed ! - Please Booking Again !");
                break;
            }

            System.out.println("\t\t\t\t\tYou Have Successfully booking a seat !");
            System.out.print("\t\t\t\t\tDo you want booking another seat ? (Y/N) : ");
            char option = scanner.next().charAt(0);
            
            if (option == 'Y' || option == 'y') {
                
                ticketCount++;
                continue;
            } else {
                System.out.println(" \t\t\t\t\tEnjoy Your Trip !");
                break;
            }
            
            
        }
        // create ticket
        return new Ticket(10, ticketCount, false);
    }

    @Override
    public void payTicket() {
        if (ticket.isPaidStatus()) {
            return;
        }
        boolean paid = Payment.performPayment(this, ticket);
        ticket.setPaidStatus(paid);
    }
}
