import java.io.File;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		db.setDB();
		
		IpScanManager ism = new IpScanManager();
		ism.start();
		
		TimeReception tr = new TimeReception();
		tr.start();
		
		Blockchain block_chain = new Blockchain();
		block_chain.start();
		
		BlockReception br = new BlockReception();
		br.start();
		
		PeerNetworkManager pnm = new PeerNetworkManager(block_chain);
		pnm.start();
		
		
	}

}
