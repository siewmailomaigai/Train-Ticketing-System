/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Asset.Schedule;
import Asset.Ticket;
import static Management.Menu.logo;
import Personnal.Customer;
import Registration.Registration;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import payment.Payment;

interface Flags {

    static final int NO_LOGIN = 0;
    static final int LOGGED_IN = 1;
    static final int ACCOUNT_MENU = 2;
    static final int EXIT = 3;
}

interface Menu {

    static void welcomeMessage() {
        System.out.println("\t\t\t\t\t\t\tWELCOME!!!!");
        System.out.println("\t\t\t\t\tTHIS IS A TRAIN RESERVATION AND TICKETING SYSTEM...");
    }

    static void mainMenu() {
        System.out.println("\t\t\t\t\t\t --------------------------");
        System.out.println("\t\t\t\t\t\t|         MAIN MENU        |");
        System.out.println("\t\t\t\t\t\t --------------------------");
        System.out.println("\t\t\t\t\t\t|     1 : Account          |");
        System.out.println("\t\t\t\t\t\t|     2 : Reservation      |");
        System.out.println("\t\t\t\t\t\t|     3 : Payment          |");
        System.out.println("\t\t\t\t\t\t|     4 : Log Out          |");
        System.out.println("\t\t\t\t\t\t --------------------------");
    }

    static void regMenu() {
        System.out.println("\t\t\t\t\t ------------------------------------------");
        System.out.println("\t\t\t\t\t|   TRAIN RESERVATION AND TICKETING SYSTEM   |");
        System.out.println("\t\t\t\t\t ------------------------------------------ ");
        System.out.println("\t\t\t\t\t| 1 : Register an account                  |");
        System.out.println("\t\t\t\t\t| 2 : Login                                |");
        System.out.println("\t\t\t\t\t| 3 : Exit                                 |");
        System.out.println("\t\t\t\t\t ------------------------------------------\n");
    }

    static void accMenu() {
        System.out.println("\t\t\t\t\t\t --------------------------");
        System.out.println("\t\t\t\t\t\t|         ACCOUNT          |");
        System.out.println("\t\t\t\t\t\t --------------------------");
        System.out.println("\t\t\t\t\t\t|     1 : Edit             |");
        System.out.println("\t\t\t\t\t\t|     2 : Delete           |");
        System.out.println("\t\t\t\t\t\t|     3 : Back             |");
        System.out.println("\t\t\t\t\t\t --------------------------");
    }

    static void exitMessage() {
        System.out.println("\t\t\t\t\tYOU CHOSE TO EXIT THE SYSTEM...");
        System.out.println("\t\t\t\t\tHAVE A NICE DAY!!!!");
    }

    static void logo() {
        System.out.println("\t\t\t _ _   _ ___   _____ ___ ___ _  _____ _____ ___ _  _  ___   _____   _____ _____ ___ __  __ ");
        System.out.println("\t\t\t| _ ) | | / __| |_   _|_ _/ __| |/ / __|_   _|_ _| \\| |/ __| / __\\ \\ / / __|_   _| __|  \\/  |");
        System.out.println("\t\t\t| _ \\ |_| \\__ \\   | |  | | (__| ' <| _|  | |  | || .` | (_ | \\__ \\\\ V /\\__ \\ | | | _|| |\\/| |");
        System.out.println("\t\t\t|___/\\___/|___/   |_| |___\\___|_|\\_\\___| |_| |___|_|\\_|\\___| |___/ |_| |___/ |_| |___|_|  |_|");
        System.out.println("");
    }
}

public class TrainTicketingSystem {

    /**
     * @param args the command line arguments
     */
    private static int choice;
    private static int flag = Flags.NO_LOGIN;
    private static Customer loggedInUser = null;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException {
        init();
        logo();
        Menu.welcomeMessage();
        while (flag != Flags.EXIT) {
            showMenu();
            System.out.print("\n\t\t\t\t\tWhat is your choice: ");
            boolean inputError = true;
            do {
                try {   
                    choice = scanner.nextInt();
                    inputError = false;
                    parseChoice();
                    pressEnterKey();
                    cls();
                } catch (InputMismatchException e) {
                    System.out.println("\t\t\t\t\tUnavailable Choice");
                    System.out.print("\t\t\t\t\tPlease reenter:");
                    scanner.next();
                }
            } while (inputError == true);
            
        }

    }

