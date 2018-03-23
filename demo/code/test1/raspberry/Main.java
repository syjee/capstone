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
		DBHelper db = new DBHelper();
		db.setDB();
		
		IpScanManager ism = new IpScanManager("192.168.137");
		ism.start();
		
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain);
		pnm.start();
		
		Thread.sleep(10000);
		BlockchainManager bm = new BlockchainManager();
		bm.formBlockchain();
		bm.showBlockchain();
		
        
	}

}
