/* 
 * Copyright 2017 Rene Bylander at WITC
 */

package edu.witc.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Rene Bylander
 * @created Mar 29, 2017
 * @updated April, 2021 - Removed unused methods
 */
public class PasswordUtil {

    public static String hashPassword(String password)
            throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for(byte b : mdArray){
            int v = b & 0xff;
            if(v < 16){
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
            
        }//end for loop
        return sb.toString();   
    }
    
    public static String getSalt(){
        SecureRandom r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes); 
    }
    
    public static void checkPasswordStrength(String password)throws Exception{
        if(password == null || password.trim().isEmpty()){
            throw new Exception("Password cannot be empty");
        }
        else if(password.length() < 8){
            throw new Exception("Password is too short. "
                    + "Must be at least 8 characters long.");
        }
    }
    
    public static boolean validatePassword(String password){
        try{
            checkPasswordStrength(password);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public static String generatePassword(){
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-+!@#$%";
        String password = "";
        int start = 0;
        int stop = 0;
        int minLength = 8;
        
        for (int i = 0; i <= minLength; i++) {
                // get a random character from the chars string
                start = getRandomNumber(charSet.length());
                stop = start + 1;
                password += charSet.substring(start, stop);
        }
        
        return password;
    
    }
    private static int getRandomNumber(int maxValue){
        double randomNumber;
        randomNumber = Math.floor(Math.random() * maxValue);
        
        return (int)randomNumber;
    }

}//end of class
