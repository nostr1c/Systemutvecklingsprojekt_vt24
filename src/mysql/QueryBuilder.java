package mysql;

import validators.Validate;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Class for building SQL queries
 */
public class QueryBuilder {
    
    /**
     * Method for creating a update query based on what has been changed.
     * Maps keys must be exact to matching column.
     * 
     * @param dbMap The values from the database.
     * @param newMap The values from the fields, or that you want you change.
     * @param table The name of the table you want use.
     * @param condition The where-condition, i.e. "id = 1".
     * @return The update query. If nothing has changed, it returns an empty string.
     */
    public static String updateQuery(HashMap<String,String> dbMap, HashMap<String,String> newMap, String table, String condition) {
        // Initialize a new StringBuilder and StringJoiner.
        StringBuilder builder = new StringBuilder();
        StringJoiner joiner = new StringJoiner(", ");
        
        // Start of the query.
        builder.append("UPDATE %s SET ".formatted(table));
        
        // Loop through the map with new values's keys.
        for (String key : newMap.keySet()) {
            
            // Get the database / old value.
            String oldValue = dbMap.get(key);
            
            // Get the new value.
            String newValue = newMap.get(key);
            
            // Set old & new value to null in string format if they are empty.
            if (oldValue == null || oldValue.isBlank()) {
                oldValue = "null";
            }
            
            if (newValue == null || newValue.isBlank()) {
                newValue = "null";
            }

            // Check wether the entry has changed.
            if (Validate.hasChanged(oldValue, newValue)) {
                
                // If new value is null, set the key/column to null, without citations.
                if (newValue.equals("null")) {
                    joiner.add("%s = %s".formatted(key, newValue));
                    continue;
                }
                
                /**
                 * Try parsing the new value to a double to check if it is an double/int.
                 * If it goes through, set the value without citations.
                 */
                try {
                    Double.valueOf(newValue);
                    joiner.add("%s = %s".formatted(key, newValue));
                } catch (NumberFormatException e) {
                    // If parsing did not work, it must be a string. Set it with citations.
                    joiner.add("%s = '%s'".formatted(key, newValue));
                }
            }
        }
        
        // Add joiner to stringbuiilder
        builder.append(joiner.toString());
        
        // Add the where-condition.
        builder.append(" WHERE %s".formatted(condition));

        // if nothing has changed (because joiner did'nt do anything), then return an empty string.
        if (joiner.length() <= 0) {
            return "";
        }
        
        // If something has changed, return the query.
        return builder.toString();
    }
}
