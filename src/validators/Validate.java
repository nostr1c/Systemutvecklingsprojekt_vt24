package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * A class containing various static validating/checker methods
 */
public class Validate {
    
    /**
     * Compares the old string with the new one to see if they have changed
     * @param oldString Old string
     * @param newString New String
     * @return 'true' if the new value is different from the old one, else 'false'
     */
    public static boolean hasChanged(String oldString, String newString) {
        return !oldString.equals(newString);
    }
    
    /**
     * Checks for empty value in one or more JTextComponent(s)
     * @param fields varargs of one or multiple JTextComponent
     * @return 'true' if any JTextComponent is empty, else 'false'
     */
    public static boolean checkForEmptyValues(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
            if (field.getText().isBlank()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if JTextComponent text is not convertible to a integer
     * @param fields varargs of JTextComponents
     * @return 'true' if the component is not convertible to a integer, else 'false'
     */
    public static boolean checkIfNotConvertibleToInt(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
            try {
                Integer.valueOf(field.getText());
            } catch (NumberFormatException ignored) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if JTextComponent text is not convertible to a double
     * @param fields varargs of JTextComponents
     * @return 'true' if the component is not convertible to a double, else 'false'
     */
    public static boolean checkIfNotConvertibleToDouble(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
            try {
                Double.valueOf(field.getText());
            } catch (NumberFormatException ignored) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Replaces a null value to a empty string
     * @param value nullable string value
     * @return an empty string if the parameter is null, otherwise the original value is return
     */
    public static String changeToEmptyStringIfNull(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }
    
    /**
     * Adds an integer validator/checker to a JTextComponent.
     * If the user inputs a value not convertible to a integer a message dialog shows.
     * @param fields one or multiple JTextComponents
     */
    public static void addIntValidatorToField(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
            
            field.getDocument().addDocumentListener(new DocumentListener() {
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        Integer.valueOf(field.getText());
                    } catch (NumberFormatException ignored) {
                        JOptionPane.showMessageDialog(null, "Felaktig inmatning! Ange heltal.", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {}

                @Override
                public void changedUpdate(DocumentEvent e) {}
            });
        }  
    }
    
    /**
     * Adds an double validator/checker to a JTextComponent.
     * If the user inputs a value not convertible to a double a message dialog shows.
     * @param fields one or multiple JTextComponents
     */
    public static void addDoubleValidatorToField(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
        
            field.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        Double.valueOf(field.getText());
                    } catch (NumberFormatException ignored) {
                        JOptionPane.showMessageDialog(null, "Felaktig inmatning! Ange decimaltal.", "Felaktig inmatning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {}

                @Override
                public void changedUpdate(DocumentEvent e) {}
            });
        }
    }
    
    /**
     * Checks if email format is correct.
     * @param email email to validate.
     * @return true if valid format, else false.
     */
    public static boolean checkEmailFormat(String email) {
        Pattern pattern = Pattern.compile("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,}$");
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();
        return matchFound;
    }
}
