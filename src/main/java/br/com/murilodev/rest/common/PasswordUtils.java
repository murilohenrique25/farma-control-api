package br.com.murilodev.rest.common;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Método para gerar um hash de senha com BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar se uma senha corresponde a um hash
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
