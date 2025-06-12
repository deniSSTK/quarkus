package org.acme.security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptMechanism {
    public static String cachePassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
