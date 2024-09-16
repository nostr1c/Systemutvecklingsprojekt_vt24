package gui.admin;

import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.CityModel;
import models.PartnerModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import utils.Components;
import mysql.MySQL;
import mysql.Queries;
import mysql.QueryBuilder;
import validators.Validate;

/**
 * JPanel for the partner tab under the administrator tab
 */
public class AdminPartnerPanel extends javax.swing.JPanel {

    private DefaultListModel<PartnerModel> listModel;
    private InfDB db;
    private PartnerModel selectedPartner;
    
    public AdminPartnerPanel() {
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
            listModel = (DefaultListModel<PartnerModel>) listPartner.getModel();
            Components.setEnabled(false, btnSave);
            setupList();
            fillComboBoxes();
        }
    }
    
    private void setupList() {
        for (HashMap<String,String> partner : Queries.getPartners()) {
            listModel.addElement(new PartnerModel(partner));
        }
    }
    
    private void fillComboBoxes() {
        for (HashMap<String, String> cityRow : Queries.getCities()) {
            cbCity.addItem(new CityModel(cityRow));
        }
    }
        
     private void updateFields() {
        tfName.setText(selectedPartner.getName());
        tfContactEmail.setText(selectedPartner.getContactEmail());
        tfContactPerson1.setText(selectedPartner.getContactName());
        tfIndustry.setText(selectedPartner.getBranch());
        tfAddress.setText(selectedPartner.getAddress());
        tfPhone.setText(selectedPartner.getPhone());
        cbCity.setSelectedItem(new CityModel(Queries.getCityBySid(selectedPartner.getCity())));
    }
    
    private void clear() {
        Components.clearTextFields(tfName, tfContactEmail, tfContactPerson1, tfIndustry, tfName, tfPhone, tfAddress);
        listPartner.clearSelection();
        selectedPartner = null;
        Components.setEnabled(false, btnSave);
        Components.setEnabled(true, btnCreate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listPartner = new javax.swing.JList<>(new DefaultListModel());
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        javax.swing.JLabel lblContactPerson = new javax.swing.JLabel();
        javax.swing.JLabel lblContactEmail = new javax.swing.JLabel();
        javax.swing.JLabel lblPhone = new javax.swing.JLabel();
        javax.swing.JLabel lblAddress = new javax.swing.JLabel();
        javax.swing.JLabel lblIndustry = new javax.swing.JLabel();
        tfPhone = new javax.swing.JTextField();
        tfContactEmail = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfContactPerson1 = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfIndustry = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        javax.swing.JLabel lblCity = new javax.swing.JLabel();
        cbCity = new javax.swing.JComboBox<>();

        listPartner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPartnerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listPartner);

        lblName.setText("Namn");

        lblContactPerson.setText("Kontaktperson");

        lblContactEmail.setText("Kontaktemail");

        lblPhone.setText("Telefon");

        lblAddress.setText("Adress");

        lblIndustry.setText("Bransch");

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setText("Avmarkera");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnCreate.setText("Skapa ny");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRemove.setText("Ta bort");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lblCity.setText("Stad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblName)
                                .addComponent(lblPhone)
                                .addComponent(lblContactPerson)
                                .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(tfPhone)
                                .addComponent(tfContactPerson1)
                                .addComponent(lblCity))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblIndustry)
                                .addComponent(lblAddress)
                                .addComponent(lblContactEmail)
                                .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfIndustry, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cbCity, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIndustry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIndustry)
                        .addGap(28, 28, 28)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblContactPerson)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfContactPerson1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfContactEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblContactEmail))
                .addGap(18, 18, 18)
                .addComponent(lblCity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCreate)
                    .addComponent(btnRemove)
                    .addComponent(btnClear))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfContactEmail, tfAddress, tfContactPerson1, tfIndustry, tfPhone)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, Kontaktnamn, KontaktEmail, Adress, Branch, Telefon
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfContactEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        CityModel city = (CityModel) cbCity.getSelectedItem();
        
        PartnerModel updatedPartner = new PartnerModel(
            selectedPartner.getPid(),
            tfName.getText(),
            tfContactPerson1.getText(),
            tfContactEmail.getText(),
            tfPhone.getText(),
            tfAddress.getText(),
            tfIndustry.getText(),
            city.getSid()
        );
        
        try {
            String updateQuery = QueryBuilder.updateQuery(
                    selectedPartner.toHashMap(),
                    updatedPartner.toHashMap(),
                    "partner",
                    "pid = %s".formatted(selectedPartner.getPid()));

            if (updateQuery.isBlank()) {
                JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
            
            db.update(updateQuery);
            selectedPartner = new PartnerModel(updatedPartner.toHashMap());
            Components.updateListEntry(listPartner, listModel, updatedPartner);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
             
        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfContactEmail, tfAddress, tfContactPerson1, tfIndustry, tfPhone)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, Kontaktnamn, KontaktEmail, Adress, Branch, Telefon
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfContactEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        CityModel city = (CityModel) cbCity.getSelectedItem();
        
        try {
            PartnerModel newPartner = new PartnerModel(
                db.getAutoIncrement("partner", "pid"),
                tfName.getText(),
                tfContactPerson1.getText(),
                tfContactEmail.getText(),
                tfPhone.getText(),
                tfAddress.getText(),
                tfIndustry.getText(),
                city.getSid()
            );
            
            Queries.addPartner(newPartner);
            listModel.addElement(newPartner);
            listPartner.setSelectedIndex(-1);
            selectedPartner = null;
            clear();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
            String query1 = "DELETE FROM projekt_partner WHERE partner_pid = %s".formatted(selectedPartner.getPid());
            String query2 = "DELETE FROM partner WHERE pid = %s".formatted(selectedPartner.getPid());
            Queries.doMultiple(query1, query2);
            Components.removeListEntry(listPartner, listModel);
            listPartner.clearSelection();
            selectedPartner = null;
            clear();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void listPartnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPartnerMouseClicked
        selectedPartner = listPartner.getSelectedValue();
        btnSave.setEnabled(true);
        btnCreate.setEnabled(false);
        updateFields();
    }//GEN-LAST:event_listPartnerMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<CityModel> cbCity;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<PartnerModel> listPartner;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfContactEmail;
    private javax.swing.JTextField tfContactPerson1;
    private javax.swing.JTextField tfIndustry;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}
