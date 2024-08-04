import javax.swing.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Random;

public class Des {
    byte[] sKey = new byte[1000];
    String sKeyString;
    static byte[] raw;
    String inputMessage,encryptData,decryptedData;

    public Des(){
        try{
            generatesSymmetricKey();
            inputMessage = JOptionPane.showInputDialog(null,"Enter message to encrypt: ");
            byte[] iByte = inputMessage.getBytes();
            byte[] eByte = encrypt(raw,iByte);
            String encryptData = new String(eByte);
            System.out.println("Encrypted message is "+encryptData);
            JOptionPane.showMessageDialog(null,"Encrypted data"+"\n" +encryptData);

            byte[] dByte=decrypt(raw,eByte);
            String decryptedData = new String(dByte);
            System.out.println("Decrypted message "+decryptedData);
            JOptionPane.showMessageDialog(null,"Decrypted data"+"\n" +decryptedData);


        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Des des = new Des();
    }

    void generatesSymmetricKey(){
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String numString = String.valueOf(num);
            byte[] numStringByte = numString.getBytes();
            sKey = getRawKey(numStringByte);
            sKeyString = new String(sKey);
            System.out.println("DES symmetric key: " + sKeyString);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    private static byte[] getRawKey(byte[] numStringByte) throws Exception{
        KeyGenerator kGen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(numStringByte);
        kGen.init(56,sr);
        SecretKey secKey = kGen.generateKey();
        raw = secKey.getEncoded();
        return raw;

    }
    private static byte[] encrypt(byte[] raw,byte[] iByte) throws Exception{

        SecretKey secKey = new SecretKeySpec(raw,"DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,secKey);
        byte[] encrypted = cipher.doFinal(iByte);
        return encrypted;


    }
    private static byte[] decrypt(byte[] raw,byte[] encrypted) throws Exception{
        SecretKey secKey = new SecretKeySpec(raw,"DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,secKey);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

}
