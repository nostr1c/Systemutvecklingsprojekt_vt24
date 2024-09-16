package gui;

import utils.TabManager;
import mysql.Queries;
import static shared.Shared.SESSION_AID;

/**
 * JFrame for the main application window
 */
public class MainFrame extends javax.swing.JFrame {

    private TabManager tabManager;
    
    public MainFrame() {
        initComponents();
        tabManager = new TabManager(tpMain);
        
        // Set tabs enabled based on role.
        switch (Queries.getRoleByAid(SESSION_AID)) {
            case "Admin":
                tabManager.enable(0, 4);
                break;
            case "Handläggare":
                tabManager.enable(0, 1, 2, 3);
                break;
            default:
                tabManager.enable(0);
                break; 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpMain = new javax.swing.JTabbedPane();
        pnlAccount = new gui.account.AccountPanel();
        projectPanel2 = new gui.project.ProjectPanel();
        pnlDepartment = new gui.department.DepartmentPanel();
        pnlGoals = new gui.goals.GoalsPanel();
        pnlAdmin = new gui.admin.AdminPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tpMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tpMain.addTab("Konto", pnlAccount);
        tpMain.addTab("Projekt", projectPanel2);
        tpMain.addTab("Avdelning", pnlDepartment);
        tpMain.addTab("Hållbarhetsmål", pnlGoals);
        tpMain.addTab("Admin", pnlAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1268, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 692, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.account.AccountPanel pnlAccount;
    private gui.admin.AdminPanel pnlAdmin;
    private gui.department.DepartmentPanel pnlDepartment;
    private gui.goals.GoalsPanel pnlGoals;
    private gui.project.ProjectPanel projectPanel2;
    private javax.swing.JTabbedPane tpMain;
    // End of variables declaration//GEN-END:variables
}
