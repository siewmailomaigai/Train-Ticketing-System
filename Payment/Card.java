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
    private Calendar expiryDate;
    private int cvv;
    private double balance;

    private boolean credit;
    public static boolean validateCard;

    public Card(String cardNum, Calendar expiryDate, int cvv) {
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
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
        return String.format("%-20s %-15s %-5d ", cardNum, expiryDate, cvv);

    }

    public static boolean validateCard(Card card) {
        Pattern p = Pattern.compile("^\\d{4} \\d{4} \\d{4} \\d{4}$");
        Matcher m = p.matcher(card.cardNum);
        boolean validCardNum = m.matches();

        Calendar c = Calendar.getInstance();
        boolean validDate = (c.get(Calendar.DAY_OF_MONTH) >= card.expiryDate.get(Calendar.DAY_OF_MONTH));

        boolean validCvv = Integer.toString(card.cvv).length() == 3;

        return validCardNum && validDate && validCvv;
    }

}