    public static void cls() throws InterruptedException, IOException {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }

    public static void pressEnterKey() {
        System.out.println("\t\t\t\t\tPress \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseChoice() {
        switch (flag) {
            case Flags.NO_LOGIN:
                parseRegistration();
                break;
            case Flags.LOGGED_IN:
                parseMainMenu();
                break;
            case Flags.ACCOUNT_MENU:
                parseAccountMenu();
                break;
        }
    }

    private static void parseRegistration() {
        switch (choice) {
            case 1:
                System.out.println("\t\t\t\t\t\t=====================");
                System.out.println("\t\t\t\t\t\t|   REGISTRATION    |");
                System.out.println("\t\t\t\t\t\t=====================");
                Registration.performRegistration();
                break;
            case 2:
                performLogin();
                break;
            case 3:
                Menu.exitMessage();
                flag = Flags.EXIT;
                break;
            default:
                System.out.println("\t\t\t\t\tPlease Enter A Valid Choice!");
                break;
        }
    }

    private static void parseMainMenu() {
        switch (choice) {
            case 1:
                flag = Flags.ACCOUNT_MENU;
                break;
            case 2:
                loggedInUser.reserveTicket();
                break;
            case 3:
                Payment.performPayment(loggedInUser, loggedInUser.getTicket());
                break;
            case 4:
                logOut();
                break;
            default:
                System.out.println("\t\t\t\t\tPlease enter a valid choice");
                pressEnterKey();
                break;

        }
    }

    private static void parseAccountMenu() {
        switch (choice) {
            case 1:
                System.out.println("\t\t\t\t\t\t=====================");
                System.out.println("\t\t\t\t\t\t|   EDIT ACCOUNT    |");
                System.out.println("\t\t\t\t\t\t=====================");
                Registration.editAccount();
                break;
            case 2:
                System.out.println("\t\t\t\t\t\t=====================");
                System.out.println("\t\t\t\t\t\t|   DELETE ACCOUNT   |");
                System.out.println("\t\t\t\t\t\t=====================");
                Customer.deleteAccount(loggedInUser);
                break;
            case 3:
                flag = Flags.LOGGED_IN;
                break;
        }
    }

    private static void showMenu() {
        switch (flag) {
            case Flags.NO_LOGIN:
                Menu.regMenu();
                break;
            case Flags.LOGGED_IN:
                Menu.mainMenu();
                break;
            case Flags.ACCOUNT_MENU:
                Menu.accMenu();
                break;
        }
    }

    private static void logOut() {
        loggedInUser = null;
        flag = Flags.NO_LOGIN;
    }

    private static void init() {
        Schedule[] scheduleList = {
            new Schedule("Kepong", "Ipoh", Schedule.setupTime(14, 35)),
            new Schedule("Kedah", "Kelantan", Schedule.setupTime(16, 00)),
            new Schedule("Perak", "Selangor", Schedule.setupTime(9, 30)),
            new Schedule("Melaka", "Negeri Sembilan", Schedule.setupTime(11, 20)),
            new Schedule("Teluk Intan", "Seremban", Schedule.setupTime(18, 50))
        };

        for (int a = 0; a < scheduleList.length; a++) {
            Schedule.addSchedule(scheduleList[a]);
        }
    }

    private static void performLogin() {
        System.out.printf("\t\t\t\t\tEnter your username :");
        scanner.nextLine();
        String userName = scanner.nextLine();
        //System.out.println(userName);

        System.out.printf("\n\t\t\t\t\tEnter your password :");
        String password = scanner.nextLine();
        //System.out.println(password);

        Customer customer = Customer.search(userName, password);
        if (customer == null) {
            System.out.println("\n\t\t\t\t\tUser doesn't exist");
            flag = Flags.NO_LOGIN;
            loggedInUser = null;
            return;
        }
        loggedInUser = customer;
        flag = Flags.LOGGED_IN;
    }

    public static void reserveTicket() {
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
            System.out.println("");
            System.out.print(" \t\t\t\t\t\t Enter your choice : ");
            selection = scanner.nextInt();

            Customer.parseTicketUserChoice(selection, destination, matrix);

        } while (selection != 3);

        scanner.close();
    }
}
