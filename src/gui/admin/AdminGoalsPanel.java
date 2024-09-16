package gui.admin;

import gui.goals.GoalsPanel;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.GoalsModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import mysql.MySQL;
import mysql.Queries;
import mysql.QueryBuilder;
import utils.Components;
import validators.Validate;

/**
 * JPanel for the goals tab under the administrator tab
 */
public class AdminGoalsPanel extends javax.swing.JPanel {

    private DefaultListModel<GoalsModel> listModel;
    private InfDB db;
    private GoalsModel selectedGoal;
    
    public AdminGoalsPanel() {
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
            listModel = (DefaultListModel<GoalsModel>) jlGoals.getModel();
            setupGoalsList();
            
            Validate.addIntValidatorToField(tfNumber);
        }
    }
    
    // Method for setting up the goals list from the database.
    private void setupGoalsList() {
        for (HashMap<String,String> goalRow : Queries.getGoals()) {
            listModel.addElement(new GoalsModel(goalRow));
        }
    }
    
    // Updates the fields.
    private void updateFields() {
        tfNumber.setText(selectedGoal.getNumber());
        cbPriority.setSelectedItem(selectedGoal.getPriority());
        taDescription.setText(selectedGoal.getDescription());
        tfName.setText(selectedGoal.getName());
    }
    
    // Unselecting and clearing fields.
    private void unselect() {
        Components.clearTextFields(tfName, taDescription, tfNumber);
        Components.clearComboBoxes(cbPriority);
        jlGoals.clearSelection();
        selectedGoal = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spGoals = new javax.swing.JScrollPane();
        jlGoals = new javax.swing.JList<>(new DefaultListModel());
        spDescription = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        javax.swing.JLabel lblNumber = new javax.swing.JLabel();
        tfNumber = new javax.swing.JTextField();
        javax.swing.JLabel lblPriority = new javax.swing.JLabel();
        cbPriority = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1280, 720));

        spGoals.setPreferredSize(new java.awt.Dimension(130, 258));

        jlGoals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlGoalsMouseClicked(evt);
            }
        });
        spGoals.setViewportView(jlGoals);

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setWrapStyleWord(true);
        spDescription.setViewportView(taDescription);

        lblDescription.setText("Beskrivning");

        lblNumber.setText("Målnummer");

        lblPriority.setText("Prioritet");

        cbPriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hög", "Medel", "Låg" }));

        btnCreate.setText("Skapa ny");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setText("Ta bort");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblName.setText("Benämning");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(133, 133, 133)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spGoals, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescription)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNumber)
                                                .addComponent(tfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblPriority)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(cbPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnSave))))
                                        .addComponent(spDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblName)
                                .addComponent(tfName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(502, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spGoals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblDescription)
                        .addGap(18, 18, 18)
                        .addComponent(spDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumber)
                            .addComponent(lblPriority))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnDelete))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Creates a new Goal button event.
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try{
            String hid = db.getAutoIncrement("hallbarhetsmal", "hid");
            GoalsModel emptyGoal = new GoalsModel(
                hid,
                "Nytt mål",
                hid,
                "",
                "Hög"
            );
            
            listModel.addElement(emptyGoal);
            jlGoals.setSelectedValue(listModel.lastElement(), true);
            selectedGoal = emptyGoal;
            updateFields();
            tfNumber.setText(hid);
            Components.clearComboBoxes(cbPriority);
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till nytt hållbarhetsmål", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    
    // Save or create button event.
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (selectedGoal  == null) return;
        
        if (Validate.checkForEmptyValues(tfName, taDescription, tfNumber)) {
            String message = """
                             Följande fält får inte vara tomma:
                             Benämning, Beskrivning, Målnummer
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }      
        
        if (Validate.checkIfNotConvertibleToInt(tfNumber)){
            String message = """
                             Följande fält måste vara ett heltal:
                             Målnummer
                             """;
            
            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        GoalsModel updatedGoal = new GoalsModel(
            jlGoals.getSelectedValue().getHid(),
            tfName.getText(),
            tfNumber.getText(),
            taDescription.getText(),
            cbPriority.getSelectedItem().toString()
        );

        try{
            GoalsModel goal = jlGoals.getSelectedValue();
            String existsQuery = "SELECT hid from hallbarhetsmal where hid = %s".formatted(goal.getHid());
            String result = db.fetchSingle(existsQuery);
            if (result == null){
                // The Hid doesn't exist in the database, thereby the selected goal should be added in the database
                GoalsModel addedGoal = new GoalsModel(
                    updatedGoal.getHid(),
                    tfName.getText(),
                    tfNumber.getText(),
                    taDescription.getText(),
                    cbPriority.getSelectedItem().toString()
                    
                );
                Queries.addGoal(addedGoal);
                GoalsPanel.addToGoalsList(addedGoal);
                Components.updateListEntry(jlGoals, listModel, addedGoal);
                jlGoals.setSelectedValue(addedGoal, true);
                selectedGoal = new GoalsModel(addedGoal.toHashMap());
                updateFields();
            } else {
                // The Hid exists in the database, thereby the selected goal should be updated in the database
                String updateQuery = QueryBuilder.updateQuery(
                    selectedGoal.toHashMap(),
                    updatedGoal.toHashMap(),
                    "hallbarhetsmal",
                    "hid = %s".formatted(selectedGoal.getHid())
                );
                
                if (updateQuery.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;
                
                db.update(updateQuery);
                selectedGoal = new GoalsModel(updatedGoal.toHashMap());
                Components.updateListEntry(jlGoals, listModel, updatedGoal);
                
                JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        updateFields();
    }//GEN-LAST:event_btnSaveActionPerformed
    
    // Delete button event.
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedGoal == null) return;
        
        int option = JOptionPane.showConfirmDialog(null,"Är du säker på att du vill ta bort hållbarhetsmålet?", "Bekräftelse", JOptionPane.YES_NO_OPTION);
        if(option == 0) {
            String query1 = "DELETE FROM avd_hallbarhet WHERE hid = %s".formatted(selectedGoal.getHid());
            String query2 = "DELETE FROM proj_hallbarhet WHERE hid = %s".formatted(selectedGoal.getHid());
            String query3 = "DELETE FROM hallbarhetsmal WHERE hid = %s".formatted(selectedGoal.getHid());
            
            try {
                Queries.doMultiple(query1, query2, query3);
                GoalsPanel.removeFromGoalsList(selectedGoal);
                Components.removeListEntry(jlGoals, listModel);
                unselect();
                
            } catch(InfException ignored) {}
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    // Select goal when clicking in the list.
    private void jlGoalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlGoalsMouseClicked
        selectedGoal = jlGoals.getSelectedValue();
        try{
            String lastElementHid = listModel.lastElement().getHid();
            String existsQuery = "SELECT hid from hallbarhetsmal where hid = %s".formatted(lastElementHid);
            String result = db.fetchSingle(existsQuery);
            if (result == null){
                listModel.removeElement(listModel.lastElement());
            }
        }
        catch (InfException e) {
            System.out.println(e.getMessage());
        }
            
        updateFields();
    }//GEN-LAST:event_jlGoalsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbPriority;
    private javax.swing.JList<GoalsModel> jlGoals;
    private javax.swing.JScrollPane spDescription;
    private javax.swing.JScrollPane spGoals;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNumber;
    // End of variables declaration//GEN-END:variables
}
