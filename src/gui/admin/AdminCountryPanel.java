package gui.admin;

import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.CountryModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import mysql.MySQL;
import mysql.Queries;
import utils.Components;
import mysql.QueryBuilder;
import validators.Validate;

/**
 * JPanel for the country tab under the administrator tab
 */
public class AdminCountryPanel extends javax.swing.JPanel {
    
    private DefaultListModel<CountryModel> listModel;
    private InfDB db;
    private CountryModel selectedCountry;

    public AdminCountryPanel() {
        initComponents();
        startup();
    }
    
    /**
    * Initializing application by setting up db connection.
    * only if the instance of db is not null, so that it doesn't crash in design mode.
    */
    private void startup()  {
        if (MySQL.getInstance().getDB() != null) {
            db = MySQL.getInstance().getDB();
            Components.setEnabled(false, btnSave);
            listModel = (DefaultListModel<CountryModel>) listCountry.getModel();
            populateList();
            Validate.addDoubleValidatorToField(tfCurrency);
        }
    }
    
    // Populate country list with items.
    private void populateList() {
        for (HashMap<String,String> row : Queries.getCountries()) {
            listModel.addElement(new CountryModel(row));
        }  
    }
    
    // Update input fields.
    private void updateFields() {
        tfName.setText(selectedCountry.getName());
        tfLanguage.setText(selectedCountry.getLanguage());
        tfCurrency.setText(selectedCountry.getCurrency());
        tfTimezone.setText(selectedCountry.getTimezone());
        tfPolitics.setText(selectedCountry.getPolitics());
        tfEconomy.setText(selectedCountry.getEconomy());
    }
    
    // Clear fields and reset.
    private void clear() {
        Components.clearTextFields(tfName, tfLanguage, tfCurrency, tfTimezone, tfPolitics, tfEconomy);
        listCountry.clearSelection();
        selectedCountry = null;
        btnSave.setEnabled(false);
        btnCreate.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listCountry = new javax.swing.JList<>(new DefaultListModel());
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        javax.swing.JLabel lblLanguage = new javax.swing.JLabel();
        javax.swing.JLabel lblCurrency = new javax.swing.JLabel();
        javax.swing.JLabel lblTimezone = new javax.swing.JLabel();
        javax.swing.JLabel lblPolitics = new javax.swing.JLabel();
        javax.swing.JLabel lblEconomy = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfLanguage = new javax.swing.JTextField();
        tfCurrency = new javax.swing.JTextField();
        tfTimezone = new javax.swing.JTextField();
        tfPolitics = new javax.swing.JTextField();
        tfEconomy = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();

        listCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listCountryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listCountry);

        lblName.setText("Namn");

        lblLanguage.setText("Språk");

        lblCurrency.setText("Valuta");

        lblTimezone.setText("Tidszon");

        lblPolitics.setText("Politisk struktur");

        lblEconomy.setText("Ekonomi");

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRemove.setText("Ta bort");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClear.setText("Avmarkera");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnCreate.setText("Skapa");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName)
                    .addComponent(lblCurrency)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfPolitics, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(tfCurrency, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lblPolitics)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEconomy)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnRemove))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfEconomy, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLanguage, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLanguage, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimezone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTimezone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 252, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(lblLanguage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurrency)
                    .addComponent(lblTimezone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTimezone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPolitics)
                    .addComponent(lblEconomy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPolitics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEconomy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnRemove)
                    .addComponent(btnClear)
                    .addComponent(btnCreate))
                .addContainerGap(342, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Country list mouse click event.
    private void listCountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCountryMouseClicked
        selectedCountry = listCountry.getSelectedValue();
        Components.setEnabled(true, btnSave);
        Components.setEnabled(false, btnCreate);
        updateFields();
    }//GEN-LAST:event_listCountryMouseClicked

    // Clear fields button event.
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    // Button remove click event.
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            String query1 = "UPDATE stad SET land = null where land = %s".formatted(selectedCountry.getLid());
            String query2 = "UPDATE projekt SET land = null where land = %s".formatted(selectedCountry.getLid());
            String query3 = "DELETE FROM land WHERE lid = %s".formatted(selectedCountry.getLid());
            
            Queries.doMultiple(query1, query2, query3);
            Components.removeListEntry(listCountry, listModel);
            listCountry.clearSelection();
            selectedCountry = null;
            clear();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    // Button create click event.
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfLanguage, tfCurrency, tfEconomy, tfPolitics, tfTimezone)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, Språk, Valuta, Ekonomi, Politik, Tidzon
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            CountryModel newCountry = new CountryModel(
                db.getAutoIncrement("land", "lid"),
                tfName.getText(),
                tfLanguage.getText(),
                tfCurrency.getText(),
                tfTimezone.getText(),
                tfPolitics.getText(),
                tfEconomy.getText()
            );
            
            Queries.addCountry(newCountry);
            listModel.addElement(newCountry);
            listCountry.setSelectedIndex(-1);
            selectedCountry = null;
            clear();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    // Button save click event.
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfLanguage, tfCurrency, tfEconomy, tfPolitics, tfTimezone)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, Språk, Valuta, Ekonomi, Politik, Tidzon
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        CountryModel updatedCountry = new CountryModel(
            selectedCountry.getLid(),
            tfName.getText(),
            tfLanguage.getText(),
            tfCurrency.getText(),
            tfTimezone.getText(),
            tfPolitics.getText(),
            tfEconomy.getText()
        );
        
        try {
            String updateQuery = QueryBuilder.updateQuery(
                    selectedCountry.toHashMap(),
                    updatedCountry.toHashMap(),
                    "land",
                    "lid = %s".formatted(selectedCountry.getLid()));

            if (updateQuery.isBlank()) {
                JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0){   
                return;
            }
            
            db.update(updateQuery);
            selectedCountry = new CountryModel(updatedCountry.toHashMap());
            Components.updateListEntry(listCountry, listModel, updatedCountry);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
             
        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<CountryModel> listCountry;
    private javax.swing.JTextField tfCurrency;
    private javax.swing.JTextField tfEconomy;
    private javax.swing.JTextField tfLanguage;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPolitics;
    private javax.swing.JTextField tfTimezone;
    // End of variables declaration//GEN-END:variables
}
