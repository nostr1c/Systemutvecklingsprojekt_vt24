package gui;

import mysql.MySQL;
import oru.inf.InfDB;
import oru.inf.InfException;
import validators.Validate;

public class ForgotPasswordFrame extends javax.swing.JFrame {
    
    private InfDB db;
    
    /**
     * Creates new form ForgotPasswordFrame
     */
    public ForgotPasswordFrame() {
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
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel lblTitle = new javax.swing.JLabel();
        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        javax.swing.JLabel lblConfirmPassword = new javax.swing.JLabel();
        pfNewPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setText("Återställ ditt lösenord");

        lblEmail.setText("Epost");

        jLabel1.setText("Nytt lösenord");

        btnChangePassword.setText("Byt lösenord");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        lblConfirmPassword.setText("Bekräfta lösenord");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblConfirmPassword)
                        .addComponent(jLabel1)
                        .addComponent(lblTitle)
                        .addComponent(lblEmail)
                        .addComponent(tfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addComponent(pfNewPassword)
                        .addComponent(pfConfirmPassword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChangePassword)
                        .addGap(18, 18, 18)
                        .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(pfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChangePassword)
                    .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Click event action when clicking save.
    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        if (Validate.checkForEmptyValues(tfEmail, pfNewPassword, pfConfirmPassword)) {
            lblInfo.setText("Inget fält får vara tomt.");
            return;
        }
        
        String email = tfEmail.getText();
        String newPassword = new String(pfNewPassword.getPassword());
        String confirmPassword = new String(pfConfirmPassword.getPassword());    
        
        try {
            if (db.fetchSingle("SELECT epost FROM anstalld WHERE epost = '%s'".formatted(email)) == null) {
                lblInfo.setText("Eposten hittades ej");
                return;
            }
        } catch (InfException ignored) {}
        
        
        if (!newPassword.equals(confirmPassword)) {
            lblInfo.setText("Lösenorden stämmer inte överrens");
            return;
        }
        
        try {
            db.update("UPDATE anstalld SET losenord = '%s' WHERE epost = '%s'".formatted(newPassword, email));
            lblInfo.setText("Lösenordet är återställt");
        } catch (InfException ignored) {}
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfNewPassword;
    private javax.swing.JTextField tfEmail;
    // End of variables declaration//GEN-END:variables
}
