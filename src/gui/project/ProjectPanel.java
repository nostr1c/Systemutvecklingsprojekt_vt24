package gui.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.CountryModel;
import models.EmployeeModel;
import models.PartnerModel;
import models.ProjectModel;
import mysql.*;
import oru.inf.*;
import static shared.Shared.SESSION_AID;
import utils.Components;
import validators.Validate;
import utils.TabManager;

/**
 * JPanel for the project tab
 */
public class ProjectPanel extends javax.swing.JPanel {

    private DefaultListModel<ProjectModel> listModel;
    private DefaultTableModel partnerModel;
    private DefaultTableModel managerModel;
    private ProjectModel selectedProject;
    private InfDB db;
    private DateTimeFormatter formatter;
    private TabManager tabManager;

    public ProjectPanel() {
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
            tabManager = new TabManager(tpProject);
           
            if (Queries.isProjectManagerAny(SESSION_AID)) {
                tabManager.enable(0, 1);
            } else {
                tabManager.enable(0);
            }
            
            // Get list and table models..
            listModel = (DefaultListModel<ProjectModel>) listProject.getModel();
            partnerModel = (DefaultTableModel) tblPartners.getModel();
            managerModel = (DefaultTableModel) tblManagers.getModel();
            
            // Set our date formatter pattern.
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            // Set scrollbar speed.
            spProject.getVerticalScrollBar().setUnitIncrement(14);

            // Handle permission level visibility and access.
            Components.setVisible(false, pnlProjectManager, btnChange);
            Components.setEnabled(false, dpStartDate, dpEndDate);
            Components.setFocusable(false, tfName, tfDescription, tfCost);
            
            // Add couble validator to cost field.
            Validate.addDoubleValidatorToField(tfCost);
            
            populateList();
            fillComboBoxes();
        }
    }
    
    /**
     * Method for populating projects list.
     */
    private void populateList() {
        String query = "";
        
        // Get comboboxes selected items.
        String option = cbOption.getSelectedItem().toString();
        String status = cbSearchStatus.getSelectedItem().toString();
        
        // Set query based on checked filters.
        switch (option) {
            case "Jobbar på":
                switch (status) {
                    case "Alla":
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                JOIN ans_proj ON projekt.pid = ans_proj.pid
                                JOIN anstalld ON ans_proj.aid = anstalld.aid
                                WHERE anstalld.aid = %s
                                """.formatted(SESSION_AID);
                        break;
                    default:
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                JOIN ans_proj ON projekt.pid = ans_proj.pid
                                JOIN anstalld ON ans_proj.aid = anstalld.aid
                                WHERE anstalld.aid = %s AND status = '%s'
                                """.formatted(SESSION_AID, status);
                        break;
                }
                break;  
            case "Min avdelnings":
                switch (status) {
                    case "Alla":
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                JOIN ans_proj ON projekt.pid = ans_proj.pid
                                JOIN anstalld ON ans_proj.aid = anstalld.aid
                                WHERE avdelning IN (SELECT avdelning FROM anstalld WHERE aid = %s)
                                """.formatted(SESSION_AID);
                        break;
                    default:
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                JOIN ans_proj ON projekt.pid = ans_proj.pid
                                JOIN anstalld ON ans_proj.aid = anstalld.aid
                                WHERE avdelning IN (SELECT avdelning FROM anstalld WHERE aid = %s)
                                AND status = '%s'
                                """.formatted(SESSION_AID, status);
                        break;
                }
                break;
            case "Projektchef för":
                switch (status) {
                    case "Alla":
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                WHERE projektchef = %s;
                                """.formatted(SESSION_AID);
                        break;
                    default:
                        query = """
                                SELECT DISTINCT projekt.* FROM projekt
                                WHERE projektchef = %s
                                AND status = '%s'
                                """.formatted(SESSION_AID, status);
                        break;
                }
                break;
        }

        try {
            // Get projects based on query.
            ArrayList<HashMap<String, String>> rows = db.fetchRows(query);
            listModel.removeAllElements();
            
            // Loop through each row and add the item in the list model.
            for (HashMap<String, String> row : rows) {
                listModel.addElement(new ProjectModel(row));
            }

        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method for filling the comboboxes with data.
     */
    private void fillComboBoxes() {
        String countryQuery = "SELECT lid, namn FROM land";
        String employeeQuery = "SELECT aid, fornamn, efternamn FROM anstalld";
        
        try {
            ArrayList<HashMap<String, String>> countryRows = db.fetchRows(countryQuery);
            ArrayList<HashMap<String, String>> employeeRows = db.fetchRows(employeeQuery);

            for (HashMap<String, String> countryRow : countryRows) {
                cbCountry.addItem(new CountryModel(countryRow));
            }
            
            for (HashMap<String, String> employeeRow : employeeRows) {
                cbManager.addItem(new EmployeeModel(employeeRow));
            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method for updating items inside comboboxes
     */
    private void fillProjectManagerComboBoxes() {
        cbManagerAddEmployee.removeAllItems();
        cbManagerAddPartner.removeAllItems();
        String employeesNotInProjectQuery = """
                       SELECT anstalld.*
                       FROM anstalld
                       WHERE anstalld.aid NOT IN (
                           SELECT anstalld.aid
                           FROM anstalld
                           JOIN ans_proj ON anstalld.aid = ans_proj.aid
                           JOIN projekt ON ans_proj.pid = projekt.pid
                           WHERE projekt.pid = %s
                       )
                       """.formatted(selectedProject.getPid());
        
        String partnerNotInProjectQuery = """
                        SELECT partner.*
                        FROM partner
                        WHERE partner.pid NOT IN (
                          SELECT partner.pid
                          FROM partner
                          join projekt_partner on partner.pid = projekt_partner.partner_pid
                          join projekt on projekt_partner.pid = projekt.pid
                          WHERE projekt.pid = %s
                        )
                        """.formatted(selectedProject.getPid());
        try {
            ArrayList<HashMap<String, String>> employeesNotInProjectRows = db.fetchRows(employeesNotInProjectQuery);
            ArrayList<HashMap<String, String>> partnerNotInProjectRows = db.fetchRows(partnerNotInProjectQuery);
            
            for (HashMap<String, String> employeesNotInProjectRow : employeesNotInProjectRows) {
                cbManagerAddEmployee.addItem(new EmployeeModel(employeesNotInProjectRow));
            }
            
            for (HashMap<String, String> partnerNotInProjectRow : partnerNotInProjectRows) {
                cbManagerAddPartner.addItem(new PartnerModel(partnerNotInProjectRow));
            }
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method for refreshing fields with updated data
     */
    private void refreshFields() {
        tfName.setText(selectedProject.getName());
        tfCost.setText(selectedProject.getCost());
        tfDescription.setText(selectedProject.getDescription());
        dpStartDate.setDate(LocalDate.parse(selectedProject.getStartDate(), formatter));
        dpEndDate.setDate(LocalDate.parse(selectedProject.getEndDate(), formatter));
        cbPrio.setSelectedItem(selectedProject.getPriority());
        cbStatus.setSelectedItem(selectedProject.getStatus());
        cbManager.setSelectedItem(new EmployeeModel(Queries.getEmployeeByAid(selectedProject.getManager())));
        cbCountry.setSelectedItem(new CountryModel(Queries.getCountryByLid(selectedProject.getCountry())));
        fillProjectManagerComboBoxes();
    }
    
    /**
     * Method for refreshing the partner table with updated data
     */
    private void refreshPartnerTable() {
        partnerModel.setRowCount(0);

        String query = """
                       SELECT partner.* FROM partner
                       JOIN projekt_partner ON partner.pid = projekt_partner.partner_pid
                       JOIN projekt ON projekt_partner.pid = projekt.pid
                       WHERE projekt.pid = %s;
                       """.formatted(selectedProject.getPid());

        try {
            ArrayList<HashMap<String, String>> rows = db.fetchRows(query);

            for (HashMap<String, String> row : rows) {
                Object[] rowData = {
                    row.get("pid"),
                    new PartnerModel(row),
                    row.get("kontaktperson"),
                    row.get("kontaktepost"),
                    row.get("telefon"),
                    row.get("adress"),
                    row.get("branch"),
                    row.get("stad")
                };

                partnerModel.addRow(rowData);

            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }

    }
    
    /**
     * Method for refreshing the manager table with updated data
     */
    private void refreshManagerTable() {
        managerModel.setRowCount(0);

        String query = """
                       SELECT anstalld.* FROM anstalld
                       JOIN ans_proj ON anstalld.aid = ans_proj.aid
                       JOIN projekt ON ans_proj.pid = projekt.pid
                       WHERE projekt.pid = %s
                       """.formatted(selectedProject.getPid());

        try {
            ArrayList<HashMap<String, String>> rows = db.fetchRows(query);

            for (HashMap<String, String> row : rows) {
                Object[] rowData = {
                    row.get("aid"),
                    new EmployeeModel(row),
                    row.get("adress"),
                    row.get("epost"),
                    row.get("telefon"),
                    row.get("anstallningsdatum"),
                    row.get("avdelning"),
                };

                managerModel.addRow(rowData);

            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }

    }
    
    /**
     * Method for handling component visibility based on project manager
     */
    private void setComponentVisiblity() {
        if (Queries.isProjectManager(SESSION_AID, selectedProject.getPid())) {
            Components.setFocusable(true, tfName, tfDescription, tfCost);
            Components.setVisible(true, pnlProjectManager, btnChange);
            Components.setEnabled(true, dpStartDate, dpEndDate, cbCountry, cbManager, cbPrio, cbStatus);
            
        } else {
            Components.setFocusable(false, tfName, tfDescription, tfCost);
            Components.setVisible(false, pnlProjectManager, btnChange);
            Components.setEnabled(false, dpStartDate, dpEndDate, cbCountry, cbManager, cbPrio, cbStatus);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpProject = new javax.swing.JTabbedPane();
        pnlProjects = new javax.swing.JPanel();
        btnFilterDate = new javax.swing.JButton();
        javax.swing.JLabel lblFromDate = new javax.swing.JLabel();
        dpFilterTo = new com.github.lgooddatepicker.components.DatePicker();
        javax.swing.JLabel lblToDate = new javax.swing.JLabel();
        dpFilterFrom = new com.github.lgooddatepicker.components.DatePicker();
        cbSearchStatus = new javax.swing.JComboBox<>();
        cbOption = new javax.swing.JComboBox<>();
        spProject = new javax.swing.JScrollPane();
        pnlProjectContainer = new javax.swing.JPanel();
        javax.swing.JLabel lblProjectInformation = new javax.swing.JLabel();
        javax.swing.JLabel lblStartDate = new javax.swing.JLabel();
        javax.swing.JLabel lblEndDate = new javax.swing.JLabel();
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        javax.swing.JLabel lblCost = new javax.swing.JLabel();
        tfCost = new javax.swing.JTextField();
        javax.swing.JLabel lblStatus = new javax.swing.JLabel();
        javax.swing.JLabel lblPriority = new javax.swing.JLabel();
        javax.swing.JLabel lblManager = new javax.swing.JLabel();
        javax.swing.JLabel lblCountry = new javax.swing.JLabel();
        pnlProjectManager = new javax.swing.JPanel();
        btnAddPartner = new javax.swing.JButton();
        javax.swing.JLabel lblPartnerId = new javax.swing.JLabel();
        btnRemovePartner = new javax.swing.JButton();
        javax.swing.JLabel lblEmployees = new javax.swing.JLabel();
        spPartners1 = new javax.swing.JScrollPane();
        tblManagers = new javax.swing.JTable();
        javax.swing.JLabel lblManagerID = new javax.swing.JLabel();
        btnAddManager = new javax.swing.JButton();
        btnRemoveManager = new javax.swing.JButton();
        cbManagerAddEmployee = new javax.swing.JComboBox<>();
        cbManagerAddPartner = new javax.swing.JComboBox<>();
        javax.swing.JLabel lblPartners = new javax.swing.JLabel();
        spPartners = new javax.swing.JScrollPane();
        tblPartners = new javax.swing.JTable();
        btnChange = new javax.swing.JButton();
        tfName = new javax.swing.JTextField();
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        dpEndDate = new com.github.lgooddatepicker.components.DatePicker();
        dpStartDate = new com.github.lgooddatepicker.components.DatePicker();
        cbCountry = new javax.swing.JComboBox<>();
        cbManager = new javax.swing.JComboBox<>();
        cbStatus = new javax.swing.JComboBox<>();
        cbPrio = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProject = new javax.swing.JList<>(new DefaultListModel());
        javax.swing.JLabel lblStatusFiltrate = new javax.swing.JLabel();
        javax.swing.JLabel lblProject = new javax.swing.JLabel();
        projectStatistics1 = new gui.project.ProjectStatistics();

        btnFilterDate.setText("Sök");
        btnFilterDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterDateActionPerformed(evt);
            }
        });

        lblFromDate.setText("Från");

        lblToDate.setText("Till");

        dpFilterFrom.setForeground(new java.awt.Color(51, 102, 255));

        cbSearchStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alla", "Planerat", "Pågående", "Avslutat" }));
        cbSearchStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSearchStatusActionPerformed(evt);
            }
        });

        cbOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jobbar på", "Min avdelnings", "Projektchef för" }));
        cbOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOptionActionPerformed(evt);
            }
        });

        spProject.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lblProjectInformation.setText("Projektinformation");
        lblProjectInformation.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblStartDate.setText("Startdatum");

        lblEndDate.setText("Slutdatum");

        lblDescription.setText("Beskrivning");

        tfDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCost.setText("Kostnad");

        tfCost.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblStatus.setText("Status");

        lblPriority.setText("Prioritet");

        lblManager.setText("Projektchef");

        lblCountry.setText("Land");

        btnAddPartner.setText("Lägg till");
        btnAddPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPartnerActionPerformed(evt);
            }
        });

        lblPartnerId.setText("Lägg till en partner");

        btnRemovePartner.setText("Ta bort");
        btnRemovePartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePartnerActionPerformed(evt);
            }
        });

        lblEmployees.setText("Handläggare");

        tblManagers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AID", "Namn", "Adress", "Epost", "Telefon", "Anställningsdatum", "Avdelning"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblManagers.getTableHeader().setReorderingAllowed(false);
        spPartners1.setViewportView(tblManagers);
        if (tblManagers.getColumnModel().getColumnCount() > 0) {
            tblManagers.getColumnModel().getColumn(0).setResizable(false);
            tblManagers.getColumnModel().getColumn(0).setPreferredWidth(3);
            tblManagers.getColumnModel().getColumn(6).setResizable(false);
        }

        lblManagerID.setText("Lägg till en handläggare");

        btnAddManager.setText("Lägg till");
        btnAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddManagerActionPerformed(evt);
            }
        });

        btnRemoveManager.setText("Ta bort");
        btnRemoveManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProjectManagerLayout = new javax.swing.GroupLayout(pnlProjectManager);
        pnlProjectManager.setLayout(pnlProjectManagerLayout);
        pnlProjectManagerLayout.setHorizontalGroup(
            pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmployees)
                    .addComponent(spPartners1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                        .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                                .addComponent(cbManagerAddEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddManager))
                            .addComponent(lblManagerID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveManager)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPartnerId)
                    .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                        .addComponent(cbManagerAddPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddPartner)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemovePartner))
        );
        pnlProjectManagerLayout.setVerticalGroup(
            pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblPartnerId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbManagerAddPartner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddPartner)))
                    .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRemovePartner)))
                .addGap(24, 24, 24)
                .addComponent(lblEmployees)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPartners1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProjectManagerLayout.createSequentialGroup()
                        .addComponent(lblManagerID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddManager)
                            .addComponent(cbManagerAddEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRemoveManager))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        lblPartners.setText("Partners");

        tblPartners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "Namn", "Kontaktperson", "Kontaktepost", "Telefon", "Adress", "Bransch", "Stad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPartners.getTableHeader().setReorderingAllowed(false);
        spPartners.setViewportView(tblPartners);
        if (tblPartners.getColumnModel().getColumnCount() > 0) {
            tblPartners.getColumnModel().getColumn(0).setResizable(false);
            tblPartners.getColumnModel().getColumn(0).setPreferredWidth(3);
            tblPartners.getColumnModel().getColumn(7).setResizable(false);
            tblPartners.getColumnModel().getColumn(7).setPreferredWidth(6);
        }

        btnChange.setText("Ändra uppgifter");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        tfName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblName.setText("Namn");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planerat", "Pågående", "Avslutat" }));

        cbPrio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Låg", "Medel", "Hög" }));

        javax.swing.GroupLayout pnlProjectContainerLayout = new javax.swing.GroupLayout(pnlProjectContainer);
        pnlProjectContainer.setLayout(pnlProjectContainerLayout);
        pnlProjectContainerLayout.setHorizontalGroup(
            pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProjectManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChange)
                            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCost)
                                    .addComponent(lblName)
                                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCost))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDescription)
                                    .addComponent(tfDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblPriority)
                                    .addComponent(cbPrio, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblManager)
                            .addComponent(lblCountry)
                            .addComponent(cbCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbManager, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblPartners)
                    .addComponent(spPartners, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStartDate)
                            .addComponent(dpStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProjectInformation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEndDate)
                            .addComponent(dpEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblStatus)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        pnlProjectContainerLayout.setVerticalGroup(
            pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProjectInformation)
                .addGap(18, 18, 18)
                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStartDate)
                            .addComponent(lblEndDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addGap(28, 28, 28)))
                .addGap(12, 12, 12)
                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                                    .addComponent(lblCost)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbPrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProjectContainerLayout.createSequentialGroup()
                                    .addComponent(lblManager)
                                    .addGap(28, 28, 28)))
                            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                                .addComponent(lblPriority)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                                .addComponent(lblDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlProjectContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                                .addComponent(lblName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlProjectContainerLayout.createSequentialGroup()
                        .addComponent(lblCountry)
                        .addGap(28, 28, 28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChange)
                .addGap(18, 18, 18)
                .addComponent(lblPartners)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPartners, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProjectManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        spProject.setViewportView(pnlProjectContainer);

        listProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listProjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listProject);

        lblStatusFiltrate.setText("Sök status");

        lblProject.setText("Projekt");

        javax.swing.GroupLayout pnlProjectsLayout = new javax.swing.GroupLayout(pnlProjects);
        pnlProjects.setLayout(pnlProjectsLayout);
        pnlProjectsLayout.setHorizontalGroup(
            pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProjectsLayout.createSequentialGroup()
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProject))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatusFiltrate)
                            .addComponent(cbSearchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(1043, Short.MAX_VALUE))
                    .addGroup(pnlProjectsLayout.createSequentialGroup()
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlProjectsLayout.createSequentialGroup()
                                .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFromDate)
                                    .addComponent(dpFilterFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlProjectsLayout.createSequentialGroup()
                                        .addComponent(dpFilterTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFilterDate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblToDate)))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spProject, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );
        pnlProjectsLayout.setVerticalGroup(
            pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProjectsLayout.createSequentialGroup()
                .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlProjectsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spProject, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlProjectsLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProject)
                            .addComponent(lblStatusFiltrate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSearchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFromDate)
                            .addComponent(lblToDate))
                        .addGap(4, 4, 4)
                        .addGroup(pnlProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpFilterFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpFilterTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilterDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );

        tpProject.addTab("Projekt", pnlProjects);
        tpProject.addTab("Statistik", projectStatistics1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpProject)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpProject)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Search button event
     */
    private void cbSearchStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSearchStatusActionPerformed
        populateList();
    }//GEN-LAST:event_cbSearchStatusActionPerformed
    
    /**
     * List click event
     */
    private void listProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listProjectMouseClicked
        selectedProject = listProject.getSelectedValue();
        setComponentVisiblity();
        refreshFields();
        refreshPartnerTable();
        refreshManagerTable();
    }//GEN-LAST:event_listProjectMouseClicked
    
    /**
     * Filter button click event
     */
    private void btnFilterDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterDateActionPerformed
        LocalDate fromDate = dpFilterFrom.getDate();
        LocalDate toDate = dpFilterTo.getDate();

        if (fromDate ==  null && toDate == null) {
            populateList();
            return ;
        }
        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(null, "Du måste välja ett från- och tilldatum", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        listModel.removeAllElements();
        String query = "";
        if (cbSearchStatus.getSelectedItem().equals("Alla")) {
            query = """
                    SELECT * from projekt
                    WHERE startdatum >= '%s' AND slutdatum <= '%s'
                    """.formatted(fromDate.toString(), toDate.toString());
        } else {
            query = """
                    SELECT * from projekt
                    WHERE status = '%s' AND startdatum >= '%s' AND slutdatum <= '%s'
                    """.formatted(cbSearchStatus.getSelectedItem(), fromDate.toString(), toDate.toString());
        }

        try {
            ArrayList<HashMap<String, String>> rows = db.fetchRows(query);

            for (HashMap<String, String> row : rows) {
                listModel.addElement(new ProjectModel(row));
            }

            if (listModel.isEmpty()) {
                String message = "Hitta inga projekt mellan: %s - %s".formatted(fromDate.toString(), toDate.toString());
                JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnFilterDateActionPerformed
    
    /**
     * Add partner button event
     */
    private void btnAddPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPartnerActionPerformed
        PartnerModel partner = (PartnerModel) cbManagerAddPartner.getSelectedItem();
        String query = "INSERT INTO projekt_partner VALUES (%s, %s)".formatted(selectedProject.getPid(), partner.getPid());

        try {
            db.insert(query);
            refreshPartnerTable();
            cbManagerAddPartner.removeItem(partner);
            ProjectStatistics.updatePartnerTable();
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till partnern", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAddPartnerActionPerformed
    
    /**
     * Remove partner button event
     */
    private void btnRemovePartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePartnerActionPerformed
        int row = tblPartners.getSelectedRow();

        if (row == -1) return;
        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill ta bort partnern?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;

        String query = "DELETE FROM projekt_partner WHERE pid = %s AND partner_pid = %s".formatted(selectedProject.getPid(), partnerModel.getValueAt(row, 0));
        PartnerModel partner = (PartnerModel) partnerModel.getValueAt(row, 1);
        
        try {
            db.delete(query);
            cbManagerAddPartner.addItem(partner);
            refreshPartnerTable();
            ProjectStatistics.updatePartnerTable();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRemovePartnerActionPerformed
    
    /**
     * Change button event
     */
    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        String startDate = dpStartDate.getDate().format(formatter);
        String endDate = dpEndDate.getDate().format(formatter);
        
        if (Validate.checkForEmptyValues(tfName, tfDescription, tfCost) || startDate.isEmpty() || endDate.isEmpty()) {
            String message = """
                             Följande fält får inte vara tomma:
                             Namn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land 
                             """;

            JOptionPane.showMessageDialog(null, message, "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        EmployeeModel manager = (EmployeeModel) cbManager.getSelectedItem();
        CountryModel country = (CountryModel) cbCountry.getSelectedItem();

        ProjectModel updatedProject = new ProjectModel(
                selectedProject.getPid(),
                tfName.getText(),
                tfDescription.getText(),
                startDate,
                endDate,
                tfCost.getText(),
                cbStatus.getSelectedItem().toString(),
                cbPrio.getSelectedItem().toString(),
                manager.getAid(),
                country.getLid()
        );

        String query = QueryBuilder.updateQuery(
            selectedProject.toHashMap(),
            updatedProject.toHashMap(),
            "projekt", "pid = %s".formatted(updatedProject.getPid())
        );

        if (query.isBlank()) {
            JOptionPane.showMessageDialog(null, "Inga ändringar har gjorts", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill göra dessa ändringar?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) {
            return;
        }

        try {
            db.update(query);
            selectedProject = new ProjectModel(updatedProject.toHashMap());
            Components.updateListEntry(listProject, listModel, updatedProject);
            JOptionPane.showMessageDialog(null, "Ändringar har sparats", "Information", JOptionPane.INFORMATION_MESSAGE);
            setComponentVisiblity();

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att spara ändringarna", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnChangeActionPerformed
    
    /**
     * Filter option event
     */
    private void cbOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOptionActionPerformed
        populateList();
    }//GEN-LAST:event_cbOptionActionPerformed
    
    /**
     * Add manager button event
     */
    private void btnAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddManagerActionPerformed
        EmployeeModel employee = (EmployeeModel) cbManagerAddEmployee.getSelectedItem();
        String query = "INSERT INTO ans_proj VALUES (%s, %s)".formatted(selectedProject.getPid(), employee.getAid());

        try {
            db.insert(query);
            refreshManagerTable();
            cbManagerAddEmployee.removeItem(employee);
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Det gick inte att lägga till handläggaren", "Fel", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAddManagerActionPerformed

    /**
     * Remove manager button event
     */
    private void btnRemoveManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveManagerActionPerformed
        int row = tblManagers.getSelectedRow();

        if (row == -1) return;
        if (JOptionPane.showConfirmDialog(null, "Är du säker på att du vill ta bort handläggaren?", "Bekräftelse", JOptionPane.YES_NO_OPTION) != 0) return;

        String query = "DELETE FROM ans_proj WHERE pid = %s AND aid = %s".formatted(selectedProject.getPid(), managerModel.getValueAt(row, 0));
        EmployeeModel employee = (EmployeeModel) managerModel.getValueAt(row, 1);
        
        try {
            db.delete(query);
            cbManagerAddEmployee.addItem(employee);
            refreshManagerTable();
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveManagerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddManager;
    private javax.swing.JButton btnAddPartner;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnFilterDate;
    private javax.swing.JButton btnRemoveManager;
    private javax.swing.JButton btnRemovePartner;
    private javax.swing.JComboBox<CountryModel> cbCountry;
    private javax.swing.JComboBox<EmployeeModel> cbManager;
    private javax.swing.JComboBox<EmployeeModel> cbManagerAddEmployee;
    private javax.swing.JComboBox<PartnerModel> cbManagerAddPartner;
    private javax.swing.JComboBox<String> cbOption;
    private javax.swing.JComboBox<String> cbPrio;
    private javax.swing.JComboBox<String> cbSearchStatus;
    private javax.swing.JComboBox<String> cbStatus;
    private com.github.lgooddatepicker.components.DatePicker dpEndDate;
    private com.github.lgooddatepicker.components.DatePicker dpFilterFrom;
    private com.github.lgooddatepicker.components.DatePicker dpFilterTo;
    private com.github.lgooddatepicker.components.DatePicker dpStartDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<ProjectModel> listProject;
    private javax.swing.JPanel pnlProjectContainer;
    private javax.swing.JPanel pnlProjectManager;
    private javax.swing.JPanel pnlProjects;
    private gui.project.ProjectStatistics projectStatistics1;
    private javax.swing.JScrollPane spPartners;
    private javax.swing.JScrollPane spPartners1;
    private javax.swing.JScrollPane spProject;
    private javax.swing.JTable tblManagers;
    private javax.swing.JTable tblPartners;
    private javax.swing.JTextField tfCost;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfName;
    private javax.swing.JTabbedPane tpProject;
    // End of variables declaration//GEN-END:variables
}
