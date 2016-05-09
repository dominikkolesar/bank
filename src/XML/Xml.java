/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import banka_database.Main;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author dominik
 */
public class Xml {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DOMSource domSource;
    private StreamResult streamResult;
     private PreparedStatement statement;
    private ResultSet results;
    

    public Xml() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void generateEmployeeXML(String filename) throws SQLException, TransformerException {
        document = builder.newDocument();
    String query = "SELECT ID,firstName,lastName,dateOfBirth FROM Client";
       Main.handler.connect();
       Main.handler.prepareStatement("SELECT ID,firstName,lastName,dateOfBirth FROM Client");
          results = statement.executeQuery("SELECT ID,firstName,lastName,dateOfBirth FROM Client");
        Element rootElement = document.createElement("clienttable");
        document.appendChild(rootElement);

        while(results.next()) {
            int clientID = results.getInt("ID");
            String firstName = results.getString("firstName");
            String lastName = results.getString("lastName");
            String dob = results.getDate("dateOfBirth").toString();

            query = "SELECT ID,balance FROM accounts WHERE clientID=" + clientID;
            Main.handler.prepareStatement(query);
            ResultSet accountsResults = statement.executeQuery();

            Element client = document.createElement("client");
            rootElement.appendChild(client);
            Element name = document.createElement("name");
            Element lastNameTag = document.createElement("lastname");
            Element dateOfBirth = document.createElement("dob");
            Element accounts = document.createElement("accounts");
            Element account = document.createElement("account");
            Element balance = document.createElement("balance");

            Attr id = document.createAttribute("id");
            id.setValue(String.valueOf(clientID));

            client.setAttributeNode(id);
            client.appendChild(name);
            client.appendChild(lastNameTag);
            client.appendChild(dateOfBirth);
            client.appendChild(accounts);

            name.appendChild(document.createTextNode(firstName));
            lastNameTag.appendChild(document.createTextNode(lastName));
            dateOfBirth.appendChild(document.createTextNode(dob));

            try {
                while(accountsResults.next()) {
                    accounts.appendChild(account);
                    Attr accountId = document.createAttribute("id");
                    accountId.setValue(String.valueOf(accountsResults.getInt("ID")));
                    account.setAttributeNode(accountId);
                    account.appendChild(balance);
                    balance.appendChild(document.createTextNode(String.valueOf(accountsResults.getDouble("balance"))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        domSource = new DOMSource(document);
        streamResult = new StreamResult(new File(filename));

        transformer.transform(domSource,streamResult);
    }
    

   
}

