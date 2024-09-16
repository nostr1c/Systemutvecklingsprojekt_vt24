package mysql;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Singleton class wrapping the InfDB class inside of it
 */
public class MySQL {
    
    private static final String DB_NAME = "ngo_2024";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "dbAdmin2024";
    private static final String DB_PASS = "dbAdmin2024PW";
    
    private static MySQL instance;
    private static InfDB db;
    private static InfException exception;
    
    /**
     * Private method for instantiating the InfDB class using it's parameters.
     * If an error occurred the exception field is set and should be checked
     */
    private MySQL() {
        try {
            db = new InfDB(DB_NAME, DB_PORT, DB_USER, DB_PASS);
        } catch (InfException e) {
            exception = e;
        }
    }
    
    /**
     * Instantiate or retrieve the class instance
     * @return MySQL instance
     */
    public static MySQL getInstance() {
        if (instance == null) {
            instance = new MySQL();
        }

        return instance;
    }
    
    /**
     * Retrieve the database
     * @return InfDB db
     */
    public InfDB getDB() {
        return db;
    }
    
    /**
     * Retrieve the exception if one occurred
     * @return InfException exception
     */
    public InfException getException() {
        return exception;
    }
}