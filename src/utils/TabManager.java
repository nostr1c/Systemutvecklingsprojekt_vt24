package utils;

/**
 * Manages JTabbedPane tabs
 */
public class TabManager {
    
    private final javax.swing.JTabbedPane tabbedPane;
    
    /**
     * Instantiates a new TabManager object
     * @param tabbedPane which JTabbedPane to control
     */
    public TabManager(javax.swing.JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        disableAll();
    }
    
    /**
     * Enabled tabs to be accessible
     * @param tabs vararg of which tabs to enable
     */
    public void enable(int ...tabs) {
        for (int tab : tabs) {
            tabbedPane.setEnabledAt(tab, true);
        }
    }
    
    /**
     * private method for disabling all methods
     */
    private void disableAll() {
        for (int tab = 0; tab < tabbedPane.getTabCount(); tab++) {
            tabbedPane.setEnabledAt(tab, false);
        }
    }
}