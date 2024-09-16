package gui.project;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import mysql.*;
import static shared.Shared.SESSION_AID;

/**
 * JPanel for the statistics tab under the project tab
 */
public class ProjectStatistics extends javax.swing.JPanel {
    
    private DefaultTableModel avgCostPerCountryModel;
    private DefaultTableModel managerCountriesModel;
    private static DefaultTableModel managerPartnersModel;
    
    public ProjectStatistics() {
        initComponents();
        startup(); 
    }
    
    /**
    * Initializing application by setting up db connection.
    * only if the instance of db is not null, so that it doesn't crash in design mode.
    */
    private void startup() {
        if (MySQL.getInstance().getDB() != null) {
            avgCostPerCountryModel = (DefaultTableModel) tblAvgCostPerCountry.getModel();
            managerCountriesModel = (DefaultTableModel) tblManagerCountries.getModel();
            managerPartnersModel = (DefaultTableModel) tblManagerPartners.getModel();
            updateSingleStats();
            updateCostCountryTable();
            updateCountriesTable();
            updatePartnerTable();
        }
    }
    
    // Update the single stats.
    public void updateSingleStats() {
        lblAvgCostAllProjects.setText(Queries.getAvgCostAllProjects() + " SEK");
        lblAvgCostAllProjectsMyDepartment.setText(Queries.getAvgCostAllProjectsMyDepartment(SESSION_AID) + " SEK");
        lblAvgCostAllProjectsManager.setText(Queries.getAvgCostAllProjectsManager(SESSION_AID) + " SEK");
    }
    
    
    // Update avg cost per country table.
    public void updateCostCountryTable() {
        avgCostPerCountryModel.setRowCount(0);
        for (HashMap<String, String> row : Queries.getAvgCostPerCountry()) {
            Object[] rowData = {
                row.get("namn"),
                row.get("cost")
            };
            avgCostPerCountryModel.addRow(rowData);
        }
    }
    
    // Update countries table.
    public void updateCountriesTable() {
        managerCountriesModel.setRowCount(0);
        for (HashMap<String, String> row : Queries.getManagerCountries(SESSION_AID)) {
            Object[] rowData = {
                row.get("namn")
            };
            managerCountriesModel.addRow(rowData);
        }
    }
    
    // Update partner table.
    public static void updatePartnerTable() {
        managerPartnersModel.setRowCount(0);
        for (HashMap<String, String> row : Queries.getManagerPartners(SESSION_AID)) {
            Object[] rowData = {
                row.get("namn")
            };
            managerPartnersModel.addRow(rowData);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAvgCostAllProjects = new javax.swing.JPanel();
        javax.swing.JLabel lblStats2 = new javax.swing.JLabel();
        lblAvgCostAllProjects = new javax.swing.JLabel();
        pnlAvgCostAllProjectsMyDepartment = new javax.swing.JPanel();
        javax.swing.JLabel lblStats3 = new javax.swing.JLabel();
        lblAvgCostAllProjectsMyDepartment = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlAvgCostAllProjectsManager = new javax.swing.JPanel();
        javax.swing.JLabel lblStats5 = new javax.swing.JLabel();
        lblAvgCostAllProjectsManager = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvgCostPerCountry = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblManagerCountries = new javax.swing.JTable();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblManagerPartners = new javax.swing.JTable();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();

        pnlAvgCostAllProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));

        lblStats2.setText("Genomsnittlig kostnad för alla projekt");

        lblAvgCostAllProjects.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAvgCostAllProjects.setText("0 SEK");

