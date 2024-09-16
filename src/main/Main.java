package main;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import gui.LoginFrame;
import mysql.MySQL;
import javax.swing.JOptionPane;

/**
 * Application entry point
 */
public class Main {
    
    public static void main(String[] args) {
        
        FlatAtomOneDarkIJTheme.setup();
        
        MySQL instance = MySQL.getInstance();
        
        if (instance.getException() == null) {
             new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, instance.getException().getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
    }
}