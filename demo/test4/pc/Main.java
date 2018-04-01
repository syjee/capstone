import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		myIP mip = new myIP("wlan0");
		String ip = mip.getMyIP();
		System.out.println("I am "+ip);
		
		String algorithm = "RSA"; // or RSA, DH, DSA etc.
	    // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
	    KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
	    keyGen.initialize(512, new SecureRandom());
	    KeyPair keypair = keyGen.genKeyPair();
	    
	    KeyPair keyPair2 = keyGen.generateKeyPair();
	    
		KeyManager km = new KeyManager(ip, keypair);
		km.setKeyFile("KeyFile");
		
		DBHelper db = new DBHelper();
		db.setDB();
		
		/*
		db.addtoFile(db.getBlockchainFile(),"1:1522070181958:null:000p+balQpGNwlxX5+q14YJGHE/WoVcEC2s5ylN7Zkc=");
		*/
		IpScanManager ism = new IpScanManager("192.168.11");
		ism.start();
		
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		
		Thread.sleep(20000);
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain,km);
		pnm.start();
		
		Thread.sleep(20000);
		pnm.broadcast(keypair.getPublic());
		
		RPCManager rpcm = new RPCManager(pnm,km);
		rpcm.start();
		 
		Thread.sleep(10000);
		rpcm.request(3);
	}

}