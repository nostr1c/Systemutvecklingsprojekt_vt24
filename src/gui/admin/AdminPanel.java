package gui.admin;

/**
 * JPanel with a JTabbedPane combining all other tabs
 */
public class AdminPanel extends javax.swing.JPanel {
    
    public AdminPanel() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpPartners = new javax.swing.JTabbedPane();
        pnlAdminEmployee = new gui.admin.AdminEmployeePanel();
        pnlAdminCountry = new gui.admin.AdminCountryPanel();
        pnlAdminPartner = new gui.admin.AdminPartnerPanel();
        pnlAdminDepartment = new gui.admin.AdminDepartmentPanel();
        pnlAdminProject = new gui.admin.AdminProjectPanel();
        pnlAdminGoals = new gui.admin.AdminGoalsPanel();

        tpPartners.addTab("Anställda", pnlAdminEmployee);
        tpPartners.addTab("Länder", pnlAdminCountry);
        tpPartners.addTab("Partners", pnlAdminPartner);
        tpPartners.addTab("Avdelningar", pnlAdminDepartment);
        tpPartners.addTab("Projekt", pnlAdminProject);
        tpPartners.addTab("Hållbarhetsmål", pnlAdminGoals);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpPartners, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpPartners, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.admin.AdminCountryPanel pnlAdminCountry;
    private gui.admin.AdminDepartmentPanel pnlAdminDepartment;
    private gui.admin.AdminEmployeePanel pnlAdminEmployee;
    private gui.admin.AdminGoalsPanel pnlAdminGoals;
    private gui.admin.AdminPartnerPanel pnlAdminPartner;
    private gui.admin.AdminProjectPanel pnlAdminProject;
    private javax.swing.JTabbedPane tpPartners;
    // End of variables declaration//GEN-END:variables
}
