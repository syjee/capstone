public class Main {

	public static void main(String[] args) throws InterruptedException {
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
		
		RPCManager rpcm = new RPCManager(pnm,block_chain);
		rpcm.start();

	}

}