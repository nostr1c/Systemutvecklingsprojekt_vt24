package gui.goals;

import java.util.HashMap;
import javax.swing.DefaultListModel;
import models.GoalsModel;
import mysql.MySQL;
import mysql.Queries;

/**
 * JPanel for the goals tab
 */
public class GoalsPanel extends javax.swing.JPanel {

    private static DefaultListModel<GoalsModel> listModel;
    private GoalsModel selectedGoal;
    
    public GoalsPanel() {
        initComponents();
        startup();
    }
    
    /**
    * Initializing application by setting up db connection.
    * only if the instance of db is not null, so that it doesn't crash in design mode.
    */
    private void startup () {
        if (MySQL.getInstance().getDB() != null) {
            listModel = (DefaultListModel<GoalsModel>) listGoals.getModel();
            setupGoalsList();
        }
    }
    /**
    * Method for setup the Goals list from the database.
    */
    private void setupGoalsList() {
        for (HashMap<String,String> goal : Queries.getGoals()) {
            listModel.addElement(new GoalsModel(goal));
        }
    }    
    /**
     * Method for updating the fields with the values from the database.
     */
    private void updateFields() {
        lblNumber.setText(selectedGoal.getNumber());
        lblPrio.setText(selectedGoal.getPriority());
        taDescription.setText(selectedGoal.getDescription());
    }
    
    /**
     * Method for adding to goals list.
     * @param goal GoalsModel object
     */
    public static void addToGoalsList(GoalsModel goal) {
        listModel.addElement(goal);
    }
    
    public static void removeFromGoalsList(GoalsModel goal) {
        listModel.removeElement(goal);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spGoals = new javax.swing.JScrollPane();
        listGoals = new javax.swing.JList<>(new DefaultListModel());
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        pnlGoalsNumber = new javax.swing.JPanel();
        javax.swing.JLabel lblGoalsNumber = new javax.swing.JLabel();
        lblNumber = new javax.swing.JLabel();
        pnlPriority = new javax.swing.JPanel();
        javax.swing.JLabel lblPriority = new javax.swing.JLabel();
        lblPrio = new javax.swing.JLabel();

        listGoals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listGoalsMouseClicked(evt);
            }
        });
        spGoals.setViewportView(listGoals);

        lblDescription.setText("Beskrivning");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        taDescription.setFocusable(false);
        taDescription.setHighlighter(null);
        jScrollPane1.setViewportView(taDescription);

        pnlGoalsNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));

        lblGoalsNumber.setText("Målnummer");

        lblNumber.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNumber.setText("0");

        javax.swing.GroupLayout pnlGoalsNumberLayout = new javax.swing.GroupLayout(pnlGoalsNumber);
        pnlGoalsNumber.setLayout(pnlGoalsNumberLayout);
        pnlGoalsNumberLayout.setHorizontalGroup(
            pnlGoalsNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGoalsNumberLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlGoalsNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGoalsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumber))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlGoalsNumberLayout.setVerticalGroup(
            pnlGoalsNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGoalsNumberLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGoalsNumber)
                .addGap(18, 18, 18))
        );

        pnlPriority.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));

        lblPriority.setText("Prioritet");

        lblPrio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPrio.setText("Låg");

        javax.swing.GroupLayout pnlPriorityLayout = new javax.swing.GroupLayout(pnlPriority);
        pnlPriority.setLayout(pnlPriorityLayout);
        pnlPriorityLayout.setHorizontalGroup(
            pnlPriorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPriorityLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlPriorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrio))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlPriorityLayout.setVerticalGroup(
            pnlPriorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPriorityLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(lblPrio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPriority)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spGoals, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pnlGoalsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spGoals))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlGoalsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 259, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     *  Method for selecting a Goal from the list and updating the fields based on which Goal are selected.
     */
    private void listGoalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGoalsMouseClicked
        selectedGoal = listGoals.getSelectedValue();
        updateFields();
    }//GEN-LAST:event_listGoalsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblPrio;
    private static javax.swing.JList<GoalsModel> listGoals;
    private javax.swing.JPanel pnlGoalsNumber;
    private javax.swing.JPanel pnlPriority;
    private javax.swing.JScrollPane spGoals;
    private javax.swing.JTextArea taDescription;
    // End of variables declaration//GEN-END:variables

}
