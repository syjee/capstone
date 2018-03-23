import java.io.File;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		db.setDB();
		
		IpScanManager ism = new IpScanManager();
		ism.start();
	
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain);
		pnm.start();
		
		Thread.sleep(10000);
		Block b = new Block(1,"a","b","c");
		pnm.broadcast(b);
		
	}

}
