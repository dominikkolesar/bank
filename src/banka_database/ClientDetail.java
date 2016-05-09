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
public class ClientDetail extends Client {
    
   private String phone;
   private String email;
   private String date;

    public ClientDetail(int id, String firstname, String lastname, String date, String phone, String email) {
        super(id, firstname, lastname);
        this.phone = phone;
        this.email = email;
        this.date = date;
        
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public String getDate(){
        return date;
    }
    
    
    
   
    
}
