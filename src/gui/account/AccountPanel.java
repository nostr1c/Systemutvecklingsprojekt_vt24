package gui.account;

import java.util.HashMap;
import javax.swing.JOptionPane;
import models.EmployeeModel;
import static shared.Shared.SESSION_AID;
import mysql.*;
import oru.inf.*;
import validators.Validate;

/**
 * JPanel for the account tab
 */
public class AccountPanel extends javax.swing.JPanel {

    private InfDB db;
    
    public AccountPanel() {
        initComponents();
        startup();
    }
    
    /**
    * Initializing application by setting up db connection.
    * only if the instance of db is not null, so that it doesn't crash in design mode.
    */
    private void startup() {
        if (MySQL.getInstance().getDB() != null) {
            db = MySQL.getInstance().getDB();
            refreshFields();
        }
    }
    
    /**
     * Method for refreshing the fields.
     */
    private void refreshFields() {
        // Get info about employee from db.
        HashMap<String,String> employee = Queries.getEmployeeByAid(SESSION_AID);
        
        // Set textfield texts
        tfAddress.setText(employee.get("adress"));
        tfEmail.setText(employee.get("epost"));
        tfFirstName.setText(employee.get("fornamn"));
        tfLastName.setText(employee.get("efternamn"));
        tfPhone.setText(employee.get("telefon"));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel lblAddress = new javax.swing.JLabel();
        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        javax.swing.JLabel lblFirstName = new javax.swing.JLabel();
        javax.swing.JLabel lblLastName = new javax.swing.JLabel();
        javax.swing.JLabel lblPhone = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        tfLastName = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        javax.swing.JLabel lblTitle = new javax.swing.JLabel();

        lblAddress.setText("Adress");

        lblEmail.setText("Epost");

        lblFirstName.setText("Förnamn");

        lblLastName.setText("Efternamn");

        lblPhone.setText("Telefon");

        tfAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfFirstName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfLastName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfPhone.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setText("Återställ");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Personuppgifter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddress)
                            .addComponent(lblPhone)
                            .addComponent(lblEmail)
                            .addComponent(lblLastName)
                            .addComponent(lblFirstName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfFirstName)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTitle))
                .addContainerGap(459, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(lblTitle)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(btnReset))
                .addContainerGap(222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Save button action
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // Check for eny empty fields.
        if(Validate.checkForEmptyValues(tfAddress, tfEmail, tfFirstName, tfLastName, tfPhone)) {
            JOptionPane.showMessageDialog(null, "Inga fält får vara tomma", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get datebase data about employee.
        EmployeeModel dbEmployee = new EmployeeModel(Queries.getEmployeeByAid(SESSION_AID));
        
        // Get new employeemodel about updated data.
        EmployeeModel updatedEmployee = new EmployeeModel(
                dbEmployee.getAid(),
                tfFirstName.getText(),
                tfLastName.getText(),
                tfAddress.getText(),
                tfEmail.getText(),
                tfPhone.getText(),
                dbEmployee.getDate(),
                dbEmployee.getPassword(),
                dbEmployee.getDepartment()
        );
        
        // Creating updating qury.
        String query = QueryBuilder.updateQuery(
            dbEmployee.toHashMap(),
            updatedEmployee.toHashMap(),
            "anstalld", "aid = %s".formatted(SESSION_AID)
        );
        
        // Check if nothing was updated.
        if (query.isBlank()) {
            JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Send confirmation dialog to user.
        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
        
        // Try updating the user.
        try {
            db.update(query);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
            refreshFields();
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Det gick inte att göra några ändringar", "Fel", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    // Reset button action.
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        refreshFields();
    }//GEN-LAST:event_btnResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}
