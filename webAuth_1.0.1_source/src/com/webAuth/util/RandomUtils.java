package com.webAuth.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomUtils {
     public static String getRandom() throws NoSuchAlgorithmException{
         SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
         byte bytes[] = new byte[16];
         random.nextBytes(bytes);
         //随机数R1
         String RandomNumber = "";
         for(int i=0;i<16;i++){
             //R2+=(char)((bytes[i]+128)%94+32);
             RandomNumber+=(char)((bytes[i]+128)%64+63);
         }
         return RandomNumber;
     }
}
