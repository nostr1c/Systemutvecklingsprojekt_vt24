package utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * A utility class containing useful methods
 */
public class Utils {
    
    /**
     * Generates a randomised password
     * @param length the length of the password
     * @return the newly generated password
     */
    public static String generatePassword(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int charIndex = random.nextInt(chars.length());
            password.append(chars.charAt(charIndex));
        }

        return password.toString();
    }
}
