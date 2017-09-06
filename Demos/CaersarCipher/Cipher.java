
public class Cipher {
	public static String encrypt(String plaintext, int key) {
		
		char[] charArray = plaintext.toCharArray(), encArray = new char[charArray.length];
		for(int i=0; i<charArray.length; i++) {
			if(Character.isLetter(charArray[i])) {
				encArray[i] = (char)(charArray[i]+key);
				if (encArray[i] > 'z') {
					encArray[i] = (char) (encArray[i] - 26);
	            } else if (encArray[i] < 'a') {
	            	encArray[i] = (char) (encArray[i] + 26);
	            }
			}
			else {
				encArray[i] = charArray[i];
			}
		}
		String ciphertext = new String(encArray);
		return ciphertext;
	}
	
	public static String decrypt(String ciphertext, int key) {
		
		char[] charArray = ciphertext.toCharArray(), decArray = new char[charArray.length];
		for(int i=0; i<charArray.length; i++) {
			if(Character.isLetter(charArray[i])) {
				decArray[i] = (char)(charArray[i]-key);
				if (decArray[i] > 'z') {
					decArray[i] = (char) (decArray[i] - 26);
	            } else if (decArray[i] < 'a') {
	            	decArray[i] = (char) (decArray[i] + 26);
	            }
			}
			else {
				decArray[i] = charArray[i];
			}
		}
		String plaintext = new String(decArray);
		return plaintext;
	}
}
