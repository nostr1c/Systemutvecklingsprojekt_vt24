package gui;

import java.util.HashMap;
import mysql.Queries;
import static shared.Shared.SESSION_AID;
import java.awt.Color;

/**
 * JFrame for the login window
 */
public class LoginFrame extends javax.swing.JFrame {
    
    public LoginFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        javax.swing.JLabel lblPassword = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        btnForgotPassword = new javax.swing.JButton();
        javax.swing.JLabel lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblEmail.setText("Epost");

        lblPassword.setText("Lösenord");

        btnLogin.setText("Logga in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnForgotPassword.setBackground(new java.awt.Color(60, 63, 65));
        btnForgotPassword.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Grey"));
        btnForgotPassword.setText("Glömt lösenord?");
        btnForgotPassword.setBorder(null);
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMouseExited(evt);
            }
        });
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Logga in");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitle)
                        .addComponent(lblPassword)
                        .addComponent(lblEmail)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnLogin)
                            .addGap(64, 64, 64)
                            .addComponent(btnForgotPassword))
                        .addComponent(pfPassword)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addGap(3, 3, 3)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForgotPassword)
                    .addComponent(btnLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Forgot password click event
    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
        new ForgotPasswordFrame().setVisible(true);
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    // Forgot password btn mouse enter event.
    private void btnForgotPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMouseExited
        btnForgotPassword.setForeground(Color.lightGray);
    }//GEN-LAST:event_btnForgotPasswordMouseExited
    // Forgot password btn mouse leave event.
    private void btnForgotPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMouseEntered
        btnForgotPassword.setForeground(Color.GRAY);
    }//GEN-LAST:event_btnForgotPasswordMouseEntered

    // Login button click event.
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = tfEmail.getText();
        String password = new String(pfPassword.getPassword());

        HashMap<String, String> employee = Queries.getLoginInformation(email);

        if (password.equals(employee.get("losenord"))) {
            SESSION_AID = employee.get("aid");
            this.setVisible(false);
            new MainFrame().setVisible(true);
        } else {
            lblInfo.setText("Fel epost eller lösenord");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfEmail;
    // End of variables declaration//GEN-END:variables
}
