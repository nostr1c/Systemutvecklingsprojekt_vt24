package gui.department;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import models.EmployeeModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import static shared.Shared.SESSION_AID;
import mysql.MySQL;

/**
 * JPanel for the department tab
 */
public class DepartmentPanel extends javax.swing.JPanel {

    private DefaultListModel<EmployeeModel> listModel;
    private InfDB db;

    public DepartmentPanel() {
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
            listModel = (DefaultListModel<EmployeeModel>) listEmployee.getModel();
            refreshList("");
        }
    }
    
    private void refreshList(String search) {
        listModel.removeAllElements();
        String query = "";
        
        if(search.isBlank()) {
            query = """
                    SELECT aid, fornamn, efternamn FROM anstalld
                    WHERE avdelning IN (SELECT avdelning FROM anstalld WHERE aid = %s)
                    """.formatted(SESSION_AID);
        } else {
            query = """
                    SELECT aid, fornamn, efternamn FROM anstalld
                    WHERE avdelning IN (SELECT avdelning FROM anstalld WHERE aid = %s)
                    AND (CONCAT(fornamn, ' ', efternamn) like '%%%s%%' OR epost like '%%%s%%')
                    """.formatted(SESSION_AID, search, search);
        }

        try {
            ArrayList<HashMap<String,String>> employees = db.fetchRows(query);
            
            for (HashMap<String,String> employee : employees) {
                listModel.addElement(new EmployeeModel(employee));
            }
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spEmployee = new javax.swing.JScrollPane();
        listEmployee = new javax.swing.JList<>(new DefaultListModel());
        tfSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

        spEmployee.setViewportView(listEmployee);

        lblSearch.setText("Sök anställd på avdelning (Namn / Epost)");

        btnSearch.setText("Sök");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(spEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(416, 416, 416))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lblSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        refreshList(tfSearch.getText());
    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JList<EmployeeModel> listEmployee;
    private javax.swing.JScrollPane spEmployee;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
