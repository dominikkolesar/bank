/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database;

import java.sql.Date;



/**
 *
 * @author dominik
 */
public class Client {
    
    private String firstname;
    private String lastname;
    private int id;
    
    
    public Client(int id,String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }
    
     public String toString()
     { 
         return id+" "+firstname + " " + lastname; 
     }
}
