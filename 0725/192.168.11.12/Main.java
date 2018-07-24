import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;


public class Main {

	/**
	 * wifi : time-stack 
	 * password : raspberry
	 * dhcp ip : 192.168.1.10 ~ 192.168.1.20
	 * @param args
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 * @throws InterruptedException 
	 * @throws NoSuchAlgorithmException 
	 */
	
	public static void main(String[] args) throws UnknownHostException, SocketException, InterruptedException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		myIP mip = new myIP("wlan0");
		String ip = mip.getMyIP();
		System.out.println("I am "+ip);
		
		String subnet = "192.168.11";
		String algorithm = "RSA"; // or RSA, DH, DSA etc.
	    // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
	    KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
	    keyGen.initialize(512, new SecureRandom());
	    KeyPair keypair = keyGen.genKeyPair();
	    
		KeyManager km = new KeyManager("/"+ip, keypair);
		km.setKeyFile("KeyFile");
		
		
		DBHelper db = new DBHelper();
		db.setDB();
//		db.addtoFile(db.getBlockchainFile(),"1:1522070181958:null:000p+balQpGNwlxX5+q14YJGHE/WoVcEC2s5ylN7Zkc=");
		
		IpScanManager ism = new IpScanManager(subnet);
		ism.start();
	
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		TimeManagement tm = new TimeManagement(km);
		
		BlockNetworkManager bnm = new BlockNetworkManager(block_chain,tm);
		bnm.start();
		
		Thread.sleep(10000);
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain,km,bnm,tm);
		pnm.start();

		
		
//		Thread.sleep(10000);
//		RPCManager rpcm = new RPCManager(pnm,block_chain,km);
//		rpcm.start();
		
/*		
		Thread.sleep(2000);
		rpcm.request(5);
		
		Thread.sleep(300000);
		rpcm.request(5);
		*/
	}

}
