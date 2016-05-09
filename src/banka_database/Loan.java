/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database;

/**
 *
 * @author dominik
 */
public class Loan {
    private double amount;
    private double paidup;
    private int interest;

    public Loan(double amount, double paidup, int interest) {
        this.amount = amount;
        this.paidup = paidup;
        this.interest = interest;
    }

    public double getAmount() {
        return amount;
    }

    public double getPaidup() {
        return paidup;
    }

    public int getInterest() {
        return interest;
    }
    
    @Override
    public String toString(){
        return amount+" "+paidup+" "+interest;
    }
    
    
}
