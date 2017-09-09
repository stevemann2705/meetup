
public class Main {

	public static void main(String[] args) {
		String plaintext = "hello world!!";
		String ciphertext = Cipher.encrypt(plaintext, 4);
		
		System.out.println("Cipher Text: " + ciphertext);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("Brute Force Ciphertext: ");
		System.out.println();
		//brute force the ciphertext (ciphertext-only attack)
		for(int i=0; i<26; i++) {
			System.out.println(Cipher.decrypt(ciphertext, i));
		}

	}

}
