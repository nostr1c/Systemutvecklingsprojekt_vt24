package gui.admin;

import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.CityModel;
import models.DepartmentModel;
import models.EmployeeModel;
import oru.inf.*;
import utils.Components;
import mysql.*;
import validators.Validate;

/**
 * JPanel for the department tab under the administrator tab
 */
public class AdminDepartmentPanel extends javax.swing.JPanel {

    private DefaultListModel<DepartmentModel> listModel;
    private DepartmentModel selectedDepartment;
    private InfDB db;
    
    public AdminDepartmentPanel() {
        initComponents();
        startup();
    }
    
    /**
    * Initializing application by setting up db connection.
    * only if the instance of db is not null, so that it doesn't crash in design mode.
    */
    public void startup() {
        if (MySQL.getInstance().getDB() != null) {
            db = MySQL.getInstance().getDB();
            listModel = (DefaultListModel<DepartmentModel>) listDepartment.getModel();
            populateList();
            fillComboBoxes();
        }
    }
    
    // Populate department list with items.
    private void populateList() {
        for (HashMap<String,String> row : Queries.getDepartments()) {
            listModel.addElement(new DepartmentModel(row));
        }   
    }
    
    // Refresh input fields.
    private void refreshFields() {
        tfName.setText(selectedDepartment.getName());
        taDescription.setText(selectedDepartment.getDescription());
        tfAddress.setText(selectedDepartment.getAddress());
        tfEmail.setText(selectedDepartment.getEmail());
        tfPhone.setText(selectedDepartment.getPhone());
        cbCity.setSelectedItem(new CityModel(Queries.getCityBySid(selectedDepartment.getCity())));
        cbManager.setSelectedItem(new EmployeeModel(Queries.getEmployeeByAid(selectedDepartment.getManager())));
    }
    
    // Fills comboboxes with items.
    private void fillComboBoxes() {
        for (HashMap<String, String> managerRow : Queries.getManagers()) {
            cbManager.addItem(new EmployeeModel(managerRow));
        }

        for (HashMap<String, String> cityRow : Queries.getCities()) {
            cbCity.addItem(new CityModel(cityRow));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfName = new javax.swing.JTextField();
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        javax.swing.JLabel lblManager = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        tfPhone = new javax.swing.JTextField();
        javax.swing.JLabel lblPhone = new javax.swing.JLabel();
        javax.swing.JLabel lblCity = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        javax.swing.JLabel lblAddress = new javax.swing.JLabel();
        spDescription = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        btnUnselect = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        spDepartment = new javax.swing.JScrollPane();
        listDepartment = new javax.swing.JList<>(new DefaultListModel());
        cbManager = new javax.swing.JComboBox<>();
        cbCity = new javax.swing.JComboBox<>();

        tfName.setColumns(16);
        tfName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblName.setText("Namn");

        lblDescription.setText("Beskrivning");

        lblManager.setText("Avdelningschef");

        tfEmail.setColumns(16);
        tfEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblEmail.setText("Epost");

        tfPhone.setColumns(16);
        tfPhone.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblPhone.setText("Telefonnummer");

        lblCity.setText("Stad");

        tfAddress.setColumns(16);
        tfAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblAddress.setText("Adress");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        spDescription.setViewportView(taDescription);

        btnUnselect.setText("Avmarkera");
        btnUnselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnselectActionPerformed(evt);
            }
        });

        btnChange.setText("Ändra");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnCreate.setText("Skapa ny");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        listDepartment.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDepartmentMouseClicked(evt);
            }
        });
        spDepartment.setViewportView(listDepartment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblCity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblManager, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUnselect)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(spDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spDescription))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblCity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChange)
                    .addComponent(btnCreate)
                    .addComponent(btnUnselect))
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spDepartment)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Unselect fields button event.
    private void btnUnselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnselectActionPerformed
        Components.clearTextFields(tfName, tfAddress, tfEmail, tfPhone, taDescription);
        listDepartment.clearSelection();
        selectedDepartment = null;
        btnChange.setEnabled(false);
        btnCreate.setEnabled(true);
    }//GEN-LAST:event_btnUnselectActionPerformed
    
    // Create department button event.
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfAddress, tfEmail, tfPhone, taDescription)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, adress, epost, telefonnummer, beskrivning
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            EmployeeModel manager = (EmployeeModel) cbManager.getSelectedItem();
            CityModel city = (CityModel) cbCity.getSelectedItem();
            
            DepartmentModel newDepartment = new DepartmentModel(
                db.getAutoIncrement("avdelning", "avdid"),
                tfName.getText(),
                taDescription.getText(),
                tfAddress.getText(),
                tfEmail.getText(),
                tfPhone.getText(),
                city.getSid(),
                manager.getAid()
            );
            
            Queries.addDepartment(newDepartment);
            listModel.addElement(newDepartment);

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till avdelningen", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    
    // Change department button event.
    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        if (Validate.checkForEmptyValues(tfName, tfAddress, tfEmail, tfPhone, taDescription)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, adress, epost, telefonnummer, beskrivning
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        EmployeeModel manager = (EmployeeModel) cbManager.getSelectedItem();
        CityModel city = (CityModel) cbCity.getSelectedItem();
        
        DepartmentModel updatedDepartment = new DepartmentModel(
                selectedDepartment.getAvdid(),
                tfName.getText(),
                taDescription.getText(),
                tfAddress.getText(),
                tfEmail.getText(),
                tfPhone.getText(),
                city.getSid(),
                manager.getAid()
        );
        
        try {
            String updateQuery = QueryBuilder.updateQuery(
                    selectedDepartment.toHashMap(),
                    updatedDepartment.toHashMap(),
                    "avdelning",
                    "avdid = %s".formatted(selectedDepartment.getAvdid()));

            if (updateQuery.isBlank()) {
                JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0){   
                return;
            }
            
            db.update(updateQuery);
            selectedDepartment = new DepartmentModel(updatedDepartment.toHashMap());
            Components.updateListEntry(listDepartment, listModel, updatedDepartment);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
             
        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnChangeActionPerformed
    
    // Depertment list mouseclicked event.
    private void listDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDepartmentMouseClicked
        selectedDepartment = listDepartment.getSelectedValue();
        Components.setEnabled(true, btnChange);
        Components.setEnabled(false, btnCreate);
        refreshFields();
    }//GEN-LAST:event_listDepartmentMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnUnselect;
    private javax.swing.JComboBox<CityModel> cbCity;
    private javax.swing.JComboBox<EmployeeModel> cbManager;
    private javax.swing.JList<DepartmentModel> listDepartment;
    private javax.swing.JScrollPane spDepartment;
    private javax.swing.JScrollPane spDescription;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}
