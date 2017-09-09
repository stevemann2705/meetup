import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptionAES {

    
    
  
    Cipher cipher;
    SecretKeySpec skeySpec;
    GCMParameterSpec ivspec;

 
    public FileEncryptionAES(String key) {
        try {
           
        	byte[] mykey = key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            mykey = sha.digest(mykey);
            mykey = Arrays.copyOf(mykey, 16); 
            
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);  // To use 256 bit keys, you need the "unlimited strength" encryption policy files from Sun.
           
            skeySpec = new SecretKeySpec(mykey, "AES");

            // build the initialization vector (randomly).
            //SecureRandom random = new SecureRandom();
            //byte iv[] = new byte[16];//generate random 16 byte IV AES is always 16bytes
            //random.nextBytes(iv);
            ivspec = new GCMParameterSpec(128, mykey);
            
            // initialize the cipher for encrypt mode
            cipher = Cipher.getInstance("AES/GCM/NoPadding");

            //System.out.println("Key: " + new String(key, "utf-8") + " This is important when decrypting");
            //System.out.println("IV: " + new String(iv, "utf-8") + " This is important when decrypting");
            //System.out.println();
           
            
        } catch (NoSuchPaddingException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

 
    /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Encrypts the file in srcPath and creates a file in destPath
     */
    public void encrypt(String srcPath, String destPath) {
        File rawFile = new File(srcPath);
        File encryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            /**
             * Initialize the cipher for encryption
             */
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(rawFile);
            outStream = new FileOutputStream(encryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FileEncryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(FileEncryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Decrypts the file in srcPath and creates a file in destPath
     */
    public void decrypt(String srcPath, String destPath) {
        File encryptedFile = new File(srcPath);
        File decryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            /**
             * Initialize the cipher for decryption
             */
             cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(encryptedFile);
            outStream = new FileOutputStream(decryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } 
        catch (InvalidKeyException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(FileEncryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(FileEncryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}