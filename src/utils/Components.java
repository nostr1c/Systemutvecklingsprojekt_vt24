package utils;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;

/**
 * A class for handling different components
 */
public class Components {
    
    /**
     * clears the text of a JTextComponent
     * @param fields one or many JTextComponent(s)
     */
    public static void clearTextFields(JTextComponent ...fields) {
        for (JTextComponent field : fields) {
            field.setText("");
        }
    }
    
    /**
     * clears the selection of a JComboBox
     * @param boxes one or many JComboBox(s)
     */
    public static void clearComboBoxes(JComboBox ...boxes) {
        for (JComboBox box : boxes) {
            box.setSelectedIndex(0);
        }
    }
    
    /**
     * clears the date of a DatePicker
     * @param pickers one or many DatePicker(s)
     */
    public static void clearDatePickers(DatePicker ...pickers) {
        for (DatePicker picker : pickers) {
            picker.clear();
        }
    }
    
    /**
     * Enable or disable JComponent(s)
     * @param bool 'true' to enable, 'false' to disable
     * @param components which JComponent(s) to use
     */
    public static void setEnabled(boolean bool, JComponent ...components) {
        for (JComponent component : components) {
            component.setEnabled(bool);
        }
    }
    
    /**
     * Set focus on a JComponent
     * @param bool 'true' to enable, 'false' to disable
     * @param components which JComponent(s) to use
     */
    public static void setFocusable(boolean bool, JComponent ...components) {
        for (JComponent component : components) {
            component.setFocusable(bool);
        }
    }
    
    /**
     * Set visibility on a JComponent
     * @param bool 'true' to enable, 'false' to disable
     * @param components which JComponent(s) to use
     */
    public static void setVisible(boolean bool, JComponent ...components) {
        for (JComponent component : components) {
            component.setVisible(bool);
        }
    }
    
    /**
     * Update a entry in a JList
     * @param <T> generic type for the DefaultListModel
     * @param list which JList to use
     * @param model which ListModel to use
     * @param newEntry which entry to update
     */
    public static <T> void updateListEntry(JList list, DefaultListModel<T> model, T newEntry) {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            model.setElementAt(newEntry, selectedIndex);
        }
    }
    
    /**
     * Remove the selected entry in a JList
     * @param <T> generic type for the DefaultListModel
     * @param list which JList to use
     * @param model which ListModel to use
     */
    public static <T> void removeListEntry(JList list, DefaultListModel<T> model) {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            model.remove(selectedIndex);
        } 
    }
}
