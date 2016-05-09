/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka_database.gui;

import banka_database.Main;
import javax.swing.JOptionPane;


public class NewCard extends javax.swing.JFrame {
    private String accountID;
    private String state;
    public NewCard(String accountID) {
        initComponents();
        this.accountID = accountID;
    }
    
    public void addNewCard(){
      
      if(radioActive.isSelected() || radioInactive.isSelected()){
             if(radioActive.isSelected()){
                 state= "Y";
             }
             if(radioInactive.isSelected()){
                 state = "N";
             }

             Main.handler.prepareStatement("INSERT into cards(active,accountID) VALUES(?, ?)");
             Main.handler.updateStatement(state,accountID);
             Main.handler.executeUpdate();
             JOptionPane.showMessageDialog(null, "Account has been added");
             this.dispose();
      }
      else {
          JOptionPane.showMessageDialog(null, "Select active or inactive");
      }
       
      
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        radioActive = new javax.swing.JCheckBox();
        radioInactive = new javax.swing.JCheckBox();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Card");

        buttonGroup1.add(radioActive);
        radioActive.setText("Active");

        buttonGroup1.add(radioInactive);
        radioInactive.setText("Inactive");

        buttonSave.setText("Save");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioInactive)
                    .addComponent(radioActive)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSave)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(radioActive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioInactive)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave)
                    .addComponent(buttonCancel))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        addNewCard();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
       this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox radioActive;
    private javax.swing.JCheckBox radioInactive;
    // End of variables declaration//GEN-END:variables
}
