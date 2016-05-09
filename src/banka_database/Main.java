/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database;

import XML.Xml;
import banka_database.gui.Login;
import banka_database.handlerDB.DataHandler;
import java.sql.SQLException;
import javax.xml.transform.TransformerException;


/**
 *
 * @author dominik
 */
public class Main {

    public static  DataHandler handler;
    public static void main(String[] args) throws SQLException, TransformerException {
        // TODO code application logic here
        handler = new DataHandler("localhost:3306","sovybanka","root","");
       /* Login login = new Login();
        login.setVisible(true);*/
        Xml xml = new Xml();
        xml.generateEmployeeXML("newFile");
    }
    
}
