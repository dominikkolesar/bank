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
public class Account {
    
    private String balance;
    private String id;
  

    public Account(String id,String balance) {
        this.balance = balance;
        this.id = id;  
    }

    public String getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    
}
