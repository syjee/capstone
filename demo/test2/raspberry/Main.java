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

		/*
		db.addtoFile(db.getPeerListFile(), "192.168.11.1");
		db.addtoFile(db.getPeerListFile(), "192.168.11.2");
		db.addtoFile(db.getPeerListFile(), "192.168.11.3");
		
		db.deleteFromFile(db.getPeerListFile(), "192.168.11.2");
		db.deleteFromFile(db.getPeerListFile(), "192.168.11.3");
		*/
		
		IpScanManager ism = new IpScanManager("192.168.11");
		ism.start();
		
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		
		Thread.sleep(10000);
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain);
		pnm.start();
		
		RPCManager rpcm = new RPCManager(pnm);
		rpcm.start();
        
	}

}
