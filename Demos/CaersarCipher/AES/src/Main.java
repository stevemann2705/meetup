
public class Main {

	public static void main(String[] args) {
		String src = "C:\\Users\\Steve Mann\\Desktop\\AES\\image.txt";
		String dest = "C:\\Users\\Steve Mann\\Desktop\\AES\\encrypted.txt";
		String destdecrypted = "C:\\Users\\Steve Mann\\Desktop\\AES\\Decrypted.txt";
		System.out.println("Initializing...");
		FileEncryptionAES aes = new FileEncryptionAES("thisisakey");
		System.out.println("Encrypting...");
		aes.encrypt(src, dest);
		System.out.println("Encrypted Successfully...");
		
		System.out.println("Decrypting...");
		aes.decrypt(dest, destdecrypted);
		System.out.println("Decrypted Successfully...");
	}

}
