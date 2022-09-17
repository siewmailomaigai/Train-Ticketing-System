/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registration;

import Management.BusTicketingSystem;
import payment.Card;
import Personnal.Customer;
import java.util.Calendar;
import java.util.Scanner;

public interface Registration {

    public static Customer performRegistration() {
        //prompt everything
        final int NUM_UPPER_LETTERS = 1;
        final int NUM_LOWER_LETTERS = 1;
        final int NUM_DIGITS = 1;
        final int NUM_UPPER_LETTERS_PASS = 1;
        final int NUM_LOWER_LETTERS_PASS = 1;
        final int NUM_DIGITS_PASS = 1;

        int upperCount_pass = 0;
        int lowerCount_pass = 0;
        int digitCount_pass = 0;
        int upperCount = 0;
        int lowerCount = 0;
        int digitCount = 0;

        Scanner scanner = new Scanner(System.in);

        String userName;
        String password;

        System.out.print("\n\t\t\t\t\tEnter username(name you wish to be display as)>");
        userName = scanner.nextLine();

        int nameLen = userName.length();

        for (int n = 0; n < nameLen; n++) {

            char nm = userName.charAt(n);

            if (Character.isUpperCase(nm)) {
                upperCount++;
            }
            if (Character.isLowerCase(nm)) {
                lowerCount++;
            }
            if (Character.isDigit(nm)) {
                digitCount++;
            }

        }

        while (upperCount < NUM_UPPER_LETTERS || lowerCount < NUM_LOWER_LETTERS || digitCount < NUM_DIGITS) {

            if (upperCount < NUM_UPPER_LETTERS) {
                System.out.println("\n\t\t\t\t\tYour username need to have at least 1 UPPERCASE letter!");
            }
            if (lowerCount < NUM_LOWER_LETTERS) {
                System.out.println("\n\t\t\t\t\tYour username need to have at least 1 LOWERCASE letter!");
            }
            if (digitCount < NUM_DIGITS) {
                System.out.println("\n\t\t\t\t\tYour username need to have at least 1 DIGIT!");
            }

            upperCount = 0;
            lowerCount = 0;
            digitCount = 0;

            System.out.println("\n\t\t\t\t\t-----------------------------");
            System.out.print("\t\t\t\t\tPlease re-enter your username >");
            userName = scanner.nextLine();

            nameLen = userName.length();

            for (int n = 0; n < nameLen; n++) {

                char nm = userName.charAt(n);

                if (Character.isUpperCase(nm)) {
                    upperCount++;
                }
                if (Character.isLowerCase(nm)) {
                    lowerCount++;
                }
                if (Character.isDigit(nm)) {
                    digitCount++;
                }

            }

        }

        System.out.print("\n\t\t\t\t\tEnter password >");
        password = scanner.nextLine();

        int passLen = password.length();

        for (int i = 0; i < passLen; i++) {

            char pw = password.charAt(i);

            if (Character.isUpperCase(pw)) {
                upperCount_pass++;
            }
            if (Character.isLowerCase(pw)) {
                lowerCount_pass++;
            }
            if (Character.isDigit(pw)) {
                digitCount_pass++;
            }
        }
        while (upperCount_pass < NUM_UPPER_LETTERS_PASS || lowerCount_pass < NUM_LOWER_LETTERS_PASS || digitCount_pass < NUM_DIGITS_PASS) {

            if (upperCount_pass < NUM_UPPER_LETTERS_PASS) {
                System.out.println("\n\t\t\t\t\tYour password need to have at least 1 UPPERCASE letter!");
            }
            if (lowerCount_pass < NUM_LOWER_LETTERS_PASS) {
                System.out.println("\n\t\t\t\t\tYour password need to have at least 1 LOWERCASE letter!");
            }
            if (digitCount_pass < NUM_DIGITS_PASS) {
                System.out.println("\n\t\t\t\t\tYour password need to have at least 1 DIGIT!");
            }

            upperCount_pass = 0;
            lowerCount_pass = 0;
            digitCount_pass = 0;

            System.out.println("\n\t\t\t\t\t-----------------------------");
            System.out.print("\t\t\t\t\tPlease re-enter your password >");
            password = scanner.nextLine();

            passLen = password.length();

            for (int i = 0; i < passLen; i++) {

                char pw = password.charAt(i);

                if (Character.isUpperCase(pw)) {
                    upperCount_pass++;
                }
                if (Character.isLowerCase(pw)) {
                    lowerCount_pass++;
                }
                if (Character.isDigit(pw)) {
                    digitCount_pass++;
                }
            }
        }

        Card card = performRegistrationCard();

        while (card == null) {
            System.out.println("\t\t\t\t\tInvalid card information");
            card = performRegistrationCard();
        }
        System.out.println("\t\t\t\t\tYou have successfully registered");
        Customer customer = new Customer(userName, password, card);
        Customer.add(customer);
        return customer;
    }

    public static Card performRegistrationCard() {
        Scanner scanner = new Scanner(System.in);
        String cardNum;
        int cvv;
        Calendar expiryDate = Calendar.getInstance();

        System.out.print("\n\t\t\t\t\tEnter your Card number >");
        cardNum = scanner.nextLine();

        System.out.print("\n\t\t\t\t\tEnter your Card expiry date (month)>");
        int month = scanner.nextInt();
        --month;
        System.out.print("\n\t\t\t\t\tEnter your Card expiry date (year)>");
        int year = scanner.nextInt();

        expiryDate.set(Calendar.MONTH, month);
        expiryDate.set(Calendar.YEAR, year);

        System.out.print("\n\t\t\t\t\tEnter you Card cvv number >");
        cvv = scanner.nextInt();

        int monthExpiryDate = expiryDate.get(Calendar.MONTH) + 1;
        int yearExpiryDate = expiryDate.get(Calendar.YEAR);

        Card card = new Card(cardNum, expiryDate, cvv);
        return Card.validateCard(card) ? card : null;
    }

    public static void editAccount() {
        System.out.println("\t\t\t\t\tEDIT ACCOUNT....");

        performRegistration();
        System.out.println("\t\t\t\t\tAccount edited..");
    }

}
