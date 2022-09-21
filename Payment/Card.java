/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 60192
 */

public class Card {

    private String cardNum;
    private int expiryMonth;
    private String expiryYear;
    private int cvv;
    private double balance;

    private boolean credit;
    public static boolean validateCard;

    public Card(String cardNum, int expiryMonth, String expiryYear, int cvv) {
        this.cardNum = cardNum;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.balance = Payment.generateRandomBalance();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s %-15s %-15s %-5d ", cardNum, expiryMonth ,expiryYear, cvv);

    }

    public static boolean validateCard(Card card) {
        Pattern p = Pattern.compile("^\\d{4} \\d{4} \\d{4} \\d{4}$");
        Matcher m = p.matcher(card.cardNum);
        boolean validCardNum = m.matches();
        
        
        boolean validMonth = ((card.expiryMonth) > 0 && (card.expiryMonth) < 13);
        
        Pattern P = Pattern.compile("^\\d{4}$");
        Matcher M = P.matcher(card.expiryYear);
        boolean validYear = M.matches();

        boolean validCvv = Integer.toString(card.cvv).length() == 3;

        return validCardNum && validMonth && validYear && validCvv;
    }

}

