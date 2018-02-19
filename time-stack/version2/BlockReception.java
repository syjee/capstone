import java.util.Scanner;

public class BlockReception extends Thread{

	public DBHelper db;
	public TimeSyncronization ts;
	public int height = 0;
	public boolean shouldRun = true;
	public BlockchainManager bm;
	
	public BlockReception() {
		// TODO Auto-generated constructor stub
		db = new DBHelper();
		ts = new TimeSyncronization();
		bm = new BlockchainManager();
	}
	
	public void doSync() {
		Scanner scan;
		String data="";
		try {
			scan = new Scanner(db.getBlockchainFile());
			while (scan.hasNextLine()) 
	        { 
	            data = scan.nextLine();  
	        }
			String[] parts = data.split(":");
			ts.timeSync(Long.valueOf(parts[1]));
			System.out.println("Transaction = " + parts[1]);
			scan.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int checkHeight() {
		Scanner scan;
		int i = 0;
		try {
			scan = new Scanner(db.getBlockchainFile());
			while (scan.hasNextLine()) 
	        { 
	            scan.nextLine(); 
	            i++;
	        }
			scan.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return i;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(shouldRun) {
			int i;
			if(height < (i = checkHeight())) {
				doSync();
				System.out.println("The height of Blockchain ++");
				height = i;
				
				bm.formBlockchain();
			}
			else
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
}
