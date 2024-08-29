package br.cefetmg.entidades.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {
    public String encriptarSenha(String senha) {
        String senhaEncriptada = null;

        try {
          // Create MessageDigest instance for MD5
          MessageDigest md = MessageDigest.getInstance("MD5");

          // Add password bytes to digest
          md.update(senha.getBytes());

          // Get the hash's bytes
          byte[] bytes = md.digest();

          // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }

          // Get complete hashed password in hex format
          senhaEncriptada = sb.toString();
          
          return senhaEncriptada;
        } 
        catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        
        return null;
    }
}
