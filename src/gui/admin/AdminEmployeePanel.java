package gui.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.DepartmentModel;
import models.EmployeeModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import utils.Components;
import mysql.MySQL;
import mysql.Queries;
import mysql.QueryBuilder;
import utils.Utils; 
import validators.Validate;

/**
 * JPanel for the employee tab under the administrator tab
 */
public class AdminEmployeePanel extends javax.swing.JPanel {

    private DefaultListModel<EmployeeModel> listModel;
    private EmployeeModel selectedEmployee;
    private InfDB db;
    private DateTimeFormatter formatter;
    
    public AdminEmployeePanel() {
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
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Components.setEnabled(false, btnChange, btnDelete, tfPassword, tfAid);
                        
            populateList();
            fillComboBoxes();
        }
    }
    
    /**
     * Method for clearing and resetting fields, buttons and variables.
     */
    private void unselect() {
        Components.clearTextFields(tfFirstName, tfLastName, tfEmail, tfPhone, tfAddress, tfAid, tfPassword, tfResponsibility);
        Components.clearComboBoxes(cbDepartment, cbMentor);
        Components.clearDatePickers(dpDate);
        Components.setEnabled(true, cbMentor, btnCreate);
        Components.setEnabled(false, btnChange, btnDelete, tfAid, tfPassword);
        listEmployee.clearSelection();
        selectedEmployee = null;
    }
    
    /**
     * Method for populating the employee list from the database.
     */
    private void populateList() {
        // Gets the list model and casts it to variable.
        listModel = (DefaultListModel<EmployeeModel>) listEmployee.getModel();
        
        String query = """
                        SELECT
                           anstalld.aid,
                           anstalld.fornamn,
                           anstalld.efternamn,
                           handlaggare.mentor,
                           avdelning
                       FROM anstalld
                       LEFT JOIN handlaggare
                        ON anstalld.aid = handlaggare.aid
                       LEFT JOIN admin 
                        ON anstalld.aid = admin.aid;
                       """;

        try {
            ArrayList<HashMap<String,String>> employees = db.fetchRows(query);
            
            //Loop through db result and add new EmployeeModel to the listModel.
            for (HashMap<String,String> employee : employees) {
                listModel.addElement(new EmployeeModel(employee));
            }
            
        } catch (InfException ignored) {}    
    }
    
    // Method for refreshing selected employee object.
    private void refreshSelectedEmployee() {
        HashMap<String, String> row = Queries.getAllEmployeeInformationByAid(selectedEmployee.getAid());

        if (row != null) {
            selectedEmployee = new EmployeeModel(row);
            listEmployee.setSelectedValue(selectedEmployee, true);
        }
    }
    
    /**
     * Method for refreshing the fields with the new selected employee.
     */
    private void refreshFields() {
        refreshSelectedEmployee();
        tfAid.setText(selectedEmployee.getAid());
        tfFirstName.setText(selectedEmployee.getFirstName());
        tfLastName.setText(selectedEmployee.getLastName());
        tfEmail.setText(selectedEmployee.getEmail());
        tfPhone.setText(selectedEmployee.getPhone());
        tfAddress.setText(selectedEmployee.getAddress());
        tfPassword.setText(selectedEmployee.getPassword());
        tfResponsibility.setText(Validate.changeToEmptyStringIfNull(selectedEmployee.getResponsibility()));
        dpDate.setDate(LocalDate.parse(selectedEmployee.getDate(), formatter));
        cbDepartment.setSelectedItem(new DepartmentModel(Queries.getDepartmentByAvdid(selectedEmployee.getDepartment())));
        
        /**
         * Checking if employee does not have a mentor, selects index 0 ("Ingen") in combobox.
         * If mentor exists, select the combobox item related to that mentor.
         */ 
        if (selectedEmployee.getMentor() == null) {
            cbMentor.setSelectedIndex(0);
        } else {
            cbMentor.setSelectedItem(new EmployeeModel(Queries.getEmployeeByAid(selectedEmployee.getMentor())));
        }
        
        
        /**
         * Check role for selected employee, sets the combobox to according role.
         * Also handles the fields enabled state for each role.
         */
        switch (Queries.getRoleByAid(selectedEmployee.getAid())) {
            case "Handläggare":
                cbRole.setSelectedIndex(0);
                Components.setEnabled(true, tfResponsibility, cbMentor);
                break;
            case "Admin":
                cbRole.setSelectedIndex(1);
                Components.setEnabled(false, tfResponsibility, cbMentor);
                break;
        }
        
    }
    
    /**
     * Fills the comboboxes with data from database.
     * Loops through departments and managers, gotten from database, 
     * and adds it (as a new instance of its model) to the combobox.
     */
    private void fillComboBoxes() {
        for (HashMap<String, String> departmentRow : Queries.getDepartments()) {
            cbDepartment.addItem(new DepartmentModel(departmentRow));
        }
        // Add a new combobox item, empty ("Ingen") at index 0.
        cbMentor.addItem(new EmployeeModel());
        
        for (HashMap<String, String> managerRow : Queries.getManagers()) {
            cbMentor.addItem(new EmployeeModel(managerRow));    
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChange = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        spEmployee = new javax.swing.JScrollPane();
        listEmployee = new javax.swing.JList<>(new DefaultListModel());
        tfAddress = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfAid = new javax.swing.JTextField();
        tfLastName = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        tfResponsibility = new javax.swing.JTextField();
        tfPassword = new javax.swing.JTextField();
        javax.swing.JLabel lblDate = new javax.swing.JLabel();
        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        javax.swing.JLabel lblAddress = new javax.swing.JLabel();
        javax.swing.JLabel lblPassword = new javax.swing.JLabel();
        javax.swing.JLabel lblLastName = new javax.swing.JLabel();
        javax.swing.JLabel lblFirstName = new javax.swing.JLabel();
        javax.swing.JLabel lblMentor = new javax.swing.JLabel();
        javax.swing.JLabel lblDepartment = new javax.swing.JLabel();
        javax.swing.JLabel lblPhone = new javax.swing.JLabel();
        javax.swing.JLabel lblAid = new javax.swing.JLabel();
        javax.swing.JLabel lblResponsibility = new javax.swing.JLabel();
        javax.swing.JLabel lblRole = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        cbMentor = new javax.swing.JComboBox<>();
        cbDepartment = new javax.swing.JComboBox<>();
        dpDate = new com.github.lgooddatepicker.components.DatePicker();

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

        btnClear.setText("Avmarkera");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnDelete.setText("Ta bort");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        listEmployee.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listEmployeeMouseClicked(evt);
            }
        });
        spEmployee.setViewportView(listEmployee);

        tfAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfFirstName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfAid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfAid.setEnabled(false);

        tfLastName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfPhone.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfResponsibility.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tfPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblDate.setText("Anställningsdatum");

        lblEmail.setText("Epost");

        lblAddress.setText("Adress");

        lblPassword.setText("Lösenord");

        lblLastName.setText("Efternamn");

        lblFirstName.setText("Förnamn");

        lblMentor.setText("Mentor");

        lblDepartment.setText("Avdelning");

        lblPhone.setText("Telefonnummer");

        lblAid.setText("AnställningsId");

        lblResponsibility.setText("Ansvarsområde");

        lblRole.setText("Roll");

        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Handläggare", "Admin" }));
        cbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfLastName)
                            .addComponent(lblLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfPhone)
                            .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblResponsibility, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMentor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfAid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbMentor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfResponsibility, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spEmployee)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblFirstName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblLastName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResponsibility)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfResponsibility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMentor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMentor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblDepartment)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRole)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete)
                        .addComponent(btnClear))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChange)
                        .addComponent(btnCreate)))
                .addContainerGap(281, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        unselect();
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * Create button click event, and its actions.
     */
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // Checks for empty values, if so open dialog box and show info, then return.
        if (Validate.checkForEmptyValues(tfFirstName, tfLastName, tfEmail, tfPhone, tfAddress) || dpDate.getDate() == null) {
            String message = """
                             Följande fält får inte vara tomma:
                             Förnamn, efternamn, adress, epost, telefonnummer, anställningsdatum
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        /**
         * Get selected values (objects)from comboboxes.
         * Get date from datepicker, also format it using our formatter.
        */
        DepartmentModel department = (DepartmentModel) cbDepartment.getSelectedItem();
        EmployeeModel mentor = (EmployeeModel) cbMentor.getSelectedItem();
        String date = dpDate.getDate().format(formatter);
                
        try {
            
            // Create the new employee model from fields.
            EmployeeModel newEmployee = new EmployeeModel(
                db.getAutoIncrement("anstalld", "aid"),
                tfFirstName.getText(),
                tfLastName.getText(),
                tfAddress.getText(),
                tfEmail.getText(),
                tfPhone.getText(),
                date,
                Utils.generatePassword(11),
                department.getAvdid()
            );
            
            // Execute our generic employee sql insertion.
            
            Queries.addEmployee(newEmployee);
            
            /**
             * Check which role is being selected, 
             * execute its owns queries based on the result.
            */
            switch (cbRole.getSelectedItem().toString()) {
                case "Handläggare":
                    
                    // Set managers fields to instance.
                    newEmployee.setMentor(mentor.getAid());
                    newEmployee.setResponsibility(tfResponsibility.getText());
                    
                    Queries.addManager(newEmployee);
                    break;
                case "Admin":
                    Queries.addAdmin(newEmployee);
                    break;
            }

            /**
             * If no exception was thrown from queries methods,
             * add the newly created object to the employee list,
             * Set last index as selected and update selectedEmployee,
             * also refresh fields.
             */
            listModel.addElement(newEmployee);
            listEmployee.setSelectedValue(listModel.lastElement(), true);
            selectedEmployee = listEmployee.getSelectedValue();
            refreshFields();

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till projeketet", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    // Change button action.
    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        if (selectedEmployee == null) return;
        
        if (Validate.checkForEmptyValues(tfFirstName, tfLastName, tfEmail, tfPhone, tfAddress) || dpDate.getDate() == null) {
            String message = """
                             Följande fält får inte vara tomma:
                             Förnamn, efternamn, adress, epost, telefonnummer, anställningsdatum, Ansvarsområde
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!Validate.checkEmailFormat(tfEmail.getText())) {
            
            JOptionPane.showMessageDialog(null,"Fel epostformat! T.ex. \"exempel@mail.com\"", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DepartmentModel department = (DepartmentModel) cbDepartment.getSelectedItem();
        EmployeeModel mentor = (EmployeeModel) cbMentor.getSelectedItem();
        String date = dpDate.getDate().format(formatter);

        EmployeeModel updatedEmployee = new EmployeeModel(
            selectedEmployee.getAid(),
            tfFirstName.getText(),
            tfLastName.getText(),
            tfAddress.getText(),
            tfEmail.getText(),
            tfPhone.getText(),
            date,
            tfPassword.getText(),
            department.getAvdid()
        );
        
        // Use querybuilder to create a update query.
        String updateQuery = QueryBuilder.updateQuery(
                selectedEmployee.toHashMap(),
                updatedEmployee.toHashMap(),
                "anstalld",
                "aid = %s".formatted(selectedEmployee.getAid()));
        
        // Send confirmation dialog, if no then return. Otherwise continue.
        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
        
        try {
            // Get the selected employees current role.
            String currentRole = Queries.getRoleByAid(selectedEmployee.getAid());
            
            // Get the new role, that shall be applied.
            String newRole = cbRole.getSelectedItem().toString();
            boolean updated = false;
            
            // Check the current role of selected employee.
            switch (currentRole) {
                case "Handläggare":
                    
                    // Set manager fields to updatedEmployee model.
                    updatedEmployee.setMentor(mentor.getAid());
                    updatedEmployee.setResponsibility(tfResponsibility.getText());
                    
                    /**
                     * Check if responsibility is not empty AND also check if it has changed at all.
                     */
                    if (!tfResponsibility.getText().isBlank() && Validate.hasChanged(updatedEmployee.getResponsibility(), selectedEmployee.getResponsibility())) {
                        String respQuery = "UPDATE handlaggare SET ansvarighetsomrade = '%s' WHERE aid = %s".formatted(updatedEmployee.getResponsibility(), selectedEmployee.getAid());
                        db.update(respQuery);
                        updated = true;
                    }
                    
                    String oldMentor = "";
                    String newMentor = "";

                    // Set oldMentor only if it is not null.
                    if (selectedEmployee.getMentor() != null) {
                        oldMentor = selectedEmployee.getMentor();
                    }
                    
                    // Set newMentor only if it is not empty.
                    if (!updatedEmployee.getMentor().isBlank()) {
                        newMentor = updatedEmployee.getMentor();
                    }
                    
                    // Check if mentor has changed.
                    if (Validate.hasChanged(oldMentor, newMentor)) {
                        String mentQuery = "";
                        
                        /** If mentor is empty, ("Ingen" is selected) then set it to null,
                         * else set it to the regarding aid..
                         */
                        if (updatedEmployee.getMentor().isBlank()) {
                            mentQuery = "UPDATE handlaggare SET mentor = null WHERE aid = %s".formatted(selectedEmployee.getAid());
                        } else {
                            mentQuery = "UPDATE handlaggare SET mentor = %s WHERE aid = %s".formatted(updatedEmployee.getMentor(), selectedEmployee.getAid());
                        }
                        db.update(mentQuery);
                        updated = true;
                    }
                    
                    // Check if role has changed.
                    if (Validate.hasChanged(currentRole, newRole)) {
                        String string1 = "UPDATE handlaggare set mentor = null where mentor = %s".formatted(selectedEmployee.getAid());
                        String string2 = "DELETE FROM handlaggare WHERE aid = %s".formatted(selectedEmployee.getAid());
                        String string3 = "INSERT INTO admin values (%s, 1)".formatted(selectedEmployee.getAid());
                        Queries.doMultiple(string1, string2, string3);
                        updated = true;
                    }
                    break;
                    
                case "Admin":
                    
                    // Check if role has changed.
                    if (Validate.hasChanged(currentRole, newRole)) {
                        String string1 = "DELETE FROM admin WHERE aid = %s".formatted(selectedEmployee.getAid());
                        String string2 = "";
                        // If mentor is not null, set regarding mentor, else set null in database.
                        if (mentor.getAid() != null) {
                            string2 = "INSERT INTO handlaggare values (%s, '%s', %s)".formatted(selectedEmployee.getAid(), tfResponsibility.getText(), mentor.getAid());
                        } else {
                            string2 = "INSERT INTO handlaggare values (%s, '%s', null)".formatted(selectedEmployee.getAid(), tfResponsibility.getText());
                        }
                        Queries.doMultiple(string1, string2);
                        updated = true;
                    }
                    break;
            }
            
            /**
             * Check if querybuilder returned an empty string, if so, nothing has changed, do nothing
             * Else perform query and also update the listentry.
            */
            if (!updateQuery.isBlank()) {
                db.update(updateQuery);
                updated = true;

                Components.updateListEntry(
                    listEmployee,
                    listModel,
                    new EmployeeModel(updatedEmployee.toHashMap())
                );
            }
            
            // If anything has changed, and no exception is thrown, or if not, show correct message dialog.
            if (updated) {
                JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
                selectedEmployee = new EmployeeModel(updatedEmployee.toHashMap());
                refreshFields();
            } else {
                JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnChangeActionPerformed
    // Delete employee button action.
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(selectedEmployee == null) return;
        
        int option = JOptionPane.showConfirmDialog(null,"Är du säker på att du vill ta bort användaren?", "Bekräftelse", JOptionPane.YES_NO_OPTION);
        if(option == 0) {
            String query1 = "UPDATE projekt SET projekt.projektchef = NULL WHERE projekt.projektchef = %s".formatted(selectedEmployee.getAid());
            String query2 = "DELETE FROM ans_proj WHERE ans_proj.aid = %s".formatted(selectedEmployee.getAid());
            String query3 = "DELETE FROM admin WHERE admin.aid = %s".formatted(selectedEmployee.getAid());
            String query4 = "UPDATE handlaggare SET handlaggare.mentor = NULL WHERE handlaggare.mentor = %s".formatted(selectedEmployee.getAid());
            String query5 = "UPDATE avdelning SET avdelning.chef = NULL WHERE avdelning.chef = %s".formatted(selectedEmployee.getAid());
            String query6 = "DELETE FROM handlaggare WHERE handlaggare.aid = %s".formatted(selectedEmployee.getAid());
            String query7 = "DELETE FROM anstalld WHERE anstalld.aid = %s".formatted(selectedEmployee.getAid());
            
            try {
                Queries.doMultiple(query1, query2, query3, query4, query5, query6, query7);
                Components.removeListEntry(listEmployee, listModel);
                unselect();
                
            } catch(InfException ignored) {}
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    // List click event actions.
    private void listEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEmployeeMouseClicked
        selectedEmployee = listEmployee.getSelectedValue();
        Components.setEnabled(true, btnChange, btnDelete, tfAid, tfPassword);
        Components.setEnabled(false, btnCreate);
        refreshFields();
    }//GEN-LAST:event_listEmployeeMouseClicked

    // Combobox action performed (mouse clicked on something in the list).
    private void cbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoleActionPerformed
            switch (cbRole.getSelectedItem().toString()) {
            case "Handläggare":
                Components.setEnabled(true, tfResponsibility, cbMentor);
                break;
            case "Admin":
                Components.setEnabled(false, tfResponsibility, cbMentor);
                break;
        }
    }//GEN-LAST:event_cbRoleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox<DepartmentModel> cbDepartment;
    private javax.swing.JComboBox<EmployeeModel> cbMentor;
    private javax.swing.JComboBox<String> cbRole;
    private com.github.lgooddatepicker.components.DatePicker dpDate;
    private javax.swing.JList<EmployeeModel> listEmployee;
    private javax.swing.JScrollPane spEmployee;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfAid;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfPassword;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfResponsibility;
    // End of variables declaration//GEN-END:variables
}
