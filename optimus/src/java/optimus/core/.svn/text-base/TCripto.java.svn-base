/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimus.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * TCripto - Classe para criptografia de dados
 * @author Administrator
 */

public class TCripto {
    private static MessageDigest md5 = null;
    private static String pwd = null;

    public TCripto(String senha){
        TCripto.pwd = senha;

    }
    
    static{
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
    //??????????????????????
    
    private static  char[] hexCodes(byte[] text){
        char[] hexOutPut = new char[text.length * 2];
        String hexString;
        
        for(int i=0;i<text.length;i++){
            hexString = "00"+Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length()-2, hexString.length(), hexOutPut, i*2);
        }
        
        return hexOutPut;
    }
    
    public static String getHash(){
        if (md5 != null){
            
            return new String(hexCodes(md5.digest(pwd.getBytes())));
        }
        return null;
    }

}