        javax.swing.GroupLayout pnlAvgCostAllProjectsLayout = new javax.swing.GroupLayout(pnlAvgCostAllProjects);
        pnlAvgCostAllProjects.setLayout(pnlAvgCostAllProjectsLayout);
        pnlAvgCostAllProjectsLayout.setHorizontalGroup(
            pnlAvgCostAllProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvgCostAllProjectsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlAvgCostAllProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvgCostAllProjects)
                    .addComponent(lblStats2))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlAvgCostAllProjectsLayout.setVerticalGroup(
            pnlAvgCostAllProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvgCostAllProjectsLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblAvgCostAllProjects)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStats2)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlAvgCostAllProjectsMyDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));

        lblStats3.setText("Genomsnittlig kostnad för alla projekt på min avdelning");

        lblAvgCostAllProjectsMyDepartment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAvgCostAllProjectsMyDepartment.setText("0 SEK");

        javax.swing.GroupLayout pnlAvgCostAllProjectsMyDepartmentLayout = new javax.swing.GroupLayout(pnlAvgCostAllProjectsMyDepartment);
        pnlAvgCostAllProjectsMyDepartment.setLayout(pnlAvgCostAllProjectsMyDepartmentLayout);
        pnlAvgCostAllProjectsMyDepartmentLayout.setHorizontalGroup(
            pnlAvgCostAllProjectsMyDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvgCostAllProjectsMyDepartmentLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlAvgCostAllProjectsMyDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvgCostAllProjectsMyDepartment)
                    .addComponent(lblStats3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAvgCostAllProjectsMyDepartmentLayout.setVerticalGroup(
            pnlAvgCostAllProjectsMyDepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAvgCostAllProjectsMyDepartmentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAvgCostAllProjectsMyDepartment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStats3)
                .addGap(23, 23, 23))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Statistik");

        pnlAvgCostAllProjectsManager.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));

        lblStats5.setText("Genomsnittlig kostnad för alla projekt som jag är projektchef för");

        lblAvgCostAllProjectsManager.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAvgCostAllProjectsManager.setText("0 SEK");

        javax.swing.GroupLayout pnlAvgCostAllProjectsManagerLayout = new javax.swing.GroupLayout(pnlAvgCostAllProjectsManager);
        pnlAvgCostAllProjectsManager.setLayout(pnlAvgCostAllProjectsManagerLayout);
        pnlAvgCostAllProjectsManagerLayout.setHorizontalGroup(
            pnlAvgCostAllProjectsManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvgCostAllProjectsManagerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlAvgCostAllProjectsManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvgCostAllProjectsManager)
                    .addComponent(lblStats5))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnlAvgCostAllProjectsManagerLayout.setVerticalGroup(
            pnlAvgCostAllProjectsManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvgCostAllProjectsManagerLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblAvgCostAllProjectsManager)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStats5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblAvgCostPerCountry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));
        tblAvgCostPerCountry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Land", "Avg kostnad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAvgCostPerCountry.setFocusable(false);
        tblAvgCostPerCountry.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAvgCostPerCountry);
        if (tblAvgCostPerCountry.getColumnModel().getColumnCount() > 0) {
            tblAvgCostPerCountry.getColumnModel().getColumn(0).setResizable(false);
            tblAvgCostPerCountry.getColumnModel().getColumn(1).setResizable(false);
        }

        tblManagerCountries.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));
        tblManagerCountries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Land"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblManagerCountries.setFocusable(false);
        tblManagerCountries.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblManagerCountries);
        if (tblManagerCountries.getColumnModel().getColumnCount() > 0) {
            tblManagerCountries.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel2.setText("Genomsnittlig kostnad per land");

        jLabel3.setText("Länder med projekt som jag är projektchef över");

        tblManagerPartners.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 58)));
        tblManagerPartners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Partner"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblManagerPartners.setFocusable(false);
        tblManagerPartners.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblManagerPartners);
        if (tblManagerPartners.getColumnModel().getColumnCount() > 0) {
            tblManagerPartners.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel4.setText("Partners i projekt som jag är projektchef över");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAvgCostAllProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(pnlAvgCostAllProjectsMyDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(pnlAvgCostAllProjectsManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlAvgCostAllProjectsMyDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAvgCostAllProjectsManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAvgCostAllProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAvgCostAllProjects;
    private javax.swing.JLabel lblAvgCostAllProjectsManager;
    private javax.swing.JLabel lblAvgCostAllProjectsMyDepartment;
    private javax.swing.JPanel pnlAvgCostAllProjects;
    private javax.swing.JPanel pnlAvgCostAllProjectsManager;
    private javax.swing.JPanel pnlAvgCostAllProjectsMyDepartment;
    private javax.swing.JTable tblAvgCostPerCountry;
    private javax.swing.JTable tblManagerCountries;
    private javax.swing.JTable tblManagerPartners;
    // End of variables declaration//GEN-END:variables
}
