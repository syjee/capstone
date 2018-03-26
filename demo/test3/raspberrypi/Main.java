import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
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
	 */
	
	public static void main(String[] args) throws UnknownHostException, SocketException, InterruptedException {
		// TODO Auto-generated method stub
		myIP mip = new myIP("wlan0");
		System.out.println("I am "+mip.getMyIP());
		DBHelper db = new DBHelper();
		db.setDB();
		db.addtoFile(db.getBlockchainFile(),"1:1522070181958:null:000p+balQpGNwlxX5+q14YJGHE/WoVcEC2s5ylN7Zkc=");
		
		IpScanManager ism = new IpScanManager("192.168.11");
		ism.start();
	
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		Thread.sleep(20000);
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain);
		pnm.start();
	
		Thread.sleep(20000);
		RPCManager rpcm = new RPCManager(pnm);
		rpcm.start();
		
		Thread.sleep(2000);
		rpcm.request(5);
		
		Thread.sleep(300000);
		rpcm.request(5);
	}

}
