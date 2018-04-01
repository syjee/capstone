import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String algorithm = "RSA"; // or RSA, DH, DSA etc.
	    // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
	    KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
	    keyGen.initialize(512, new SecureRandom());
	    KeyPair keypairA = keyGen.genKeyPair();
	    
	    KeyPair keypairB = keyGen.genKeyPair();
	    KeyPair keypairC = keyGen.genKeyPair();
	    
	    
		KeyManager km = new KeyManager("192.168.11.100", keypairA);
		km.setKeyFile("KeyFile");
		
		km.addIdentity("192.168.11.105", keypairB.getPublic());
		km.addIdentity("192.168.11.100", keypairC.getPublic());
		
		km.save();
		
		System.out.println("KeyManager size : "+km.size());
		
		String ip = "192.168.11.105";
		String plainText = "HELLO WORLD";
	    String cipherText = km.encrypt(plainText, keypairB.getPrivate()); 
	    System.out.println(cipherText);
	    
	    String decodeText = km.decrypt(cipherText, km.getPublicKey(ip));
	    System.out.println(decodeText);
	    
	    String str = keypairB.getPublic().toString();

	}

}
