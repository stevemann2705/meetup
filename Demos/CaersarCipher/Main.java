
public class Main {

	public static void main(String[] args) {
		String plaintext = "hello world!!";
		String ciphertext = Cipher.encrypt(plaintext, 4);
		
		//brute force the ciphertext (ciphertext-only attack)
		for(int i=0; i<26; i++) {
			System.out.println(Cipher.decrypt(ciphertext, i));
		}

	}

}
