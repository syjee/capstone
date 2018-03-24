public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		DBHelper db = new DBHelper();
		db.setDB();
		
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
		rpcm.request(4);
		

	}

}