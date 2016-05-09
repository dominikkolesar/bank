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
public class Card {
    
    private String id;
    private String active;

    public Card(String id, String active) {
        this.id = id;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getBalance() {
        return active;
    }
    
    
}
