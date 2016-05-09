/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database.gui;

import banka_database.Account;
import banka_database.Card;
import banka_database.Client;
import banka_database.ClientDetail;
import banka_database.Loan;
import banka_database.Main;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author dominik
 */
public class ClientList extends javax.swing.JFrame {

    
    
    private  String password;
    private String userName;
    private  HashMap<Integer,Account> listAccount;
    private  HashMap<Integer,Card> listCards;
    private String stringClient;
    private List<Loan> listLoans;
   
    
    public ClientList(String userName,String password) {
        initComponents();
       dataFromArrayListToComboBoxClient();
        this.labelName.setText(userName);
        this.password = password;
        this.userName = userName;
        //mozme aj takto dvojaky sposob
        //setPassword(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
   
    //nacita pozicky daneho clienta ktore vlozi do jListu
    public void setLoanTable(){
        DefaultTableModel dm = Main.handler.getLoansData(stringClient);
       tableLoans.setModel(dm);
        
    }
    //vlozi pozicku vynuluje pole a zavola funciu ktora updatne tabulku
    public void insertLoan(){
        String[] parts = stringClient.split(" ");
       int clientID = Integer.parseInt(parts[0]);
        String amount = fieldLoan.getText();
        String paidup = fieldPaidup.getText();
        String interest = fieldInterest.getText();
        
        
        if(!( (amount.equals("")|| paidup.equals("") || interest.equals("") ) ) ){
            Main.handler.prepareStatement("Insert INTO loans (clientID,amount,paidup,interest) VALUES('"+clientID+"' ,'"+amount+"', '"+paidup+"', '"+interest+"')");
            Main.handler.executeUpdate();
             JOptionPane.showMessageDialog(null, "Loan has been added");
             fieldLoan.setText("");
             fieldPaidup.setText("");
             fieldInterest.setText("");
             setLoanTable();
        }
        else {
            JOptionPane.showMessageDialog(null, "Somethink is wrong");
        }
    }
    
    //udaje z arraylistu vlozi do komboboxu
    public void dataFromArrayListToComboBoxClient(){
        List<Client> list = new ArrayList<>();
        list = Main.handler.clientInfo();
        comboBoxClient.removeAllItems();
       for(Client client:list){
           comboBoxClient.addItem(client.toString());
        } 
    }
    
    //funckia prijme hashMapu ktora obsahuje int ako kluc a accout class , naplni comboBoxAcc 
    public void dataFromArrayListToComboBoxAccout(String value){
        listAccount =   Main.handler.getAccountInfo(value);
        comboBoxAccount.removeAllItems(); 
        for(Integer n:listAccount.keySet()){
            comboBoxAccount.addItem((listAccount.get(n)).getId());
        }
    }  
    //nastavi label balance
    public void setBalanceLabel(String value){
        if(value!=null){
             int id = Integer.parseInt(value);
            fieldBalance.setText(listAccount.get(id).getBalance());
        }
    }
    public void dataFromHashmapToComboBoxCard(String value){
        listCards = Main.handler.getCardInfo(value);
        comboBoxCard.removeAllItems();
         for(Integer n:listCards.keySet()){
            comboBoxCard.addItem((listCards.get(n)).getId());
        }
    }
    // odosle selec z komboboxu do funckie detail info a account info a vyplni lable hodnotami ziskanimi z tychto arraylistov
    public void dataFromComboBoxClientToLabel(String value){
        List<ClientDetail> list = new ArrayList();
        list = Main.handler.clientDetailInfo(value);
        detailName.setText(list.get(0).getFirstname());
        detailSurename.setText(list.get(0).getLastname());
        detailDate.setText(list.get(0).getDate());
        detailMail.setText(list.get(0).getEmail());
        detailPhone.setText(list.get(0).getPhone());     
    }
    public void setLabelActive(String value){
        if(value!=null){
             int id = Integer.parseInt(value);
            fieldActivate.setText(listCards.get(id).getBalance());
        }
        else 
            fieldActivate.setText("");
    }
    
   
    //vymaze account
    public void deleteAccount(){
        String value =(String) comboBoxAccount.getSelectedItem();
        String[] parts = value.split(" ");
        int accountID = Integer.parseInt(parts[0]);
        Main.handler.prepareStatement("Delete from accounts where accounts.ID='"+accountID+"'");
        Main.handler.executeUpdate();
        JOptionPane.showMessageDialog(null, "Account has been deleted");
    }
   /* public void insertBalance(){
        String value =(String) comboBoxAccount.getSelectedItem();
        String[] parts = value.split(" ");
        int accountID = Integer.parseInt(parts[0]);
        Main.handler.prepareStatement("UPDATE accounts where clientID='"+accountID+"'");
        Main.handler.executeUpdate();
        JOptionPane.showMessageDialog(null, "Account has been deleted");
    }*/
    
     public void deleteCard(){
        String value =(String) comboBoxCard.getSelectedItem();
        String[] parts = value.split(" ");
        int cardID = Integer.parseInt(parts[0]);
        Main.handler.prepareStatement("Delete from cards where card.ID='"+cardID+"'");
        Main.handler.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cart has been deleted");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        buttonInsert = new javax.swing.JButton();
        buttonWindraw = new javax.swing.JButton();
        buttonActive = new javax.swing.JButton();
        buttonAddCard = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboBoxCard = new javax.swing.JComboBox<>();
        comboBoxAccount = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        fieldBalance = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fieldActivate = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        buttonDeactive = new javax.swing.JButton();
        buttonDeleteCard = new javax.swing.JButton();
        buttonAddAcc = new javax.swing.JButton();
        buttonDeleteAcc = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fieldLoan = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        fieldPaidup = new javax.swing.JTextField();
        fieldInterest = new javax.swing.JTextField();
        buttAddLoan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLoans = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        detailName = new javax.swing.JLabel();
        detailSurename = new javax.swing.JLabel();
        detailDate = new javax.swing.JLabel();
        detailPhone = new javax.swing.JLabel();
        detailMail = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        comboBoxClient = new javax.swing.JComboBox<>();
        AddNewClient = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Employeer:");

        labelName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(67, 60, 117));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Clients:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Clients info:");

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        buttonInsert.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonInsert.setText("Insert");
        buttonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertActionPerformed(evt);
            }
        });

        buttonWindraw.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonWindraw.setText("Windraw");
        buttonWindraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWindrawActionPerformed(evt);
            }
        });

        buttonActive.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonActive.setText("Activate");
        buttonActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActiveActionPerformed(evt);
            }
        });

        buttonAddCard.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonAddCard.setText("Add card");
        buttonAddCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCardActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Accounts ID:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Cards ID:");

        comboBoxCard.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        comboBoxCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCardActionPerformed(evt);
            }
        });

        comboBoxAccount.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        comboBoxAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAccountActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Balance:");

        fieldBalance.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        fieldBalance.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Activate:");

        fieldActivate.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        fieldActivate.setText("jLabel14");

        jButton7.setText("jButton7");

        buttonDeactive.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonDeactive.setText("Deactivate");
        buttonDeactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeactiveActionPerformed(evt);
            }
        });

        buttonDeleteCard.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonDeleteCard.setText("Delete");
        buttonDeleteCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteCardActionPerformed(evt);
            }
        });

        buttonAddAcc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonAddAcc.setText("Add ");
        buttonAddAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddAccActionPerformed(evt);
            }
        });

        buttonDeleteAcc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttonDeleteAcc.setText("Delete");
        buttonDeleteAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteAccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel13)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboBoxCard, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldActivate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(buttonActive)
                                    .addGap(23, 23, 23)
                                    .addComponent(buttonDeactive))))
                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(139, 139, 139)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(buttonAddCard)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonDeleteCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboBoxAccount, 0, 88, Short.MAX_VALUE)
                                        .addComponent(buttonAddAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonDeleteAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(buttonInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonWindraw, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(fieldBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(269, 269, 269)
                            .addComponent(jButton7)))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonWindraw, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAddAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonDeleteAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jLabel11)
                            .addComponent(fieldBalance))
                        .addGap(65, 65, 65)))
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboBoxCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCard, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteCard, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(fieldActivate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonActive, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeactive, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        jTabbedPane1.addTab("Accounts", jPanel2);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Loans:");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Loan Amount:");

        fieldLoan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Add loan");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("Paidup:");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Interest:");

        fieldPaidup.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        fieldInterest.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        buttAddLoan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        buttAddLoan.setText("Add");
        buttAddLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttAddLoanActionPerformed(evt);
            }
        });

        tableLoans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLoans);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(buttAddLoan))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldPaidup, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(193, 193, 193))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel12)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(fieldLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16))
                    .addComponent(fieldPaidup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(fieldInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(buttAddLoan)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Loans", jPanel3);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Name:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Surname:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Date of Birth:");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Phone:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Email:");

        detailName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        detailName.setText("name");

        detailSurename.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        detailSurename.setText("surname");

        detailDate.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        detailDate.setText("date");

        detailPhone.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        detailPhone.setText("phone");

        detailMail.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        detailMail.setText("email");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailSurename, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(detailName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(detailMail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(detailName))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(detailSurename, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(detailDate))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(detailPhone))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(detailMail))
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Client info", jPanel1);

        comboBoxClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxClientActionPerformed(evt);
            }
        });

        AddNewClient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        AddNewClient.setText("Add new client");
        AddNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewClientActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu2.setText("Account setting");

        jMenuItem1.setText("Log out");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Change password");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(124, 124, 124)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(219, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(AddNewClient)
                        .addGap(133, 133, 133))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNewClient)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     //zatvori main formular a otvori login okno
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         this.dispose();
       Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    //form na zmenu hesla
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         ChangePassword passForm = new ChangePassword(userName,password);
        passForm.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void comboBoxClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClientActionPerformed
        
        String value = (String) comboBoxClient.getSelectedItem();
        stringClient = (String) comboBoxClient.getSelectedItem();
        if(value!=null){
            dataFromComboBoxClientToLabel(value);
            dataFromArrayListToComboBoxAccout(stringClient); 
            setLoanTable();
        }
        fieldBalance.setText("");
        
    }//GEN-LAST:event_comboBoxClientActionPerformed

    private void comboBoxAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAccountActionPerformed
      String value = (String) comboBoxAccount.getSelectedItem();
        if(value!=null){
             setBalanceLabel(value);
            dataFromHashmapToComboBoxCard(value);
        }
       
    }//GEN-LAST:event_comboBoxAccountActionPerformed

    private void comboBoxCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCardActionPerformed
            String value = (String) comboBoxCard.getSelectedItem();
                setLabelActive(value);
    }//GEN-LAST:event_comboBoxCardActionPerformed

    private void buttonAddAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddAccActionPerformed
        //String value = (String) comboBoxClient.getSelectedItem();
         //[0]ID, [1]FIRSTNAME, [2]LASTNAME
       String[] parts = stringClient.split(" ");
        NewAccount account = new NewAccount(parts[0]);
        account.setVisible(true);
        dataFromArrayListToComboBoxAccout(stringClient);
                
    }//GEN-LAST:event_buttonAddAccActionPerformed

    private void buttonDeleteAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteAccActionPerformed
        String[] options = new String[] {"Yes", "No"};
    int response = JOptionPane.showOptionDialog(null, "Do you want delete this account?", "Delete",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    if(response==0){
        deleteAccount();
        dataFromArrayListToComboBoxAccout(stringClient);
    }
   
    }//GEN-LAST:event_buttonDeleteAccActionPerformed

    private void buttonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertActionPerformed
        String value =(String) comboBoxAccount.getSelectedItem();
        String[] parts = value.split(" ");
        String accountID = parts[0];
        Balance insertBalance = new Balance(accountID);
        insertBalance.setVisible(true);
        
    }//GEN-LAST:event_buttonInsertActionPerformed

    private void buttonWindrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWindrawActionPerformed
        String value =(String) comboBoxAccount.getSelectedItem();
        String[] parts = value.split(" ");
        String accountID = parts[0];
        Balance insertBalance = new Balance(accountID);
        insertBalance.setVisible(true);
    }//GEN-LAST:event_buttonWindrawActionPerformed

    private void buttonAddCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCardActionPerformed
        String value =(String) comboBoxAccount.getSelectedItem();
        String[] parts = value.split(" ");
        String accountID = parts[0];
        NewCard card = new NewCard(accountID);
       card.setVisible(true);
    }//GEN-LAST:event_buttonAddCardActionPerformed

    private void buttonDeleteCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteCardActionPerformed
           String[] options = new String[] {"Yes", "No"};
    int response = JOptionPane.showOptionDialog(null, "Do you want delete this account?", "Delete",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    if(response==0){
        deleteAccount();
    }
    }//GEN-LAST:event_buttonDeleteCardActionPerformed

    private void buttonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActiveActionPerformed
        String value =(String) comboBoxCard.getSelectedItem();
        String[] parts = value.split(" ");
        String cardID = parts[0];
        Main.handler.prepareStatement("update cards set active= 'Y' where id = '"+cardID+"'");
        Main.handler.executeUpdate();
    }//GEN-LAST:event_buttonActiveActionPerformed

    private void buttonDeactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeactiveActionPerformed
         String value =(String) comboBoxCard.getSelectedItem();
        String[] parts = value.split(" ");
        String cardID = parts[0];
        Main.handler.prepareStatement("update cards set active= 'N' where id = '"+cardID+"'");
        Main.handler.executeUpdate();
    }//GEN-LAST:event_buttonDeactiveActionPerformed

    private void buttAddLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttAddLoanActionPerformed
       insertLoan();
                
    }//GEN-LAST:event_buttAddLoanActionPerformed

    private void AddNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewClientActionPerformed
        AddNewClient newClient = new AddNewClient();
        newClient.setVisible(true);
    }//GEN-LAST:event_AddNewClientActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dataFromArrayListToComboBoxClient();
    }//GEN-LAST:event_jButton2ActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNewClient;
    private javax.swing.JButton buttAddLoan;
    private javax.swing.JButton buttonActive;
    private javax.swing.JButton buttonAddAcc;
    private javax.swing.JButton buttonAddCard;
    private javax.swing.JButton buttonDeactive;
    private javax.swing.JButton buttonDeleteAcc;
    private javax.swing.JButton buttonDeleteCard;
    private javax.swing.JButton buttonInsert;
    private javax.swing.JButton buttonWindraw;
    private javax.swing.JComboBox<String> comboBoxAccount;
    private javax.swing.JComboBox<String> comboBoxCard;
    private javax.swing.JComboBox<String> comboBoxClient;
    private javax.swing.JLabel detailDate;
    private javax.swing.JLabel detailMail;
    private javax.swing.JLabel detailName;
    private javax.swing.JLabel detailPhone;
    private javax.swing.JLabel detailSurename;
    private javax.swing.JLabel fieldActivate;
    private javax.swing.JLabel fieldBalance;
    private javax.swing.JTextField fieldInterest;
    private javax.swing.JTextField fieldLoan;
    private javax.swing.JTextField fieldPaidup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelName;
    private javax.swing.JTable tableLoans;
    // End of variables declaration//GEN-END:variables
}
