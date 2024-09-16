package gui.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.CountryModel;
import models.EmployeeModel;
import models.ProjectModel;
import mysql.*;
import oru.inf.*;
import utils.Components;
import validators.Validate;

/**
 * JPanel for the project tab under the administrator tab
 */
public class AdminProjectPanel extends javax.swing.JPanel {

    private DefaultListModel<ProjectModel> listModel;
    private ProjectModel selectedProject;
    private InfDB db;
    private DateTimeFormatter formatter;
    
    public AdminProjectPanel() {
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
            listModel = (DefaultListModel<ProjectModel>) listProject.getModel();
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            Validate.addDoubleValidatorToField(tfCost);
            populateList();
            fillComboBoxes();
            
            btnChange.setEnabled(false);
            btnRemove.setEnabled(false);
        }
    }
    
    private void fillComboBoxes() {
        String countryQuery = "SELECT lid, namn FROM land";
        String employeeQuery = "SELECT aid, fornamn, efternamn FROM anstalld";
        
        try {
            for (HashMap<String, String> countryRow : db.fetchRows(countryQuery)) {
                cbCountry.addItem(new CountryModel(countryRow));
            }
            
            for (HashMap<String, String> employeeRow : db.fetchRows(employeeQuery)) {
                cbManager.addItem(new EmployeeModel(employeeRow));
            }
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void populateList() {
        for (HashMap<String,String> row : Queries.getProjects()) {
            listModel.addElement(new ProjectModel(row));
        }
    }
    
    private void refreshFields() {
        tfName.setText(selectedProject.getName());
        taDescription.setText(selectedProject.getDescription());
        dpStart.setDate(LocalDate.parse(selectedProject.getStartDate(), formatter));
        dpEnd.setDate(LocalDate.parse(selectedProject.getEndDate(), formatter));
        tfCost.setText(selectedProject.getCost());
        cbStatus.setSelectedItem(selectedProject.getStatus());
        cbPriority.setSelectedItem(selectedProject.getPriority());
        cbManager.setSelectedItem(new EmployeeModel(Queries.getEmployeeByAid(selectedProject.getManager())));
        cbCountry.setSelectedItem(new CountryModel(Queries.getCountryByLid(selectedProject.getCountry())));
    }
    
    private void unselect() {
        Components.clearTextFields(tfName, taDescription, tfCost);
        Components.clearComboBoxes(cbStatus, cbPriority, cbManager, cbCountry);
        Components.clearDatePickers(dpStart, dpEnd);
        Components.setEnabled(true, btnCreate);
        Components.setEnabled(false, btnChange, btnRemove);
        listProject.clearSelection();
        selectedProject = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChange = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnUnselect = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        spProject = new javax.swing.JScrollPane();
        listProject = new javax.swing.JList<>(new DefaultListModel());
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        javax.swing.JLabel lblStartDate = new javax.swing.JLabel();
        javax.swing.JLabel lblEndDate = new javax.swing.JLabel();
        spDescription = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        javax.swing.JLabel lblStatus = new javax.swing.JLabel();
        javax.swing.JLabel lblPriority = new javax.swing.JLabel();
        javax.swing.JLabel lblManager = new javax.swing.JLabel();
        javax.swing.JLabel lblCountry = new javax.swing.JLabel();
        javax.swing.JLabel lblCost = new javax.swing.JLabel();
        tfCost = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();
        cbPriority = new javax.swing.JComboBox<>();
        cbManager = new javax.swing.JComboBox<>();
        cbCountry = new javax.swing.JComboBox<>();
        dpEnd = new com.github.lgooddatepicker.components.DatePicker();
        dpStart = new com.github.lgooddatepicker.components.DatePicker();

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

        btnUnselect.setText("Avmarkera");
        btnUnselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnselectActionPerformed(evt);
            }
        });

        btnRemove.setText("Ta bort");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        listProject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listProjectMouseClicked(evt);
            }
        });
        spProject.setViewportView(listProject);

        lblName.setText("Namn");

        lblStartDate.setText("Startdatum");

        lblEndDate.setText("Slutdatum");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        spDescription.setViewportView(taDescription);

        lblDescription.setText("Beskrivning");

        lblStatus.setText("Status");

        lblPriority.setText("Prioritet");

        lblManager.setText("Projektchef");

        lblCountry.setText("Land");

        lblCost.setText("Kostnad");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planerat", "Pågående", "Avslutat" }));

        cbPriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hög", "Medel", "Låg" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spProject, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbManager, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfName)
                        .addComponent(dpStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblName)
                        .addComponent(lblStartDate)
                        .addComponent(lblEndDate)
                        .addComponent(lblManager)
                        .addComponent(lblCountry)
                        .addComponent(dpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescription)
                    .addComponent(lblCost)
                    .addComponent(lblStatus)
                    .addComponent(lblPriority)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUnselect)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove))
                    .addComponent(cbPriority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCost)
                    .addComponent(spDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addContainerGap(315, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spProject, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(lblDescription))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStartDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUnselect)
                        .addComponent(btnRemove))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEndDate)
                            .addComponent(lblCost))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblManager)
                            .addComponent(lblStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCountry)
                            .addComponent(lblPriority))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate)
                            .addComponent(btnChange))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        String startDate = dpStart.getDate().format(formatter);
        String endDate = dpEnd.getDate().format(formatter);
        
        if (Validate.checkForEmptyValues(tfName, taDescription, tfCost) || startDate.isBlank()|| endDate.isBlank()) {
            String message = """
            Följande fält får inte vara tomma:
            Namn, beskrivning, kostnad, startdatum, slutdatum
            """;

            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (Validate.checkIfNotConvertibleToDouble(tfCost)) {
            String message = """
            Följande fält måste vara decimaltal:
            Kostnad 
            """;

            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        EmployeeModel manager = (EmployeeModel) cbManager.getSelectedItem();
        CountryModel country = (CountryModel) cbCountry.getSelectedItem();

        ProjectModel updatedProject = new ProjectModel(
            selectedProject.getPid(),
            tfName.getText(),
            taDescription.getText(),
            startDate,
            endDate,
            tfCost.getText(),
            cbStatus.getSelectedItem().toString(),            
            cbPriority.getSelectedItem().toString(),
            manager.getAid(),
            country.getLid()
        );

        try {
            String updateQuery = QueryBuilder.updateQuery(
                selectedProject.toHashMap(),
                updatedProject.toHashMap(),
                "projekt",
                "pid = %s".formatted(selectedProject.getPid()));

            if (updateQuery.isBlank()) {
                JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0){
                return;
            }

            db.update(updateQuery);
            selectedProject = new ProjectModel(updatedProject.toHashMap());
            Components.updateListEntry(listProject, listModel, updatedProject);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);

        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (Validate.checkForEmptyValues(tfName, taDescription,tfCost)) {
            String message = """
            Följande fält får inte vara tomma:
            Namn, beskrivning, kostnad, prioritet
            """;

            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (Validate.checkIfNotConvertibleToDouble(tfCost)) {
            String message = """
            Följande fält måste vara decimaltal:
            Kostnad 
            """;

            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            
            String startDate = dpStart.getDate().format(formatter);
            String endDate = dpEnd.getDate().format(formatter);

            EmployeeModel manager = (EmployeeModel) cbManager.getSelectedItem();
            CountryModel country = (CountryModel) cbCountry.getSelectedItem();
            
            ProjectModel newProject = new ProjectModel(
                db.getAutoIncrement("projekt", "pid"),
                tfName.getText(),
                taDescription.getText(),
                startDate,
                endDate,
                tfCost.getText(),
                cbStatus.getSelectedItem().toString(),
                cbPriority.getSelectedItem().toString(),
                manager.getAid(),
                country.getLid()
            );

            Queries.addProject(newProject);
            listModel.addElement(newProject);
            listProject.setSelectedValue(listModel.lastElement(), true);
            selectedProject = listProject.getSelectedValue();
            refreshFields();

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till projeketet", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUnselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnselectActionPerformed
        unselect();
    }//GEN-LAST:event_btnUnselectActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if(selectedProject == null) {
            return;
        }
       
        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill ta bort projektet?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
            
        String query1 = "DELETE FROM projekt_partner WHERE pid = %s".formatted(selectedProject.getPid());
        String query2 = "DELETE FROM ans_proj WHERE pid = %s".formatted(selectedProject.getPid());
        String query3 = "DELETE FROM proj_hallbarhet WHERE pid = %s".formatted(selectedProject.getPid());
        String query4 = "DELETE FROM projekt WHERE pid = %s".formatted(selectedProject.getPid());
        
        try {
            Queries.doMultiple(query1, query2, query3, query4);
            Components.removeListEntry(listProject, listModel);
            unselect();

        } catch(InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att ta bort projektet", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void listProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listProjectMouseClicked
        selectedProject = listProject.getSelectedValue();
        Components.setEnabled(true, btnChange, btnRemove);
        Components.setEnabled(false, btnCreate);
        refreshFields();
    }//GEN-LAST:event_listProjectMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUnselect;
    private javax.swing.JComboBox<CountryModel> cbCountry;
    private javax.swing.JComboBox<EmployeeModel> cbManager;
    private javax.swing.JComboBox<String> cbPriority;
    private javax.swing.JComboBox<String> cbStatus;
    private com.github.lgooddatepicker.components.DatePicker dpEnd;
    private com.github.lgooddatepicker.components.DatePicker dpStart;
    private javax.swing.JList<ProjectModel> listProject;
    private javax.swing.JScrollPane spDescription;
    private javax.swing.JScrollPane spProject;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfCost;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
