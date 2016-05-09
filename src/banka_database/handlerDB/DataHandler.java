/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database.handlerDB;

import banka_database.Account;
import banka_database.Card;
import banka_database.Client;
import banka_database.ClientDetail;
import banka_database.Loan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author dominik
 */
public class DataHandler {
    
    private final String dateFix = "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
    private String url;
    private String database;
    private String user;
    private String password;
    private String driver;

    private Connection dbConnection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
     public DataHandler(String url, String database, String user, String password) {
        this.url = "jdbc:mysql://" + url + "/";
        this.database = database;
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.jdbc.Driver";
    }

     //pripojenie
    public boolean connect() {
        try {
            dbConnection = DriverManager.getConnection(url+database+dateFix,user,password);
        } catch (SQLException e) {
            System.out.println("error " +e);
            return false;
        }
        return true;
    }
    //odpojenie
    public void disconnect(){
        if(dbConnection != null){
            try{
                 dbConnection.close();
            }catch(Exception ex){
                System.out.println(ex);
            }
            
        }
    }
    
   
   
    public void prepareStatement(String query){
        try {
            statement = dbConnection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
    public void updateStatement(int position, String value){
        if(statement != null){
            try {
                statement.setString(position,value);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void updateStatement(String... value){
        if(statement != null){
            try{

                for(int i = 1; i <= value.length; i++){
                    statement.setString(i,value[i-1]);
                }

            } catch(SQLException e){
                e.printStackTrace();
            }

        }
    }
    public boolean executeUpdate(){
         if(statement != null){
            try {
                statement.executeUpdate();   
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         return false;
    }
    public boolean executeManipulate(String query){
      
        try{

            if(connect()){
             prepareStatement(query);
                executeUpdate();
                disconnect();
                return true;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }
    
    public HashMap<String, ArrayList<String>> executeForResult(String query) throws NoResultException {
        Statement st;
        ResultSet res = null;

        HashMap<String,ArrayList<String>> result = new LinkedHashMap<>();

        try {

            if(connect()){
                st = dbConnection.createStatement();
                res = st.executeQuery(query);

                ResultSetMetaData rsmd = res.getMetaData();
                int maxColumn = rsmd.getColumnCount();

                for(int i = 1; i <= maxColumn; i++){

                    String columnName = rsmd.getColumnName(i);
                    ArrayList<String> values = new ArrayList<>();

                    while(res.next()){
                        values.add(res.getString(columnName));
                    }

                    if(values.isEmpty()){
                        throw new NoResultException("Empty set with query "+query);
                    }

                    else{
                        result.put(columnName,values);
                    }


                    revertResultSet(res);

                }

                disconnect();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
      //prijme datum z formularu a mm/dd/yyyy a zmeni ho na format yyyy/mm/dd
      public String parseDateFromField(String date){
        try {
                DateFormat dffrom = new SimpleDateFormat("MM/dd/yy");
                DateFormat dfto = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date to = dffrom.parse(date);
                String s = dfto.format(to);
                return s;
               
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        return null;
    }
      public int getLastID(){
           int id = 0;
            try{
              if(connect()){
                  prepareStatement("SELECT ID from client order by ID desc limit 1");
                  resultSet = statement.executeQuery();
                  while(resultSet.next()){
                      id = resultSet.getInt("ID");
                  }
                    return id;
              }
             
        }catch(Exception ex){
            System.out.println(ex);
        }
            return -1;
           
        
    }
    
    //funckia vrati tabulku loan
        public DefaultTableModel getLoansData(String value ){
        //add colums to table
          //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = value.split(" ");
       int clientID = Integer.parseInt(parts[0]);
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("Amount");
        dm.addColumn("PaidUp");
        dm.addColumn("Interest");
        try{
            prepareStatement("SELECT id,amount,paidup,interest from loans where clientID = '"+clientID+"'");
                     
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                    String id = resultSet.getString("id");
                    String amount = resultSet.getString("amount");
                    String paidup = resultSet.getString("paidup");
                    String interest = resultSet.getString("interest");
                dm.addRow(new String[]{id,amount,paidup,interest});
             }
            return dm;
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    
    //vrati arrayList clientov 
    public List<Client> clientInfo(){
        List<Client> list = new ArrayList();
        try{
              if(connect()){
                  prepareStatement("SELECT id,firstname,lastname from client");
                  resultSet = statement.executeQuery();
                  while(resultSet.next()){
                      int id = resultSet.getInt("ID");
                      String name = resultSet.getString("firstname");
                      String surname= resultSet.getString("lastname");
                      Client client = new Client(id,name,surname);
                      list.add(client);
                  }
                    return list;
              }
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    
    //vrati arraylist clientDetail
    public List<ClientDetail> clientDetailInfo(String value){
        //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = value.split(" ");
       int id = Integer.parseInt(parts[0]);
       String name = parts[1];
       String surname= parts[2];
        List<ClientDetail> list = new ArrayList();
        
        if(connect()){
            prepareStatement("SELECT dateofbirth,email,mobile from client "
                    + "inner join clientdetails on client.id = clientdetails.id "
                    + "where client.id = '"+id+"'");
            try {
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String date = resultSet.getString("dateofbirth");
                    String email = resultSet.getString("email");
                    String mobile = resultSet.getString("mobile");
                ClientDetail detail = new ClientDetail(id,name,surname,date,mobile,email);
                list.add(detail);
                 }
                return list;
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
        }
        return null;
    }
    
      //vrati arraylist loan
    public List<Loan> loanInfo(String value){
        //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = value.split(" ");
       int id = Integer.parseInt(parts[0]);
      
        List<Loan> list = new ArrayList();
        
        if(connect()){
            prepareStatement("SELECT amount,paidup,interest from loan where clientID = '"+id+"' ");
            try {
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    Double amount = resultSet.getDouble("amount");
                    Double paidup = resultSet.getDouble("paidup");
                    int interest = resultSet.getInt("interest");
                Loan loan = new Loan(amount,paidup,interest);
                list.add(loan);
                 }
                return list;
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
        }
        return null;
    }
    //vrati informacie id a active card
   public HashMap<Integer,Card> getCardInfo(String value){
        //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = value.split(" ");
       int accountID = Integer.parseInt(parts[0]);
       HashMap<Integer,Card> list = new LinkedHashMap<>();
        if(connect()){
            prepareStatement("SELECT ID,active from cards where accountID = '"+accountID+"'");                                      
            try {
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                     int ID = resultSet.getInt("ID");
                    String idcard = String.valueOf(ID);
                    String active = resultSet.getString("active");
                    Card card = new Card(idcard,active);
                   list.put(ID, card);
                 }
                return list;
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
        }
        return null;
    }
   //funkcia vrati info  id accountu a balance
   /* public List<Account> getAccountInfo(String value){
        //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = value.split(" ");
       int clientID = Integer.parseInt(parts[0]);
        List<Account> list = new ArrayList();
        if(connect()){
           prepareStatement("SELECT ID,balance from accounts where accounts.clientID = '"+clientID+"'");                                  
            try {
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String id = resultSet.getString("ID");
                    String balance = resultSet.getString("balance");
                    Account acc = new Account(id,balance);
                list.add(acc);
                 }
                return list;
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
        }
        return null;
    }*/
   public HashMap<Integer,Account> getAccountInfo(String value){
           HashMap<Integer,Account> map = new LinkedHashMap<>();
            //[0]ID, [1]FIRSTNAME, [2]LASTNAME
         String[] parts = value.split(" ");
       int clientID = Integer.parseInt(parts[0]);
           Account c;
           
          try{
              prepareStatement("SELECT ID,balance from accounts where accounts.clientID = '"+clientID+"'");
               resultSet = statement.executeQuery();
               while(resultSet.next()){
                   int id = resultSet.getInt(1);
                   String balance = resultSet.getString(2);
                   String IDacc = String.valueOf(id);
                   c = new Account(IDacc,balance);
                   map.put(id,c);
               }
               return map;
              
          }catch(Exception ex){
              System.out.println(ex);
          }
          return null;
       }
   
        
    
    

     private void revertResultSet(ResultSet set) throws SQLException {
        while(set.previous());
    }
     public class NoResultException extends Exception {

        public NoResultException(String message){
            super(message);
        }

    }
    
       //vytvori hashMapu  ktora vrati cislo stlpca a triedu client
       public HashMap<Integer,ClientDetail> clientdetail( String query){
           HashMap<Integer,ClientDetail> map = new LinkedHashMap<>();
           ClientDetail c;
           
          try{
              prepareStatement(query);
               resultSet = statement.executeQuery();
               while(resultSet.next()){
                   int id = resultSet.getInt(1);
                   String name = resultSet.getString(2);
                   String surname = resultSet.getString(3);  
                   String date = resultSet.getString(4);
                   String email = resultSet.getString("email");
                   String phone = resultSet.getString("mobile");
                   c = new ClientDetail(id,name,surname,date,phone,email);
                   map.put(id, c);
               }
               return map;
              
          }catch(Exception ex){
              System.out.println(ex);
          }
          return null;
       }
       
       
     
    
}
