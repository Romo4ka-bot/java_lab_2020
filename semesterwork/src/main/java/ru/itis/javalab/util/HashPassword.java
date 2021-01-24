package ru.itis.javalab.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashing(String password) {
        StringBuilder hash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[]hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            hash = new StringBuilder();
            for (byte b : hashInBytes) {
                hash.append(String.format("%02x", b));
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        return hash.toString();
    }
}
